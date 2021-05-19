package com.portal.interceptor;

import com.alibaba.fastjson.JSON;
import com.portal.annotations.LoginRequired;
import com.portal.utils.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SsoInterceptor implements HandlerInterceptor {

    //sso中心的ip地址
    private String ssoAddress;
    //cookie的存活时间
    private Integer cookieMaxAge;
    //cookie 的键
    private String cookieName;

    public SsoInterceptor() {
    }

    public SsoInterceptor(String ssoAddress,String cookieName,Integer cookieMaxAge) {
        this.ssoAddress = ssoAddress;
        this.cookieMaxAge = cookieMaxAge;
        this.cookieName = cookieName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //解决 return false  跨域问题【两者缺一不可，*号表示匹配所有】
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        PrintWriter writer = null;

        if (handler != null) {
            if (handler instanceof HandlerMethod) {
                System.out.println("进入了过滤器");
                // 拦截代码
                // 判断被拦截的请求的访问的方法的注解(是否时需要拦截的)
                HandlerMethod hm = (HandlerMethod) handler;
                LoginRequired methodAnnotation = hm.getMethodAnnotation(LoginRequired.class);
                // 是否拦截
                if (methodAnnotation == null) {
                    return true;
                }
                //从header中获取信息
                String token = request.getHeader("token");//获取token
                System.out.println("拦截器的token:"+token);
                //校验token是否有效
                Map<String, String> successMap = new HashMap<>();
                if (token == null || token.length() == 0) {
                    successMap.put("code","10086");
                    successMap.put("msg","token校验失败");
                }else{
                    //校验token是否有效
                    try{
                        return  JwtUtils.verify(token);
                    } catch (Exception e){
                        successMap.put("code","10086");
                        successMap.put("msg","token校验失败");
                    }
                }
                try {
                    writer = response.getWriter();
                    writer.print(JSON.toJSONString(successMap));
                } catch (IOException e) {
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
            }
        }
        return false;

    }

}

