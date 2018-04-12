package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.MbrPrizen;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* mbr_prizen, 会员奖励记录表SV
*
* @author guoxianwei  2016-11-11 15:55:30
*/

public interface MbrPrizenSV {

    /**
    * 查询会员奖励记录表 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<MbrPrizen> queryMbrPrizen(String name, int rowStart, int rowSize);

    /**
    * 新增会员奖励记录表
    *
    * @param mbrPrizen
    */
    void create(MbrPrizen mbrPrizen);

    /**
    * 修改会员奖励记录表
    *
    * @param mbrPrizen
    */
    void update(MbrPrizen mbrPrizen);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计会员奖励记录表数量
    * @param name 名称
    * @return 会员奖励记录表数量
    */
    int count(String name);

    /**
    * 加载会员奖励记录表
    *
    * @param id 主键
    * @return 会员奖励记录表
    */
    MbrPrizen load(String id);


}
