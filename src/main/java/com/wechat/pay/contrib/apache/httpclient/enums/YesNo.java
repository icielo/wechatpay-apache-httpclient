package com.wechat.pay.contrib.apache.httpclient.enums;

/**
 * 是否
 *
 * @Author: lincl
 * @Date: 2020-03-10
 */
public enum YesNo {

    /**
    * 是
    */
   _1("是"),
    /**
    * 否
    */
   _0("否");

    /**
     * 字典名称
     */
    private String name;

    /**
    * 枚举是1、0时的，前缀
    */
    private final String P = "_";

    YesNo(String name) {
        this.name = name;
    }

    /**
     * 获取字典名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获取字典值
     *
     * @return
     */
    public String getValue() {
        if (this.name().startsWith(P)) {
            return this.name().substring(1);
        }
        return this.name().toLowerCase();
    }

    /**
    * 根据字典名获取枚举类
    *
    * @return
    */
    public static YesNo getByName(String name) {
        for (YesNo item : YesNo.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据字典值获取枚举类
     *
     * @return
     */
    public static YesNo getByValue(String value) {
        for (YesNo item : YesNo.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 是否等于某个值
     * @param value
     * @return
     */
    public boolean equals(String value){
        return this.getValue().equals(value);
    }
}
