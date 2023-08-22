package com.isa.wildcards.menu;

import java.util.Scanner;

public class SubMenuTwo {
    public static void ShowMenu() {
        Scanner scan = new Scanner(System.in);
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
                    System.out.println("Type what do you want to search:");

                    //TODO implementacja metod do wyszukiwania podstawowego

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
