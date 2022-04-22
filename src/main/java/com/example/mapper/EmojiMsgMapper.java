package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.EmojiMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/2/9 11:34
 */
@Mapper
public interface EmojiMsgMapper extends BaseMapper<EmojiMsg> {
}
