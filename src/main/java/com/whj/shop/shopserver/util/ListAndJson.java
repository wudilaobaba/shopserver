package com.whj.shop.shopserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whj.shop.shopserver.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.List;

/**
 * List转Json
 * Json转List
 */
@Converter
public class ListAndJson implements AttributeConverter<List<Object>,String> {
    //                                        范型解释：需要处理的字段的类型，String
    @Autowired
    private ObjectMapper mapper;
    @Override
    public String convertToDatabaseColumn(List<Object> objects) {
        try {
            return mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    @Override
    public List<Object> convertToEntityAttribute(String s) {
        try {
            if( s == null){
                return null;
            }
            List<Object> list = mapper.readValue(s, List.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }


}
