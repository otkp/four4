package org.epragati.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.epragati.aadhaar.AadhaarRequestVO;
import org.epragati.dto.GateWayResponse;
import org.epragati.dto.Movie;
import org.epragati.dto.VehicleCov;
import org.epragati.dto.VehicleDetails;
import org.epragati.redisrepository.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author saikiran.kola
 *
 */
@RestController
@RequestMapping("/redisController")
public class WebController {
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private RedisRepository redisRepository;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/findAllMovies")
	public @ResponseBody Map<Object, Object> keys() {
		return redisRepository.findAllMovies();
	}

	@RequestMapping("/values")
	public @ResponseBody Map<String, String> findAll() {
		Map<Object, Object> aa = redisRepository.findAllMovies();
		Map<String, String> map = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : aa.entrySet()) {
			String key = (String) entry.getKey();
			map.put(key, aa.get(key).toString());
		}
		return map;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> add(@RequestParam String key, @RequestParam String value) {

		Movie movie = new Movie(key, value);

		redisRepository.add(movie);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> delete(@RequestParam String key) {
		redisRepository.delete(key);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/addVehicle", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public GateWayResponse<?> addVehicle(@RequestParam(name = "vehicleNo", required = true) String VehicleNo,
			@RequestBody(required = true) VehicleDetails vehicle) {

		System.out.println("add vehicles");

		redisRepository.addVeh(VehicleNo, vehicle);
		return new GateWayResponse<>(HttpStatus.ACCEPTED);

	}

	@GetMapping(value = "findAllVehicles", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public GateWayResponse<?> findAllKeys() {
		Map<Object, Object> list = redisRepository.findAllVehicles();
		return new GateWayResponse<>(HttpStatus.ACCEPTED, list, "success");
	}

	@GetMapping(value = "findVehicleByKey")
	public GateWayResponse<?> findbasedOnVehicleNo(
			@RequestParam(name = "vehicleNo", required = true) String vehicleNo) {
		ArrayList<VehicleCov> vehicleDetails = new ArrayList<VehicleCov>();
		try {
			vehicleDetails = redisRepository.findVehicle(vehicleNo);
			if (vehicleDetails != null) {
				return new GateWayResponse<>(HttpStatus.ACCEPTED, vehicleDetails, "Success");
			}
		} catch (Exception e) {
			logger.info(" {}", e);
			return new GateWayResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "failed");
		}
		return new GateWayResponse<>(HttpStatus.NOT_FOUND, Collections.EMPTY_LIST, "No Data");

	}

	@GetMapping(value = "findAadharResponseKey")
	public GateWayResponse<?> findbasedOnAadharNumber(
			@RequestParam(name = "aadharNumber", required = true) String aadharNumber) {
		List<AadhaarRequestVO> aadharDetails = new ArrayList<AadhaarRequestVO>();
		try {
		//	aadharDetails = redisRepository.findAadharNumber(aadharNumber);
			if (aadharDetails != null) {
				return new GateWayResponse<>(HttpStatus.ACCEPTED, aadharDetails, "Success");
			}
		} catch (Exception e) {
			logger.info(" {}", e);
			return new GateWayResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "failed");
		}
		return new GateWayResponse<>(HttpStatus.NOT_FOUND, Collections.EMPTY_LIST, "No Data");

	}

	@RequestMapping(value = "/addAadharResponse", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public GateWayResponse<?> addAadharResponse(
			@RequestParam(name = "aadharNumber", required = true) String aadharNumber,
			@RequestBody(required = true) List<AadhaarRequestVO> adhaarResponseList) {
		try {
		//redisRepository.addAadharResponseList(aadharNumber, adhaarResponseList);
		}catch(Exception e)
		{
			return new GateWayResponse<>(HttpStatus.METHOD_FAILURE, "failure");

		}
		// return new ResponseEntity<List<AadhaarRequestVO>>(HttpStatus.OK);
		return new GateWayResponse<>(HttpStatus.OK, "sucess", "success");
	}

}
