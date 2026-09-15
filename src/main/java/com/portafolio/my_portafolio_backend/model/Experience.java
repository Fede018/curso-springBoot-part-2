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

    @NotBlank(message = "El titulo no puede estar vacìo")
    private String jobTitle; //Ej: "Full Stack Developer"

    @NotBlank(message = "El nombre de la compañia no puede estar vacio")
    private String companyName;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inincio no puede ser futura")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    private LocalDate endDate; // Puede ser null si es el puesto actual

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description; // Responsabilidad y logros

    // La validaciòn de la clave foranea se maneja a nivel de servicio
    private Long personalInfoId; // Clave foranea a PersonalInfo
}
