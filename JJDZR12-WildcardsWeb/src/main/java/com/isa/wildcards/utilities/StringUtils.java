package com.isa.wildcards.utilities;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    public String abbreviate(String input, int maxWidth, String ellipsis) {
        if (input == null) {
            return null;
        }
        if (input.length() <= maxWidth) {
            return input;
        }
        return input.substring(0, maxWidth - ellipsis.length()) + ellipsis;
    }
}
