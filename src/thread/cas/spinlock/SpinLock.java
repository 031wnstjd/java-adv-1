package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static thread.utils.MyLogger.log;
import static thread.utils.ThreadUtils.sleep;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득 시도");

        while (!lock.compareAndSet(false, true)) { // 나뉘어져 있던 확인, 변경 연산을 CAS 연산을 통해 하나의 원자적인 연산으로 묶음
            // 락을 획득할 때까지 스핀 대기(바쁜 대기) 한다.
            log("락 획득 실패 - 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
