package com.portal.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化Response时间格式
 * 在springmvc返回json数据的时候默认日期字段显示的是long类型的时间戳
 * 如果想要返回格式化的日期如：yyyy-MM-dd这种格式的需要进行出现
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonGenerator.writeString(sdf.format(value));
    }
}
