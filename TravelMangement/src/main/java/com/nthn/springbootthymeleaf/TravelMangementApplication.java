package com.nthn.springbootthymeleaf;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelMangementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelMangementApplication.class, args);

//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dzmam0pa9", // insert here you cloud name
//                "api_key", "928137927281665", // insert here your api code
//                "api_secret", "3Bbhy5bxDVkJ1H1A0-ZtjmE3nsM")); // insert here your api secret
//        SingletonManager manager = new SingletonManager();
//        manager.setCloudinary(cloudinary);
//        manager.init();

        System.out.println("TravelMangementApplication Run....");
    }

}
