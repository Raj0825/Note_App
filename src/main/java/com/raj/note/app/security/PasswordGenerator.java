package com.raj.note.app.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345"));
    }
}
//$2a$10$cBOBr0n4iK55mP/jRZERQeI9Xei28G1.moCaj.9H/wRV3eIMPmthW