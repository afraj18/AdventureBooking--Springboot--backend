package com.example.AZServiceBookSystem.dto;

import com.example.AZServiceBookSystem.enums.ReservationStatus;
import com.example.AZServiceBookSystem.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {
    private Long id;
    private Date bookDate;
    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private String serviceName;
    private Long userId;
    private String userName;
    private Long companyId;
    private Long adId;
}
