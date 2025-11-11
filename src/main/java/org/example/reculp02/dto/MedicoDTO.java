package org.example.reculp02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicoDTO {
    private Long idMedico;
    private String cmp;
    private String nombres;
    private String apellidos;
    private Long idEspecialidad;      // Relación con Especialidad
    private String nombreEspecialidad; // Nombre descriptivo
}
