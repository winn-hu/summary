package com.blueStarWei.aop.service.impl;

import com.blueStarWei.aop.service.CalculationService;
import org.springframework.stereotype.Service;

@Service("calculationService")
public class CaculationServiceImpl implements CalculationService {

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
