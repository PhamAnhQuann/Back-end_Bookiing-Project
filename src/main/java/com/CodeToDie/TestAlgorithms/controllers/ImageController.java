package com.CodeToDie.TestAlgorithms.controllers;

import com.CodeToDie.TestAlgorithms.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;
    private static final String UPLOAD_DIR = "D:\\image_bookiing";
    @PostMapping("/save-image")
        public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return  new ResponseEntity<>("Please select a file to upload! ", HttpStatus.BAD_REQUEST);
        }

        try{
            File uploadDir = new File(UPLOAD_DIR);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            File destFile = new File(uploadDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            file.transferTo(destFile);

            System.out.println("File saved at: " + destFile.getAbsolutePath());
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>("Fail to upload file",HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> getImageByName(@PathVariable("imageName") String imageName){
        try {
            Path imagePath = Paths.get(UPLOAD_DIR).resolve(imageName);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Có thể thay đổi loại MIME tùy thuộc vào định dạng ảnh
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
