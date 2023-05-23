package Fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/22 13:44
 */
public class FastjsonSCLearning {
    public static void main(String[] args) {

        //测试Person类的反序列化，本质为测试JavaBeanDeserializer反序列化
        Person person = new Person();
        person.setAge(19);
        person.setName("bzk");
        String personJSON = JSON.toJSONString(person);
        Person person1 = JSON.parseObject(personJSON, Person.class);


        //测试BooleanCodec反序列化
//        String jsonString = JSON.toJSONString(Boolean.TRUE);
//        JSON.parseObject(jsonString,Boolean.class);


        //测试CharacterCodec反序列化
//        String jsonString = JSON.toJSONString('a');
//        Character character = JSON.parseObject(jsonString, Character.class);
//        System.out.println("jsonString:"+jsonString);
//        System.out.println("character:"+character);

        //测试IntegerCodec反序列化
//        String jsonString = JSON.toJSONString(123);
//        JSON.parseObject(jsonString,Integer.class);
    }
}
