package demo.threadtest;

/**
 * @ClassName: demo.threadtest.PriorityTest
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/5/12 11:24
 * @Version: 1.0
 */

class LowPriority implements Runnable{
    @Override
    public void run() {
        System.out.println("this is low process");
    }
}

class MidPriority implements Runnable{
    @Override
    public void run() {
        System.out.println("this is mid process");
    }
}

class HigPriority implements Runnable{
    @Override
    public void run() {
        System.out.println("this is high process");
    }
}


public class PriorityTest {
    public static void main(String[] args) {
        Thread low = new Thread(new LowPriority(), "low");
        low.setPriority(1);
        Thread mid = new Thread(new MidPriority(), "mid");
        mid.setPriority(5);
        Thread hig = new Thread(new HigPriority(), "hig");
        hig.setPriority(10);
        low.start();
        mid.start();
        hig.start();
    }
}
