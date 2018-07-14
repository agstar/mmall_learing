package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

import java.sql.*;

public interface UserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 包含用户信息的响应
     * @author rcl
     * @date 2018/5/27 11:13
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 注册用户信息
     *
     * @param user 注册的用户信息
     * @return 包含提示信息的响应
     * @author rcl
     * @date 2018/5/27 11:14
     */
    ServerResponse<String> register(User user);

    /**
     * 验证 可验证用户名或email
     *
     * @param val  待验证的值
     * @param type 待验证的类型，分为username或email
     * @return 提示验证结果的响应
     * @author rcl
     * @date 2018/5/27 11:14
     */
    ServerResponse<String> checkValid(String val, String type);

    /**
     * 获取用户问题
     *
     * @param username 用户名
     * @return 用户的问题
     * @author rcl
     * @date 2018/5/27 11:15
     */
    ServerResponse<String> selectQuestion(String username);


    /**
     * 检查用户问题回答
     *
     * @param username 用户名
     * @param question 用户的问题
     * @param answer   用户的问题答案
     * @return 验证结果的响应
     * @author rcl
     * @date 2018/5/27 11:15
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    /**
     * 忘记密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @param forgetToken token
     * @return 修改提示的响应
     * @author rcl
     * @date 2018/5/27 11:15
     */
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    /**
     * 重置密码
     *
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @param user        当前用户信息
     * @return 重置结果的响应
     * @author rcl
     * @date 2018/5/27 11:15
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 更新个人信息
     *
     * @param user 待更新的用户信息
     * @return 更新后的用户信息
     * @author rcl
     * @date 2018/5/27 11:16
     */
    ServerResponse<User> updateInfomation(User user);

    /**
     * 校验用户是否存在
     *
     * @param userId 用户id
     * @return 用户信息
     * @author rcl
     * @date 2018/5/27 11:29
     */
    ServerResponse<User> getInfomation(Integer userId);


    /**
     * 校验是否是管理员
     *
     * @param user 当前登录用户
     * @return 是管理员返回success，否则返回error
     * @author rcl
     * @date 2018/6/3 10:41
     */
    ServerResponse checkAdminRole(User user);


}
