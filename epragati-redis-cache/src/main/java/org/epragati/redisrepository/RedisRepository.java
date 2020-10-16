package org.epragati.redisrepository;

import java.util.ArrayList;
import java.util.Map;

import org.epragati.dto.Movie;
import org.epragati.dto.VehicleCov;
import org.epragati.dto.VehicleDetails;
/**
 * 
 * @author saikiran.kola
 *
 */
public interface RedisRepository {

    /**
     * Return all movies
     */
    Map<Object, Object> findAllMovies();
    Map<Object,Object> findAllVehicles();

    /**
     * Add key-value pair to Redis.
     */
    void add(Movie movie);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String id);
    
    /**
     * find a movie
     */
    Movie findMovie(String id);
    
    void  addVeh(String vehicleNo,VehicleDetails vehicle);
    
    ArrayList<VehicleCov> findVehicle(String VehicleNo);
    
  //  ArrayList<AadhaarRequestVO> findAadharNumber(String aadharNumber);
   // void  addAadharResponseList(String aadharNumber,List<AadhaarRequestVO> adhaarResponseList);
    
    
    
}
