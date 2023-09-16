package com.isa.wildcards.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.wildcards.menu.Color;
import com.isa.wildcards.menu.LoginMenu;
import com.isa.wildcards.menu.SubMenuOne;
import com.isa.wildcards.menu.SubMenuTwo;
import com.isa.wildcards.searchhistory.UserHistory;
import com.isa.wildcards.user.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Login {

    private static final String fileName = "src/main/resources/users.json";

    public Login() {

    }

    public User LogInToApp(Scanner scan) throws FileNotFoundException {

        Gson gson = new Gson();
        Users users = gson.fromJson(new FileReader(fileName), Users.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.CYAN.getCode() + "Enter your username: " + Color.RESET.getCode());
        String username = scanner.nextLine();
        System.out.println(Color.CYAN.getCode() + "Enter your password: " + Color.RESET.getCode());
        String password = scanner.nextLine();

        Optional<User> first = users.getUsers().stream().filter(user -> user.getUsername().equals(username)
                && user.getPassword().equals(password)).findFirst();

        if (first.isPresent()) {
            System.out.println(Color.CYAN.getCode() + "Welcome " + first.get().getUsername() + Color.RESET.getCode());
            User user = first.get();
            user.setSearchHistoryFileAfterGetExistUser(user.getUsername());
            return user;
            //TODO: menu dla zalogowanych uzytkownikow
        } else {
            System.out.println(Color.CYAN.getCode() + "Wrong user. Do you want to create a new user?(yes/no)" + Color.RESET.getCode());
            String ans = scanner.nextLine();
            if (ans.equals("yes")) {
                System.out.println(Color.CYAN.getCode() + "create new user" + Color.RESET.getCode());
                return createNewUser();
            }
            LoginMenu.showMenu(scan);
        }return null;
    }

    public static User createNewUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.CYAN.getCode() + "Enter username for new user:" + Color.RESET.getCode());
        String name = scanner.nextLine();
        System.out.println(Color.CYAN.getCode() + "Enter password for new user:" + Color.RESET.getCode());
        String password = scanner.nextLine();

        User user = new User(name, password);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Users users = null;
        try {
            users = gson.fromJson(new FileReader(fileName), Users.class);
        } catch (FileNotFoundException e) {
            System.out.println(Color.RED.getCode() + "Error: File not found" + Color.RESET.getCode());
        }
        users.getUsers().add(user);

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println(Color.RED.getCode() + "Error: File not found" + Color.RESET.getCode());
        }return user;
    }
}