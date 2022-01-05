package google.guava.eventbus;

public class Demo1 {
    public static void main(String[] args) {
        //初始化并注册事件
        ListenerClass1 listenerClass1 = new ListenerClass1();
        ListenerClass1 listenerClass1Copy = new ListenerClass1();
        ListenerClass2 listenerClass2 = new ListenerClass2();

        //提交触发事件
        com.google.common.eventbus.EventBus eventBus = EventBus.getEventBus();
        MyEvent myEvent = new MyEvent("外星人来啦", -1);
        eventBus.post(myEvent);

    }
}
