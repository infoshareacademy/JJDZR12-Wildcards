package com.isa.wildcards.menu;


public class MenuUtils {


    public static void printOptionsMain() {
        System.out.println(Colors.cyan + "Main Menu:" + Colors.reset);
        System.out.println(Colors.cyan + "1. Search with login" + Colors.reset);
        System.out.println(Colors.cyan + "2. Search without login" + Colors.reset);
        System.out.println(Colors.cyan + "3. Create user" + Colors.reset);
        System.out.println(Colors.cyan + "4. Exit \n" + Colors.reset);

        System.out.println(Colors.green + "If you select search with login, you will be provided with these options: \n" +
                "- Search \n" +
                "- Search history \n" + Colors.reset);
        System.out.println(Colors.green + "If you select search without login, you will be provided with these options: \n" +
                "- Search \n" + Colors.reset);
        System.out.print(Colors.yellow + "Enter your choice (1-4): " + Colors.reset);
    }

    public static void printOptionsLoginMenu() {
        System.out.println(Colors.magenta + "Login Menu:" + Colors.reset);
        System.out.println(Colors.magenta + "1. Login" + Colors.reset);
        System.out.println(Colors.magenta + "2. Return to Main Menu \n" + Colors.reset);
        System.out.print(Colors.yellow + "Enter your choice (1-2): " + Colors.reset);
    }

    public static void printOptionsSubMenuTwo() {
        System.out.println(Colors.magenta + "Search without login" + Colors.reset);
        System.out.println(Colors.magenta + "1. Search" + Colors.reset);
        System.out.println(Colors.magenta + "2. Return to Main Menu \n" + Colors.reset);
        System.out.println(Colors.yellow + "Enter your choice (1-2): " + Colors.reset);
    }

    public static void printOptionsSubMenuOne() {
        System.out.println(Colors.magenta + "Search with login:" + Colors.reset);
        System.out.println(Colors.magenta + "1. Search" + Colors.reset);
        System.out.println(Colors.magenta + "2. Show search history" + Colors.reset);
        System.out.println(Colors.magenta + "3. Return to Login Menu \n" + Colors.reset);
        System.out.println(Colors.yellow + "Enter your choice (1-3): " + Colors.reset);
    }
    public static void invalidChoice() {
        System.out.println(Colors.red + "Invalid choice, please select again" + Colors.reset);
    }
    public static void wrongInput() {
        System.out.println(Colors.red + "Invalid input. You need to enter a number!" + Colors.reset);
    }
    public static void returning() {
        System.out.println(Colors.yellow + "Returning to Menu" + Colors.reset);
    }
}
