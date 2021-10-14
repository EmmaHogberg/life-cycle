package com.emma.life_cycle;

import java.util.UUID;

public class UniqueID {

    private static String password = "hemma";

    public static String getID(String name, String inputPassword) {

        String id = "";

        if (inputPassword.equals(password)) {
            id = name + UUID.randomUUID().toString();
        }
        return id;
    }
}
