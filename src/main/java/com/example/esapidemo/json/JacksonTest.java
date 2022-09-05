package com.example.esapidemo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JacksonTest {


    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsenwei");
        map.put("age", 40);
        String valueAsString = mapper.writeValueAsString(map);
        System.out.println(valueAsString);

    }
}
