package com.cwms.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
@Service
public class ImageService {

	 public byte[] loadImage(String imagePath) throws IOException {
	        // Implement logic to read the image file as a byte array
	        Path filePath = Path.of(imagePath);
	        return Files.readAllBytes(filePath);
	    }
}
