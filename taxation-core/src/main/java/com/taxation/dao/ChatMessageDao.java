package com.taxation.dao;

import com.taxation.bean.ChatMessageEntity;

public interface ChatMessageDao {
    int insert(ChatMessageEntity record);

    int insertSelective(ChatMessageEntity record);
}