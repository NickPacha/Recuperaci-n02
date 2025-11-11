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

    private final EspecialidadRepository repository;
    private final EspecialidadMapper mapper;

    @Override
    public EspecialidadDTO create(EspecialidadDTO dto) throws ServiceException {
        try {
            if (repository.existsByNombre(dto.getNombre())) {
                throw new ServiceException("Ya existe una especialidad con el nombre: " + dto.getNombre());
            }

            Especialidad entity = mapper.toEntity(dto);
            Especialidad saved = repository.save(entity);
            return mapper.toDTO(saved);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al crear especialidad", e);
        }
    }

    @Override
    public EspecialidadDTO read(Long id) throws ServiceException {
        try {
            Optional<Especialidad> optional = repository.findById(id);
            if (optional.isEmpty()) {
                throw new ServiceException("Especialidad no encontrada con ID: " + id);
            }
            return mapper.toDTO(optional.get());
        } catch (Exception e) {
            throw new ServiceException("Error al buscar especialidad por ID", e);
        }
    }

    @Override
    public EspecialidadDTO update(Long id, EspecialidadDTO dto) throws ServiceException {
        try {
            if (!repository.existsById(id)) {
                throw new ServiceException("Especialidad no encontrada con ID: " + id);
            }
            if (repository.existsByNombreAndIdEspecialidadNot(dto.getNombre(), id)) {
                throw new ServiceException("Ya existe otra especialidad con el nombre: " + dto.getNombre());
            }

            Especialidad entity = repository.findById(id).get();
            entity.setNombre(dto.getNombre());

            Especialidad updated = repository.save(entity);
            return mapper.toDTO(updated);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar especialidad", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!repository.existsById(id)) {
                throw new ServiceException("Especialidad no encontrada con ID: " + id);
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar especialidad", e);
        }
    }

    @Override
    public List<EspecialidadDTO> listAll() throws ServiceException {
        try {
            List<Especialidad> entities = repository.findAll();
            return mapper.toDTOs(entities);
        } catch (Exception e) {
            throw new ServiceException("Error al listar especialidades", e);
        }
    }
}
