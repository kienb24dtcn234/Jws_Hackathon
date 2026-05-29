package re.exam.service;

import re.exam.dto.HotWheelPatchDTO;
import re.exam.dto.HotWheelRequestDTO;
import re.exam.dto.HotWheelResponseDTO;
import re.exam.entity.HotWheel;
import re.exam.enums.HotWheelStatus;
import re.exam.repository.HotWheelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotWheelService {

    private final HotWheelRepository repository;

    public HotWheelService(HotWheelRepository repository) {
        this.repository = repository;
    }

    public HotWheelResponseDTO create(HotWheelRequestDTO dto) {
        HotWheel hotWheel = new HotWheel();
        hotWheel.setModel(dto.getModel());
        hotWheel.setCollection(dto.getCollection());
        hotWheel.setYear(dto.getYear());
        hotWheel.setPrice(dto.getPrice());
        hotWheel.setCondition(dto.getCondition() != null ? dto.getCondition() : HotWheelStatus.CARDED_MINT);
        hotWheel.setIsDeleted(false);
        return toResponse(repository.save(hotWheel));
    }

    public List<HotWheelResponseDTO> getAll() {
        return repository.findAll().stream()
                .filter(hw -> !hw.getIsDeleted())
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public HotWheelResponseDTO update(Long id, HotWheelRequestDTO dto) {
        HotWheel hotWheel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        hotWheel.setModel(dto.getModel());
        hotWheel.setCollection(dto.getCollection());
        hotWheel.setYear(dto.getYear());
        hotWheel.setPrice(dto.getPrice());
        if (dto.getCondition() != null) hotWheel.setCondition(dto.getCondition());
        return toResponse(repository.save(hotWheel));
    }

    public HotWheelResponseDTO patch(Long id, HotWheelPatchDTO dto) {
        HotWheel hotWheel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        if (dto.getModel() != null) hotWheel.setModel(dto.getModel());
        if (dto.getCollection() != null) hotWheel.setCollection(dto.getCollection());
        if (dto.getYear() != null) hotWheel.setYear(dto.getYear());
        if (dto.getPrice() != null) hotWheel.setPrice(dto.getPrice());
        if (dto.getCondition() != null) hotWheel.setCondition(dto.getCondition());
        return toResponse(repository.save(hotWheel));
    }

    public void delete(Long id) {
        HotWheel hotWheel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        hotWheel.setIsDeleted(true);
        repository.save(hotWheel);
    }

    private HotWheelResponseDTO toResponse(HotWheel hotWheel) {
        HotWheelResponseDTO dto = new HotWheelResponseDTO();
        dto.setId(hotWheel.getId());
        dto.setModel(hotWheel.getModel());
        dto.setCollection(hotWheel.getCollection());
        dto.setYear(hotWheel.getYear());
        dto.setPrice(hotWheel.getPrice());
        dto.setCondition(hotWheel.getCondition());
        return dto;
    }
}

