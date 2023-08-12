package com.example.rainbowend.Controller.Index;

import com.alibaba.fastjson.JSONObject;
import com.example.rainbowend.Entity.ResponseResult;
import com.example.rainbowend.Entity.User;
import com.example.rainbowend.Service.Index.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rainbow
 *
 * @DATE:2023/8/7
 */

@RestController
@RequestMapping("index")
public class IndexUserController {
    private static final Logger logger = LoggerFactory.getLogger(IndexUserController.class);
    @Resource
    private IndexService indexService;

    @PostMapping("getUserInfo")
    public ResponseResult getUserInfo(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //获取请求参数
            String email = (String) jsonObject.getJSONObject("user").get("email");
            //获取用户信息
            User user = indexService.getUserInfo(email);
            map.put("email", user.getEmail());
            map.put("username", user.getUsername());
            return ResponseResult.ok("验证成功", map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseResult.error("验证失败，请重新登录");
        }
    }

    /**
     * 1、获取图片文件
     * 2、将文件请求链接存储到数据库
     * 3、返回文件的访问链接
     * @return
     */
    @PostMapping("setImage")
    public ResponseResult changePicture(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //获取参数
            MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
            String email = params.getParameter("email");   //邮箱
            String fileName = email + ".jpg";  //文件名称
            //获取静态资源路径
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            String saveDir = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "/src/main/resources/static/userImages/";
            //获取文件
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            MultipartFile multipartFile = files.get(0);
            //保存文件
            File file = new File(saveDir + fileName);
            //保存到指定目录
            multipartFile.transferTo(file);
            //判断图片是否保存成功
            if (file.exists()) {
                System.out.println("文件存在");
                //将图片链接保存到数据库
                String imgUrl="userImages/"+fileName;
                int i=indexService.saveImgUrl(imgUrl);
                if(i>0){
                    return ResponseResult.ok("文件上传成功");
                }else{
                    return ResponseResult.error("文件上传失败，请重新上传");
                }
            } else {
                return ResponseResult.error("文件上传失败，请重新上传");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseResult.error("文件上传失败，请重新登录");
        }
    }


    /**
     * 根据用户信息，返回头像
     * 1、获取邮箱
     * @param jsonObject
     * @return
     */
    @PostMapping("userImageUrl")
    public ResponseResult UserImageUrl(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        try {
            //获取参数
            String email=jsonObject.getString("email");
            //获取图片的url
            User user=indexService.getUserUrl(email);
            if(user!=null){
                map.put("imageUrl",user.getImageUrl());
                return ResponseResult.ok("获取图片访问地址成功",map);
            }else{
                return ResponseResult.error("获取图片访问地址失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseResult.error("获取图片失败");
        }
    }
}
