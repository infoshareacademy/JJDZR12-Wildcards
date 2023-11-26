package com.isa.wildcards.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.wildcards.menu.*;
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
        //empty because of empty
    }

    public User LogInToApp(Scanner scan) throws FileNotFoundException {

        Gson gson = new Gson();
        Users users = gson.fromJson(new FileReader(fileName), Users.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.CYAN.getCode() + "Enter your username: " + Color.RESET.getCode());
        String username = scanner.nextLine();
        System.out.println(Color.CYAN.getCode() + "Enter your password: " + Color.RESET.getCode());
        String password = scanner.nextLine();

        Optional<User> first = users.getUsers()
                .stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();

        if (first.isPresent()) {
            System.out.println(Color.YELLOW.getCode() + "You logged in as " + first.get().getUsername() + Color.RESET.getCode());
            User user = first.get();
            user.setSearchHistoryFileAfterGetExistUser(user.getUsername());
            return user;
        } else {
            System.out.println(Color.CYAN.getCode() + "Wrong user. Do you want to create a new user? (Enter 'yes' to create new user)" + Color.RESET.getCode());
            String ans = scanner.nextLine();
            if (ans.equals("yes")) {
                System.out.println(Color.YELLOW.getCode() + "Creating new user" + Color.RESET.getCode());
                return createNewUser();
            }
            System.out.println(Color.YELLOW.getCode() + "Returning to menu" + Color.RESET.getCode());
            LoginMenu.showMenu(scan);
        }return null;
    }

    public static User createNewUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.CYAN.getCode() + "Enter new username:" + Color.RESET.getCode());
        String name = scanner.nextLine();
        System.out.println(Color.CYAN.getCode() + "Enter new password:" + Color.RESET.getCode());
        String password = scanner.nextLine();

        User user = new User(name, password);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Users users = null;
        try {
            users = gson.fromJson(new FileReader(fileName), Users.class);
        } catch (FileNotFoundException e) {
            System.out.println(Color.RED.getCode() + "Error: File not found" + Color.RESET.getCode());
        }
        Optional<User> sameLogin = users.getUsers()
                .stream()
                .filter(l -> l.getUsername().equals(name))
                .findFirst();

        if(sameLogin.isPresent()) {

            System.out.println(Color.YELLOW.getCode() + sameLogin.get().getUsername() + " already exists!" + Color.RESET.getCode());
            Menu.showMenu();
            return new User();
        } else {
            users.getUsers().add(user);
            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(users, writer);
            } catch (IOException e) {
                System.out.println(Color.RED.getCode() + "Error: File not found" + Color.RESET.getCode());
            }return user;
        }
    }
}