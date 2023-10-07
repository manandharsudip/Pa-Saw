package com.cotiviti.Pasaw.functions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CustomFunction {

  public String uploadFunction(String uploadDir, MultipartFile catImage)
    throws IOException {
    String filename = catImage.getOriginalFilename();
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = catImage.getInputStream()) {
      Path filePath = uploadPath.resolve(filename);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      return filename;
    } catch (IOException ioe) {
      throw new IOException("Could not save image file: " + filename, ioe);
    }
  }
}
