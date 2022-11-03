//package com.nthn.springbootthymeleaf.utils;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nthn.springbootthymeleaf.pojo.Google;
//import org.apache.http.client.fluent.Form;
//import org.apache.http.client.fluent.Request;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class GoogleUtils {
//    public static String GOOGLE_CLIENT_ID = "376000391844-mpik3hclk8dv6e8tte8rpdq5757r782s.apps.googleusercontent.com";
//    public static String GOOGLE_CLIENT_SECRET = "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";
//    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/Travel/login-google";
//    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
//    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
//    public static String GOOGLE_GRANT_TYPE = "authorization_code";
//
//    public String getToken(final String code) throws IOException {
//        String response = Request.Post(GOOGLE_LINK_GET_TOKEN).bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID).add("client_secret", GOOGLE_CLIENT_SECRET).add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code).add("grant_type", GOOGLE_GRANT_TYPE).build()).execute().returnContent().asString();
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode node = mapper.readTree(response).get("access_token");
//        return node.textValue();
//    }
//
//    public Google getUserInfo(final String accessToken) throws IOException {
//        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
//        String response = Request.Get(link).execute().returnContent().asString();
//        ObjectMapper mapper = new ObjectMapper();
//        Google googlePojo = mapper.readValue(response, Google.class);
//        System.out.println(googlePojo);
//        return googlePojo;
//    }
//
//    public UserDetails buildUser(Google google) {
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        List<GrantedAuthority> authorities = new ArrayList();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return new User(google.getEmail(), "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
//}
//
