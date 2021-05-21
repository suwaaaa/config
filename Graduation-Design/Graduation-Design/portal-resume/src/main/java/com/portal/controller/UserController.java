package com.portal.controller;

import com.portal.pojo.User;
import com.portal.service.UserService;
import com.portal.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping(value = "/user/adduser", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        Boolean i = userservice.addUser(user);
        if (i == true){
            return JsonData.buildSuccess();
        }
        else return JsonData.buildError("AddUser Error");
    }

    @RequestMapping(value = "/user/queryuser/{user_id}", method = RequestMethod.GET)
    public User queryUser(@PathVariable("user_id") Long id) {
        return userservice.queryUser(id);
    }

    @RequestMapping(value = "/user/queryall", method = RequestMethod.GET)
    public List<User> queryUserAll() {
        return userservice.queryUserAll();
    }

    @RequestMapping(value = "/user/addUserSelective", method = RequestMethod.POST)
    public Object addUserSelective(@RequestBody User user) {

        return JsonData.buildSuccess(userservice.addUserSelective(user));
    }

    @RequestMapping(value = "/user/deleteUser/{user_id}", method = RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("user_id") Long id) {
        Boolean i = userservice.deleteUser(id);
        if (i != false){
            return JsonData.buildSuccess();
        }
        else return JsonData.buildError("deleteUser error");
    }

    @RequestMapping(value = "/user/updateUserSelective", method = RequestMethod.POST)
    public Object updateUserSelective(@RequestBody User user) {
        Boolean i = userservice.updateUserSelective(user);
        if (i != false){
            return JsonData.buildSuccess();
        }
        else return JsonData.buildError("updateUserSelective error");
    }

    @RequestMapping(value = "/user/updateUserAll", method = RequestMethod.PUT)
    public Object updateUserAll(@RequestBody User user) {
        Boolean i = userservice.updateUserAll(user);
        if (i != false){
            return  JsonData.buildSuccess("updateUserAll successful");
        }
        else return JsonData.buildError("updateUserSelective error");
    }

    @RequestMapping(value = "/user/queryUserNumAll", method = RequestMethod.GET)
    public Integer queryUserNumAll() {
        return userservice.queryUserNumAll();
    }

//    @Value("${file.path}")
//    private String filePath;
//
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    @ResponseBody
//    public Boolean upload(
//            @RequestParam("user_photo_url") MultipartFile file, HttpServletRequest request
//    ) {
//        User user = new User();
//        System.out.println("文件的地址是---  ---" + filePath);
//        String userrealname = request.getParameter("name").replace(" ", "");
//        System.out.println("使用的用户名字是---  ---" + userrealname);
//        Integer userID = Integer.parseInt(request.getParameter("id"));
//        System.out.println("使用的用户id是---  ---" + userID);
//        user.setUser_id(userID);
//        user.setUser_real_name(userrealname);
//        String fileName = file.getOriginalFilename();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
////        fileName = UUID.randomUUID() + suffixName;
//        fileName = userrealname+ suffixName;
//        user.setUser_photo_url(fileName);
//        File dest = new File(filePath + fileName);
//
//
//
//
//        if (file.isEmpty() && file.getSize() > 30720) {
//            return false;
//        }
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//            return userservice.uploadUserImg(user);
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return userservice.uploadUserImg(user);
//    }


}
