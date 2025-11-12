package org.example.reculp02.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.reculp02.dto.EspecialidadDTO;
import org.example.reculp02.entity.Especialidad;
import org.example.reculp02.mappers.EspecialidadMapper;
import org.example.reculp02.repository.EspecialidadRepository;
import org.example.reculp02.service.service.EspecialidadService;
import org.example.reculp02.service.service.ServiceException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    @Override
    public EspecialidadDTO create(EspecialidadDTO dto) throws ServiceException {
        if (dto == null) {
            throw new ServiceException("El DTO de especialidad no puede ser nulo.");
        }

        // Validar que el nombre no esté vacío
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new ServiceException("El nombre de la especialidad es obligatorio.");
        }

        // Validar unicidad del nombre
        Optional<Especialidad> existing = especialidadRepository.findByNombreIgnoreCase(dto.getNombre());
        if (existing.isPresent()) {
            throw new ServiceException("Ya existe una especialidad con el nombre: " + dto.getNombre());
        }

        Especialidad entity = especialidadMapper.toEntity(dto);
        Especialidad saved = especialidadRepository.save(entity);
        return especialidadMapper.toDTO(saved);
    }

    @Override
    public EspecialidadDTO read(Long id) throws ServiceException {
        Especialidad entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Especialidad no encontrada con ID: " + id));
        return especialidadMapper.toDTO(entity);
    }

    @Override
    public EspecialidadDTO update(Long id, EspecialidadDTO dto) throws ServiceException {
        if (dto == null) {
            throw new ServiceException("El DTO de especialidad no puede ser nulo.");
        }

        Especialidad existing = especialidadRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Especialidad no encontrada con ID: " + id));

        // Validar que el nombre no esté vacío
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new ServiceException("El nombre de la especialidad es obligatorio.");
        }

        // Validar unicidad del nombre (excluyendo el propio registro)
        Optional<Especialidad> duplicate = especialidadRepository.findByNombreIgnoreCaseAndIdNot(dto.getNombre(), id);
        if (duplicate.isPresent()) {
            throw new ServiceException("Ya existe otra especialidad con el nombre: " + dto.getNombre());
        }

        // Actualizar campos
        existing.setNombre(dto.getNombre());
        existing.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO");

        Especialidad updated = especialidadRepository.save(existing);
        return especialidadMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        Especialidad entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Especialidad no encontrada con ID: " + id));

        // Eliminación lógica: cambiar estado a INACTIVO
        entity.setEstado("INACTIVO");
        especialidadRepository.save(entity);
    }

    @Override
    public List<EspecialidadDTO> listAll() throws ServiceException {
        return especialidadMapper.toDTOs(especialidadRepository.findAll());
    }
}