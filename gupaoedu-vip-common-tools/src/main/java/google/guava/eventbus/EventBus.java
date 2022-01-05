package google.guava.eventbus;

public class EventBus {
    private final static com.google.common.eventbus.EventBus eventBus = new com.google.common.eventbus.EventBus();
    public static com.google.common.eventbus.EventBus getEventBus() {
        return eventBus;
    }
}
