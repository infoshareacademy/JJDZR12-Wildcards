package com.isa.wildcards.menu;


public class MenuUtils {


    public static void printOptionsMain() {
        System.out.println(Colors.CYAN.getCode() + "Main Menu:" + Colors.RESET.getCode());
        System.out.println(Colors.CYAN.getCode() + "1. Search with login" + Colors.RESET.getCode());
        System.out.println(Colors.CYAN.getCode() + "2. Search without login" + Colors.RESET.getCode());
        System.out.println(Colors.CYAN.getCode() + "3. Create user" + Colors.RESET.getCode());
        System.out.println(Colors.CYAN.getCode() + "4. Exit \n" + Colors.RESET.getCode());

        System.out.println(Colors.GREEN.getCode() + "If you select search with login, you will be provided with these options: \n" +
                "- Search \n" +
                "- Search history \n" + Colors.RESET.getCode());
        System.out.println(Colors.GREEN.getCode() + "If you select search without login, you will be provided with these options: \n" +
                "- Search \n" + Colors.RESET.getCode());
        System.out.print(Colors.YELLOW.getCode() + "Enter your choice (1-4): " + Colors.RESET.getCode());
    }

    public static void printOptionsLoginMenu() {
        System.out.println(Colors.MAGENTA.getCode() + "Login Menu:" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "1. Login" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "2. Return to Main Menu \n" + Colors.RESET.getCode());
        System.out.print(Colors.YELLOW.getCode() + "Enter your choice (1-2): " + Colors.RESET.getCode());
    }

    public static void printOptionsSubMenuTwo() {
        System.out.println(Colors.MAGENTA.getCode() + "Search without login" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "1. Search" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "2. Return to Main Menu \n" + Colors.RESET.getCode());
        System.out.println(Colors.YELLOW.getCode() + "Enter your choice (1-2): " + Colors.RESET.getCode());
    }

    public static void printOptionsSubMenuOne() {
        System.out.println(Colors.MAGENTA.getCode() + "Search with login:" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "1. Search" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "2. Show search history" + Colors.RESET.getCode());
        System.out.println(Colors.MAGENTA.getCode() + "3. Return to Login Menu \n" + Colors.RESET.getCode());
        System.out.println(Colors.YELLOW.getCode() + "Enter your choice (1-3): " + Colors.RESET.getCode());
    }
    public static void invalidChoice() {
        System.out.println(Colors.RED.getCode() + "Invalid choice, please select again" + Colors.RESET.getCode());
    }
    public static void wrongInput() {
        System.out.println(Colors.RED.getCode() + "Invalid input. You need to enter a number!" + Colors.RESET.getCode());
    }
    public static void returning() {
        System.out.println(Colors.YELLOW.getCode() + "Returning to Menu" + Colors.RESET.getCode());
    }
}
