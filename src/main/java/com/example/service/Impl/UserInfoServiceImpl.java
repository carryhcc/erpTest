package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserInfoMapper;
import com.example.service.UserInfoService;
import example.model.UserInfo;
import org.springframework.stereotype.Service;

/**
* @author cchu
* @description 针对表【USER_INFO】的数据库操作Service实现
* @createDate 2022-01-11 11:37:23
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {

}




