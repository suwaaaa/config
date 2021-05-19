package com.portal.controller;

import com.portal.pojo.User2;
import com.portal.service.UserAdminService;
import com.portal.utils.PhoneAndEmailCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    @RequestMapping("/adminLogin")
    public String adminLogin(String username,String password){
        User2 user2 = null;
        boolean isPhone = PhoneAndEmailCheckUtil.checkMobileNumber(username);
        if(isPhone){
            user2 = userAdminService.getUserByPhone(username);
        }else if(PhoneAndEmailCheckUtil.checkEmail(username)){
            user2 = userAdminService.getUserByEmail(username);
        }else{
            user2 = userAdminService.getUserByOnlyName(username);
        }
        //校验密码
        if(user2 !=null){
            Integer roleId = user2.getRole().getRoleId();
            //密码匹配且角色是管理员
            if(password.equals(user2.getUserPassword()) && (roleId==5)){
                return "success";
            }
        }
        return "fail";

    }

    @RequestMapping("/getUserList")
    public List<User2> getUserList(@RequestBody User2 user2){
        System.out.println("userService"+ user2.getUserPhone()+ user2.getUserName());
        return  userAdminService.getUserList(user2);
    }

    @RequestMapping("/getUserListNoParam")
    public List<User2> getUserListNoParam(){
        return  userAdminService.getUserList(null);
    }

    @RequestMapping("/getOneUser/{id}")
    public User2 getOneUser(@PathVariable("id") Integer id){
        System.out.println("获取单个用户");
        return  userAdminService.getOneUser(id);
    }
    @RequestMapping("/updateUser")
    public Integer updateUser(@RequestBody User2 user2){
        return userAdminService.updateUser(user2);
    }

    @RequestMapping("/saveUser")
    public Integer saveUser(User2 user2){
        return  userAdminService.saveUser(user2);
    }
    @RequestMapping("/deleteUser/{id}")
    public Integer deleteUser(@PathVariable("id") Integer id){
        return userAdminService.deleteUser(id);
    }
}
