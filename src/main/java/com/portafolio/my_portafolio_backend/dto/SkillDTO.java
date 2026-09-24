package com.portafolio.my_portafolio_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {
    private Long id;

    @NotBlank(message = "El nombre de la habilidad no puede estar vacío")
    private String name; // Ej: "Java", "HTML", "CSS"

    @NotNull(message = "El porcentaje no puede ser nulo")
    @Min(value = 0, message = "El porcentaje debe ser igual o mayor a 0")
    @Max(value = 100, message = "El porcentaje debe ser igual o mayor a 100")
    private Integer levelPercentaje; // Ej: 90, 85 (para barras de progreso)

    @NotBlank(message = "La clase del icono no puede estar vacía")
    private String iconClass; // Ej: "fab fa-java" para Fontawesome

    @NotNull(message = "El ID de la informacion personal es obligatorio")
    private Long personalInfoId;  // Clave foranea a PersonalInfo
}
