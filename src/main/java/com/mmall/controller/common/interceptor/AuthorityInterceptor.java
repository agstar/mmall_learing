package com.mmall.controller.common.interceptor;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 统一权限判断
 *
 * @author rcl
 * @date 2018/7/30 21:56
 */
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        //请求中Controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //解析handlerMethod
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();
        //解析参数，具体的参数key以及value
        StringBuffer requestParam = new StringBuffer();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            String key = entry.getKey();
            String value = Arrays.toString(entry.getValue());
            requestParam.append(key).append("=").append(value);
        }
        if (StringUtils.equals(className, "UserManageController") && StringUtils.equals(methodName, "login")) {
            log.info("权限拦截器拦截到请求，className:{},methodName:{}", className, methodName);
            return true;
        }
        log.info("权限拦截器拦截到请求，className:{},methodName:{},param:{}", className, methodName,requestParam.toString());
        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            String userJson = RedisShardedPoolUtil.get(loginToken);
            user = JsonUtil.string2Obj(userJson, User.class);
        }
        if (user == null || !user.getRole().equals(Const.Role.ROLE_ADMIN)) {
            //这里需要重置response，否则会报异常
            response.reset();
            //这里需要重新设置编码，否则会乱码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            if (user == null) {
                //富文本定制返回值
                if (StringUtils.equals(className, "ProductManageController") && StringUtils.equals(methodName, "richtxtImgUpload")) {
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("success", false);
                    map.put("msg", "请登录管理员账号");
                    writer.print(JsonUtil.obj2String(map));
                } else {
                    writer.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户未登录")));
                }
            } else {
                //富文本定制返回值
                if (StringUtils.equals(className, "ProductManageController") && StringUtils.equals(methodName, "richtxtImgUpload")) {
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("success", false);
                    map.put("msg", "无权限操作");
                    writer.print(JsonUtil.obj2String(map));
                } else {
                    writer.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户没有权限")));
                }
            }
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
