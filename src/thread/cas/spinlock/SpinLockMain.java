package thread.cas.spinlock;

import static thread.utils.MyLogger.log;
import static thread.utils.ThreadUtils.sleep;

public class SpinLockMain {

    public static void main(String[] args) {
//        SpinLockBad spinLock = new SpinLockBad();
        SpinLock spinLock = new SpinLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    // critical section
                    log("비즈니스 로직 실행");
                    sleep(1); // 오래 걸리는 로직에서는 스핀락 사용하면 안됨. (락 획득 과정 내 while문에서 불필요한 CPU 자원 낭비)
                } finally {
                    spinLock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
