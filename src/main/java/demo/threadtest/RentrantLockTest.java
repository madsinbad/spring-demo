package demo.threadtest;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: RentrantLock
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/5/16 15:34
 * @Version: 1.0
 */
class ProducerProcess implements Runnable {
    public Vector<String> vector;
    public ReentrantLock reentrantLock;
    public Condition producerCondition;
    public Condition consumerCondition;
    public int size;

    public ProducerProcess(Vector vector, ReentrantLock reentrantLock, Condition producerCondition, Condition consumerCondition, int size) {
        this.vector = vector;
        this.reentrantLock = reentrantLock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
        this.size = size;
    }

    public void produce() {
        try {
            reentrantLock.lock();
            AtomicReference<String> atomicReference = new AtomicReference<>();
            if (vector.size() == size) {
                System.out.println("容器满了，待消费者消费...");
                producerCondition.await();
            }
            System.out.println("生产者开始生产（当前长度： " + vector.size() + " ）...");
            Thread.sleep(100);
            String randomString = String.valueOf(Math.random());
            vector.add(randomString);
            System.out.println("生产者生产结束（当前长度： " + vector.size() + " ）...");
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           reentrantLock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}

class ConsumerProcess implements Runnable {
    public Vector<String> vector;
    public ReentrantLock reentrantLock;
    public Condition producerCondition;
    public Condition consumerCondition;

    public ConsumerProcess(Vector vector, ReentrantLock reentrantLock, Condition producerCondition, Condition consumerCondition) {
        this.vector = vector;
        this.reentrantLock = reentrantLock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    public void consume() {
        try {
            reentrantLock.lock();
            if (vector.size() == 0) {
                System.out.println("容器空了，待生产者生产...");
                consumerCondition.await();
            }
            System.out.println("消费者开始消费（当前长度： " + vector.size() + " ）...");
            Thread.sleep(100);
            vector.remove(0);
            System.out.println("消费者消费结束（当前长度： " + vector.size() + " ）...");
            producerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
}


public class RentrantLockTest {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Condition producerCondition = reentrantLock.newCondition();
        Condition consumerCondition = reentrantLock.newCondition();
        Vector vector = new Vector();
        Thread producer = new Thread(new ProducerProcess(vector, reentrantLock, producerCondition, consumerCondition, 5));
        Thread comsumer = new Thread(new ConsumerProcess(vector, reentrantLock, producerCondition, consumerCondition));
        producer.setPriority(10);
        producer.start();
        comsumer.start();
//        executorService.submit(producer);
//        executorService.submit(comsumer);+
//        executorService.shutdown();
    }
}

