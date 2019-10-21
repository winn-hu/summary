package com.blueStarWei.aop.service.impl;

import com.blueStarWei.aop.service.CaculationService;
import org.springframework.stereotype.Service;

@Service("caculationService")
public class CaculationServiceImpl implements CaculationService {

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int sub(int num1, int num2) {
        return num1 - num2;
    }

    public int mutiply(int num1, int num2) {
        return num1 * num2;
    }

    public int divid(int num1, int num2) {
        return num1 / num2;
    }
}
