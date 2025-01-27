package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) { // 지속적으로 while문으로 체크하면서, 없으면 sleep으로 1초간 대기
            log("[put] 큐가 가득참, 생산자 대기");
            sleep(1000); // lock을 반납하지 못해서 다른 스레드들이 무한 대기에 빠지는 문제가 발생
        }

        queue.offer(data); // 변경 (검증 ~ 변경 사이에 공유 자원 변경이 일어나면 안 되므로, 임계영역에 synchronized 키워드 추가)
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000);
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
