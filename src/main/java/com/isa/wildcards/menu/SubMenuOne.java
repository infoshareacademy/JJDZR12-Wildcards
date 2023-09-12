package com.isa.wildcards.menu;

import com.isa.wildcards.searchengine.Search;
import com.isa.wildcards.searchhistory.UserHistory;

import java.util.Scanner;

public class SubMenuOne {
    public static void showMenu(Scanner scan) {
        UserHistory.initializeQueriesList();

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
                    Search.searchMovie(scan, true);
                    break;
                case 2:
                    UserHistory.show(scan);
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
