package cn.jinronga.redis.service.impl;

import cn.jinronga.redis.common.utils.R;
import cn.jinronga.redis.constant.Constants;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jinronga.redis.dao.UserDao;
import cn.jinronga.redis.entity.UserEntity;
import cn.jinronga.redis.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public R queryPage(Map<String, Object> params) {


        return null;
    }

    @Override
    public void insertUser(UserEntity user) {
        boolean flag = this.save(user);
        //1 先插入mysql并成功
        if (flag) {
            //2 需要再次查询一下mysql将数据捞回来并ok
            user = this.getOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getId, user.getId()));
            if (user != null) {
                redisTemplate.opsForValue().set(Constants.REDIS_USER + user.getId(), user);
            }
        }
    }

    /**
     * 业务逻辑并没有写错，对于(QPS《=1000)可以使用，但是大于不行，会导致缓存击穿或者缓存雪崩
     *
     * @param userId
     * @return
     */
    @Override
    public UserEntity selectUserById(Long userId) {
        //1 先从redis里面查询，如果有直接返回结果，如果没有再去查询mysql
        UserEntity userInfo = (UserEntity) redisTemplate.opsForValue().get(Constants.REDIS_USER + userId);
// 1s 1000 2000
        if (userInfo != null) {
            return userInfo;
        } else {
            synchronized (this){
                //2 redis里面无，继续查询mysql
                userInfo = this.getOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getId, userId));
                if (userInfo != null) {
                    //3.2 mysql有，需要将数据写回redis，保证下一次的缓存命中率
                    redisTemplate.opsForValue().set(Constants.REDIS_USER + userInfo.getId(), userInfo);
                }
            }

        }
        return userInfo;
    }
















    
    /**
     *    加强补充，避免突然key实现了，打爆mysql，做一下预防，尽量不出现击穿的情况。
     * @param userId
     * @return
     */
    @Override
    public UserEntity selectUserById1(Long userId) {
        //1 先从redis里面查询，如果有直接返回结果，如果没有再去查询mysql
        UserEntity userInfo = (UserEntity) redisTemplate.opsForValue().get(Constants.REDIS_USER + userId);

        if (userInfo != null) {
            return userInfo;
        } else {
            synchronized (UserServiceImpl.class) {
                // qps高频要用，对于高QPS的优化，进来就先加锁，保证一个请求操作，让外面的redis等待一下，避免击穿mysql
                //2 redis里面无，继续查询mysql
                userInfo = this.getOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getId, userId));
                if (userInfo != null) {
                    //3.2 mysql有，需要将数据写回redis，保证下一次的缓存命中率
                    redisTemplate.opsForValue().set(Constants.REDIS_USER + userInfo.getId(), userInfo);
                }
            }
        }
        return userInfo;
    }

    @Override
    public void deleteUserById(List<Long> userIds) {
        userIds.forEach(userId -> {
            boolean flag = this.removeById(userId);
            if (flag) {
                redisTemplate.delete(Constants.REDIS_USER + userId);
            }
        });
    }

    @Override
    public void updateUserInfo(UserEntity user) {

        boolean flag = this.update(user, new UpdateWrapper<UserEntity>().lambda().eq(UserEntity::getId, user.getId()));
        if (flag) {
            //2 需要再次查询一下mysql将数据捞回来并ok
            user = this.getOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getId, user.getId()));
            redisTemplate.opsForValue().set(Constants.REDIS_USER + user.getId(), user);
        }
    }
}