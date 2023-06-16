package cn.edu.whut.sept.zuul.ZuulInputOutput;

import java.util.List;

/**
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
     *打印信息
     */
    public void print(String str) {
        System.out.print(str);
    }

    public void println(String str) {
        System.out.println(str);
    }
}
