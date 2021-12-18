package cn.jinronga.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ArticleService
{
    public static final String ARTICLE = "article:";
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void likeArticle(String articleId)
    {
        String key = ARTICLE+articleId;
        Long likeNumber = stringRedisTemplate.opsForValue().increment(key);
        log.info("文章编号:{},喜欢数:{}",key,likeNumber);
    }
}