package cn.jinronga.redis.enums;

import cn.jinronga.redis.entity.UserEntity;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

public enum UserFunnel implements Funnel<UserEntity> {
    INSTANCE;


    @Override
    public void funnel(UserEntity userEntity, PrimitiveSink primitiveSink) {
        primitiveSink.putString(userEntity.getName(), Charset.defaultCharset());
        primitiveSink.putString(userEntity.getId()+"",Charset.defaultCharset());
    }
}
