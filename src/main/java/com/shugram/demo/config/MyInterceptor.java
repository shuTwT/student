package com.shugram.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.shugram.demo.jwt.JwtUtil;
import com.shugram.demo.service.UserService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.UserLoginToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        //如果不是映射到方法直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method =handlerMethod.getMethod();
        //检查方法是否有passToken注解，有则跳过认证，直接通过
        if (method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }
        //System.out.println(method);
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                //执行认证
                if (token==null){
                    throw  new RuntimeException("无token，请重新登陆");
                }
                //获取token 中的userid
                String userId;
                try{
                    userId= JWT.decode(token).getClaim("id").asString();
                }catch (JWTDecodeException j){
                    throw  new RuntimeException("token不正确");
                }
                //查询数据库，看看是否存在此用户，方法自己写
                if (1!=1){
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                //验证token
                if (JwtUtil.verify(token)){
                    return true;
                }else{
                    throw new RuntimeException("token过期或不正确");
                }
            }
        }

        if (handlerMethod.getBean().getClass().getName().equals("springfox.documentation.swagger.web.ApiResourceController")) {
            return true;
        }
        throw new RuntimeException("没有权限注解一律不通过");
    }
    /**
     * 目标方法执行后
     * 该方法在控制器处理请求方法调用之后、解析视图之前执行
     * 可以通过此方法对请求域中的模型和视图做进一步修改
     */
    @Override
    public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
       // System.out.println("postHandle执行{}");

    }
    /**
     * 页面渲染后
     * 该方法在视图渲染结束后执行
     * 可以通过此方法实现资源清理、记录日志信息等工作
     */
    @Override
    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
       // System.out.println("afterCompletion执行异常");

    }
}
