package com.annie.dto;

/**
 * @Author: Blade
 * @Description:
 * @Date: 上午 9:32 2017/7/11 0011
 */
public class ResultDto<T> {
    private String resultCode;
    private String resultMsg;
    private T resultData;

    public ResultDto() {
    }

    public ResultDto(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResultDto(String resultCode, String resultMsg, T resultData){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "ResultDto  [resultCode="+resultCode+", resultMsg="+resultMsg+", resultData="+resultData+"]";
    }
}
