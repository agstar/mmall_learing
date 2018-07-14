package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询数据库有多少人
     *
     * @author rcl
     * @date 2018/5/26 18:55
     */
    int checkUsername(String username);

    /**
     * xml中tpye对应Param注解中的内容
     * 一般多个参数加上注解
     *
     * @author rcl
     * @date 2018/5/26 18:55
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkUserEmail(@Param("email") String email);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password") String password, @Param("userId") Integer userId);

    int checkEmailByUserId(@Param("email") String email, @Param("userId") Integer userId);
}