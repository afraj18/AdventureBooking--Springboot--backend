package com.example.AZServiceBookSystem.services.client;

import com.example.AZServiceBookSystem.dto.AdDetailsForClientDTO;
import com.example.AZServiceBookSystem.dto.AdDto;
import com.example.AZServiceBookSystem.dto.ReservationDTO;
import com.example.AZServiceBookSystem.dto.ReviewDTO;
import com.example.AZServiceBookSystem.entity.Ad;
import com.example.AZServiceBookSystem.entity.Reservation;
import com.example.AZServiceBookSystem.entity.Review;
import com.example.AZServiceBookSystem.entity.User;
import com.example.AZServiceBookSystem.enums.ReservationStatus;
import com.example.AZServiceBookSystem.enums.ReviewStatus;
import com.example.AZServiceBookSystem.repository.AdRepository;
import com.example.AZServiceBookSystem.repository.ReservationRepository;
import com.example.AZServiceBookSystem.repository.ReviewRepository;
import com.example.AZServiceBookSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdDto> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDto> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public boolean bookService(ReservationDTO reservationDTO){
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if(optionalAd.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);

             return true;
        }
        else{
            return false;
        }
    }

    public AdDetailsForClientDTO getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);

        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();

        if(optionalAd.isPresent()){
            adDetailsForClientDTO.setAdDto(optionalAd.get().getAdDto());

            List<Review> reviews = reviewRepository.findAllByAdId(adId);
            adDetailsForClientDTO.setReviewDTOList(reviews.stream().map(Review::getDto).collect(Collectors.toList()));
        }
        return adDetailsForClientDTO;
    }

    public List<ReservationDTO> getAllBookingsByUserId(Long id){
        return reservationRepository.findAllByUserId(id).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public boolean giveReview(ReviewDTO reviewDTO){
        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());
        Optional<Reservation> optionalReservation = reservationRepository.findById(reviewDTO.getBookId());

        if(optionalUser.isPresent() && optionalReservation.isPresent()){
            Review review = new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDTO.getReview());
            review.setRating(reviewDTO.getRating());

            review.setUser(optionalUser.get());
            review.setAd(optionalReservation.get().getAd());

            reviewRepository.save(review);

            Reservation booking = optionalReservation.get();
            booking.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(booking);

            return true;
        }
        return false;
    }
}
