package cn.jinronga.redis.redis.utils;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    //--------------BitMap命令----------


    /**
     * 基于Redis位图的用户签到功能实现类
     * 实现功能：
     * 1. 用户签到
     * 2. 检查用户是否签到
     * 3. 获取用户签到次数
     * 4. 获取用户连续签到次数
     * 5. 获取用户每天的签到情况
     */

    /**
     * 用户签到
     * 指令：SETBIT
     *
     * @param userId 用户Id
     * @param date   日期
     * @return
     */
    public boolean doSign(Long userId, LocalDate date) {
        int offSet = date.getDayOfMonth() - 1;
        return redisTemplate.opsForValue().setBit(buildUserKey(userId, date), offSet, true);
    }

    /**
     * 检查用户某天是否签到
     * 指令：GETBIT
     *
     * @param userId 用户id
     * @param date   当前签到状态
     * @return
     */
    public boolean checkSign(Long userId, LocalDate date) {

        int offSet = date.getDayOfMonth() - 1;
        return redisTemplate.opsForValue().getBit(buildUserKey(userId, date), offSet);
    }


    /**
     * 通过日期获取用户签到次数
     * 指令：BITCOUNT
     *
     * @param userId 用户id
     * @param date   时间
     * @return
     */
    public long getSigCount(Long userId, LocalDate date) {

        return (long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(buildUserKey(userId, date).getBytes(StandardCharsets.UTF_8)));
    }

//    public long getContinuousSingCount(Long userId,LocalDate date){
//         int singCount=0;
//        String type = String.format("u%d", date.getDayOfMonth());
//
//        redisTemplate.execute((RedisCallback<List<Long>>)con-> con.bitField(buildUserKey(userId,date)))
//
//
//
//    }

    /**
     * 获取到返回值后判断用户当月连续签到次数
     *
     * @param userId
     * @param date
     * @return
     */
    public long getContinuousSignCount(Long userId, LocalDate date) {
        int signCount = 0;
        String type = String.format("u%d", date.getDayOfMonth());
        List<Long> lists = bitfield(buildUserKey(userId, date), date.getDayOfMonth(), 0);
        if (lists != null && lists.size() > 0) {
            //取低位连续不为0的个数即为连续签到的次数，需考虑当天尚未签到的情况
            long val = lists.get(0) == null ? 0 : lists.get(0);
            for (int i = 0; i < date.getDayOfMonth(); i++) {

                if (val >> 1 << 1 == val) {  //先右移再左边移，如果相同低位为0
                    //低位为0且非当天说明连续签到中断了
                    if (i > 0) break;
                } else {
                    signCount += 1;
                }
                val >>= 1;
            }
        }

        return signCount;
    }

    public Map<String, Boolean> getSignInfo(Long userId, LocalDate date) {
        HashMap<String, Boolean> signMap = new HashMap<>();
        String type = String.format("u%d", date.lengthOfMonth());
        List<Long> lists = bitfield(buildUserKey(userId, date), date.lengthOfMonth(), 0);
        if (lists != null && lists.size() > 0) {
            long val = lists.get(0) == null ? 0 : lists.get(0);
            //由低位到高位，为0表示未签到 为1表示已签到
            for (int i = 0; i < date.lengthOfMonth(); i++) {
                LocalDate localDate = date.withDayOfMonth(i);
                signMap.put(formatDate(localDate, "yyyy-MM-dd"), val >> 1 << 1 != val);
                val >>= 1;
            }

        }
        return signMap;
    }

    /**
     * 获取当月首次签到日期
     *
     * @param userId 用户ID
     * @param date   日期
     * @return 首次签到日期
     */
    public LocalDate getFirstSignDate(Long userId, LocalDate date) {

//        redisTemplate.opsForValue().bitField()
        return date;
    }

    /**
     * 执行：BITFIELD命令
     *
     * @param buildSignKey
     * @param limit
     * @param offset
     * @return
     */
    public List<Long> bitfield(String buildSignKey, int limit, int offset) {
        return (List<Long>) redisTemplate.execute((RedisCallback<List<Long>>) con ->
                con.bitField(buildSignKey.getBytes(StandardCharsets.UTF_8), BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset)));
    }


    public static String buildUserKey(Long userId, LocalDate date) {

        return String.format("userId:%d:%s", userId, formatDate(date));
    }

    private static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    private static String formatDate(LocalDate date) {
        return formatDate(date, "yyyyMM");
    }

    /**
     * 统计出现次数
     *
     * @param string 目标字符串
     * @param sub    目标子字符串
     * @return
     */
    public static int countMatched(String string, String sub) {
        //判空，为空直接返回0，表示不存在
        if (StringUtil.isNullOrEmpty(string)) {
            return 0;
        }
        int count = 0;
        int index;
        // 循环一次将出现的字符串索引向后移一位，计数器+1，并截取原字符串索引+1的字符串，
        // 如“abcdeeeee”第一次循环找到“e”索引4，substring(4+1)方法截取后得到新的字符串“eeee”，
        // 循环第二次找到“e”的索引0，substring(0+1)方法截取后得到新的字符串“eee”，，，以此类推
        while ((index = string.indexOf(sub)) > -1) {
            count++;
            string = string.substring(index + 1);
        }
        return count;
    }

}
