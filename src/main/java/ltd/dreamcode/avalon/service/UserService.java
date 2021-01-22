package ltd.dreamcode.avalon.service;


import ltd.dreamcode.avalon.entity.User;

import java.util.Map;

public interface UserService {
    Map<String, String> userRegister(String userName, String userPassword, String captchaCode, String captchaResult);//Map<K,V>:Map以键值对存储数据

    Map<String, String> userLogin(String userName, String userPassword, String captchaCode, String captchaResult);

    Map<String, String> verifyToken(String token);
}