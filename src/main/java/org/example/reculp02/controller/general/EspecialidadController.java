package org.example.reculp02.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.reculp02.dto.EspecialidadDTO;
import org.example.reculp02.service.service.EspecialidadService;
import org.springframework.http.HttpStatus;
import org.example.reculp02.service.service.ServiceException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/especialidades")
@CrossOrigin(origins = "http://localhost:4200")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(especialidadService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> read(@PathVariable Long id) throws ServiceException {
        EspecialidadDTO dto = especialidadService.read(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EspecialidadDTO> create(@RequestBody EspecialidadDTO especialidadDTO) throws ServiceException {
        EspecialidadDTO created = especialidadService.create(especialidadDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> update(@PathVariable Long id, @RequestBody EspecialidadDTO especialidadDTO) throws ServiceException {
        EspecialidadDTO updated = especialidadService.update(id, especialidadDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        especialidadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}