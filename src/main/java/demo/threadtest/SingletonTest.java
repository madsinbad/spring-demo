package demo.threadtest;

/**
 * @ClassName: demo.threadtest.Singleton
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/5/12 16:38
 * @Version: 1.0
 */
class Singleton {
    private static volatile Singleton singleton;
    private Singleton(){}
    public static Singleton getInstance(){
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
class SingleCreate implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " + Singleton.getInstance());
    }
}
public class SingletonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new SingleCreate(), String.valueOf(i)).start();
        }
        int a  = 0;
    }
}

