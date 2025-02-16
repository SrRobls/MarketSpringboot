package com.marketplace.apimarketplace.Service;

import com.marketplace.apimarketplace.Config.AwsS3Config;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final AwsS3Config awsS3Config;

    private final List<String> allowedContentTyes = List.of("image/png", "image/jpeg", "image/jpg");

    public String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("La imagen no puede estar vacia.");
        }

        if (!allowedContentTyes.contains(file.getContentType())) {
            throw new IllegalArgumentException("El formato de la imagen debe ser png, jpeg o jpg.");
        }

        //Generamos un UUIID para hacer generar un identificador universal y evitar nombre duplicados.
        String originalFilename = file.getOriginalFilename().replaceAll("\\s+", "_"); // Reemplaza espacios por guiones bajos
        String fileName = UUID.randomUUID() + "_" + originalFilename;

        try {
            s3Client.putObject(
                    PutObjectRequest
                            .builder()
                            .bucket(awsS3Config.getBucketName())
                            .key(fileName)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())

            );

            return "https://" + awsS3Config.getBucketName() + ".s3." + awsS3Config.getRegion() + ".amazonaws.com/" + fileName;

        } catch (IOException e){
            throw new RuntimeException("La imagen no puede estar vacia.", e);
        }
    }
}
