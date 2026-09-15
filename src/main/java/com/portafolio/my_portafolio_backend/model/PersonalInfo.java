package com.portafolio.my_portafolio_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id; //Clave primaria

    @NotBlank(message = "El nombre no puede estar vacìo")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;

    @NotBlank(message = "El titulo no puede estar vacío")
    private String title; // Ejemplo: "Full Stack Developer"

    @NotBlank(message = "La descripcion del perfil no puede estar vacía")
    private String profileDescription; // El texto largo del "Who am I"

    @NotBlank(message = "La imagen no puede estar vacía")
    private String profileImageUrl; //URL o ruta de la imagen

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;

    @Email(message = "El email no es valido")
    private String email;

    @NotBlank(message = "El telefono no puede estar vacío")
    private String phone;

    @URL(message = "Linkedin es una red obligatoria")
    private String linkedinUrl;

    @URL(message = "Github es una red obligatoria")
    private String githubUrl;
}
