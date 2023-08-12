package com.example.rainbowend.Dao.Index;

import com.example.rainbowend.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Rainbow
 *
 * @DATE:2023/8/7
 */
@Mapper
public interface IndexDao {

    /**
     * 获取用户信息
     */
    @Select("SELECT t.* FROM users t " +
            "WHERE t.EMAIL=#{email}")
    User getUserInfo(String email);

    /**
     * 更新图片访问路径
     * @param imgUrl
     * @return
     */
    @Update("UPDATE users t " +
            "SET t.IMAGE_URL=#{imgUrl}")
    int saveImgUrl(String imgUrl);

}
