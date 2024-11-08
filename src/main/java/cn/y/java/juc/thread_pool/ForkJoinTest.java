package cn.y.java.juc.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j(topic = "ForkJoinTest")
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        log.info("result: {}", pool.invoke(new Add(10)));
    }

    @Slf4j(topic = "Add")
    public static class Add extends RecursiveTask<Integer>{

        private final int num;
        public Add(int num) {this.num = num;}

        @Override
        protected Integer compute() {
            if (num == 1){
                log.info("end");
                return 1;
            }
            log.info("fork: {}", num);
            // 分解任务
            Add add = new Add(num - 1);
            add.fork();

            // 等待分解的任务执行完成
            Integer result = add.join();
            log.info("join: {}, result: {}", result, result + num);
            return result + num;
        }
    }
}
