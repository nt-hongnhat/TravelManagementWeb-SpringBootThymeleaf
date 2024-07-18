package com.nthn.springbootthymeleaf.service;

import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

  private final Cloudinary cloudinary;

  @SuppressWarnings("rawtypes")
  public Map upload(@NotNull MultipartFile file) throws IOException {
    return this.cloudinary.uploader().upload(file.getInputStream(), Map.of());
  }
}
