package com.isa.wildcards.menu;

import java.util.Scanner;

public class LoginMenu {
    public static void loginMenu() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            MenuUtils.printOptionsLoginMenu();

            if (!scan.hasNextInt()) {
                MenuUtils.wrongInput();
                scan.nextLine();
                continue;
            }

            int subLoginChoice = scan.nextInt();
            scan.nextLine();

            switch (subLoginChoice) {
                case 1:
                    System.out.println("Enter Login credentials:");

                    //TODO implementacja metod do logowania do systemu następnie przejście do reszty menu
                    //TODO cały kod włącznie z SOUT-em w tym case wyciągniety do zewnętrznej metody

                    SubMenuOne.subMenuOne();
                    break;
                case 2:
                    MenuUtils.returning();
                    break;
                default:
                    MenuUtils.invalidChoice();
                    break;
            }
            if (subLoginChoice == 2) {
                break;
            }
        }
    }
}
