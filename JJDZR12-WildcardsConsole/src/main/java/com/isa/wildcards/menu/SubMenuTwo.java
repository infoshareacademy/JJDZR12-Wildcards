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
                    break;
                case 2:
                    MenuUtils.returning();
                    Menu.showMenu();
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
