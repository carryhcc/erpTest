package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dao.User;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/10 11:24
 */
//@Component("userService")
public interface UserMapper extends BaseMapper<User> {
}