package com.apiex.parkingcontrol.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apiex.parkingcontrol.models.ParkingSpot;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {

	boolean existsByLicensePlateCar(String licensePlateCar);
	boolean existsByApartment(String apartment);
	boolean existsByParkingSpotNumber(String apartment);
}
