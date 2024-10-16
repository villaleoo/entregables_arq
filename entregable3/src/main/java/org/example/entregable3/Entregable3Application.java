package org.example.entregable3;

import jakarta.annotation.PostConstruct;
import org.example.entregable3.utils.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Entregable3Application {

    @Autowired
    private Loader loadData;

    public static void main(String[] args) {
        SpringApplication.run(Entregable3Application.class, args);
    }
    @PostConstruct
    public void init() throws IOException {
        loadData.loadCarreras();
        loadData.loadEstudiantes();
        loadData.loadInscripciones();
    }

}
