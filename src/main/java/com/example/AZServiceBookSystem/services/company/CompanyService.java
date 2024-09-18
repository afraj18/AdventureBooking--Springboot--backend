package com.example.AZServiceBookSystem.services.company;

import com.example.AZServiceBookSystem.dto.AdDto;
import com.example.AZServiceBookSystem.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    boolean postAd(Long userId, AdDto adDto) throws IOException;
    List<AdDto> getAllAds(Long userId);

    AdDto getAdById(Long adId);

    boolean updateAd(long adId,AdDto adDto) throws IOException;
    boolean deleteAd(Long id);

    List<ReservationDTO> getAllAdBookings(Long id);

    boolean changeStatus(Long bookingId,String status);
}
