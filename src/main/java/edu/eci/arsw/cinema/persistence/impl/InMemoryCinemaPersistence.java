/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author cristian
 */
@Component
@Qualifier("inMemoryCinemaPersistence")

public class InMemoryCinemaPersistence implements CinemaPersitence{

	private final Map<String,Cinema> cinemas=new HashMap<>();

	public InMemoryCinemaPersistence() {
		//load stub data
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions= new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c=new Cinema("cinemaX",functions);
		cinemas.put("cinemaX", c);
		functionDate = "2018-12-18 15:30";
		functions= new ArrayList<>();
		funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
		funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
		CinemaFunction funct3 = new CinemaFunction(new Movie("The Night 3","Horror"),functionDate);
		functions.add(funct1);
		functions.add(funct2);
		functions.add(funct3);
		Cinema d = new Cinema("Movies Medellin", functions);
		cinemas.put("Movies Medellin", d);
		functionDate = "2018-12-18 15:30";
		functions= new ArrayList<>();
		funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
		funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
		funct3 = new CinemaFunction(new Movie("The Night 3","Horror"),functionDate);
		functions.add(funct1);
		functions.add(funct2);
		functions.add(funct3);
		Cinema e=new Cinema("Movies Bogotá",functions);
		cinemas.put("Movies Bogotá", e);
	}    

	@Override
	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		Cinema cineElegido = cinemas.get(cinema);
		for (CinemaFunction i: cineElegido.getFunctions()) {
			if (i.getMovie().getName().equals(movieName) && i.getDate().equals(date)) {
				i.buyTicket(row, col);
			}
		}
	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException {
		if(cinemas.get(cinema) == null) {
			throw new CinemaPersistenceException("no existe el cinema");
		}
		String dateFormat = "\\d{4}-\\d{1,2}-\\d{1,2}";
		if(!Pattern.matches(dateFormat,date) ) {
			throw new CinemaPersistenceException("El formato de la fecha esta incorrecto debe ser YYYY-MM-DD");
		}
		Cinema cineElegido = cinemas.get(cinema);
		List<CinemaFunction> funcionesFecha = new ArrayList<CinemaFunction>();
		for(CinemaFunction i : cineElegido.getFunctions()) {
			if(i.getDate().contains(date)) {
				funcionesFecha.add(i);
			}
		}
		return funcionesFecha;
	}
	@Override
	public CinemaFunction getFunctionsbyCinemaAndDate(String cinema, String date, String movie) throws CinemaPersistenceException {
		if(cinemas.get(cinema) == null) {
			throw new CinemaPersistenceException("no existe el cinema");
		}
		String dateFormat = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
		if(!Pattern.matches(dateFormat,date) ) {
			throw new CinemaPersistenceException("El formato de la fecha esta incorrecto debe ser YYYY-MM-DD");
		}
		Cinema cineElegido = cinemas.get(cinema);
		CinemaFunction funcionesFecha = null;
		for(CinemaFunction i : cineElegido.getFunctions()) {
			if(i.getMovie().getName().equals(movie)){
				if(i.getDate().equals(date)) {
					funcionesFecha= i;
				}
			}
		}
		return funcionesFecha;
	}

	@Override
	public void saveCinema(Cinema c) throws CinemaPersistenceException {
		if (cinemas.containsKey(c.getName())){
			throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
		}
		else{
			cinemas.put(c.getName(),c);
		}   
	}

	@Override
	public Cinema getCinema(String name) throws CinemaPersistenceException {
		if(cinemas.get(name) == null) {
			throw new CinemaPersistenceException("no existe el cinema");
		}
		return cinemas.get(name);
	}
	@Override
	public Set<Cinema> getAllCinemas() throws CinemaException{
		Set<Cinema> todoCinemas = new HashSet<Cinema>(cinemas.values());
		if (todoCinemas.size()==0) {
			throw new CinemaException("No hay cinemas");
		}
		return todoCinemas;
	}
	@Override
	public void addFuncion(String cinema, String[] funcion) throws CinemaPersistenceException {
		System.out.println(cinema);
		System.out.println("llegue");
		if(cinemas.get(cinema) == null) {
			throw new CinemaPersistenceException("El cienema no exite");
		}
		cinemas.get(cinema).addFuncion(funcion);
	}
	@Override
	public void modfuncion(String cinema, String funcion, String newDate, String oldData) throws CinemaPersistenceException {
		if(cinemas.get(cinema) == null) {
			throw new CinemaPersistenceException("El cienema no exite");
		}
		cinemas.get(cinema).modFunction(funcion, newDate, oldData);
		//cinemas.get(cinema).modFuncion(funcion);
	}

	@Override
	public void eliminarFuncion(String cinema, String[] funciones) throws CinemaPersistenceException {
		System.out.println(cinema);
		if(cinemas.get(cinema) == null) {
			throw new CinemaPersistenceException("El cienema no exite");
		}
		cinemas.get(cinema).eliminarFuncion(funciones);
	}
}

