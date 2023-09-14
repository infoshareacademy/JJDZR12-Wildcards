package com.isa.wildcards.menu;

import com.isa.wildcards.login.Login;
import com.isa.wildcards.searchhistory.UserHistory;
import com.isa.wildcards.user.User;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginMenu {
    public static void showMenu(Scanner scan) {

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
                    Login login = new Login();
                    try {
                        UserHistory.setValuesUserHistoryWriter(login.LogInToApp(scan));
                        SubMenuOne.showMenu(scan);
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found");
                    }

                    //TODO implementacja metod do logowania do systemu następnie przejście do reszty menu
                    //TODO cały kod włącznie z SOUT-em w tym case wyciągniety do zewnętrznej metody

                    //TODO Zamienić var user na metodę logowania

                    //TODO UserHistory.setValuesUserHistoryWriter musi wyłowywać się na koniec metody logowania

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
