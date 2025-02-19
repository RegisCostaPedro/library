package com.ms.library.controllers;

import com.ms.library.dtos.ReservationRecordDto;
import com.ms.library.models.ReservationModel;
import com.ms.library.services.ReservationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationModel> createReservation(@RequestBody ReservationRecordDto reservationRecordDto) {

        ReservationModel reservationModel = new ReservationModel();
        BeanUtils.copyProperties(reservationRecordDto, reservationModel);

        return new ResponseEntity<>(reservationService.saveReservation(reservationModel), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ReservationModel>> findAllReservation() {
        return new ResponseEntity<>(reservationService.findAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationModel> findReservationById(@PathVariable UUID id) {
        return new ResponseEntity<>(reservationService.findReservationById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReservationModel> updateReservation(@RequestBody ReservationRecordDto reservationRecordDto,
                                                              @RequestParam UUID loanId,
                                                              @PathVariable UUID id) {
        ReservationModel reservationModel = new ReservationModel();
        BeanUtils.copyProperties(reservationRecordDto, reservationModel);

        return new ResponseEntity<>(reservationService.updateReservation(reservationModel, id,loanId), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteReservationById(@PathVariable UUID id) {
        reservationService.deleteReservation(id);
    }
}
