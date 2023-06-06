package demo.threadtest;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @ClassName: demo.threadtest.ThreadLocalTest
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/6/2 15:46
 * @Version: 1.0
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello world");
        Consumer<String> consumer = System.out::println;
        Arrays.asList("String", "is", "empty").forEach(consumer);
    }
}
