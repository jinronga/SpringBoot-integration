package cn.jinronga.redis.redis.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class GeneratorTest {
    static final String URL="jdbc:mysql://118.31.21.193:3306/mybatis-plus?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL,"root","1460595002")
                .globalConfig(builder -> builder.outputDir("C:\\Users\\hua-cloud\\Desktop\\trees\\Code"))

                .strategyConfig(builder -> builder.addInclude("user"))

                .execute();

    }
}
