package com.spring.inAction.Bean21;

/**
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category 诵诗杂技师
 * @author xuanjian
 */
public class PoeticJuggler extends Juggler {
    
    private Poem poem;
    
    //注入Poem
    public PoeticJuggler(Poem poem) {
        super();
        this.poem = poem;
    }
    //注入豆袋子数量和Poem
    public PoeticJuggler(int beanBags, Poem poem){
        super(beanBags);
        this.poem = poem;
    }
    
    @Override
    public void perform() {
        super.perform();
        System.out.println("正在朗诵……");
        poem.recite();
    }
}
