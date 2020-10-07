/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaServicesInterface;

@RestController
@RequestMapping(value ="/cinema")
public class CinemaAPIController {
	@Autowired
	@Qualifier("cinemaServices")
	private CinemaServicesInterface css;


	@RequestMapping(method = RequestMethod.GET)

	public ResponseEntity<?> getAllCinemas()  {
		//obtener datos que se enviarán a través del API
		try {
			return new ResponseEntity<>(css.getAllCinemas(), HttpStatus.ACCEPTED);
		} catch (CinemaException e) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getCinema(@PathVariable String name){
		try {
			return new ResponseEntity<>(css.getCinemaByName(name), HttpStatus.ACCEPTED);
		} catch (CinemaPersistenceException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/{name}/{fecha}", method = RequestMethod.GET)
	public ResponseEntity<?> getFuncionsByDate(@PathVariable String name, @PathVariable String fecha){
		
		try {
			return new ResponseEntity<>(css.getFunctionsbyCinemaAndDate(name, fecha), HttpStatus.ACCEPTED);
		} catch (CinemaPersistenceException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/{name}/{fecha}/{movie}", method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByDate(@PathVariable String name, @PathVariable String fecha, @PathVariable String movie){
		try {
			
			return new ResponseEntity<>(css.getFunctionsbyCinemaAndDate(name, fecha, movie), HttpStatus.ACCEPTED);
		} catch (CinemaPersistenceException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{nombreCine}", method = RequestMethod.POST)
	public ResponseEntity<?> addFuncion(@PathVariable String nombreCine, @RequestBody String[] funciones){
		try {
			css.addfuncion(nombreCine, funciones);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/{nombreCinema}", method = RequestMethod.PUT)
	public ResponseEntity<?> modFuncion(@PathVariable String nombreCinema,  @RequestBody String[] funcion){
		try {
			css.modFuncion(nombreCinema, funcion[0], funcion[1], funcion[2]);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CinemaPersistenceException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/Eliminar/{nombreCinema}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarFuncion(@PathVariable String nombreCinema, @RequestBody String[] funcion) {
		try {
			css.eliminarFuncion(nombreCinema, funcion);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (CinemaPersistenceException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}

}


