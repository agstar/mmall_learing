package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.UserService;
import com.mmall.util.MD5Util;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //todo 密码登录md5
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

    /**
     * 注册
     *
     * @author rcl
     * @date 2018/5/26 20:34
     */
    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse<String> validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 校验参数
     *
     * @param type 类型 分为eamil和username
     * @param val  值
     * @author rcl
     * @date 2018/5/26 20:44
     */
    @Override
    public ServerResponse<String> checkValid(String val, String type) {
        if (StringUtils.isNotBlank(val)) {
            if (StringUtils.isNotBlank(type)) {
                if (Const.USERNAME.equals(type)) {
                    int resultCount = userMapper.checkUsername(val);
                    if (resultCount > 0) {
                        return ServerResponse.createByErrorMessage("用户名已存在");
                    }
                }
                if (Const.EMAIL.equals(type)) {
                    int resultCount = userMapper.checkUserEmail(val);
                    if (resultCount > 0) {
                        return ServerResponse.createByErrorMessage("email已存在");
                    }
                }
            } else {
                return ServerResponse.createByErrorMessage("参数错误");
            }
            return ServerResponse.createBySuccessMessage("校验成功");
        }
        return ServerResponse.createByErrorMessage("参数错误");
    }

    /**
     * 找回密码
     *
     * @author rcl
     * @date 2018/5/26 21:32
     */
    @Override
    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }

        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            //说明问题及问题答案是这个用户的
            String forgetToken = UUID.randomUUID().toString();
            RedisShardedPoolUtil.setEx(Const.TOKEN_PREFIX + username, forgetToken, 60 * 60 * 12);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServerResponse.createBySuccessMessage("用户不存在");
        }
        String token = RedisShardedPoolUtil.get(Const.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取充值密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<User> updateInfomation(User user) {
        //username是不能被更新的
        //dmail也要进行一个校验，校验新的email是不是已经存在，并且存在的email如果相同的话，不能是我们当前的这个用户的
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("eamil已存在，请更换email再尝试更新");
        }
        User updateUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .question(user.getQuestion())
                .answer(user.getAnswer()).build();
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("更新个人信息成功", updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    @Override
    public ServerResponse<User> getInfomation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


    /**
     * 校验是否是管理员
     *
     * @param user 当前登录用户
     * @return 是管理员返回success，否则返回error
     * @author rcl
     * @date 2018/6/3 10:41
     */
    @Override
    public ServerResponse checkAdminRole(User user) {
        if (user != null && user.getRole() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }


}
























