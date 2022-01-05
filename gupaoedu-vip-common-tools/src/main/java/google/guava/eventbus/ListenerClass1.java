package google.guava.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class ListenerClass1 {
    public ListenerClass1() {
        google.guava.eventbus.EventBus.getEventBus().register(this);
    }

    @Subscribe
    @AllowConcurrentEvents
    private void on(MyEvent myEvent) {
        System.out.println("ListenerClass1"+myEvent);
    }
}
