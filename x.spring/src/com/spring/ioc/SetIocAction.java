package com.spring.ioc;

/**
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category Set 方法注入
 * @author xuanjian
 */
public class SetIocAction {
    // 注入对象
    private IocDao springDao;

    public IocDao getSpringDao() {
        return springDao;
    }

    // 一定要写被注入对象的set方法
    public void setSpringDao(IocDao springDao) {
        System.out.println("通过Set方法将IocDao对象注入到SetIocAction中。");
        this.springDao = springDao;
    }
    //如果没有调用setSpringDao方法，直接调用此方法，就会空指针异常，因为对象还没有注入进来
    public void daoAction() {
        springDao.ok();
    }
}