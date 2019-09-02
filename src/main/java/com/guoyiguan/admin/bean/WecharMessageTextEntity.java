package com.guoyiguan.admin.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author zhangyj3
 * @version V1.0
 * @since 2019-09-02 10:33
 */
public class WecharMessageTextEntity {
    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private String createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("Content")
    private String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageTextEntity{" +
            "toUserName='" + toUserName + '\'' +
            ", fromUserName='" + fromUserName + '\'' +
            ", createTime='" + createTime + '\'' +
            ", msgType='" + msgType + '\'' +
            ", content='" + content + '\'' +
            '}';
    }

}
