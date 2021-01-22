package ltd.dreamcode.avalon.dao;


import ltd.dreamcode.avalon.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    String TABLE_NAME = "user";//表的名字
    String INSERT_USER_FIELD = "userName, userPassword, createDate, status,nickName";//表中的字段
    String SELECT_ID = "id";

    /**
     * 用户注册
     * @param user
     * @return int
     */
    @Insert({"insert into", TABLE_NAME, "(", INSERT_USER_FIELD, ")",
            "values (#{userName}, #{userPassword}, #{createDate}, #{status}, #{nickName})"})
    Integer insertUser(User user);

    /**
     * 根据用户名查询id
     * @param userName
     * @return long
     */
    @Select({"select", SELECT_ID , "from", TABLE_NAME, "where userName = #{userName}"})
    public Long selectIdByName(String userName);
    /**
     * 用户登录
     * @param userName,userPassword
     * @return long
     */
    @Select({"select userName  from", TABLE_NAME, "where userName = #{userName}"})
    public String loginUserName(String userName);
    @Select({"select userPassword  from", TABLE_NAME, "where userName = #{userName}"})
    public String loginUserPassword(String userName);
}