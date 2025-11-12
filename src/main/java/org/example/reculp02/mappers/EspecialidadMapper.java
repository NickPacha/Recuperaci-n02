package org.example.reculp02.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.example.reculp02.dto.EspecialidadDTO;
import org.example.reculp02.entity.Especialidad;
import org.example.reculp02.mappers.base.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EspecialidadMapper implements BaseMapper<Especialidad, EspecialidadDTO> {

    @Override
    public EspecialidadDTO toDTO(Especialidad entity) {
        if (entity == null) return null;

        return new EspecialidadDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getEstado()
        );
    }

    @Override
    public Especialidad toEntity(EspecialidadDTO dto) {
        if (dto == null) return null;

        return Especialidad.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .estado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO")
                .build();
    }

    @Override
    public List<EspecialidadDTO> toDTOs(List<Especialidad> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Especialidad> toEntityList(List<EspecialidadDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}