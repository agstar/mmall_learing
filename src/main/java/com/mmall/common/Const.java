package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 常量类
 *
 * @author rcl
 * @date 2018/5/26 19:06
 */
public class Const {


    public static final String CURRENT_USER = "currentUser";
    public static final String TOKEN_PREFIX = "token_";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface RedisCacheExtime {
        /**
         * 30分钟
         */
        int REDIS_SESSION_EXTIME = 60 * 30;
    }

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    /**
     * 购物车状态
     *
     * @author rcl
     * @date 2018/7/11 22:20
     */
    public interface Cart {
        /**
         * 购物车选中状态
         *
         * @date 2018/7/2 22:47
         */
        int CHECKED = 1;
        /**
         * 购物车为选中状态
         *
         * @date 2018/7/2 22:47
         */
        int UN_CHECKED = 0;

        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
    }


    public static class Role {
        /**
         * 普通用户
         */
        public static int ROLE_CUSTOMER = 0;
        /**
         * 管理员
         */
        public static int ROLE_ADMIN = 1;
    }

    /**
     * 订单状态
     *
     * @author rcl
     * @date 2018/7/8 19:39
     */
    public enum ProductStatusEnum {
        /**
         * 在售
         */
        ON_SALE(1, "在线");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * 支付状态
     *
     * @author rcl
     * @date 2018/7/11 22:20
     */
    public enum OrderStatusEnum {
        /**
         * 已取消
         */
        CANCELED(0, "已取消"),
        /**
         * 未支付
         */
        NO_PAY(10, "未支付"),
        /**
         * 已付款
         */
        PAID(20, "已付款"),
        /**
         * 已发货
         */
        SHIPPED(40, "已发货"),
        /**
         * 订单完成
         */
        ORDER_SUCCESS(50, "订单完成"),
        /**
         * 订单关闭
         */
        ORDER_CLOSE(60, "订单关闭");

        private int code;
        private String value;

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }

    }

    /**
     * 支付回调状态
     *
     * @author rcl
     * @date 2018/7/11 22:20
     */
    public interface AlipayCallback {
        String TRADE_STATUS_TWAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";
        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    /**
     * 支付平台
     *
     * @author rcl
     * @date 2018/7/11 22:19
     */
    public enum PayPlatformEnum {
        /**
         * 支付宝
         */
        ALIPAY(1, "支付宝");

        private int code;
        private String value;

        PayPlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }


    /**
     * 支付方式
     *
     * @author rcl
     * @date 2018/7/11 22:21
     */
    public enum PaymentTypeEnum {
        /**
         * 支付宝
         */
        ONLINE_PAY(1, "在线支付");

        private int code;
        private String value;

        PaymentTypeEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static PaymentTypeEnum codeOf(int code) {
            for (PaymentTypeEnum paymentTypeEnum : values()) {
                if (paymentTypeEnum.getCode() == code) {
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public interface REDIS_LOCK{
        /**
         * 关闭订单的分布式锁
         *
         */
        String CLOSE_ORDER_TASK_LOCK = "CLOSE_ORDER_TASK_LOCK";
    }

}
