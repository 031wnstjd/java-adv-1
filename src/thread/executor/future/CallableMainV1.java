package thread.executor.future;

import java.util.concurrent.Callable;

import static thread.utils.MyLogger.log;

public class CallableMainV1 {

    public static void main(String[] args) {

    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            log("Callable 완료");
            return 0;
        }
    }
}
