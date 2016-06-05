package com.spring.inAction.Bean21;

/**
 * Title: MVNO-CRM <br>
 * Description: 木兰花令 拟古决绝词<br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category 纳兰词
 * @author xuanjian
 */
public class Nalanci implements Poem{
    private static String[] LINES = {
        "人生若只如初见",
        "何事秋风悲画扇",
        "等闲变即故人心",
        "却道故人心易变",
        "骊山语罢清宵半",
        "泪雨零铃终不怨",
        "何如薄幸锦衣郎",
        "比翼连枝当日愿"
    };
    
    public Nalanci() {
    }

    @Override
    public void recite() {
        for (String string : LINES) {
            System.out.println(string);
        }
    }
    
    
}
