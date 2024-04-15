package com.backend.utils.response;

import lombok.Data;

@Data
public class ResponseData<T> {
    private int status;
    private String message;
    private String description;
    private T data;
    private long timestamp;

    public ResponseData(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseData<T> success(T data, String description) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(ReturnCodes.SUCCESS.getCode());
        responseData.setMessage(ReturnCodes.SUCCESS.getMessage());
        responseData.setDescription(description);
        responseData.setData(data);
        return responseData;
    }

    public static <T> ResponseData<T> fail(ReturnCodes code, String description) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(code.getCode());
        responseData.setMessage(code.getMessage());
        responseData.setDescription(description);
        return responseData;
    }
}
