package com.zzwdkj.mbr.service;

import com.zzwdkj.biz.dao.BizGiftDAO;
import com.zzwdkj.biz.entity.BizGift;
import com.zzwdkj.biz.service.BizGiftSV;
import com.zzwdkj.common.Std;
import com.zzwdkj.mbr.dao.*;
import com.zzwdkj.mbr.entity.*;
import com.zzwdkj.base.service.IdentifierSV;
import com.zzwdkj.ord.dao.OrdDAO;
import com.zzwdkj.ord.entity.Ord;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
* mbr, 会员SVImpl
*
* @author guoxianwei  2016-09-07 15:01:39
*/
@Service("mbrSV")
public class MbrSVImpl implements MbrSV {

    @Resource
    private MbrDAO mbrDAO;
    @Resource
    private MbrRechargeDAO mbrRechargeDAO;
    @Resource
    private MbrWalletDAO mbrWalletDAO;
    @Resource
    private MbrWalletChgedDAO mbrWalletChgedDAO;
    @Resource
    private BizGiftDAO bizGiftDAO;
    @Resource
    private MbrOauthDAO mbrOauthDAO;
    @Resource
    private MbrCoinDAO mbrCoinDAO;
    @Resource
    private MbrCoinSV mbrCoinSV;
    @Resource
    private MbrOauthSV mbrOauthSV;
    @Resource
    private MbrCoinChgedDAO mbrCoinChgedDAO;
    @Resource
    private BizGiftSV bizGiftSV;
    @Resource
    private IdentifierSV identifierSV;
    @Resource
    private MbrSV mbrSV;


    /**
    * 查询会员 ，带分页
    *
    * @param name     菜单名称
    * @param rowStart 起始行
    * @param rowSize  分页大小
    * @return 菜单资源
    */
    @Override
    public List<Mbr> queryMbr(String name, int rowStart, int rowSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrDAO.query(params, rowStart, rowSize);
    }

    /**
    * 统计会员数量
    * @param name 名称
    * @return 会员
    */
    @Override
    public int count(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return mbrDAO.count(params);
    }


    /**
    * 新增会员
    *
    * @param mbr
    */
    @Override
    public void create(Mbr mbr) {
        mbr.setId(identifierSV.uniqueId());
        mbr.setCrtime(new Date());
        mbr.setUptime(new Date());
        mbrDAO.insert(mbr);
    }

    /**
    * 修改会员
    *
    * @param mbr
    */
    @Override
    public void update(Mbr mbr) {
        mbr.setUptime(new Date());
        mbrDAO.update(mbr);
    }

    /**
    * 删除一条记录
    *
    * @param id
    */
    @Override
    public void remove(String id) {
        mbrDAO.delete(id);
    }

    /**
    * 加载会员
    *
    * @param id 主键
    * @return 会员
    */
    @Override
    public Mbr load(String id) {
        return mbrDAO.load(id);
    }

    /**
     * 通过openId和商家编号获取会员信息
     *
     * @param openid 微信的openid
     * @param bizNo 商家编号
     * @return
     */
    @Override
    public Mbr loadByOpenidAndBizNo(String openid,String bizNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid",openid);
        params.put("bizNo",bizNo);
        return mbrDAO.queryUnique("loadByOpenidAndBizNo", params);
    }


    /**
     * 创建充值记录返回充值单号
     *
     * @param mbr
     * @param payMoney
     * @param payWay
     * @return
     */
    @Override
    public String createRecharge(Mbr mbr, BigDecimal payMoney, Std.PayWay payWay) {
        MbrRecharge recharge = new MbrRecharge();
        recharge.setPayno(identifierSV.payNo(payWay));
        recharge.setId(identifierSV.uniqueId());
        recharge.setMemberId(mbr.getId());
        recharge.setBizNo(mbr.getBizNo());
        recharge.setMoney(payMoney);
        recharge.setRadio(1);
        recharge.setState(MbrRecharge.St.PROC.getKey());
        recharge.setPayWay(payWay.getKey());
        recharge.setCrtime(new Date());
        recharge.setUptime(new Date());
        mbrRechargeDAO.insert(recharge);
        return recharge.getPayno();
    }

    /**
     * 根据手机号加载会员
     *
     * @param mobile 会员手机号
     * @return 会员
     */
    @Override
    public Mbr loadByMobile(String mobile) {
        return mbrDAO.queryUnique("loadByMobile", mobile);
    }

    /**
     *
     * @param payno
     * @param reCgd
     */
    @Override
    public void upReCg(String payno, Std.YN reCgd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("payno", payno);
        List<MbrRecharge> rechargeList = mbrRechargeDAO.query(params);
        if (rechargeList != null && !rechargeList.isEmpty()) {
            MbrRecharge recharge = rechargeList.get(0);
            MbrRecharge recharge_update=new MbrRecharge();
            BigDecimal chargeCoin = null;
            recharge_update.setId(recharge.getId());
            recharge_update.setUptime(new Date());
            if (reCgd.equals(Std.YN.YES)) {
                recharge_update.setState(MbrRecharge.St.SUCCESS.getKey());
            } else {
                recharge_update.setState(MbrRecharge.St.FAIL.getKey());
            }

            if (reCgd.equals(Std.YN.YES)) {
                //人民币和充值币兑换比例
                chargeCoin = recharge.getMoney().multiply(new BigDecimal(recharge.getRadio()));
                //加上活动赠送金额
                Mbr mbr=mbrSV.load(recharge.getMemberId());
                recharge_update.setGiveMoney(0);//初始化赠送金额
                List<BizGift> bizGiftList = bizGiftSV.queryByCoinAndBizNo(mbr.getBizNo(),recharge.getMoney().intValue());
                if (bizGiftList!= null){
                    for(int i=0;i<bizGiftList.size();i++){
                        Integer total_mbr=bizGiftSV.queryBizGiftTotalByMemberId(recharge.getMemberId(),bizGiftList.get(i).getId());
                        Integer total_all=bizGiftSV.queryBizGiftTotalByALL(bizGiftList.get(i).getId());
                        int rtCount=bizGiftSV.updateStockAll(bizGiftList.get(i).getId());//更新活动总剩余量
                        //total_mbr<bizGiftList.get(0).getTotalMbr()&&total_all<bizGiftList.get(0).getTotalAll()
                        if ((total_mbr<bizGiftList.get(0).getTotalMbr()||bizGiftList.get(0).getTotalMbr()==0)&&
                                (total_all<bizGiftList.get(0).getTotalAll()||bizGiftList.get(0).getTotalAll()==0)&&
                                (rtCount>0||bizGiftList.get(0).getTotalAll()==0)) {
                            chargeCoin = chargeCoin;
                            if (reCgd.equals(Std.YN.YES)) {
                                MbrCoin mbrCoin_sel = mbrCoinSV.loadByMemberId(recharge.getMemberId());
                                System.out.println("-----------------------------------------------------------------------------mbrCoin_sel------------------------------------------------" + mbrCoin_sel);
                                Date date = new Date();
                                if (mbrCoin_sel != null && mbrCoin_sel.getId() != null) {//更新会员积分
                                    MbrCoin mbrCoin_update = new MbrCoin();
                                    mbrCoin_update.setId(mbrCoin_sel.getId());
                                    mbrCoin_update.setCoin(mbrCoin_sel.getCoin().add(new BigDecimal(bizGiftList.get(i).getMoney().intValue())));
                                    mbrCoin_update.setUptime(date);
                                    mbrCoinDAO.update(mbrCoin_update);

                                } else {//创建会员积分
                                    MbrCoin mbrCoin = new MbrCoin();
                                    mbrCoin.setId(identifierSV.uniqueId());
                                    mbrCoin.setMemberId(recharge.getMemberId());
                                    mbrCoin.setCoin(new BigDecimal(bizGiftList.get(i).getMoney().intValue()));
                                    mbrCoin.setState(1);
                                    mbrCoin.setCrtime(date);
                                    mbrCoin.setUptime(date);
                                    mbrCoinDAO.insert(mbrCoin);
                                }
                                //添加积分变更记录
                                MbrCoinChged mbrCoinChged = new MbrCoinChged();
                                mbrCoinChged.setId(identifierSV.uniqueId());
                                mbrCoinChged.setMemberId(recharge.getMemberId());
                                mbrCoinChged.setCoin(new BigDecimal(bizGiftList.get(i).getMoney().intValue()));
                                mbrCoinChged.setChgType(1);
                                mbrCoinChged.setRemark("充值赠送");
                                mbrCoinChged.setCrtime(date);
                                mbrCoinChgedDAO.insert(mbrCoinChged);
                            }
                            recharge_update.setGiveMoney(bizGiftList.get(i).getMoney());
                            recharge_update.setGiftId(bizGiftList.get(i).getId());
                            break;
                        }
                    }
                }
                createWalletRefund(recharge.getMemberId(), chargeCoin, "充值");
            }
            mbrRechargeDAO.update(recharge_update);
        }
    }


    /**
     * 钱包返钱
     *
     * @param memberId 客户ID
     * @param money  返款金额
     * @param reason 原因
     */
    @Override
    public void createWalletRefund(String memberId, BigDecimal money, String reason) {
        MbrWallet mbrWallet = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        List<MbrWallet> mbrWallets = mbrWalletDAO.query(params);
        if (mbrWallets != null && !mbrWallets.isEmpty()) {
            mbrWallet = mbrWallets.get(0);
        }
        BigDecimal money_Chged=money;
        if (mbrWallet != null) {
            if (mbrWallet.getMoney() != null) {
                money = mbrWallet.getMoney().add(money);
            }
            MbrWallet mbrWallet_update=new MbrWallet();
            mbrWallet_update.setId(mbrWallet.getId());
            mbrWallet_update.setMoney(money);
            mbrWallet_update.setUptime(new Date());
            mbrWalletDAO.update(mbrWallet_update);
            MbrWalletChged wallatChged = new MbrWalletChged();
            wallatChged.setMemberId(memberId);
            wallatChged.setChgType(1);
            wallatChged.setRemark(reason);
            wallatChged.setMoney(money_Chged);
            wallatChged.setId(identifierSV.uniqueId());
            wallatChged.setCrtime(new Date());
            mbrWalletChgedDAO.insert(wallatChged);

        } else {
            mbrWallet = new MbrWallet();
            String id = identifierSV.uniqueId();
            mbrWallet.setMoney(money);
            mbrWallet.setMemberId(memberId);
            mbrWallet.setId(identifierSV.uniqueId());
            mbrWallet.setState(1);
            mbrWallet.setCrtime(new Date());
            mbrWallet.setUptime(new Date());
            mbrWalletDAO.insert(mbrWallet);
            MbrWalletChged wallatChged = new MbrWalletChged();
            wallatChged.setMemberId(memberId);
            wallatChged.setChgType(1);
            wallatChged.setRemark(reason);
            wallatChged.setMoney(money);
            wallatChged.setId(identifierSV.uniqueId());
            wallatChged.setCrtime(new Date());
            mbrWalletChgedDAO.insert(wallatChged);
        }
    }

    /**
     * 积分返现
     *
     * @param memberId 客户ID
     * @param coin 返还积分
     * @param reason 原因
     */
    @Override
    public void createCoinRefund(String memberId, BigDecimal coin, String reason) {
        MbrCoin mbrCoin = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        List<MbrCoin> mbrCoins = mbrCoinDAO.query(params);
        if (mbrCoins != null && !mbrCoins.isEmpty()) {
            mbrCoin =mbrCoins.get(0);
        }
        BigDecimal coin_Chged = coin;
        if (mbrCoin != null) {
            if (mbrCoin.getCoin() != null) {
                coin = mbrCoin.getCoin().add(coin);
            }
            MbrCoin mbrCoin_update=new MbrCoin();
            mbrCoin_update.setId(mbrCoin.getId());
            mbrCoin_update.setCoin(coin);
            mbrCoin_update.setUptime(new Date());
            mbrCoinDAO.update(mbrCoin_update);
            MbrCoinChged coinChged = new MbrCoinChged();
            coinChged.setMemberId(memberId);
            coinChged.setChgType(1);
            coinChged.setRemark(reason);
            coinChged.setCoin(coin_Chged);
            coinChged.setId(identifierSV.uniqueId());
            coinChged.setCrtime(new Date());
            mbrCoinChgedDAO.insert(coinChged);

        } else {
            mbrCoin = new MbrCoin();
            String id = identifierSV.uniqueId();
            mbrCoin.setCoin(coin);
            mbrCoin.setMemberId(memberId);
            mbrCoin.setId(identifierSV.uniqueId());
            mbrCoin.setState(1);
            mbrCoin.setCrtime(new Date());
            mbrCoin.setUptime(new Date());
            mbrCoinDAO.insert(mbrCoin);
            MbrCoinChged coinChged = new MbrCoinChged();
            coinChged.setMemberId(memberId);
            coinChged.setChgType(1);
            coinChged.setRemark(reason);
            coinChged.setCoin(coin);
            coinChged.setId(identifierSV.uniqueId());
            coinChged.setCrtime(new Date());
            mbrCoinChgedDAO.insert(coinChged);
        }
    }


    /**
     * 查询充电钱包
     *
     * @param mobile 手机号码
     * @return 充电钱包
     */
    @Override
    public MbrWallet loadMbrWallet(String mobile) {
        MbrWallet mbrWallet = mbrWalletDAO.queryUnique("queryByMobile", mobile);
        return mbrWallet;
    }

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
    @Override
    public String createMbr(String mobile, String bizNo, String nick,String name, Std.TPB tpbWay,String user_openid) {
        if (StringUtils.isEmpty(mobile)) {
            return "手机号不能为空！";
        }
        if (StringUtils.isEmpty(bizNo)) {
            return "商家编号不能为空！";
        }
        //Mbr mbr = this.loadByMobile(mobile);
        Mbr mbr_=this.loadByOpenidAndBizNo(user_openid,bizNo);
        Date date = new Date();
        if (mbr_!=null) {
           /* if (!pwd.equals(mbr.getPwd())) { //账号已经存在，且输入的密码不正确
                return "您输入手机号码对应的账号密码不正确，请重新输入！";
            }
            List<MbrOauth> mbrOauthList = mbrOauthSV.queryMbrOauthByMbrid(mbr.getId(),1);//查询用户是否已经绑定微信
            if (mbrOauthList != null && mbrOauthList.size() > 0) {
                return "手机号码对应的账号已经绑定过微信，请更换手机号码！";
            }
            MbrOauth mbrOauth = new MbrOauth();
            mbrOauth.setId(identifierSV.uniqueId());
            mbrOauth.setTpb(tpbWay.getKey());
            mbrOauth.setOpenid(user_openid);
            mbrOauth.setCrtime(date);
            mbrOauth.setUptime(date);
            mbrOauth.setMemberId(mbr.getId());
            mbrOauth.setState(1);
            mbrOauthDAO.insert(mbrOauth);*/
            return "您输入手机号码已经注册过！";
        } else {
            Mbr mbr = new Mbr();
            mbr.setId(identifierSV.uniqueId());
            mbr.setMobile(mobile);
            //mbr.setPwd(MD5Util.MD5(pwd));
            mbr.setBizNo(bizNo);
            mbr.setNick(nick);
            mbr.setName(name);
            mbr.setCrtime(date);
            mbr.setState(Mbr.St.NEWRG.getKey());
            mbr.setUptime(new Date());
            mbrDAO.insert(mbr);
            MbrOauth mbrOauth = new MbrOauth();
            mbrOauth.setId(identifierSV.uniqueId());
            mbrOauth.setTpb(1);
            mbrOauth.setOpenid(user_openid);
            mbrOauth.setCrtime(date);
            mbrOauth.setUptime(date);
            mbrOauth.setMemberId(mbr.getId());
            mbrOauth.setState(1);
            mbrOauthDAO.insert(mbrOauth);

            MbrWallet mbrWallet = new MbrWallet();
            mbrWallet.setId(mbr.getId());
            mbrWallet.setMemberId(mbr.getId());
            mbrWallet.setMoney(BigDecimal.ZERO);
            mbrWallet.setState(1);
            mbrWallet.setPaypwd("");
            mbrWallet.setCrtime(new Date());
            mbrWallet.setUptime(new Date());
            mbrWalletDAO.insert(mbrWallet);
        }
//        if (this.isEmailExists(email)) {
//            throw new BusinessException("邮箱已经被绑定！");
//        }

        return null;
    }


    /**
     * 查询
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<Mbr> queryMbrByMap(Map<String, Object> params, int rowStart, int pageSize) {
        if (rowStart==-1||pageSize==-1){
            return mbrDAO.query(params);
        }else {
            return mbrDAO.query(params,rowStart,pageSize);
        }
    }

    /**
     * 查询数据个数
     *
     * @param params
     * @return
     */
    @Override
    public int countByMap(Map<String, Object> params) {
        return mbrDAO.count(params);
    }

    /**
     * 查询会员，关联钱包
     *
     * @param params
     * @param rowStart
     * @param pageSize
     * @return
     */
    @Override
    public List<Mbr> queryMbrJoinWalletByMap(Map<String, Object> params, int rowStart, int pageSize) {
        if (rowStart==-1||pageSize==-1){
            return mbrDAO.query("queryMbrJoinWalletByMap",params);
        }else {
            return mbrDAO.query("queryMbrJoinWalletByMap",params,rowStart,pageSize);
        }
    }


}
