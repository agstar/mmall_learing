package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;
    private BigDecimal cartTotalPrice;
    /**是否已全部勾选*/
    private Boolean allChecked;
    /***/
    private String imageHost;
}
