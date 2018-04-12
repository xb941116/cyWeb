package com.zzwdkj.base;

/**
 * enum 实现接口，系统中静态数据enum均实现此接口
 *
 * @author guoxianwei 2015-07-09 09:27
 */
public interface BaseEnum<T> {

    Integer getKey();

    String getVal();

}
