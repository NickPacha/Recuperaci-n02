package org.example.reculp02.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "ESPECIALIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDESPECIALIDAD")
    private Long idEspecialidad;

    @NotBlank(message = "El nombre de la especialidad no puede estar vacío")
    @Size(max = 50, message = "El nombre de la especialidad no puede tener más de 50 caracteres")
    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;
}
