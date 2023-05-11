package EvalCode;

import java.io.IOException;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/11 15:49
 */
//如果想执行必须删除package！！！
public class Test {
    public Test() {
        try {
            Runtime.getRuntime().exec("open -a Calculator.app");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
