package com.portafolio.my_portafolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id;

    private String jobTitle; //Ej: "Full Stack Developer"

    private String companyName;

    private LocalDate startDate;

    private LocalDate endDate; // Puede ser null si es el puesto actual

    private String description; // Responsabilidad y logros

    private Long personalInfoId; // Clave foranea a PersonalInfo
}
