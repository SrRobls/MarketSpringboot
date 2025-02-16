package com.marketplace.apimarketplace;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMarketPlaceApplication {

    public static void main(String[] args) {
        //Cargamos las variables de entorno
        //Dotenv dotenv = Dotenv.load();
        //System.setProperty("AWS_S3_BUCKET_NAME", dotenv.get("AWS_S3_BUCKET_NAME"));
        //System.setProperty("AWS_S3_ACCESS_KEY", dotenv.get("AWS_S3_ACCESS_KEY"));
        //System.setProperty("AWS_S3_SECRET_KEY", dotenv.get("AWS_S3_SECRET_KEY"));
        //System.setProperty("AWS_S3_REGION", dotenv.get("AWS_S3_REGION"));
        //System.setProperty("JWT_SECRET_KEY", dotenv.get("JWT_SECRET_KEY"));
        //System.setProperty("JWT_EXPIRATION", dotenv.get("JWT_EXPIRATION"));

        SpringApplication.run(ApiMarketPlaceApplication.class, args);
    }




}
