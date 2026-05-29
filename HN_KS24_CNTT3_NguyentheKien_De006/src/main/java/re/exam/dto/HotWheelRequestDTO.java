package re.exam.dto;

import lombok.Data;
import re.exam.enums.HotWheelStatus;

@Data
public class HotWheelRequestDTO {
    private String model;
    private String collection;
    private Integer year;
    private Double price;
    private HotWheelStatus condition;
}
