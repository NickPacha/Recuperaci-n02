package org.example.reculp02.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "MEDICO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICO")
    private Long idMedico;

    @NotBlank(message = "El CMP del médico es obligatorio") // Cambiado a NotBlank
    @Column(name = "MED_CMP", nullable = false, unique = true, length = 20) // Añadido length
    private String medCmp; // Cambiado a String

    @NotBlank(message = "El nombre del médico no puede estar vacío")
    @Column(name = "MED_Nombre", nullable = false)
    private String medNombre;

    @NotBlank(message = "Los apellidos del médico no pueden estar vacíos")
    @Column(name = "MED_Apellidos", nullable = false)
    private String medApellidos;

    @NotNull(message = "La especialidad es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
    private Especialidad especialidad;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "ESTADO", nullable = false)
    private String estado = "ACTIVO";

    // Opcional: fecha de registro, etc.
    /*
    @Column(name = "FECHA_REGISTRO")
    private LocalDate fechaRegistro;
    */
}