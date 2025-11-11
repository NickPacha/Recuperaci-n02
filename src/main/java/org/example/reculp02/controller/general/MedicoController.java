package org.example.reculp02.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.example.reculp02.dto.MedicoDTO;
import org.example.reculp02.service.service.MedicoService;
import org.example.reculp02.service.service.ServiceException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/medicos")
@CrossOrigin(origins = "http://localhost:8080")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(medicoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> read(@PathVariable Long id) throws ServiceException {
        MedicoDTO dto = medicoService.read(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody MedicoDTO medicoDTO) throws ServiceException {
        MedicoDTO created = medicoService.create(medicoDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) throws ServiceException {
        MedicoDTO updated = medicoService.update(id, medicoDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
