package ltd.dreamcode.avalon.serviceImpl;


import ltd.dreamcode.avalon.dao.UserMapper;
import ltd.dreamcode.avalon.entity.User;
import ltd.dreamcode.avalon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 实现用户注册的业务逻辑
     * @param user
     * @throws Exception
     * @return
     */
    @Override
    public Map<String, String> userRegister(User user){
        Long id = userMapper.selectIdByName(user.getUserName());
        Map<String, String> result = new HashMap<>(1);
        if (id != null) {
            System.out.println("注册失败");
            result.put("status", "false");
            result.put("content", "用户名已存在");
            return result;
        } else {
            // 规定状态0为正常
            user.setStatus(0);
            // 获取当前系统时间作为用户注册时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String createdate = simpleDateFormat.format(date);
            user.setCreateDate(createdate);
            user.setNickName("Steve");

            Integer ok = userMapper.insertUser(user);

            if (ok != 1) {
                System.out.println("你库炸了");
                result.put("status", "false");
                return result;
            } else {
                result.put("status", "true");
                return result;
            }
        }

    }
    /**
     * 实现用户登录的业务逻辑
     * @param userName,userPassword
     * @throws Exception
     */
    @Override
    public Map<String, String> userLogin(String userName,String userPassword)  {

        System.out.println(userName);
        System.out.println(userName);
        Map<String, String> result = new HashMap<>(1);

        //用户不存在或密码输入错误
        if (userMapper.loginUserName(userName) == null||!userMapper.loginUserPassword(userName).equals(userPassword)) {

            result.put("status", "false");
            result.put("content","用户不存在或密码输入错误");
            return result;

        }else if(userMapper.loginUserPassword(userName).equals(userPassword)) {
            result.put("status", "true");
            return result;
        }
        return result;
    }
}
