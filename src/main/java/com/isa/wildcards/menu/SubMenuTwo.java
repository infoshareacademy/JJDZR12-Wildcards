package com.isa.wildcards.menu;

import com.isa.wildcards.searchengine.Search;

import java.util.Scanner;

public class SubMenuTwo {
    public static void ShowMenu(Scanner scan) {

        while (true) {
            MenuUtils.printOptionsSubMenuTwo();

            if (!scan.hasNextInt()) {
                MenuUtils.wrongInput();
                scan.nextLine();
                continue;
            }

            int subTwoChoice = scan.nextInt();
            scan.nextLine();

            switch (subTwoChoice) {
                case 1:
                    Search.searchMovie(scan, false);

                    //TODO implementacja metod do wyszukiwania podstawowego
                    //TODO cały kod włącznie z SOUT-em w tym case wyciągniety do zewnętrznej metody

                    break;
                case 2:
                    MenuUtils.returning();
                    break;
                default:
                    MenuUtils.invalidChoice();
                    break;
            }
            if (subTwoChoice == 2) {
                break;
            }
        }
    }
}
