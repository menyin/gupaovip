package com.gupaoedu.vip.pattern.observe.subject;

import com.gupaoedu.vip.pattern.observe.core.Event;

public class Observe {
    public void onSubjectAdd(Event e){
        System.out.println(e);
    }
    public void onSubjectDelete(Event e){
        System.out.println(e);
    }
    public void onSubjectSelect(Event e){
        System.out.println(e);
    }
}
