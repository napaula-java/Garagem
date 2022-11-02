package com.apiex.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.apiex.parkingcontrol.models.ParkingSpot;
import com.apiex.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;
    
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
    	this.parkingSpotRepository = parkingSpotRepository;
    }

	
	@Transactional
	public Object save(ParkingSpot parkingSpot) {
		return parkingSpotRepository.save(parkingSpot);
	}
	
		public boolean existsByLicensePlateCar(String licensePlateCar) {
			return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
		}
		
			public boolean existsByApartment(String apartment) {
				return parkingSpotRepository.existsByApartment(apartment);
			}
			
				public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
					return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}

				public List<ParkingSpot> findAll() {
					return parkingSpotRepository.findAll();
				}

				public Optional<ParkingSpot> findById(UUID id) {
					return parkingSpotRepository.findById(id);
				}
                 
				@Transactional
				public void delete(ParkingSpot parkingSpot) {
					parkingSpotRepository.delete(parkingSpot);
				}
}
