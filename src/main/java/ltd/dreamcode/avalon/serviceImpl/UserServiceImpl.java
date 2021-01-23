package ltd.dreamcode.avalon.serviceImpl;


import ltd.dreamcode.avalon.dao.UserMapper;
import ltd.dreamcode.avalon.entity.User;
import ltd.dreamcode.avalon.service.UserService;
import ltd.dreamcode.avalon.utils.BCrypt;
import ltd.dreamcode.avalon.utils.StringUtils;
import ltd.dreamcode.avalon.utils.TokenUtils;
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
     *
     * @param userName,userPassword,captchaCode,captchaResult
     * @return
     */
    @Override
    public Map<String, String> userRegister(String userName, String userPassword, String captchaCode, String captchaResult) {
        Long id = userMapper.selectIdByName(userName);
        Map<String, String> result = new HashMap<>(1);
        if(StringUtils.isEmail(userName)==false){
            result.put("status","false");
            result.put("content","请输入有效的邮箱");
            return result;
        }

        if (id != null) {
            System.out.println("注册失败");
            result.put("status", "false");
            result.put("content", "用户名已存在");
        } else {
            User user = new User();
            user.setUserName(userName);
            user.setUserPassword(BCrypt.hashpw(userPassword,BCrypt.gensalt()));
            // 规定状态0为正常
            user.setStatus(0);
            // 获取当前系统时间作为用户注册时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String createDate = simpleDateFormat.format(date);
            user.setCreateDate(createDate);
            user.setNickName("Steve");

            //向数据库插入用户
            Integer ok = userMapper.insertUser(user);

            if (ok != 1) {
                System.out.println("你库炸了");
                result.put("status", "false");
                result.put("content","后台数据库错误");
            } else {
                result.put("status", "true");
            }
        }
        return result;
    }

    /**
     * 实现用户登录的业务逻辑
     *
     * @param userName,userPassword,captchaCode,captchaResult
     */
    @Override
    public Map<String, String> userLogin(String userName, String userPassword, String captchaCode, String captchaResult) {
        Map<String, String> result = new HashMap<>(1);

        //用户不存在或密码输入错误
        if (userMapper.loginUserName(userName) == null || !BCrypt.checkpw(userPassword,userMapper.loginUserPassword(userName))) {

            result.put("status", "false");
            result.put("content", "用户名或密码错误");

        } else{
            result.put("status", "true");
            result.put("content", TokenUtils.token(userName, userPassword));
        }
        return result;
    }

    /**
     * 实现令牌验证的业务逻辑
     *
     * @param token
     */
    @Override
    public Map<String, String> verifyToken(String token) {
        Map<String, String> result = new HashMap<>(1);
        if(token==null)
            result.put("status", "false");
        else
            result.put("status", Boolean.toString(TokenUtils.verify(token)));
        return result;
    }
}
