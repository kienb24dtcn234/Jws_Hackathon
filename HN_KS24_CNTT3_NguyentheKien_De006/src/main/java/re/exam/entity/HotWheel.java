package re.exam.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import re.exam.enums.HotWheelStatus;

@Entity
@Table(name = "HotWheel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotWheel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String collection;
    private Integer year;
    private Double price;

    @Enumerated(EnumType.STRING)
    private HotWheelStatus condition = HotWheelStatus.CARDED_MINT;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
