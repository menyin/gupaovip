package gupaoedu.vip.concurrent.liondemo;

import java.util.concurrent.*;

/**
 * 测试FutureTask的应用
 * 逻辑：FutureTask的实例myFutureTask的get方法会阻塞等待结果，有几种情况会返回结果：
 *     1.正常执行任务MyTask,myFutureTask.get();会阻塞知道，MyTask任务执行完毕得到结果；
 *     2.当使用future对象（注意不是myFutureTask）取消正在执行的任务后，必须用myFutureTask.mySet()设置值，否则myFutureTask.get()会一直阻塞等待
 *
 *
 */
public class ThreadPool4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyFutureTask myFutureTask = new MyFutureTask(new MyTask());

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Future<?> future = executorService.submit(myFutureTask);
        future.cancel(true); //true表可以取消正在执行的任务
        myFutureTask.mySet("资本主义好");
//        setValue(myFutureTask);
        String result = myFutureTask.get(); //***非常重要：它会阻塞直到#mySet设置值完，如果调用了
        System.out.println(result);
    }



    private static void setValue(MyFutureTask futureTask){
        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("时间到....");
            futureTask.mySet("资本主义好");
        }).start();
    }

    private static class MyFutureTask extends FutureTask<String> {
        public MyFutureTask(Callable<String> callable) {
            super(callable);
        }
        public void mySet(String str){
            super.set(str);
        }
    }

    private static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(6000);
            return "社会主义好";
        }
    }
}
