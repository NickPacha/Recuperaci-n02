package org.example.reculp02.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.example.reculp02.dto.MedicoDTO;
import org.example.reculp02.entity.Medico;
import org.example.reculp02.mappers.base.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicoMapper implements BaseMapper<Medico, MedicoDTO> {

    private final EspecialidadMapper especialidadMapper;

    @Override
    public MedicoDTO toDTO(Medico entity) {
        if (entity == null) return null;

        Long idEspecialidad = null;
        String nombreEspecialidad = null;

        if (entity.getEspecialidad() != null) {
            idEspecialidad = entity.getEspecialidad().getId();
            nombreEspecialidad = entity.getEspecialidad().getNombre();
        }

        return new MedicoDTO(
                entity.getIdMedico(),
                entity.getMedCmp(),
                entity.getMedNombre(),
                entity.getMedApellidos(),
                new org.example.reculp02.dto.EspecialidadDTO(
                        idEspecialidad,
                        nombreEspecialidad,
                        null // Opcional: puedes incluir el estado de la especialidad si lo necesitas
                ),
                entity.getEstado()
        );
    }

    @Override
    public Medico toEntity(MedicoDTO dto) {
        if (dto == null) return null;

        return Medico.builder()
                .idMedico(dto.getIdMedico()) // Se puede incluir en actualizaciones
                .medCmp(dto.getMedCmp())
                .medNombre(dto.getMedNombre())
                .medApellidos(dto.getMedApellidos())
                .especialidad(especialidadMapper.toEntity(dto.getEspecialidad()))
                .estado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO")
                .build();
    }

    @Override
    public List<MedicoDTO> toDTOs(List<Medico> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Medico> toEntityList(List<MedicoDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}