package com.zzwdkj.base.service;

import com.zzwdkj.common.Std;

/**
 * 标识符生成SV,用于生成主键，支付凭证号等
 *
 * @author guoxianwei 2015-06-19 12:44
 */
public interface IdentifierSV {
    /**
     * 生成主键
     *
     * @return 主键
     */
    String uniqueId();

    /**
     * 生成主键
     *
     * @return 主键
     */
    String getUniqueId();

    /**
     * 生成支付凭证号
     *
     * @param payWay 支付方式
     * @return 支付凭证号
     */
    String payNo(Std.PayWay payWay);

    /**
     * 生成订单号
     *
     * @return 订单号
     */
    String payNo();

    /**
     * 生成资源编码
     *
     * @return 编码
     */
    String resCode();

    /**
     * 生成交易流水号
     *
     * @return 流水号
     */
    String trno();

    /**
     * 生成机构编码
     *
     * @return 机构编码
     */
    String orgCode();


    /**
     * 生成系统角色编码
     *
     * @return 角色编码
     */
    String sysRoleCode(String sysCode);

    /**
     * 生成新开盘号
     *
     * @return 新盘号
     */
    String dunitCode();


    /**
     * 生成激活码
     *
     * @return 激活码
     */
    String cdkey();

    /**
     * 生成商家编码
     *
     * @return 商家编码
     */
    String bizCode();

    /**
     * 生成GPRS模块编号
     *
     * @param type 设备类型
     * @return 模块编号
     */
    String gprsNo(String type);

    /**
     * 生成产品编号
     *
     * @param type 设备类型
     * @return 产品编号
     */
    String prodNo(String type);

    /**
     * 生成GPRS模块编号
     *
     * @return 模块编号
     */
    String gprsNo();

    /**
     * 生成产品编号
     *
     * @return 产品编号
     */
    String prodNo();
}
