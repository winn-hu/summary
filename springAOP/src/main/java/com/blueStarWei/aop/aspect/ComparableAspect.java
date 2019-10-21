package com.blueStarWei.aop.aspect;

import com.blueStarWei.aop.service.ComparableService;
import com.blueStarWei.aop.service.impl.ComparableServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ComparableAspect {

    /**
     * DeclareParents 引入通知：用来替换实现类
     * value中类的修饰符最好用*【我使用了public修饰符，报错】
     */
    @DeclareParents(value = "* com.blueStarWei.aop.service.CaculationService", defaultImpl = ComparableServiceImpl.class)
    private ComparableService service;
}
