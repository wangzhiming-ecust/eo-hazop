package com.rao.kg.service;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EncodeAndDecode {

    public String encode(String str){
        String result =  null;
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String decode(String str){
        String result =  null;
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;

    }
}
