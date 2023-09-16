package com.proj.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * 防止SQL注入的检测辅助工具类
 *
 * @author dong.ning
 */
public class SqlUtil {

    /**
     * 特殊字符串列表
     */
    private final static List<Character> CLEAR_SAFE_CHAR_LIST = Lists.newArrayList(
            new Character[]{'\'', '%'}
    );


    /**
     * 过滤安全字符串
     *
     * @param text
     * @return
     */
    public static String clearSafe(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        StringBuilder sqlNew = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            Character ch = text.charAt(i);
            if (!CLEAR_SAFE_CHAR_LIST.contains(ch)) {
                sqlNew.append(ch);
            }
        }
        return sqlNew.toString();
    }
}
