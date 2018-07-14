package com.mmall.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.phone = builder.phone;
        this.question = builder.question;
        this.answer = builder.answer;
        this.role = builder.role;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    public User(Integer id, String username, String password, String email, String phone, String question, String answer, Integer role, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.role = role;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static class Builder {
        private Integer id;

        private String username;

        private String password;

        private String email;

        private String phone;

        private String question;

        private String answer;

        private Integer role;

        private Date createTime;

        private Date updateTime;

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder question(String val) {
            question = val;
            return this;
        }

        public Builder answer(String val) {
            answer = val;
            return this;
        }

        public Builder role(Integer val) {
            role = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder updateTime(Date val) {
            updateTime = val;
            return this;
        }

        public User builder() {
            return new User(this);
        }
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }


    public String getQuestion() {
        return question;
    }


    public String getAnswer() {
        return answer;
    }


    public Integer getRole() {
        return role;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}