package com.isa.wildcards.menu;

import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            MenuUtils.printOptionsMain();

            if (!scan.hasNextInt()) {
                MenuUtils.wrongInput();
                scan.nextLine();
                continue;
            }

            int input = scan.nextInt();
            scan.nextLine();

            switch (input) {
                case 1:
                    LoginMenu.showMenu(scan);
                    break;

                case 2:
                    SubMenuTwo.ShowMenu(scan);
                    break;
                case 3:
                    System.out.println("Creating new user");

                    //TODO implementacja metod do tworzenia uzytkownika nastepnie powrot do menu glownego
                    //TODO cały kod włącznie z SOUT-em w tym case wyciągniety do zewnętrznej metody

                    break;
                case 4:
                    MenuUtils.returning();
                    scan.close();
                    return;
                default:
                    MenuUtils.invalidChoice();
                    break;
            }
            System.out.println("\n");
        }
    }
}

