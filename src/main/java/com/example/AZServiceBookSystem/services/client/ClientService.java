package com.example.AZServiceBookSystem.services.client;

import com.example.AZServiceBookSystem.dto.AdDetailsForClientDTO;
import com.example.AZServiceBookSystem.dto.AdDto;
import com.example.AZServiceBookSystem.dto.ReservationDTO;
import com.example.AZServiceBookSystem.dto.ReviewDTO;

import java.util.List;

public interface ClientService {
    List<AdDto> getAllAds();
    List<AdDto> searchAdByName(String name);

    boolean bookService(ReservationDTO reservationDTO);
    AdDetailsForClientDTO getAdDetailsByAdId(Long adId);
    List<ReservationDTO> getAllBookingsByUserId(Long id);

    boolean giveReview(ReviewDTO reviewDTO);
}
