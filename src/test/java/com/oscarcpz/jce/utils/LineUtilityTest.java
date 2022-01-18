package com.oscarcpz.jce.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineUtilityTest {

    @Test
    public void operation_element_is_numeric() {
        String result = LineUtility.operation("3");
        Assertions.assertEquals("9", result);
    }

    @Test
    public void operation_element_is_not_numeric() {
        String result = LineUtility.operation("hi");
        Assertions.assertEquals("-", result);
    }

    @Test
    public void operation_element_is_null() {
        String result = LineUtility.operation(null);
        Assertions.assertEquals("-", result);
    }
}