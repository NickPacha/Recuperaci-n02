package org.example.reculp02.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    @Column(name = "IDMEDICO")
    private Long idMedico;

    @NotBlank(message = "El CMP no puede estar vacío")
    @Size(max = 9, message = "El CMP no puede tener más de 9 caracteres")
    @Column(name = "CMP", length = 9, nullable = false, unique = true)
    private String cmp;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 25, message = "El nombre no puede tener más de 25 caracteres")
    @Column(name = "NOMBRES", length = 25, nullable = false)
    private String nombres;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 25, message = "El apellido no puede tener más de 25 caracteres")
    @Column(name = "APELLIDOS", length = 25, nullable = false)
    private String apellidos;

    @NotNull(message = "La especialidad es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDESPECIALIDAD", nullable = false,
            foreignKey = @ForeignKey(name = "FK_MEDICO_ESPECIALIDAD"))
    private Especialidad especialidad;
}
