package com.gupaoedu.vip.pattern.delegate.decorator.impl;

import com.gupaoedu.vip.pattern.delegate.decorator.HouYi;

public class HouYiImplExt implements HouYi {
    private HouYi houYi;
    public HouYiImplExt(HouYi houYi) {
        this.houYi = houYi;
    }

    @Override
    public void sheRi() {
        this.houYi.sheRi();
        System.out.println("带发光效果，亮瞎眼~~");
    }
}
