package com.proj.model;

/**
 * 公共常量定义类
 *
 * @author dong.ning
 */
public class Constants {

    /**
     * api执行成功的提示文字
     */
    public final static String API_TIP_OK = "执行成功";

    /**
     * api执行成功的提示文字
     */
    public final static String API_TIP_ERR = "执行失败";

    /**
     * api执行成功的提示文字
     */
    public final static String API_TIP_ERR_BY_ZERO = "分母不能为0";
    /**
     * api执行成功的提示文字
     */
    public final static String API_TIP_ERR_IS_ZERO = "当前为0";

    /**
     * api执行成功的状态码
     */
    public final static int API_STATE_CODE_OK = 200;

    /**
     * api执行失败的状态码，逻辑错误
     */
    public final static int API_STATE_CODE_ERR = 500;

    /**
     * api执行notfound的状态码-找不到
     */
    public final static int API_STATE_CODE_NOTFOUND = 404;

    /**
     * api执行forbidden的状态码-无权限
     */
    public final static int API_STATE_CODE_FORBIDDEN = 403;

    /**
     * api执行continue-next的状态码-继续下一个
     */
    public final static int API_STATE_CODE_CONTINUE_NEXT = 100;

}
