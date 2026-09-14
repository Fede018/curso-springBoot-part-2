package com.portafolio.my_portafolio_backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id; //Clave primaria
    private String firstName;
    private String lastName;
    private String title; // Ejemplo: "Full Stack Developer"
    private String profileDescription; // El texto largo del "Who am I"
    private String profileImageUrl; //URL o ruta de la imagen
    private Integer yearsOfExperience;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String githubUrl;
}
