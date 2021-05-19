package com.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.portal.annotations.LoginRequired;
import com.portal.mapper.UserMapper;
import com.portal.pojo.*;
import com.portal.service.UserService;
import com.portal.utils.*;
import com.zhenzi.sms.ZhenziSmsClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserServiceController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserService userService;
    @Value("${sms.apiUrl}")
    private String apiUrl;
    @Value("${sms.appId}")
    private String appId;
    @Value("${sms.appSecret}")
    private String appSecret;
    @Value("${sms.templateId}")
    private String templateId;

    @Autowired
    private UserMapper userMapper;

    @LoginRequired(loginSuccess = true)
    public String testUserWeb(){
        return "测试用户web";
    }

    @GetMapping("/test")
    public String test(){
        return "用户服务提供模块的测试";
    }

    @PostMapping("/loginMy")
    @ResponseBody
    public Object loginMy(@RequestBody LoginVo loginVo){
        System.out.println("/loginMy login:"+loginVo);
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        String smsCode = loginVo.getSmsCode();
        JSONObject jsonObject = new JSONObject();
        User2 user2 = null;
        String token = "";
        //检查是否是短信验证登录
        if(!StringUtil.isEmpty(smsCode)){
            //判断短信验证码是否正确
            String smsCodeRedis = redisTemplate.opsForValue().get("smsCode");
            System.out.println("redis 中的smsCode:"+smsCode);
            if(!loginVo.getSmsCode().equals(smsCodeRedis)){
                jsonObject.put("message","登录失败，密码错误");
                jsonObject.put("code",5009);
                return jsonObject;
            }
            user2 = getUserByPhoneParma(username);
            if(user2.getActive()==false){
                jsonObject.put("message","该账户已经注销");
                jsonObject.put("code",5010);
                return jsonObject;
            }
            if(user2 ==null){
                jsonObject.put("message","用户不存在");
                jsonObject.put("code",4000);
                return jsonObject;
            }
            //生成token
            token = JwtUtils.Sign(user2.getUserName(), user2.getUserId().toString());
//            com.portal.util.CookieUtil.setCookie(request,response,"token",token,1000,true);
            jsonObject.put("message","登录成功");
            jsonObject.put("code",200);
            jsonObject.put("token",token);
            user2.setUserPassword(null);
            jsonObject.put("user", user2);
            return jsonObject;

        }else{
            //模拟md5第一次加密
            String passMD5 = MD5util.inputPassMD5(password);
            //判断是否是邮箱登录
            if(username.contains("@")){
                user2 = getUserByEmailParma(username);
            }else{
                user2 = getUserByPhoneParma(username);
            }
            //校验密码
            if(user2 !=null){
                //获取盐
                String salt = user2.getSalt();
                //进行第二次MD5加密
                String calcPass = MD5util.formPassDB(passMD5,salt);
                //验证密码是否匹配
                if(user2.getActive()==false){
                    jsonObject.put("message","该账户已经注销");
                    jsonObject.put("code",5010);
                    return jsonObject;
                }
                if(calcPass.equals(user2.getUserPassword())){
                    //生成token
                    token = JwtUtils.Sign(user2.getUserName(), user2.getUserId().toString());
//                    com.portal.util.CookieUtil.setCookie(request,response,"token",token,3600*60*60,true);
                    jsonObject.put("message","登录成功");
                    jsonObject.put("code",200);
                    jsonObject.put("token",token);
                    user2.setUserPassword(null);
                    jsonObject.put("user", user2);
                    return jsonObject;
                }else{
                    jsonObject.put("message","用户名密码不匹配");
                    jsonObject.put("code",500);
                    return jsonObject;

                }
            }
        }
        jsonObject.put("message","该用户不存在");
        jsonObject.put("code",4000);
        return jsonObject;
    }


    //进行用户名或者邮箱和密码的登陆校验
    @RequestMapping("/login")
    public MyResponseBody login(@RequestBody LoginVo loginVo){
        MyResponseBody myResponseBody = new MyResponseBody();
        System.out.println("userService login:"+loginVo);
        String username = loginVo.getUsername();
        User2 user2 = null;
        //检查是否是短信验证登录
        if(loginVo.getSmsCode()!=null){
            //判断短信验证码是否正确
            if(!loginVo.getSmsCode().equals("123456")){
                return myResponseBody.buildMessage(5009,"短信验证码错误");
            }
            user2 = userService.getUserByPhone(username);
            if(user2.getActive()==false){
                return  myResponseBody.buildMessage(5010,"该账户已经注销");
            }
            if(user2 !=null){
                return  myResponseBody.build(200,"登录成功", user2);
            }
        }else{
            //模拟md5第一次加密
            String passMD5 = MD5util.inputPassMD5(loginVo.getPassword());
            //判断是否是邮箱登录
            if(username.contains("@")){
                user2 = userService.getUserByEmail(username);
            }else{
                user2 = userService.getUserByPhone(username);
            }
            //校验密码
            if(user2 !=null){
                //获取盐
                String salt = user2.getSalt();
                //进行第二次MD5加密
                String calcPass = MD5util.formPassDB(passMD5,salt);
                //验证密码是否匹配
                if(user2.getActive()==false){
                    return  myResponseBody.buildMessage(5010,"该账户已经注销");
                }
                if(calcPass.equals(user2.getUserPassword())){
                    user2.setUserPassword("");
                    return  myResponseBody.build(200,"登录成功", user2);
                }else{
                    return myResponseBody.buildMessage(500,"用户名密码不匹配");
                }
            }
        }
        return myResponseBody.buildMessage(4000,"该用户不存在");
    }

    @PostMapping("/userRegister")
    public MyResponseBody userRegister(@RequestBody RegisterVo registerVo){
        MyResponseBody myResponseBody = new MyResponseBody();
        System.out.println("userService register 参数:"+registerVo);
        String nickName = registerVo.getNickName();
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        String smsCode = registerVo.getSmsCode();
        User2 user2 = null;
        User2 result = null;

        if(!StringUtil.isEmpty(smsCode)){
            //判断短信验证码是否正确
            String smsCodeRedis = redisTemplate.opsForValue().get("smsCode");
            System.out.println("redis 中的smsCode:"+smsCode);
            if(!smsCode.equals(smsCodeRedis)){
                return myResponseBody.buildMessage(5009,"短信验证码错误");
            }
            result = userService.getUserByPhone(username);
            if(result!=null){
                return MyResponseBody.buildMessage(4000,"手机号已存在");
            }
            user2 = new User2();
            user2.setUserPhone(username);
            user2.setUserName("phone_"+username);
            password = "123456";
        }else{
            //判断用户名类型
            if(username.contains("@")){
                //邮箱注册
                //判断邮箱格式是否正确
                if(!EmailCheckUtil.isEmail(username)){
                    return myResponseBody.buildMessage(5001,"邮箱格式不正确");
                }
                result = getUserByNickName(nickName);
                System.out.println(result);
                if(result!=null){
                    return myResponseBody.buildMessage(5003,"改昵称已存在");
                }
                result = userService.getUserByEmail(username);
                //判断邮箱号是否存在
                if(result!=null){
                    return myResponseBody.buildMessage(5002,"该邮箱已存在");
                }
                user2 = new User2();
                user2.setUserEmail(username);
                if(nickName!=null){
                    user2.setUserName("email_"+username);
                }
                user2.setUserName(nickName);

            }else{
                //手机号注册
                //判断手机号格式是否正确
                if(!PhoneCheckUtil.isPhoneLegal(username)){
                    return MyResponseBody.buildMessage(CodeMesg.PHONEPASSERROR.getCode(),CodeMesg.PHONEPASSERROR.getMessage());
                }
                result = getUserByNickName(nickName);
                if(result!=null){
                    return myResponseBody.buildMessage(5003,"改昵称已存在");
                }
                //判断手机号是否存在
                result = userService.getUserByPhone(username);
                if(result!=null){
                    return MyResponseBody.buildMessage(4000,"手机号已存在");
                }
                user2 = new User2();
                user2.setUserPhone(username);
                user2.setUserName("phone_"+username);
            }
        }

        //将输入的密码进行二次加密，存储到数据库
        String passMD5 = MD5util.inputToDB(password,"1a2b3c4d");
        user2.setUserPassword(passMD5);
        user2.setSalt("1a2b3c4d");
        //设置创建时间
        user2.setUserCreatime(new Date());
        user2.setActive(true);
        int row = userService.insertUser(user2);
        if(row>0){
            return MyResponseBody.buildMessage(200,"注册成功");
        }else {
            return MyResponseBody.buildMessage(500, "注册失败");
        }
    }


    //向外提供查询用户服务
    @GetMapping("/getUserByPhone/{phone}")
    public User2 getUserByPhone(@PathVariable("phone") String phone){
        System.out.println("user-service phone:"+phone);
        User2 user2ByPhone = userService.getUserByPhone(phone);
        if(user2ByPhone !=null){
            user2ByPhone.setUserPassword(null);
            user2ByPhone.setSalt(null);
        }
        return user2ByPhone;
    }

    //向外提供查询用户服务
    @GetMapping("/getUserByPhone")
    public User2 getUserByPhoneParma(@RequestParam("phone") String phone){
        System.out.println("user-service phone:"+phone);
        User2 user2ByPhone = userService.getUserByPhone(phone);
        return user2ByPhone;
    }

    //根据昵称获取用户
    @GetMapping("/getUserByNickName/{username}")
    public User2 getUserByNickName(@PathVariable("username") String username){
        System.out.println("user-service getUserByNickName:"+username);
        User2 user2ByNickName = userService.getUserByNickName(username);
        if(user2ByNickName !=null){
            user2ByNickName.setUserPassword(null);
            user2ByNickName.setSalt(null);
        }
        return user2ByNickName;
    }

    //根据昵称获取用户
    @GetMapping("/getUserByNickName")
    public User2 getUserByNickNameParma(@RequestParam("username") String username){
        System.out.println("user-service getUserByNickName:"+username);
        User2 user2ByNickName = userService.getUserByNickName(username);
        return user2ByNickName;
    }


    @GetMapping("/getUserById/{id}")
    public User2 getUserById(@PathVariable("id") Integer id){
        User2 user2 = new User2();
        user2.setUserId(id);
        User2 user21 = userMapper.selectOne(user2);
        if(user21 !=null){
            user21.setUserPassword(null);
            user21.setSalt(null);
        }
        return user21;
    }

    @GetMapping("/getUserByEmail/{email}")
    public User2 getUserByEmail(@PathVariable("email") String  email){
        User2 user2 = new User2();
        user2.setUserEmail(email);
        User2 user21 = userMapper.selectOne(user2);
        if(user21 !=null){
            user21.setUserPassword(null);
            user21.setSalt(null);
        }
        return user21;
    }

    @GetMapping("/getUserByEmail")
    public User2 getUserByEmailParma(@RequestParam("email") String  email){
        User2 user2 = new User2();
        user2.setUserEmail(email);
        User2 user21 = userMapper.selectOne(user2);
        return user21;
    }


    //修改昵称
    @PostMapping("/updateNickName")
    public MyResponseBody updateNickName(@RequestBody User2 user2){
        MyResponseBody myResponseBody = new MyResponseBody();
        System.out.println("userService updateNickName:"+ user2);
        String nickName = user2.getUserName();
        User2 result = userService.getUserByNickName(nickName);
        if(result!=null){
            return myResponseBody.buildMessage(6000,"改昵称已经存在");
        }else{
            int i = userMapper.updateByPrimaryKeySelective(user2);
            if(i<=0){
                myResponseBody.buildMessage(500,"更新失败");
            }
        }
        return  myResponseBody.buildMessage(200,"更新成功");
    }



    //修改手机号
    @PostMapping("/updatePhone")
    public MyResponseBody updatePhone(@RequestBody User2 user2){
        MyResponseBody myResponseBody = new MyResponseBody();
        System.out.println("userService updateNickName:"+ user2);
        String phone = user2.getUserPhone();
        User2 result = userService.getUserByPhone(phone);
        if(result!=null){
            return myResponseBody.buildMessage(4000,"手机号已经存在");
        }else{
            int i = userMapper.updateByPrimaryKeySelective(user2);
            if(i<=0){
                myResponseBody.buildMessage(500,"更新失败");
            }
        }
        return  myResponseBody.buildMessage(200,"手机号修改成功");
    }

    //修改昵称
    @PostMapping("/updateEmail")
    public MyResponseBody updateEmail(@RequestBody User2 user2){
        MyResponseBody myResponseBody = new MyResponseBody();
        System.out.println("userService updateEmail:"+ user2);
        String email = user2.getUserEmail();
        User2 result = userService.getUserByEmail(email);
        if(result!=null){
            return myResponseBody.buildMessage(5002,"该邮箱已经被绑定");
        }else{
            int i = userMapper.updateByPrimaryKeySelective(user2);
            if(i<=0){
                myResponseBody.buildMessage(500,"更新失败");
            }
        }
        return  myResponseBody.buildMessage(200,"邮箱修改成功");
    }


    @PostMapping("/deleteUserById/{id}")
    public MyResponseBody deleteUserById(@PathVariable("id") Integer id){
        MyResponseBody myResponseBody = new MyResponseBody();
        User2 user2 = new User2();
        user2.setUserId(id);
        user2 = userMapper.selectOne(user2);
        if(user2 ==null){
            return myResponseBody.buildMessage(4000,"用户不存在");
        }
        user2.setActive(false);
        Integer i = userMapper.updateByPrimaryKeySelective(user2);
        if(i<=0){
            return myResponseBody.buildMessage(500,"注销失败");
        }
        return myResponseBody.buildMessage(200,"注销成功");
    }

    @PostMapping("/passwordReset")
    public MyResponseBody passwordRest(@RequestBody ResetVo resetVo){
        MyResponseBody myResponseBody = new MyResponseBody();
        System.out.println("userService username:"+resetVo);
        String username = resetVo.getUsername();
        String password  = resetVo.getNewPassword();
        //根据用户名
        //判断是否是邮箱登录
        User2 user2 = null;
        if(username.contains("@")){
            user2 = userService.getUserByEmail(username);
        }else{
            user2 = userService.getUserByPhone(username);
        }
        int i = 0;
        if(user2 !=null){
            //将输入的密码进行二次加密，存储到数据库
            String passMD5 = MD5util.inputToDB(password,"1a2b3c4d");
            user2.setUserPassword(passMD5);
            i = userMapper.updateByPrimaryKeySelective(user2);
        }
        if(i<=0){
            myResponseBody.buildMessage(500,"修改失败");
        }
        return myResponseBody.buildMessage(200,"修改成功");
    }

    //完善用户信息
    @PostMapping("/completeUserInfo")
    public MyResponseBody completeUserInfo(@RequestBody User2 user2){
        System.out.println("userservice completeUserInfo:"+ user2);
        MyResponseBody myResponseBody = new MyResponseBody();
        String nickName = user2.getUserName();
        String phone = user2.getUserPhone();
        String email = user2.getUserEmail();
        User2 result = null;
        if(nickName!=null||!nickName.equals("") ){
            //判断昵称是否存在
            result = getUserByNickName(nickName);
            if(result!=null){
                return  myResponseBody.buildMessage(6000,"昵称已存在");
            }
        }
        if(phone!=null ||!phone.equals("")){
            result = getUserByPhone(phone);
            if(result!=null){
                return myResponseBody.buildMessage(4000,"手机号已经存在");
            }
        }
        if(email!=null || !email.equals("") ){
            result = getUserByEmail(email);
            if(result!=null){
                return myResponseBody.buildMessage(6001,"该邮箱已存在");
            }
        }


        int i = userMapper.updateByPrimaryKeySelective(user2);
        if(i<=0){
            return myResponseBody.buildMessage(500,"更新失败");
        }
        return  myResponseBody.buildMessage(200,"更新成功");
    }


    //进行用户名或者邮箱和密码的登陆校验
    @PostMapping("/checkUserAndPassword")
    @ResponseBody
    public User2 checkUserAndPassword(@RequestBody LoginVo loginVo){
        User2 user2 = null;
        System.out.println("userService login:"+loginVo);
        String username = loginVo.getUsername();
        //检查是否是短信验证登录
        if(loginVo.getSmsCode()!=null){
            //判断短信验证码是否正确
            if(!loginVo.getSmsCode().equals("123456")){
                return null;
            }
            user2 = userService.getUserByPhone(username);
            return user2;
        }else{
            //模拟md5第一次加密
            String passMD5 = MD5util.inputPassMD5(loginVo.getPassword());
            //判断是否是邮箱登录
            if(username.contains("@")){
                user2 = userService.getUserByEmail(username);
            }else{
                user2 = userService.getUserByPhone(username);
            }
            //校验密码
            if(user2 !=null){
                //获取盐
                String salt = user2.getSalt();
                //进行第二次MD5加密
                String calcPass = MD5util.formPassDB(passMD5,salt);
                //验证密码是否匹配
                if(!calcPass.equals(user2.getUserPassword())){
                  return null;
                }
            }
        }
        return user2;
    }




    //根据用户名获取用户
    @GetMapping("/getUserByUsername")
    public User2 getUserByUsername(@RequestParam("username") String username){
        System.out.println("userservice getUserByUsername:"+username);
        return userService.getUserByUsername(username);
    }


    /**
     * 修改邮箱
     * @return
     */
    @PostMapping("/updateEmailByPhone")
    public MyResponseBody updateEmailByPhone(User2 user2){
        System.out.println("updateEmailByPhone:"+ user2);
        String email = user2.getUserEmail();
        //判断用户输入的修改的邮箱是否已经存在
        User2 user2ByEmail = userService.getUserByEmail(email);
        if(user2ByEmail !=null){
            return MyResponseBody.buildMessage(CodeMesg.EMAILEXITERROR.getCode(),CodeMesg.EMAILEXITERROR.getMessage());
        }
        //更新邮箱
        int row = userService.updateUser(user2);
        if(row<=0){
            return MyResponseBody.buildMessage(5014,"更新邮箱失败");
        }
        return MyResponseBody.buildMessage(5015,"更新邮箱成功");

    }

    @PostMapping("/updateBaseInfo")
    public MyResponseBody updateBaseInfo(@RequestBody User2 user2){
        System.out.println("updateBaseInfo:"+ user2);
        //更新基本信息
        int row = userService.updateUser(user2);
        if(row<=0){
            return MyResponseBody.buildMessage(500,"基本信息更新失败");
        }
        return MyResponseBody.buildMessage(200,"基本信息更新成功");

    }

    @GetMapping("/sms/{number}")
    @ApiOperation("发送短信验证信息")
    public String sendSms(@PathVariable("number") String number){
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", number);
        params.put("templateId", templateId);
        String[] templateParams = new String[2];
        //生成的验证码
        String randomCode = GernealRandomNumberUtil.getNonce_str();
        templateParams[0] = randomCode;
        //将验证码保存到redis
        redisTemplate.opsForValue().set("smsCode",randomCode);
        //验证码有限期
        templateParams[1] = "5分钟";
        params.put("templateParams", templateParams);
        //返回结果
        String result = "";
        try {
            result = client.send(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/sms/getSmsCode")
    public String getSmsCode(){
        String smsCode = redisTemplate.opsForValue().get("smsCode");
        System.out.println("getSmsCode code:"+smsCode);
        return  smsCode;
    }

}
