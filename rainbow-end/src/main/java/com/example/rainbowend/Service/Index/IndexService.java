package com.example.rainbowend.Service.Index;

import com.example.rainbowend.Entity.User;

/**
 * Rainbow
 *
 * @DATE:2023/8/7 0007
 */
public interface IndexService {

    User getUserInfo(String email);

    int saveImgUrl(String imgUrl);

    User getUserUrl(String email);
}
