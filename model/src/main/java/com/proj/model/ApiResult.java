package com.proj.model;

import lombok.Data;

/**
 * api接口返回统一定义实体
 *
 * @author dong.ning
 */
@Data
public final class ApiResult {

    /**
     * 内容
     */
    private String msg;

    /**
     * 状态码
     */
    private int code;

    /**
     * 需要携带的数据
     */
    private Object data;


    /**
     * 构造函数初始化，默认成功
     */
    public ApiResult() {
        this.ok();
    }


    /*--成功、失败和其他情况的指定可直接返回的内置方法--------------------*/

    /**
     * 资源不存在【固定传输】
     *
     * @return
     */
    public ApiResult fixedNotFound() {
        return this.custom(Constants.API_STATE_CODE_NOTFOUND);
    }

    /**
     * 无权限【固定传输】
     *
     * @return
     */
    public ApiResult fixedForbidden() {
        return this.custom(Constants.API_STATE_CODE_FORBIDDEN);
    }

    /**
     * 继续，下一个【固定传输】
     *
     * @return
     */
    public ApiResult fixedContinueNext() {
        return this.custom(Constants.API_STATE_CODE_CONTINUE_NEXT);
    }

    /**
     * 存入数据
     *
     * @param data
     * @return
     */
    public ApiResult saveData(Object data) {
        this.setData(data);
        return this;
    }

    /**
     * 成功
     *
     * @return
     */
    public ApiResult ok() {
        this.setCode(Constants.API_STATE_CODE_OK);
        this.setMsg(Constants.API_TIP_OK);
        return this;
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public ApiResult ok(Object data) {
        this.setCode(Constants.API_STATE_CODE_OK);
        this.setMsg(Constants.API_TIP_OK);
        this.setData(data);
        return this;
    }

    /**
     * 成功
     *
     * @param data
     * @param message
     * @return
     */
    public ApiResult ok(Object data, String message) {
        this.setCode(Constants.API_STATE_CODE_OK);
        if (null == message) {
            message = Constants.API_TIP_OK;
        }
        this.setMsg(message);
        this.setData(data);
        return this;
    }

    /**
     * 逻辑错误
     *
     * @return
     */
    public ApiResult err() {
        this.setCode(Constants.API_STATE_CODE_ERR);
        this.setMsg(Constants.API_TIP_ERR);
        return this;
    }

    /**
     * 逻辑错误
     *
     * @param data
     * @return
     */
    public ApiResult err(Object data) {
        this.setCode(Constants.API_STATE_CODE_ERR);
        this.setMsg(Constants.API_TIP_ERR);
        this.setData(data);
        return this;
    }

    /**
     * 逻辑错误
     *
     * @param data
     * @param message
     * @return
     */
    public ApiResult err(Object data, String message) {
        this.setCode(Constants.API_STATE_CODE_ERR);
        if (null == message) {
            message = Constants.API_TIP_ERR;
        }
        this.setMsg(message);
        this.setData(data);
        return this;
    }

    /**
     * 自定义http-code
     *
     * @param code
     * @return
     */
    public ApiResult custom(int code) {
        this.setMsg(null);
        this.setCode(code);
        return this;
    }

    /**
     * 自定义http-code
     *
     * @param code
     * @param data
     * @param message
     * @return
     */
    public ApiResult custom(int code, Object data, String message) {
        this.setCode(code);
        this.setMsg(message);
        this.setData(data);
        return this;
    }

}
