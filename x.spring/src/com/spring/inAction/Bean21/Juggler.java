package com.spring.inAction.Bean21;

/**
 * Title: MVNO-CRM <br>
 * Description: 常说Bean类，就是要被装配的类，业务实现类？<br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category 杂技师
 * @author xuanjian
 */
public class Juggler implements Performer {

    private int beanBags = 3;
    
    public Juggler() {
    }
    
    public Juggler(int beanBags){
        this.beanBags = beanBags;
    }
    @Override
    public void perform() {
        System.out.println("杂技师抛出" + beanBags +"个豆袋子");
    }

}
