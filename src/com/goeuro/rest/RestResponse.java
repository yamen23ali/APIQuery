/*
 * This project was made as test for GoEuro
 */
package com.goeuro.rest;

/**
 *
 * @author mindvalley
 */
public class RestResponse {

    private String body;
    private int code;

    public RestResponse(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

}
