package com.zzwdkj.base.service;

import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.Std;
import com.hckj.framework.utils.Formatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 标识符生成SV,用于生成主键，支付凭证号等
 *
 * @author guoxianwei 2015-06-19 13:50
 */
@Service("identifierSV")
public class IdentifierSVImpl implements IdentifierSV {

    private static final String[] CHAR_CONSTANT = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static Lock payNo_lock = null;
    private static Lock uniqueId_lock = null;
    private static Lock trno_lock = null;
    private static Lock orgcode_lock = null;
    private static Lock rolecode_lock = null;
    private static Lock rescode_lock = null;
    private static Lock common_lock = null;

    private static final ConcurrentLinkedQueue<String> paynolinkedQueue = new ConcurrentLinkedQueue<String>();
    private static final ConcurrentLinkedQueue<String> payno2linkedQueue = new ConcurrentLinkedQueue<String>();
    private static final ConcurrentLinkedQueue<String> btnolinkedQueue = new ConcurrentLinkedQueue<String>();
    private static final ConcurrentLinkedQueue<String> dunitCodelinkedQueue = new ConcurrentLinkedQueue<String>();

    static {
        payNo_lock = new ReentrantLock();// 锁对象
        uniqueId_lock = new ReentrantLock();// 锁对象
        trno_lock = new ReentrantLock();// 锁对象
        orgcode_lock = new ReentrantLock();// 锁对象
        rolecode_lock = new ReentrantLock();// 锁对象
        rescode_lock = new ReentrantLock();// 锁对象
        common_lock = new ReentrantLock();// 锁对象
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 生成主键
     *
     * @return 主键
     */
    @Override
    public String uniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成主键
     *
     * @return 主键
     */
    @Override
    public String getUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成支付凭证号
     *
     * @param payWay 支付方式
     * @return 支付凭证号
     */
    @Override
    public String payNo(Std.PayWay payWay) {
        payNo_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            String time = jdbcTemplate.queryForObject(UNIQUE_ID_SQL_MYSQL, String.class);
            String val = "";
            if (!paynolinkedQueue.isEmpty()) {
                val = paynolinkedQueue.poll();
            } else {
                int cval = nextval("CUST_PAY_CHARGE", 100);
                for (int i = 0; i < 100; i++) {
                    paynolinkedQueue.add(time + sysCode + payWay.getKey() + getSeq(cval + i, 6));
                }
                val = paynolinkedQueue.poll();
            }
            return val;
        } finally {
            payNo_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成订单号
     *
     * @return 订单号
     */
    @Override
    public String payNo() {
        payNo_lock.lock();// 得到锁
        try {
            String time = Formatter.formatShortDate(System.currentTimeMillis());
            String val = "";
            int cval = nextval("CUST_PAY", 1);
            val = time + getRandom(1) + getSeq(cval, 4);
            return val;
        } finally {
            payNo_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成交易流水号
     *
     * @return 流水号
     */
    @Override
    public String trno() {
        trno_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            String time = jdbcTemplate.queryForObject(UNIQUE_ID_SQL_MYSQL, String.class);
            String val = "";
            if (!btnolinkedQueue.isEmpty()) {
                val = btnolinkedQueue.poll();
            } else {
                int cval = nextval("MBR_PRJ", 100);
                for (int i = 0; i < 100; i++) {
                    btnolinkedQueue.add(time + sysCode + getSeq(cval + i, 6));
                }
                val = btnolinkedQueue.poll();
            }
            return val;
        } finally {
            trno_lock.unlock();// 释放锁
        }

    }

    /**
     * 生成资源编码
     *
     * @return 编码
     */
    @Override
    public String resCode() {
        rescode_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            String time = jdbcTemplate.queryForObject(UNIQUE_ID_SQL_MYSQL, String.class);
            return time + sysCode + getSeq(nextval2("SYS_RES", 1), 5);
        } finally {
            rescode_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成机构编码
     *
     * @return 机构编码
     */
    @Override
    public String orgCode() {
        orgcode_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            return getSeq(nextval2("PBA_ORG", 1), 5) + sysCode;
        } finally {
            orgcode_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成系统角色编码
     *
     * @param sysCode
     * @return 角色编码
     */
    @Override
    public String sysRoleCode(String sysCode) {
        rolecode_lock.lock();// 得到锁
        try {
            return sysCode + getSeq(nextval2("SYS_ROLE_CODE", 1), 4);
        } finally {
            rolecode_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成新开盘号
     *
     * @return 新盘号
     */
    @Override
    public String dunitCode() {
        common_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        String time = jdbcTemplate.queryForObject(UNIQUE_ID_SQL_MYSQL, String.class);
        try {
            String val = "";
            if (!dunitCodelinkedQueue.isEmpty()) {
                val = dunitCodelinkedQueue.poll();
            } else {
                int cval = nextval("PLAT_DUNIT", 10);
                for (int i = 0; i < 10; i++) {
                    dunitCodelinkedQueue.add(time + sysCode + (cval + i));
                }
                val = dunitCodelinkedQueue.poll();
            }
            return val;
        } finally {
            common_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成激活码
     *
     * @return 激活码
     */
    @Override
    public String cdkey() {
        return getRandom2(12).toUpperCase();
    }

    /**
     * 生成商家编码
     *
     * @return 商家编码
     */
    @Override
    public String bizCode() {
        orgcode_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            return "B" + sysCode + getSeq(nextval2("LDS_BIZ", 1), 5);
        } finally {
            orgcode_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成GPRS模块编号
     *
     * @return 模块编号
     */
    @Override
    public String gprsNo() {
        common_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            return sysCode + getSeq(nextval2("LDS_GPRS", 1), 7);
        } finally {
            common_lock.unlock();// 释放锁
        }
    }


    /**
     * 生成GPRS模块编号
     *
     * @param type 设备类型
     * @return 模块编号
     */
    @Override
    public String gprsNo(String type) {
        common_lock.lock();// 得到锁
        // String sysCode = BaseConfig.getSysCode();
        try {
            return type + getSeq(nextval2("LDS_GPRS" + type, 1), 7);
        } finally {
            common_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成产品编号
     *
     * @return 产品编号
     */
    @Override
    public String prodNo() {
        orgcode_lock.lock();// 得到锁
        String sysCode = BaseConfig.getSysCode();
        try {
            return sysCode + getSeq(nextval2("LDS_PROD", 1), 7);
        } finally {
            orgcode_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成产品编号
     *
     * @param type 设备类型
     * @return 产品编号
     */
    @Override
    public String prodNo(String type) {
        orgcode_lock.lock();// 得到锁
        try {
            return type + getSeq(nextval2("LDS_PROD", 1), 7);
        } finally {
            orgcode_lock.unlock();// 释放锁
        }
    }

    /**
     * 生成随机密码
     *
     * @param length 密码长度
     * @return 随机密码
     */
    public String randomPwd(int length) {
        if (length > 59) length = 59;

        return getRandom(length);
    }

    public static String getRandom2(int length) {
        StringBuilder sb = new StringBuilder("");
        // 种子你可以随意生成，但不能重复
        String[] ranArr = new String[36];
        String[] CHAR_CONSTANT2 = {"1", "4", "3", "2", "5", "7", "9", "8", "6", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Random ran = new Random();
        // 数量你可以自己定义。
        for (int i = 0; i < CHAR_CONSTANT2.length; i++) {
            // 得到一个位置
            int j = ran.nextInt(CHAR_CONSTANT2.length - i);
            // 得到那个位置的数值
            ranArr[i] = CHAR_CONSTANT2[j];
            // 将最后一个未用的数字放到这里
            CHAR_CONSTANT2[j] = CHAR_CONSTANT2[CHAR_CONSTANT2.length - 1 - i];
        }
        for (int i = 0; i < length; i++) {
            sb.append(ranArr[i]);
        }
        return sb.toString();
    }

    public static String getRandom(int length) {
        StringBuilder sb = new StringBuilder("");
        // 种子你可以随意生成，但不能重复
        String[] seed = {"1", "4", "3", "2", "5", "7", "9", "8", "6","0"};
        String[] ranArr = new String[10];
        Random ran = new Random();
        // 数量你可以自己定义。
        for (int i = 0; i < seed.length; i++) {
            // 得到一个位置
            int j = ran.nextInt(seed.length - i);
            // 得到那个位置的数值
            ranArr[i] = seed[j];
            // 将最后一个未用的数字放到这里
            seed[j] = seed[seed.length - 1 - i];
        }
        for (int i = 0; i < length; i++) {
            sb.append(ranArr[i]);
        }
        return sb.toString();
    }

    public static String getSeq(int input, int length) {
        StringBuilder sb = new StringBuilder();
        String inputStr = String.valueOf(input);
        for (int i = 0; i < length - inputStr.length(); i++) {
            sb.append("0");
        }
        return sb.append(inputStr).toString();
    }

    /**
     * 获取当日下一个值
     *
     * @param table 表名
     * @return 值
     */
    public int nextval(String table, int size) {
        List<Integer> resultList = jdbcTemplate.query("select val+1 from CFG_ID_GEN where tbl = ? and  DATE_FORMAT(crtime,'%Y%m%d') =?", new Object[]{table, Formatter.formatBankDate(System.currentTimeMillis())}, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rownum) throws SQLException {
                return rs.getInt(1);
            }
        });
        if (resultList == null || resultList.isEmpty()) {
            jdbcTemplate.update("insert into CFG_ID_GEN(id,tbl,val,crtime,uptime) values(?,?,?,?,?) ", new Object[]{uniqueId(), table, size, new Date(), new Date()});
            return 1;
        } else {
            jdbcTemplate.update("update CFG_ID_GEN set val=val+" + size + ",uptime =? where tbl = ? and  DATE_FORMAT(crtime,'%Y%m%d') =?", new Object[]{new Date(), table, Formatter.formatBankDate(System.currentTimeMillis())});
            return resultList.get(0);
        }
    }

    /**
     * 获取下一个值
     *
     * @param table 表名
     * @return 值
     */
    public int nextval2(String table, int size) {
        List<Integer> resultList = jdbcTemplate.query("select val+1 from CFG_ID_GEN where tbl = ? ", new Object[]{table}, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rownum) throws SQLException {
                return rs.getInt(1);
            }
        });
        if (resultList == null || resultList.isEmpty()) {
            jdbcTemplate.update("insert into CFG_ID_GEN(id,tbl,val,crtime,uptime) values(?,?,?,?,?) ", new Object[]{uniqueId(), table, 1, new Date(), new Date()});
            return 1;
        } else {
            jdbcTemplate.update("update CFG_ID_GEN set val=val+" + size + ",uptime =? where tbl = ? ", new Object[]{new Date(), table});
            return resultList.get(0);
        }
    }

    //TODO 此处SQL需要根据数据库变化而变化
    public static final String UNIQUE_ID_SQL = "select to_char(systimestamp, 'yyyymmdd') as time from dual";
    public static final String UNIQUE_ID_SQL_MYSQL = "select DATE_FORMAT(now(),'%Y%m%d')";


    public static void main(String[] args) {
        for (; ; ) {
            System.out.println(getRandom(4).toUpperCase());

        }

    }

}
