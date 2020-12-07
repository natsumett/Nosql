package com.bjtu.redis;

import java.util.LinkedList;
import java.util.List;
import redis.clients.jedis.Jedis;

public class RedisUtil {

        /**
         * 从连接池中获取实例
         * @return
         */
        public static Jedis GetJedis(){
            return JedisInstance.getInstance().getResource();
        }

        /**
         * 判断键是否存在
         * @param key
         * @return
         */
        public boolean RedisExist(String key) {
            return GetJedis().exists(key);
        }
        /**
         * set
         * @param key
         * @param value
         */
        public void RedisSet(String key, String value) {
            GetJedis().set(key,value);
        }
        /**
         * get
         * @param key
         * @return
         */
        public String RedisGet(String key) {
            return GetJedis().get(key);
        }
        /**
         * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
         * @param key
         * @param value
         * @return
         */
        public String RedisGetSet(String key, String value) {
            return GetJedis().getSet(key,value);
        }
        /**
         * 存入多个键值对 自己封装
         * @param keysvalues
         */
        public void RedisMset(String... keysvalues){
            List<String> list = new LinkedList<String>();
            int i =0;
            if(keysvalues!=null){
                for (String keys : keysvalues){
                    list.add(i,keys);
                    i++;
                }
            }
            for (i=0;i<list.size();i++){
                GetJedis().set(list.get(i),list.get(i+1));
                i=i+1;
            }
        }
        /**
         * 输入多个键得到List<>值
         * @param keys
         * @return
         */
        public List<String> RedisMget(String...keys){
            List<String> keyslist = new LinkedList<String>();
            int i =0;
            if(keys!=null){
                for(String keyss :keys){
                    keyslist.add(i,keyss);
                    i++;
                }
            }
            List<String> listObj = new LinkedList<String>();
            for (i=0;i<keyslist.size();i++){
                listObj.add(i,GetJedis().get(keyslist.get(i)));
            }
            return listObj;
        }
        /**
         * 设置一个有过期时间的key-value对
         * @param key
         * @param seconds
         * @param value
         */
        public void RedisSetex(String key, int seconds,String value) {
            GetJedis().setex(key,seconds,value);
        }
        /**
         * 单独设置键的过期时间
         * @param key
         * @param seconds
         */
        public void RedisExpire(String key, int seconds) {
            GetJedis().expire(key,seconds);
        }
        /**
         * 移除键的过期时间，键保持持久
         * @param key
         */
        public void RedisPersist(String key) {
            GetJedis().persist(key);
        }
        /**
         * 查看键的剩余过期时间
         * @param key
         * @return
         */
        public Long RedisTTL(String key) {
            return GetJedis().ttl(key);
        }
        /**
         * 删除一个或者多个键
         * @param keys
         */
        public void RedisDel(String... keys) {
            List<String> list = new LinkedList<String>();
            int i =0;
            if(keys!=null){
                for (String key :keys){
                    list.add(i,key);
                    i++;
                }
            }
            for (i=0;i<list.size();i++){
                GetJedis().del(list.get(i));
            }
        }
        /**
         * 重命名key
         * @param key
         * @param newkey
         */
        public void RedisRename(String key, String newkey) {
            GetJedis().rename(key,newkey);
        }
    }
