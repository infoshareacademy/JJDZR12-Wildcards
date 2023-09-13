package com.isa.wildcards.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public void LogInToApp() throws FileNotFoundException {

        Gson gson = new Gson();
        Users users = gson.fromJson(new FileReader(fileName), Users.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Optional<User> first = users.getUsers().stream().filter(user -> user.getUsername().equals(username)
                && user.getPassword().equals(password)).findFirst();

        if (first.isPresent()) {
            System.out.println("Welcome " + first.get().getUsername());
            //TODO: menu dla zalogowanych uzytkownikow
        } else {
            System.out.println("Wrong user. Do you want to create a new user?(yes/no)");
            String ans = scanner.nextLine();
            if (ans.equals("yes")) {
                System.out.println("create new user");
                createNewUser();
            }
        }
    }

    public void createNewUser() throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username for new user:");
        String name = scanner.nextLine();
        System.out.println("Enter password for new user:");
        String password = scanner.nextLine();

        UUID id = UUID.randomUUID();
        User user = new User(id, name, password, null);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Users users = gson.fromJson(new FileReader(fileName), Users.class);
        users.getUsers().add(user);

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

