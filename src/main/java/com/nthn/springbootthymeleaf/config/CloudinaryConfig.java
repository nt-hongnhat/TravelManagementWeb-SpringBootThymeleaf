package com.nthn.springbootthymeleaf.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

  @Value("${cloud.}")
  @Bean
  public Cloudinary getCloudinary() {

    return new Cloudinary(
        ObjectUtils.asMap(
            "cloud-name",
            "dwqzj7jnw",
            "api-key",
            "554948713766324",
            "secret-key",
            "5zCpE_DYMlaBXOBy5cGyXh46dVg"));
  }
}
