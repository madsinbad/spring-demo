package demo.threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: demo.threadtest.SynTest
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/5/11 16:03
 * @Version: 1.0
 */

class Resource {
    public List<String> resourceList;
    public int maxSize;
    public Resource(int maxSize) {
        this.maxSize = maxSize;
        this.resourceList = new ArrayList();
    }
    public synchronized void consume() throws InterruptedException {
        while (resourceList.size() == 0) {
            System.out.println("容器为空，等待生产者生产");
            this.wait();
        }
        resourceList.remove(0);
        System.out.println(Thread.currentThread().getName() + "消耗一件资源，当前容器中有" + resourceList.size() + "个");
        this.notify();
    }

    public synchronized void produce() throws InterruptedException {
        while (resourceList.size() >= maxSize) {
            System.out.println("容器已满，等待消费");
            this.wait();
        }
        resourceList.add(String.valueOf(Math.random()));
        System.out.println(Thread.currentThread().getName() + "生产一件资源，当前容器中有" + resourceList.size() + "个");
        this.notify();
    }
}

class Producer implements Runnable {
    private Resource resource;
    private String name;

    public Producer(Resource resource, String name) {
        this.resource = resource;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName(name);
            for(;;) {
                this.resource.produce();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Consumer implements Runnable {
    private Resource resource;
    private String name;

    public Consumer(Resource resource, String name) {
        this.resource = resource;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName(name);
            for(;;) {
                this.resource.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SynTest {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new  ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer(resource, "demo.threadtest.Producer" + String.valueOf(i))).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Consumer(resource, "demo.threadtest.Consumer" + String.valueOf(i))).start();
        }
    }
}
