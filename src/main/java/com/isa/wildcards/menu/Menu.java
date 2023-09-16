package com.isa.wildcards.menu;

import com.isa.wildcards.login.Login;
import com.isa.wildcards.searchhistory.UserHistory;

import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        Scanner scan = new Scanner(System.in);

            while (true) {
                Logo.printLogo();
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
                        UserHistory.setValuesUserHistoryWriter(Login.createNewUser());
                        SubMenuOne.showMenu(scan);
                        break;
                    case 4:
                        MenuUtils.returning();
                        break;
                    default:
                        MenuUtils.invalidChoice();
                        break;
                }
                System.out.println("\n");
            return;
            }
        }
    }


