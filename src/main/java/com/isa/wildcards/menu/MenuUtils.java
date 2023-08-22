package com.isa.wildcards.menu;

public class MenuUtils {
    public static void printOptionsMain() {
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
    }

    public static void printOptionsLoginMenu() {
        System.out.println("Login Menu:");
        System.out.println("1. Login");
        System.out.println("2. Return to Main Menu \n");
        System.out.print("Enter your choice (1-2): ");
    }

    public static void printOptionsSubMenuTwo() {
        System.out.println("Search without login");
        System.out.println("1. Search");
        System.out.println("2. Return to Main Menu \n");
        System.out.println("Enter your choice (1-2): ");
    }

    public static void printOptionsSubMenuOne() {
        System.out.println("Search with login:");
        System.out.println("1. Search");
        System.out.println("2. Show search history");
        System.out.println("3. Return to Login Menu");
        System.out.println("Enter your choice (1-3): ");
    }

    public static void invalidChoice() {
        System.out.println("Invalid choice, please select again");
    }

    public static void wrongInput() {
        System.out.println("Invalid input. You need to enter a number!");
    }

    public static void returning() {
        System.out.println("Returning to Menu");
    }
}
