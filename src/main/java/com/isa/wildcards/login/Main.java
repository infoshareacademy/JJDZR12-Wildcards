package com.isa.wildcards.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.wildcards.user.User;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

//        String json = "{\n" +
//                "  \"users\": [\n" +
//                "    {\n" +
//                "      \"uuid\": \"5fb036c1-51ad-11ee-a50c-21a07fcf0a38\",\n" +
//                "      \"username\": \"Admin\",\n" +
//                "      \"password\": \"admin123!\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"uuid\": \"5fb036c0-51ad-11ee-a50c-21a07fcf0a38\",\n" +
//                "      \"username\": \"Test\",\n" +
//                "      \"password\": \"test123!\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        Gson gson = new Gson();
        Users users = gson.fromJson(new FileReader
                ("src/main/resources/users.json"), Users.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Optional<User> first = users.getUsers().stream().filter(user -> user.getUsername().equals(username)
                && user.getPassword().equals(password)).findFirst();

        if (first.isPresent()) {
            System.out.println("Welcome " + username);
        } else {
            boolean condition = true;
            while (condition) {
                System.out.println("Wrong user. Do you want to create a new user?(yes/no)");
                String ans = scanner.nextLine();
                if (ans.equals("yes")) {
                    System.out.println("create new user");
                    createNewUser();
                    break;
                } else {
                    condition = false;

                }
            }
        }
    }

    public static void createNewUser() throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username for new user:");
        String name = scanner.nextLine();
        System.out.println("Enter password for new user:");
        String password = scanner.nextLine();
        UUID id = UUID.randomUUID();
        User user = new User(id, name, password, null);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

//        String json = "{\n" +
//                "  \"users\": [\n" +
//                "    {\n" +
//                "      \"uuid\": \"5fb036c1-51ad-11ee-a50c-21a07fcf0a38\",\n" +
//                "      \"username\": \"Admin\",\n" +
//                "      \"password\": \"admin123!\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"uuid\": \"5fb036c0-51ad-11ee-a50c-21a07fcf0a38\",\n" +
//                "      \"username\": \"Test\",\n" +
//                "      \"password\": \"test123!\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        Users users = gson.fromJson(new FileReader("src/main/resources/users.json"), Users.class);

        users.getUsers().add(user);

        try(FileWriter writer = new FileWriter("src/main/resources/users.json")){

            gson.toJson(users,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }

//    public void writeUserToJson(User user) throws FileNotFoundException {
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try(FileWriter writer = new FileWriter("src\\main\\resources\\users.json")){
//            gson.toJson(user,writer);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    }



