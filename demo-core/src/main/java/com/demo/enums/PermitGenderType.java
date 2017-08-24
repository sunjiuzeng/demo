package com.demo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/6/2
 * Time: 下午12:37
 * To change this template use File | Settings | File Templates.
 */
public enum PermitGenderType {

    F(1, "女"),
    M(0, "男");

    private int code;

    private String desc;

    PermitGenderType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, PermitGenderType> map = new HashMap<>();

    static {
        for (PermitGenderType type : PermitGenderType.values()){
            map.put(type.getCode(), type);
        }
    }

    public static PermitGenderType valueOf(int code){
        return map.get(code);
    }
}
