package com.sharing.cn.common.test.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author ext.shikai1
 */
@Data
public class Message {

    public static final String TAGS = "TAGS";
    public static final int COMPRESS_THRESHOLD = 100;
    public static String EXPIRE = "EXPIRE";
    public static String JMQ_INNER_KEY_IS_FORCEBOT = "JMQ_INNER_IS_FORCEBOT";
    protected short queueId;
    protected boolean compressed;
    protected String topic;
    protected short flag;
    protected String app;
    protected String businessId;
    protected byte priority;
    protected boolean ordered;
    protected String text;
    protected long bodyCRC;
    protected byte[] body;
    protected Map<String, String> attributes;
    protected transient Map<String, String> innerAttributes;
    protected long sendTime;

    public Message() {
    }

    public Message(String topic, String text, String businessId) {
        this.setTopic(topic);
        this.setBusinessId(businessId);
        this.setText(text);
    }

    public Message topic(String topic) {
        this.setTopic(topic);
        return this;
    }

    public Message flag(short flag) {
        this.setFlag(flag);
        return this;
    }

    public Message app(String app) {
        this.setApp(app);
        return this;
    }

    public Message businessId(String businessId) {
        this.setBusinessId(businessId);
        return this;
    }

    public Message priority(byte priority) {
        this.setPriority(priority);
        return this;
    }

    public Message ordered(boolean ordered) {
        this.setOrdered(ordered);
        return this;
    }

    public Message text(String text) {
        this.setText(text);
        return this;
    }

    public Message attributes(Map<String, String> attributes) {
        this.setAttributes(attributes);
        return this;
    }

    public Message queueId(short queueId) {
        this.setQueueId(queueId);
        return this;
    }

}
