package gupaoedu.vip.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

//public class EventListener implements ApplicationListener {
public class EventListener implements ApplicationListener<EventBean> {
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        if (applicationEvent instanceof EventBean) {
//            System.out.println(applicationEvent);
//        }
//    }

    public void onApplicationEvent(EventBean eventBean) {
        if (eventBean instanceof EventBean) {
            System.out.println(eventBean);
        }
    }
}
