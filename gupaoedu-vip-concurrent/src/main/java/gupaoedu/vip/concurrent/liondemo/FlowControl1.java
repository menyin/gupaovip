package gupaoedu.vip.concurrent.liondemo;

public class FlowControl1 {
    /**
     * Executors.newSingleThreadScheduledExecutor()定时器延时功能实现限流（qps）
     * 超过限流值的请求则会延迟处理，并且延迟时间是qps的“统计间隔剩余时间”
     * ？？有空可以在springmvc的action里做下定时器流量整形实践
     * @param args
     */
    public static void main(String[] args) {

    }
}
