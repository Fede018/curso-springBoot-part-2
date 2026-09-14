package com.portafolio.my_portafolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private Long id;
    private String degree; // Ej: "Ingenierìa en Sistemas"
    private String institution; // Ej: "Universidad de Mar de Plata"
    private LocalDate startDate; //
    private LocalDate endDate; // Puede ser null si està en curso
    private String description; // Breve descripcion de logros o cursos
    private Long personalInfoId; // Clave forànea a PersonalInfo
}
