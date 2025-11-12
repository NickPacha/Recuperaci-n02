package org.example.reculp02.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.reculp02.dto.MedicoDTO;
import org.example.reculp02.entity.Medico;
import org.example.reculp02.mappers.MedicoMapper;
import org.example.reculp02.mappers.EspecialidadMapper; // <--- Importar
import org.example.reculp02.repository.MedicoRepository;
import org.example.reculp02.service.service.MedicoService;
import org.example.reculp02.service.service.ServiceException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;
    private final EspecialidadMapper especialidadMapper; // <--- Inyectar

    @Override
    public MedicoDTO create(MedicoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new ServiceException("El DTO de médico no puede ser nulo.");
        }

        // Validar CMP (ahora es String)
        if (dto.getMedCmp() == null || dto.getMedCmp().trim().isEmpty()) {
            throw new ServiceException("El CMP del médico es obligatorio.");
        }

        // Validar unicidad del CMP
        Optional<Medico> existingByCmp = medicoRepository.findByMedCmp(dto.getMedCmp());
        if (existingByCmp.isPresent()) {
            throw new ServiceException("Ya existe un médico con el CMP: " + dto.getMedCmp());
        }

        // Validar especialidad (opcional pero recomendado)
        if (dto.getEspecialidad() == null) {
            throw new ServiceException("La especialidad es obligatoria.");
        }

        Medico entity = medicoMapper.toEntity(dto);
        Medico saved = medicoRepository.save(entity);
        return medicoMapper.toDTO(saved);
    }

    @Override
    public MedicoDTO read(Long id) throws ServiceException {
        Medico entity = medicoRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Médico no encontrado con ID: " + id));
        return medicoMapper.toDTO(entity);
    }

    @Override
    public MedicoDTO update(Long id, MedicoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new ServiceException("El DTO de médico no puede ser nulo.");
        }

        Medico existing = medicoRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Médico no encontrado con ID: " + id));

        // Validar CMP (ahora es String)
        if (dto.getMedCmp() == null || dto.getMedCmp().trim().isEmpty()) {
            throw new ServiceException("El CMP del médico es obligatorio.");
        }

        // Validar unicidad del CMP (excluyendo el propio registro)
        Optional<Medico> duplicateByCmp = medicoRepository.findByMedCmpAndIdMedicoNot(dto.getMedCmp(), id);
        if (duplicateByCmp.isPresent()) {
            throw new ServiceException("Ya existe otro médico con el CMP: " + dto.getMedCmp());
        }

        // Actualizar campos
        existing.setMedCmp(dto.getMedCmp()); // Asignar el String
        existing.setMedNombre(dto.getMedNombre());
        existing.setMedApellidos(dto.getMedApellidos());
        existing.setEspecialidad(especialidadMapper.toEntity(dto.getEspecialidad()));
        existing.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO");

        Medico updated = medicoRepository.save(existing);
        return medicoMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        Medico entity = medicoRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Médico no encontrado con ID: " + id));

        // Eliminación lógica: cambiar estado a INACTIVO
        entity.setEstado("INACTIVO");
        medicoRepository.save(entity);
    }

    @Override
    public List<MedicoDTO> listAll() throws ServiceException {
        return medicoMapper.toDTOs(medicoRepository.findAll());
    }
}