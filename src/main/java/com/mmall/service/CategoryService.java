package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

public interface CategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategroyName(Integer categoryId, String categoryName);

    /**
     * 根据parentId查询子节点集合
     *
     * @author rcl
     * @date 2018/6/3 12:00
     */
    ServerResponse<List<Category>> getChildrenCategroy(Integer parentId);
    /**
     * 递归查询本节点及子节点的id
     * @param categoryId 本节点
     * @return 本节点及子节点的id的集合
     * @author rcl
     * @date 2018/6/9 14:11
     */
    ServerResponse<List<Integer>> selectChildreCategoryById(Integer categoryId);
}
