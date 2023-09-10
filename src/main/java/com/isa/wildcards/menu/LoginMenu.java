package com.isa.wildcards.menu;

import com.isa.wildcards.searchhistory.UserHistory;
import com.isa.wildcards.user.User;

import java.io.IOException;
import java.util.Scanner;

public class LoginMenu {
    public static void showMenu(Scanner scan) throws IOException {

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

                    //TODO Zamienić var user na metodę logowania
                    User user = new User(1, "artemnizhnyk", "12345");
                    //TODO UserHistory.setValuesUserHistoryWriter musi wyłowywać się na koniec metody logowania
                    UserHistory.setValuesUserHistoryWriter(user);
                    SubMenuOne.showMenu(scan);
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
