package com.portal.pojo;

import java.io.Serializable;

public class RedisData implements Serializable {
    private String key;
    private String value;
    private Long time;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RedisData{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", time=" + time +
                '}';
    }
}
