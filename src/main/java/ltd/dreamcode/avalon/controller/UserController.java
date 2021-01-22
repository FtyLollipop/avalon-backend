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
     *
     * @param userName,userPassword,captchaCode,captchaResult
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> userRegister(String userName, String userPassword, String captchaCode, String captchaResult) {

        return userServiceImpl.userRegister(userName, userPassword, captchaCode, captchaResult);
    }

    /**
     * 用户登录控制层
     *
     * @param userName,userPassword,captchaCode,captchaResult
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(String userName, String userPassword, String captchaCode, String captchaResult) {

        return userServiceImpl.userLogin(userName, userPassword, captchaCode, captchaResult);
    }

    /**
     * 令牌验证控制层
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
    public Map<String, String> verifyToken(String token) {

        return userServiceImpl.verifyToken(token);
    }
}