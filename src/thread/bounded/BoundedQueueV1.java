package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.utils.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) { // 검증
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.offer(data); // 변경 (검증 ~ 변경 사이에 공유 자원 변경이 일어나면 안 되므로, 임계영역에 synchronized 키워드 추가)
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
