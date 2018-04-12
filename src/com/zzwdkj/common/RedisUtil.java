package com.zzwdkj.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Redis 公共类
 * @author xizhuangchui
 * @createDate 2015-6-27 16:39:40
 */
public class RedisUtil {
    private static  JedisPool pool = null;
    public static  String ip = "127.0.0.1";
    public static  int port = 6379;
    /**
     * 十分钟(600秒)
     */
    public static  int  SMS_TIME=10*60;
    /**
     * 构建redis连接池
     * @return JedisPool
     */
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(500);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            Properties properties = BaseConfig.getSysProperties();
         /*   InputStream in = RedisUtil.class.getResourceAsStream("../../../redis.properties");  //---------------------这个是文件的路径（注意区别很简单，就是加上包的路径）
            try {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
            RedisUtil.ip=properties.getProperty("redis_ip").toString();
            RedisUtil.port=Integer.parseInt(properties.getProperty("redis_port").toString());
            pool = new JedisPool(config,ip, port);
        }
        return pool;
    }

    /**
     * 返还到连接池
     *
     * @param pool redis连接池
     * @param redis  redis对象
     */
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }

    /**
     * 获取数据
     *
     * @param key key的值
     * @return String value的值
     */
    public static String get(String key){
        String value = null;

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }

        return value;
    }

    /**
     *插入数据
     * @param key
     * @param value
     * @return Boolean 返回插入状态
     */
    public static Boolean set(String key, String value){
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.set(key,value);
            return true;
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }

    }

    /**
     * 若key不存在则储存
     * @param key
     * @param value
     * @return Long 成功操作的个数
     */
    public static Long setnx(String key, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.setnx(key,value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 设置key的过期时间，单位：秒，如果key在超时之前被修改，则该键关联的超时将被移除。
     * @param key
     * @param seconds 超时时间（秒）
     * @param value
     * @return 正常返回 OK
     */
    public static String setex(String key, int seconds, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.setex(key,seconds,value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }


    /**
     *设置key对应的一条数据超时时间
     * @param key
     * @param milliseconds  超时的时间（毫秒）
     * @return 成功操作的个数
     */
    public static Long expire(String key, int milliseconds) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();

            return jedis.expire(key, milliseconds);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 指定超时时间
     * @param key
     * @param millisecondsTimestamp 超时时间的时间戳
     * @return 成功返回1失败返回0
     */
    public static Long expireAt(String key, long millisecondsTimestamp) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.expireAt(key, millisecondsTimestamp);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 查询剩余时间
     * @param key
     * @return 剩余时间
     */
    public static Long ttl(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.ttl(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }
    /**
     * 判断key是否存在
     * @param key
     * @return Boolean
     */
    public static Boolean exists(String key){
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.exists(key);
            return true;
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 删除多个
     * @param keys key数组
     * @return 成功操作的个数
     */
    public static Long del(String[] keys) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 删除一个
     * @param key
     * @return 成功操作的个数
     */
    public static Long del(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 返回指定Map中的key集合
     * @param pattern
     * @return
     */
    public static Set<String> gethKeys(String pattern) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hkeys(pattern);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }
    /**
     * 返回指定Map中的values集合
     * @param key
     * @return
     */
    public static List<String> gethVals(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hvals(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }
    /**
     * 修改key
     * @param oldkey
     * @param newkey
     * @return
     */
    public static String rename(String oldkey, String newkey) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.rename(oldkey, newkey);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 如果不存在则修改key
     * @param oldkey
     * @param newkey
     * @return
     */
    public static Long renamenx(String oldkey, String newkey) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.renamenx(oldkey, newkey);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     *获取value，并重新赋值
     * @param key
     * @param value
     * @return
     */
    public static String getSet(String key, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.getSet(key, value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 给value追加值
     * @param key
     * @param value
     * @return
     */
    public static Long append(String key, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.append(key, value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 获取指定位置的value值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static String substr(String key, int start, int end) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.substr(key, start,end);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 存入map数据
     * @param key
     * @param hash
     * @return
     */
    public static String sethm(String key, Map<String, String> hash) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hmset(key, hash);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     *  存入MAP并设置超时
     * @param key
     * @param seconds 超时时间（秒）
     * @param hash Map<String, String>
     * @return Boolean
     */
    public static Boolean sethmExpire(String key,int seconds , Map<String, String> hash) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            String state= jedis.hmset(key, hash);
            if (state.equals("OK")){
                jedis.expire(key,seconds);
            }else {
                return false;
            }
            return true;
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 插入map指定的key对应的value
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long seth(String key, String field, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }
    /**
     * 若不存在则插入map指定的key对应的value
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long sethnx(String key, String field, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hsetnx(key, field,value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }
    /**
     * 获取map指定的key对应的value
     * @param key
     * @param fields
     * @return
     */
    public static List<String> gethm(String key, String fields) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hmget(key, fields);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 删除map指定的key
     * @param key
     * @param fields
     * @return
     */
    public static Long delh(String key, String fields) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hdel(key, fields);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 获取指定key的MAP
     * @param key
     * @return Map<String, String>
     */
    public static Map<String, String> gethAll(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return null;
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    public static void main(String[] args) {
        String message = "C0700000000000046A100G10000001";

        set("test",message);
        System.out.println(get("test"));



    }
}
