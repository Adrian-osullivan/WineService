package edu.tus.winemanager.controllers;


import java.util.List;
import java.util.Optional;

import edu.tus.winemanager.dto.Rating;
import edu.tus.winemanager.exceptions.BadRequestException;
import edu.tus.winemanager.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.Wine;

import javax.validation.Valid;


@RestController
@Service
public class WineService {
	
	@Autowired
	WineRepository wineRepo;

	@GetMapping("/wines")
	public List<Wine> getWines() { return wineRepo.findAll();}

	@RequestMapping("/wines/{id}")
	Optional<Wine> getWine(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Optional<Wine> getWine = wineRepo.findById(id);
		if (getWine.isPresent()) {
			return wineRepo.findById(id);
		}
		else
			throw new ResourceNotFoundException("Wine Not Found :: " + id);

	}
	@GetMapping("/wines/country/{country}")
	public List<Wine> getWinesForCountry(@PathVariable("country") String country ) {
		return wineRepo.findByCountry(country);}

	@PostMapping("/wines")
	public ResponseEntity createWine(@Valid @RequestBody Wine wine)  {

		// Validate Null Entries
		// name, year, grapes, country, region, rating, expiry_date
		//ValidateNullEntries(wine);

		wineRepo.save(wine);
		return ResponseEntity.status(HttpStatus.OK).body(wine);
	}

	private void ValidateNullEntries(Wine wine) throws BadRequestException {
		if (wine.getCountry() == null ||  wine.getExpiryDate() == null || wine.getGrapes() == null || wine.getName() == null
		|| wine.getRating() == null || wine.getRegion() == null) {
			//throw new BadRequestException("name, year, grapes, country, region, rating & expiry date must be populated");
		}
	}

	@PutMapping("/wines/{id}")
	public ResponseEntity updateWine(@PathVariable(value="id") Long wineId, @RequestBody Wine wine) throws ResourceNotFoundException, BadRequestException {

		// Validate Null Entries
		ValidateNullEntries(wine);


		// Save the account change
		Optional<Wine> saveWine = wineRepo.findById(wineId);
		if (saveWine.isPresent()) {
			// name, year, grapes, country, region, rating, expiry_date
			saveWine.get().setName(wine.getName());
			saveWine.get().setYear(wine.getYear());
			saveWine.get().setGrapes(wine.getGrapes());
			saveWine.get().setCountry(wine.getCountry());
			saveWine.get().setRegion(wine.getRegion());
			saveWine.get().setRating(wine.getRating());
			saveWine.get().setExpiryDate(wine.getExpiryDate());
			wineRepo.save(saveWine.get());
			return ResponseEntity.status(HttpStatus.OK).body(saveWine);
		}
		else
			throw new ResourceNotFoundException("Wine Not Found :: " + wineId);

	}

	@DeleteMapping ("/wines/{id}")
	public ResponseEntity deleteWine(@PathVariable(value="id") Long wineId) throws ResourceNotFoundException {
		Optional<Wine> deleteAccount = wineRepo.findById(wineId);

		// Check and see if the account exists
		if (deleteAccount.isPresent()) {
			// Delete the Wine
			wineRepo.deleteById(wineId);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else
			throw new ResourceNotFoundException("Wine Not Found :: " + wineId);
	}

	@GetMapping("/PagedWines")
	public Page<Wine> getWinesByPage(Pageable pageable) {

		Page<Wine> winePage =  wineRepo.findAll( pageable);
	return winePage;
	}
	@GetMapping("/wines/CountryAndRating")
	public List<Wine> getCountryAndRatingWines(@RequestParam String country, @RequestParam Rating rating ) {
		return wineRepo.findByCountryAndRating(country, rating);
	}


}
