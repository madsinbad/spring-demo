package demo.threadtest;

import java.util.HashMap;
import java.util.Map;

public class FutureTaskTest {
    static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        Map a = new HashMap();
//        Thread A = new Thread(() -> {
//            synchronized (object){
//                while(true) {
//                    try {
//                        object.wait();
//                        Thread.sleep(2000);
//                        object.notify();
//                        System.out.println("玩家A击球给玩家B");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "A");
//        Thread B = new Thread(() -> {
//            System.out.println("游戏开始");
//            synchronized (object){
//                while(true) {
//                    try {
//                        object.notify();
//                        System.out.println("玩家B击球给玩家A");
//                        object.wait();
//                        Thread.sleep(2000L);
//                        // 试验执行完 notify() 方法后，A 线程是否能立即获取到锁
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "B");
//        A.start();
//        B.start();
        Thread C = new Thread(() -> {
            try {
                System.out.println(Thread.interrupted());
                Thread.currentThread().interrupt();
                System.out.println(Thread.interrupted());
                System.out.println(Thread.interrupted());
                Thread.sleep(1000);
                Thread.currentThread().isInterrupted();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },  "C");
        C.start();
//        Thread.sleep(2000);
//        C.interrupt();

    }
}
