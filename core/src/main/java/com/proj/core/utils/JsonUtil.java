package com.proj.core.utils;

import cn.hutool.json.JSONUtil;

/**
 * json处理辅助工具类
 *
 * @author dong.ning
 */
public class JsonUtil {

    /**
     * 将一个对象，转换为，json字符串
     *
     * @param object
     * @return
     */
    public static String toJsonStr(Object object) {
        return JSONUtil.toJsonStr(object);
    }

}
