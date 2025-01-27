package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data); // 스레드가 대기하지 않고 즉시 반환
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll(); // 스레드가 대기하지 않고 즉시 반환
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
