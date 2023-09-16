package com.isa.wildcards.menu;


public class MenuUtils {


    public static void printOptionsMain() {
        System.out.println(Color.CYAN.getCode() + "Main Menu:" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "1. Search with login" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "2. Search without login" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "3. Create user" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "4. Exit \n" + Color.RESET.getCode());

        System.out.println(Color.MAGENTA.getCode() + "If you select search with login, you will be provided with these options: \n" +
                "- Search \n" +
                "- Search history \n" + Color.RESET.getCode());
        System.out.println(Color.MAGENTA.getCode() + "If you select search without login, you will be provided with these options: \n" +
                "- Search \n" + Color.RESET.getCode());
        System.out.print(Color.YELLOW.getCode() + "Enter your choice (1-4): " + Color.RESET.getCode());
    }

    public static void printOptionsLoginMenu() {
        System.out.println(Color.CYAN.getCode() + "Login Menu:" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "1. Login" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "2. Return to Main Menu \n" + Color.RESET.getCode());
        System.out.print(Color.YELLOW.getCode() + "Enter your choice (1-2): " + Color.RESET.getCode());
    }

    public static void printOptionsSubMenuTwo() {
        System.out.println(Color.CYAN.getCode() + "Search without login" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "1. Search" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "2. Return to Main Menu \n" + Color.RESET.getCode());
        System.out.println(Color.YELLOW.getCode() + "Enter your choice (1-2): " + Color.RESET.getCode());
    }

    public static void printOptionsSubMenuOne() {
        System.out.println(Color.CYAN.getCode() + "Search with login:" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "1. Search" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "2. Show search history" + Color.RESET.getCode());
        System.out.println(Color.CYAN.getCode() + "3. Return to Login Menu \n" + Color.RESET.getCode());
        System.out.println(Color.YELLOW.getCode() + "Enter your choice (1-3): " + Color.RESET.getCode());
    }
    public static void invalidChoice() {
        System.out.println(Color.RED.getCode() + "Invalid choice, please select again" + Color.RESET.getCode());
    }
    public static void wrongInput() {
        System.out.println(Color.RED.getCode() + "Invalid input. You need to enter a number!" + Color.RESET.getCode());
    }
    public static void returning() {
        System.out.println(Color.YELLOW.getCode() + "Returning to Menu" + Color.RESET.getCode());
    }
    public static void closing() {
        System.out.println(Color.YELLOW.getCode() + "Closing application" + Color.RESET.getCode());
        System.exit(0);
        }
    }
