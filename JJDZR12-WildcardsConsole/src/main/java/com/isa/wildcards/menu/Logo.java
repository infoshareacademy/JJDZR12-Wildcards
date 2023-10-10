package com.isa.wildcards.menu;

public class Logo {

    public static void printLogo() {
        String color = "\u001B[38;5;202m";
        String reset = "\u001B[0m";
        String wildsearch =color +
                "WW          WW  III   L      DDD         SSS   EEEEE      A      RRRR    CCCC   HH  HH\n" +
                " WW        WW    I    L      D   D      S      E         A A     R   R   C      HH  HH\n" +
                "  W  W  W  W     I    L      D   D       SSS   EEE      AAAAA    RRRR    C      HHHHHH\n" +
                "   W W  W W      I    L      D   D          S  E       A     A   R   R   C      HH  HH\n" +
                "    W    W      III   LLLLL  DDD        SSSS   EEEEE  A       A  R   R   CCCC   HH  HH\n" + "\n" +
                "                               SEARCH ENGINE" + reset;

        System.out.println(wildsearch);
    }
}
