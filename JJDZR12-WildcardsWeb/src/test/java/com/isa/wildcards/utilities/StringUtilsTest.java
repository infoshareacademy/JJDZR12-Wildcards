package com.isa.wildcards.utilities;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringUtilsTest {
    private final StringUtils abbreviate = new StringUtils();

    @Test
    void abbreviateNullInput() {
        assertNull(abbreviate.abbreviate(null, 10, "..."));
    }

    @Test
    void abbreviateInputShorterThanMaxWidth() {
        String input = "Short";
        assertEquals(input, abbreviate.abbreviate(input, 10, "..."));
    }

    @Test
    void abbreviateInputEqualToMaxWidth() {
        String input = "Exactly10";
        assertEquals(input, abbreviate.abbreviate(input, 10, "..."));
    }

    @Test
    void abbreviateInputLongerThanMaxWidth() {
        String input = "ThisIsAVeryLongString";
        String expected = "ThisIsA...";
        assertEquals(expected, abbreviate.abbreviate(input, 10, "..."));
    }

    @Test
    void abbreviateMaxWidthEqualToEllipsisLength() {
        String input = "LongString";
        String expected = "...";
        assertEquals(expected, abbreviate.abbreviate(input, 3, "..."));
    }

    @Test
    void abbreviateEmptyInput() {
        assertEquals("", abbreviate.abbreviate("", 5, "..."));
    }
}
