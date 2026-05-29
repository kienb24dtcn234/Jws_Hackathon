package re.exam.controller;

import re.exam.dto.HotWheelRequestDTO;
import re.exam.dto.HotWheelResponseDTO;
import re.exam.dto.HotWheelPatchDTO;
import re.exam.service.HotWheelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotwheels")
public class HotWheelController {

    private final HotWheelService service;

    public HotWheelController(HotWheelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HotWheelResponseDTO> create(@RequestBody HotWheelRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotWheelResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotWheelResponseDTO> update(@PathVariable Long id,
                                                        @RequestBody HotWheelRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HotWheelResponseDTO> patch(@PathVariable Long id,
                                                       @RequestBody HotWheelPatchDTO dto) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
