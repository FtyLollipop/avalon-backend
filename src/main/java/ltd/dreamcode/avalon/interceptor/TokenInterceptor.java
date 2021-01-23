package ltd.dreamcode.avalon.interceptor;

import ltd.dreamcode.avalon.utils.TokenUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        response.setCharacterEncoding("utf-8");
        String token=request.getHeader("accessToken");
        if(token!=null){
            boolean result= TokenUtils.verify(token);
            if(result){
                return true;
            }
        }
        return false;
    }
}
