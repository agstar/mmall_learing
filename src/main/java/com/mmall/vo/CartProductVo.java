package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartProductVo {

    private Integer id;
    private Integer userId;
    private Integer productId;
    /**
     * 产品数量
     */
    private Integer quantity;
    /**产品标题*/
    private String productName;
    /**产品标题*/
    private String productSubtitle;
    /**产品主图*/
    private String productMainImage;
    /**产品价格*/
    private BigDecimal productPrice;
    /**产品状态*/
    private Integer productStatus;
    /**产品总价*/
    private BigDecimal productTotalPrice;
    /**产品数量*/
    private Integer productStock;
    /**产品是否勾选*/
    private Integer productChecked;
    /**限制数量的一个返回结果*/
    private String limitQuantity;
}
