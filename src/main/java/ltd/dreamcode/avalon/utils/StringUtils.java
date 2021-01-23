package ltd.dreamcode.avalon.utils;

/**
 * @desc   字符串工具类
 * @author FtyLollipop
 **/
public final class StringUtils {

    private StringUtils() {}

    /**
     * 是否有效邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email) {
            return false;
        }
        return email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");
    }
}
