package com.isa.wildcards.menu;

import java.util.Scanner;

public class SubMenuOne {
    public static void showMenu() {
        Scanner scan = new Scanner(System.in);
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
                    System.out.println("Type what do you want to search:");

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
