package re.exam.dto;

import re.exam.enums.HotWheelStatus;
import lombok.Data;

@Data
public class HotWheelResponseDTO {
    private Long id;
    private String model;
    private String collection;
    private Integer year;
    private Double price;
    private HotWheelStatus condition;
}
