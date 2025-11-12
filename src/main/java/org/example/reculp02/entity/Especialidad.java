package org.example.reculp02.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "ID_ESPECIALIDAD")
    private Long id;

    @NotBlank(message = "El nombre de la especialidad no puede estar vacío")
    @Column(name = "ESPE_NOMBRE", nullable = false)
    private String nombre;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "ESTADO", nullable = false)
    private String estado = "ACTIVO"; // valor por defecto, puede ser ACTIVO o INACTIVO
}