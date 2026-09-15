package com.portafolio.my_portafolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    private Long id;

    @NotBlank(message = "El nombre del titulo no puede estar vacìo")
    private String degree; // Ej: "Ingenierìa en Sistemas"

    @NotBlank(message = "El nombre de la institucion no puede estar vacìo")
    private String institution; // Ej: "Universidad de Mar de Plata"

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDate startDate; //

    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    private LocalDate endDate; // Puede ser null si està en curso

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description; // Breve descripcion de logros o cursos

    // La validacion de la clave foranea se maneja a nivel de servicio
    private Long personalInfoId; // Clave forànea a PersonalInfo
}
