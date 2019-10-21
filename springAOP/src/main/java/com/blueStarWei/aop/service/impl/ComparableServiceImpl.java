package com.blueStarWei.aop.service.impl;

import com.blueStarWei.aop.service.ComparableService;

public class ComparableServiceImpl implements ComparableService {

    public int max(int num1, int num2) {
        return num1 >= num2 ? num1 : num2;
    }

    public int min(int num1, int num2) {
        return num1 <= num2 ? num1 : num2;
    }
}
