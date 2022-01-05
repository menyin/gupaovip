package gupaoedu.vip.spring.event;

import org.springframework.context.ApplicationEvent;

public class EventBean extends ApplicationEvent{
    private String showtips;
    public EventBean(Object source) {
        super(source);
    }

    public EventBean(Object source, String showtips) {
        super(source);
        this.showtips = showtips;
    }
    @Override
    public String toString(){
        return source.toString()+";"+showtips;
    }
}
