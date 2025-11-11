package org.example.reculp02.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.reculp02.dto.MedicoDTO;
import org.example.reculp02.entity.Medico;
import org.example.reculp02.mappers.MedicoMapper;
import org.example.reculp02.repository.MedicoRepository;
import org.example.reculp02.service.service.MedicoService;
import org.example.reculp02.service.service.ServiceException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repository;
    private final MedicoMapper mapper;

    @Override
    public MedicoDTO create(MedicoDTO dto) throws ServiceException {
        try {
            if (repository.existsByCmp(dto.getCmp())) {
                throw new ServiceException("Ya existe un médico con el CMP: " + dto.getCmp());
            }

            Medico entity = mapper.toEntity(dto);
            Medico saved = repository.save(entity);
            return mapper.toDTO(saved);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al crear médico", e);
        }
    }

    @Override
    public MedicoDTO read(Long id) throws ServiceException {
        try {
            Optional<Medico> optional = repository.findById(id);
            if (optional.isEmpty()) {
                throw new ServiceException("Médico no encontrado con ID: " + id);
            }
            return mapper.toDTO(optional.get());
        } catch (Exception e) {
            throw new ServiceException("Error al buscar médico por ID", e);
        }
    }

    @Override
    public MedicoDTO update(Long id, MedicoDTO dto) throws ServiceException {
        try {
            if (!repository.existsById(id)) {
                throw new ServiceException("Médico no encontrado con ID: " + id);
            }
            if (repository.existsByCmpAndIdMedicoNot(dto.getCmp(), id)) {
                throw new ServiceException("Ya existe otro médico con el CMP: " + dto.getCmp());
            }

            Medico entity = repository.findById(id).get();
            entity.setCmp(dto.getCmp());
            entity.setNombres(dto.getNombres());
            entity.setApellidos(dto.getApellidos());

            Medico updated = repository.save(entity);
            return mapper.toDTO(updated);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar médico", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!repository.existsById(id)) {
                throw new ServiceException("Médico no encontrado con ID: " + id);
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar médico", e);
        }
    }

    @Override
    public List<MedicoDTO> listAll() throws ServiceException {
        try {
            List<Medico> entities = repository.findAll();
            return mapper.toDTOs(entities);
        } catch (Exception e) {
            throw new ServiceException("Error al listar médicos", e);
        }
    }
}
