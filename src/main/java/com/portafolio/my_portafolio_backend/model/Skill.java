package com.portafolio.my_portafolio_backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;
    private String name; // Ej: "Java", "HTML", "CSS"
    private Integer levelPercentaje; // Ej: 90, 85 (para barras de progreso)
    private String iconClass; // Ej: "fab fa-java" para Fontawesome
    private Long personalInfoId;  // Clave foranea a PersonalInfo
}
