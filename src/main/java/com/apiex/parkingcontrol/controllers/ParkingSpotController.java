package com.apiex.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
//import java.util.Optional;
//import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiex.parkingcontrol.dto.ParkingSpotdto;
import com.apiex.parkingcontrol.models.ParkingSpot;
import com.apiex.parkingcontrol.services.ParkingSpotService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parkingspot")
public class ParkingSpotController {

	final ParkingSpotService parkingSpotService;
	
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
		@PostMapping
		public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotdto parkingSpotdto){
			if(parkingSpotService.existsByLicensePlateCar(parkingSpotdto.getLicensePlateCar())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use");
			}
			
			if(parkingSpotService.existsByApartment(parkingSpotdto.getApartment())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartment is already in use");
			}
				if(parkingSpotService.existsByParkingSpotNumber(parkingSpotdto.getParkingSpotNumber())) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot number is already in use");
				}
				
		ParkingSpot parkingSpot = new ParkingSpot();
		BeanUtils.copyProperties(parkingSpotdto, parkingSpot);
		parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpot));
		
		}
	
		@GetMapping
		public ResponseEntity<List<ParkingSpot>> getAllParkingSpots(){
			return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
			
		}
		
//		@GetMapping(value = "/{id}")
//		public ResponseEntity<Object> getOneParkingSpot(@PathVariable("id") UUID id){
//			Optional<ParkingSpot> ParkingSpotOptional = parkingSpotService.findById(id);
//			if (!ParkingSpotOptional.isPresent()) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
//			} else {
//			return ResponseEntity.status(HttpStatus.OK).body(ParkingSpotOptional.get());
//		}
//		}
		
//		@DeleteMapping(value = "/{id}")
//		public ResponseEntity<Object> deleteParkingSpot(@PathVariable("id") UUID id) {
//			Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
//			if (!parkingSpotOptional.isPresent()) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
//		} else {
//			parkingSpotService.delete(parkingSpotOptional.get());
//			return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted sucessfully!");
//		}
}
		
//		 @PutMapping("/put/{id}")
//		    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
//		                                                    @RequestBody @Valid ParkingSpotdto parkingSpotdto){
//		        Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
//		        if (!parkingSpotOptional.isPresent()) {
//		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
//		        }
//		        var parkingSpot = new ParkingSpot();
//		        BeanUtils.copyProperties(parkingSpotdto, parkingSpot);
//		        parkingSpot.setId(parkingSpotOptional.get().getId());
//		        parkingSpot.setRegistrationDate(parkingSpotOptional.get().getRegistrationDate());
//		        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpot));
//		    }
//}