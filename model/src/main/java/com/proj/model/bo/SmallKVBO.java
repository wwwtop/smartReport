package com.proj.model.bo;

import lombok.Data;

/**
 * 简短的kv实体BO
 *
 * @author dong.ning
 */
@Data
public class SmallKVBO {

    /**
     * 值
     */
    private String value;

    /**
     * 需要显示的文字
     */
    private String text;


    public SmallKVBO() {

    }

    public SmallKVBO(String text) {
        this.setText(text);
    }

    public SmallKVBO(String text, String value) {
        this.setText(text);
        this.setValue(value);
    }

}
