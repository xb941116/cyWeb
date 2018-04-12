package com.zzwdkj.mbr.service;

import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.entity.Mbr;
import com.zzwdkj.mbr.entity.MbrWallet;
import com.zzwdkj.ord.entity.Ord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* mbr, 会员SV
*
* @author guoxianwei  2016-09-07 15:01:39
*/

public interface MbrSV {

    /**
    * 查询会员 ，带分页
    *
    * @param name 名称
    * @param rowStart 起始行
    * @param rowSize 分页大小
    * @return 结果集
    */
    List<Mbr> queryMbr(String name, int rowStart, int rowSize);

    /**
    * 新增会员
    *
    * @param mbr
    */
    void create(Mbr mbr);

    /**
    * 修改会员
    *
    * @param mbr
    */
    void update(Mbr mbr);

    /**
    * 删除一条记录
    *
    * @param id
    */
    void remove(String id);

    /**
    * 统计会员数量
    * @param name 名称
    * @return 会员数量
    */
    int count(String name);

    /**
    * 加载会员
    *
    * @param id 主键
    * @return 会员
    */
    Mbr load(String id);


    /**
     * 通过openId和商家编号获取会员信息
     * @param openid 微信的openid
     * @param bizNo 商家编号
     * @return
     */
    Mbr loadByOpenidAndBizNo(String openid,String bizNo);

    /**
     * 创建充值记录返回充值单号
     * @param mbr
     * @param payMoney
     * @param payWay
     * @return
     */
    String createRecharge(Mbr mbr, BigDecimal payMoney, Std.PayWay payWay);

    /**
     * 根据手机号加载会员
     *
     * @param mobile 会员手机号
     * @return 会员
     */
    Mbr loadByMobile(String mobile);

    /**
     *  更新充值状态
     * 成功后，新增充值币
     * @param pno
     * @param reCgd
     */
    void upReCg(String pno, Std.YN reCgd);


    /**
     * 积分返现         修改人：sfn；时间：12.7
     *
     * @param custid 客户ID
     * @param coin  返还积分
     * @param reason 原因
     */
    void createCoinRefund(String custid, BigDecimal coin, String reason);



    /**
     * 钱包返钱
     *
     * @param custid 客户ID
     * @param money  返款金额
     * @param reason 原因
     */
    void createWalletRefund(String custid, BigDecimal money, String reason);
    /**
     * 查询充电钱包
     *
     * @param mobile 手机号码
     * @return 充电钱包
     */
    MbrWallet loadMbrWallet(String mobile);

    /**
     * 创建用户
     * @param mobile 手机号
     * @param bizNo 商家编号
     * @param nick 昵称
     * @param name 姓名
     * @param tpbWay 注册方式（1微信，2QQ）
     * @param user_openid 微信的openid
     * @return
     */
    String createMbr(String mobile, String bizNo, String nick,String name,Std.TPB tpbWay, String user_openid);

    /**
     * 查询
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<Mbr> queryMbrByMap(Map<String, Object> params, int rowStart, int pageSize);

    /**
     * 查询数据个数
     * @param params
     * @return
     */
    int countByMap(Map<String, Object> params);

    /**
     * 查询会员，关联钱包
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    List<Mbr> queryMbrJoinWalletByMap(Map<String, Object> params, int rowStart, int pageSize);
}
