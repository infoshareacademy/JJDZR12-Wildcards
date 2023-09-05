package com.isa.wildcards.menu;

import com.isa.wildcards.searchengine.Search;

import java.util.Scanner;

public class SubMenuOne {
    public static void showMenu(Scanner scan) {

        while (true) {
            MenuUtils.printOptionsSubMenuOne();

            if (!scan.hasNextInt()) {
                MenuUtils.wrongInput();
                scan.nextLine();
                continue;
            }

            int subOneChoice = scan.nextInt();
            scan.nextLine();

            switch (subOneChoice) {
                case 1:
                    Search.searchMovie(scan);

                    //TODO implementacja metod do wyszukiwania zaawansowanego
                    //TODO cały kod włącznie z SOUT-em w tym case wyciągniety do zewnętrznej metody

                    break;
                case 2:
                    System.out.println("Showing search history: ");

                    //TODO implementacja metod do pokazywania historii
                    //TODO cały kod włącznie z SOUT-em w tym case wyciągniety do zewnętrznej metody

                    break;
                case 3:
                    MenuUtils.returning();
                    break;
                default:
                    MenuUtils.invalidChoice();
                    break;
            }
            if (subOneChoice == 3) {
                break;
            }
        }
    }
}
