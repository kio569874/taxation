package com.taxation.bean;

import java.io.Serializable;
import java.util.Date;

public class ChatMessageEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4884148745455327552L;

	private String chatMessageId;

    private String userId;

    private String providerId;

    private Date createTime;

    private String sendFalg;

    private byte[] context;

    public String getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(String chatMessageId) {
        this.chatMessageId = chatMessageId == null ? null : chatMessageId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSendFalg() {
        return sendFalg;
    }

    public void setSendFalg(String sendFalg) {
        this.sendFalg = sendFalg == null ? null : sendFalg.trim();
    }

    public byte[] getContext() {
        return context;
    }

    public void setContext(byte[] context) {
        this.context = context;
    }
}