package com.marketplace.apimarketplace.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsS3Config {
    @Value("${AWS_S3_ACCESS_KEY}")
    private String awsS3AccessKey;
    @Value("${AWS_S3_SECRET_KEY}")
    private String awsS3SecretKey;
    @Value("${AWS_S3_REGION}")
    private String awsS3Region;
    @Value("${AWS_S3_BUCKET_NAME}")
    private String awsS3BucketName;


    //Construimos el cliente para hacer la conexión con S3, con las credenciales
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(awsS3Region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsS3AccessKey, awsS3SecretKey)))
                .build();
    }

    public String getBucketName() {
        return awsS3BucketName;
    }

    public String getRegion() {
        return awsS3Region;
    }

}
