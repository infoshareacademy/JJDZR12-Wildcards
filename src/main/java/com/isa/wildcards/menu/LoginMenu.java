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
                    break;
                case 2:
                    MenuUtils.returning();
                    Menu.showMenu();
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
