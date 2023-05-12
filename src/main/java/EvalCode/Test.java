import java.io.IOException;


//发现了很奇特的事情，.class文件去除了package，但是.java文件如果同时去除，就能弹计算器，如果不去就弹不出来，难道.java和.class还有联系？
public class Test {
    public Test() {
        try {
            Runtime.getRuntime().exec("open -a Calculator.app");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
