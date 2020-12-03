package com.suning.crowdfunding.domain;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/30 16:35
 * @desc
 */
public class AJAXResult {
    private boolean success;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
