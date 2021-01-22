package ltd.dreamcode.avalon.controller;

import ltd.dreamcode.avalon.entity.User;
import ltd.dreamcode.avalon.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController

public class UserController extends UserServiceImpl {
    @Autowired
    UserServiceImpl userServiceImpl;

    /**
     * 用户注册控制层
     * @param param
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> userRegister(User param) {
        Map<String, String> response = userServiceImpl.userRegister(param);
        return response;
    }



    /**
     //     * 用户登录控制层
     //     * @param userName,userPassword
     //     * @return String
     //     * @throws Exception
     //     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(  String userName,String userPassword){

        return userServiceImpl.userLogin(userName,userPassword);
    }
}