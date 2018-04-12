package com.zzwdkj.base;

import java.util.List;
import java.util.Map;

/**
 * 需要向前台传递状态枚举值的实体可以实现此接口
 *
 * @author guoxianwei 2015-07-09 14:46
 */
public interface BaseSt {
    /**
     * 获取实体的状态枚举值
     *
     * @return 状态枚举值集合
     */
    List<Map<String, Object>> getSt();
}
