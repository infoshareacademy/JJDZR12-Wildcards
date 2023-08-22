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
                    LoginMenu.loginMenu();
                    break;

                case 2:
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
                    break;
                case 3:
                    System.out.println("Creating new user");

                    //TODO implementacja metod do tworzenia uzytkownika nastepnie powrot do menu glownego

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

