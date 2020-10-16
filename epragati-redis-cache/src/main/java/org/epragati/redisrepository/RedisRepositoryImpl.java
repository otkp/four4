package org.epragati.redisrepository;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.epragati.dto.Movie;
import org.epragati.dto.VehicleCov;
import org.epragati.dto.VehicleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author saikiran.kola
 *
 */

@Repository
public class RedisRepositoryImpl implements RedisRepository {
	private static final String VEHICLEKEY = "VEHICE";
	private static final String MOVIEKEY="MOVIE";
	private static final String AADHARKEY="AADHARRESPONSE";
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations hashOperations;

	@Autowired
	public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public void add(final Movie movie) {
		hashOperations.put(MOVIEKEY, movie.getId(), movie.getName());
	}

	public void delete(final String id) {
		hashOperations.delete(VEHICLEKEY, id);
	}

	public Movie findMovie(final String id) {
		return (Movie) hashOperations.get(MOVIEKEY, id);
	}

	public Map<Object, Object> findAllMovies() {
		return hashOperations.entries(MOVIEKEY);
	}

	@Override
	public void addVeh(String vehicleNo, VehicleDetails vehicle) {

		hashOperations.put(VEHICLEKEY, vehicleNo, vehicle.getVehiclecov());
	}

	@Override
	public Map<Object, Object> findAllVehicles() {
		// TODO Auto-generated method stub

		return hashOperations.entries(VEHICLEKEY);

	}

	@Override
	public ArrayList<VehicleCov> findVehicle(String VehicleNo) {
		ArrayList<VehicleCov> v=  (ArrayList<VehicleCov>) hashOperations.get(VEHICLEKEY, VehicleNo);
		return v;
		
	}

//	@Override
//	public ArrayList<AadhaarRequestVO> findAadharNumber(String aadharNumber) {
//		ArrayList<AadhaarRequestVO> v=  (ArrayList<AadhaarRequestVO>) hashOperations.get(AADHARKEY, aadharNumber);
//		return v;
//	}
//
//	@Override
//	public void addAadharResponseList(String aadharNumber, List<AadhaarRequestVO> vehicle) {
//		// TODO Auto-generated method stub
//		hashOperations.put(AADHARKEY,aadharNumber,vehicle);
//		
//	}

}
