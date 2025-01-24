package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.utils.MyLogger.log;
import static thread.utils.ThreadUtils.sleep;

public class CasMainV3 {

    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + " resultValue: " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get(); // 원래 값을 읽음
            sleep(100); // 스레드 동시 실행을 위한 대기
            log("getValue: " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1); // 원래 값과 동일한지 확인, 동일하다면 값을 증가시킴
            log("result: " + result);
        } while (!result); // 만약 중간에 다른 스레드가 메인 메모리에 있는 값을 업데이트해서 false가 반환되면 do-while문이 반복 실행 됨

        return getValue + 1; // atomicInteger.get()을 안한 이유? -> 그 사이에 또 다른 스레드가 값울 증가시킬 수 있기 때문
    }
}
