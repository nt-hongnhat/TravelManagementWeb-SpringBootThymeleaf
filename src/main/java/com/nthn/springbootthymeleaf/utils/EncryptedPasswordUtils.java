package com.nthn.springbootthymeleaf.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncryptedPasswordUtils {

  // Encrypt Password with BCryptPasswordEncoder
  public static String encryptPassword(String password) {
    final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(password);
  }
}
