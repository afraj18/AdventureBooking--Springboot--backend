package com.example.AZServiceBookSystem.services.company;

import com.example.AZServiceBookSystem.dto.AdDto;
import com.example.AZServiceBookSystem.dto.ReservationDTO;
import com.example.AZServiceBookSystem.entity.Ad;
import com.example.AZServiceBookSystem.entity.Reservation;
import com.example.AZServiceBookSystem.entity.User;
import com.example.AZServiceBookSystem.enums.ReservationStatus;
import com.example.AZServiceBookSystem.repository.AdRepository;
import com.example.AZServiceBookSystem.repository.ReservationRepository;
import com.example.AZServiceBookSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad = new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setPrice(adDto.getPrice());
            ad.setImg(adDto.getImg().getBytes());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public List<AdDto> getAllAds(Long userId){
        return adRepository.findAdByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public AdDto getAdById(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);

        if(optionalAd.isPresent()){
            return optionalAd.get().getAdDto();
        }
        return null;
    }

    public boolean updateAd(long adId,AdDto adDto) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            Ad ad = optionalAd.get();

            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setPrice(adDto.getPrice());

            if(adDto.getImg()!=null){
                ad.setImg(adDto.getImg().getBytes());
            }

            adRepository.save(ad);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteAd(Long id){
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()){
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long id){
        return reservationRepository.findAllByCompanyId(id).stream()
                .map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public boolean changeStatus(Long bookingId,String status){
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if(optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status,"Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }
            else{
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }
}
