package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.FileService;
import com.mmall.service.ProductService;
import com.mmall.service.UserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 后台产品相关
 *
 * @author rcl
 * @date 2018/6/23 23:48
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    /**
     * 新建产品
     *
     * @author rcl
     * @date 2018/6/23 20:39
     */
    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpServletRequest request, Product product) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充我们增加产品的业务逻辑
            return productService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        //使用拦截器验证登录及权限
    }

    /**
     * 修改产品状态
     *
     * @author rcl
     * @date 2018/6/23 20:39
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpServletRequest request, Integer productId, Integer status) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.setSalaStatus(productId, status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        //使用拦截器验证登录及权限
    }

    /**
     * 查看产品详情
     *
     * @author rcl
     * @date 2018/6/23 20:40
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse setDetail(HttpServletRequest request, Integer productId) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.manageProductDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        //使用拦截器验证登录及权限
    }

    /**
     * 获取产品集合
     *
     * @author rcl
     * @date 2018/6/23 22:29
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.getProductList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        //使用拦截器验证登录及权限
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse searchProduct(HttpServletRequest request, Integer productId, String productName,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.searchProduct(productName, productId, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        //使用拦截器验证登录及权限
    }

    /**
     * 图片上传
     *
     * @author rcl
     * @date 2018/6/23 23:32
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpServletRequest request, @RequestParam() MultipartFile file) {
        /*String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = fileService.uplaod(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + fileName;
            Map<String, String> map = Maps.newHashMap();
            map.put("uri", fileName);
            map.put("url", url);
            return ServerResponse.createBySuccess(map);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }*/
        //使用拦截器验证登录及权限
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = fileService.uplaod(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + fileName;
        Map<String, String> map = Maps.newHashMap();
        map.put("uri", fileName);
        map.put("url", url);
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 富文本中的图片上传
     *
     * @author rcl
     * @date 2018/6/23 23:31
     */
    @RequestMapping("richtxt_img_upload.do")
    @ResponseBody
    public Map<String, Object> richtxtImgUpload(HttpServletRequest request, MultipartFile file
            , HttpServletResponse response) {
        Map<String, Object> map = Maps.newHashMap();
        /*String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            map.put("success", false);
            map.put("msg", "请登录管理员账号");
            return map;
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            map.put("success", false);
            map.put("msg", "请登录管理员账号");
            return map;
        }
        //富文本中对于返回值有自己的要求，此处使用的是simditor
        if (userService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = fileService.uplaod(file, path);
            if (StringUtils.isBlank(fileName)) {
                map.put("success", false);
                map.put("msg", "上传失败");
                return map;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + fileName;
            map.put("success", true);
            map.put("msg", "上传成功");
            map.put("file_path", url);
            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
            return map;
        } else {
            map.put("success", false);
            map.put("msg", "无权限操作");
            return map;
        }*/

        //使用拦截器验证登录及权限
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = fileService.uplaod(file, path);
        if (StringUtils.isBlank(fileName)) {
            map.put("success", false);
            map.put("msg", "上传失败");
            return map;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + fileName;
        map.put("success", true);
        map.put("msg", "上传成功");
        map.put("file_path", url);
        response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
        return map;
    }


}
