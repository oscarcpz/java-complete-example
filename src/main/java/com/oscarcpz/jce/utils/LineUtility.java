package com.oscarcpz.jce.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LineUtility {

    private static final Logger log = LoggerFactory.getLogger(LineUtility.class);

    /**
     * Perform operation over a given element
     * @param element element as String
     * @return '-' if error, else element calculated
     */
    public static String operation(final String element) {
        if(StringUtils.isEmpty(element)) {
            return "-";
        }

        if(!StringUtils.isNumeric(element)) {
            return "-";
        }

        String result = String.valueOf(Integer.parseInt(element) * 3);
        log.debug("\t{} -> {}", element, result);
        return result;
    }
}
