package com.CodeToDie.TestAlgorithms.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Getter
@Setter
public class ImageDTO {
    private String pathImage;
    private String name;
}
