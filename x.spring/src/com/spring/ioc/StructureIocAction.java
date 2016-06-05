package com.spring.ioc;

/**
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category 构造方法注入
 * @author xuanjian
 */
public class StructureIocAction {
    private IocDao iocDao;

    public StructureIocAction(IocDao iocDao) {
        System.out.println("通过构造方法将IocDao对象注入到StructureIocAction中。");
        this.iocDao = iocDao;
    }
    
    public void daoAction(){
        iocDao.ok();
    }
}
