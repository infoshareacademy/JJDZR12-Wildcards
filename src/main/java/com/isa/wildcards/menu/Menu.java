package com.isa.wildcards.menu;

import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        Scanner scanMain = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Search with login");
            System.out.println("2. Search without login");
            System.out.println("3. Create user");
            System.out.println("4. Exit \n");

            System.out.println("If you select search with login, you will be provided with these options: \n" +
                    "- Search \n" +
                    "- Search history \n");
            System.out.println("If you select search without login, you will be provided with these options: \n" +
                    "- Search \n");
            System.out.print("Enter your choice (1-4): ");

            if (!scanMain.hasNextInt()) {
                System.out.println("Invalid input. You need to enter a number!");
                scanMain.nextLine();
                continue;
            }

            int input = scanMain.nextInt();
            scanMain.nextLine();

            switch (input) {
                case 1:
                    System.out.println("Search with login.");

                    break;
                case 2:
                    System.out.println("Search without login");

                    break;
                case 3:
                    System.out.println("Creating new user");

                    break;
                case 4:
                    System.out.println("Exiting the menu");
                    scanMain.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }

            scanMain.close();
        }
        }

    }

