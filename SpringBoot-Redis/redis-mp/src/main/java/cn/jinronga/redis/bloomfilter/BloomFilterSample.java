package cn.jinronga.redis.bloomfilter;

import cn.jinronga.redis.entity.UserEntity;
import cn.jinronga.redis.enums.UserFunnel;
import com.google.common.hash.BloomFilter;

/**
 * 布隆过滤器  Guava--谷歌开源
 *
 */
public class BloomFilterSample {
    public static void main(String[] args) {
        BloomFilter<UserEntity> bloomFilter = BloomFilter.create(UserFunnel.INSTANCE, 10, 0.01);
//        // 查询new Person("chen", "yahui")是否存在
//        System.out.println(bloomFilter.mightContain(new UserEntity("chen", "yahui"))); //false
//        // 将new Person("chen", "yahui")对象放入BloomFilter中
//        bloomFilter.put(new UserEntity("chen","yahui"));
//        // 再次查询new Person("chen", "yahui")是否存在
//        System.out.println(bloomFilter.mightContain(new UserEntity("chen", "yahui"))); //true
    }
}
