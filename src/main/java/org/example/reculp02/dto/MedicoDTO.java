package org.example.reculp02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private Long idMedico;
    private String medCmp; // Cambiado a String
    private String medNombre;
    private String medApellidos;
    private EspecialidadDTO especialidad;
    private String estado;
}