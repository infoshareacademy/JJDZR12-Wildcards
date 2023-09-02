package com.isa.wildcards.menu;

public enum Colors {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    RESET("\u001B[0m");

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
