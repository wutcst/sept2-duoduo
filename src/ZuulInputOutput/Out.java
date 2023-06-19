package ZuulInputOutput;

import java.util.List;

/**
 * 一个非常简单的类来实现输出功能
 *
 * @author duoduo
 */
public class Out {

    public Out() { }

    public void print(List<String> msg) {
        msg
            .forEach(System.out::println);
    }

    /**
     * 输出一个数据消息
     * @param str the message to print
     */
    public void print(String str) {
        System.out.print(str);
    }

    /**
     * 打印带有回车的消息
     *
     * @param str the message to print
     */
    public void println(String str) {
        System.out.println(str);
    }
}
