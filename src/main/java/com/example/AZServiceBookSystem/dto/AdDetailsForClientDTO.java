package com.example.AZServiceBookSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDTO {

    private AdDto adDto;

    private List<ReviewDTO> reviewDTOList;

}
