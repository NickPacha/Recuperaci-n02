package org.example.reculp02.mappers;

import org.springframework.stereotype.Component;
import org.example.reculp02.dto.MedicoDTO;
import org.example.reculp02.entity.Medico;
import org.example.reculp02.entity.Especialidad;
import org.example.reculp02.mappers.base.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicoMapper implements BaseMapper<Medico, MedicoDTO> {

    @Override
    public MedicoDTO toDTO(Medico entity) {
        if (entity == null) return null;
        return new MedicoDTO(
                entity.getIdMedico(),
                entity.getCmp(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getEspecialidad() != null ? entity.getEspecialidad().getIdEspecialidad() : null,
                entity.getEspecialidad() != null ? entity.getEspecialidad().getNombre() : null
        );
    }

    @Override
    public Medico toEntity(MedicoDTO dto) {
        if (dto == null) return null;
        Especialidad esp = new Especialidad();
        esp.setIdEspecialidad(dto.getIdEspecialidad());

        return Medico.builder()
                .idMedico(dto.getIdMedico())
                .cmp(dto.getCmp())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .especialidad(esp)
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
