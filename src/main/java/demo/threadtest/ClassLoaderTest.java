package demo.threadtest;

/**
 * @ClassName: demo.threadtest.ClassLoaderTest
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/5/31 15:04
 * @Version: 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        ClassLoader classLoader2 = classLoader1.getParent();
        ClassLoader classLoader3 = classLoader2.getParent();

        System.out.println(classLoader1);
        System.out.println(classLoader2);
        System.out.println(classLoader3);
    }
}
