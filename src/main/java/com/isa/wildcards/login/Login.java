package com.isa.wildcards.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.wildcards.user.User;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;


public class Login {
    public void logInToApp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username =scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        searchForUserInJsonFile(username,password);
    }
    public void searchForUserInJsonFile(String username, String password){
        String json = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"username\": \"Admin\",\n" +
                "      \"password\": \"admin123!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"username\": \"Test\",\n" +
                "      \"password\": \"test123!\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        Gson gson = new Gson();
        Users users = gson.fromJson(json,Users.class);
        System.out.println(users);
        System.out.println(users.getUsers().get(1).getUsername());
        Optional<User> first = users.getUsers().stream().filter(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password)).findFirst();
        //first.ifPresent(u -> System.out.println("test"));
    }

//    public void createNewUserInJsonFile(User user) throws IOException {
//
//        String filePath = "src/main/resources/users.json";
//        Path path = Paths.get(filePath);
//        UUID uuid = UUID.randomUUID();
//        user.setId(uuid);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        gson.toJson(user,new FileWriter(filePath));
//
//        }
//    }
}