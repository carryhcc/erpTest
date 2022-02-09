package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.SyncMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息中心表 Mapper 接口
 * @author hcc
 * @since 2022-02-09
 */
@Mapper
public interface SyncMsgMapper extends BaseMapper<SyncMsg> {

}
