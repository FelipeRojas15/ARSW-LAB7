package edu.eci.arsw.cinema.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;

@Service
public interface CinemaServicesInterface {

	List<CinemaFunction> filtroDisponibilida(String name, String fecha, String asientos) throws CinemaFilterException, CinemaPersistenceException;

	List<CinemaFunction> filtroGenero(String fecha, String cinema, String genero) throws CinemaFilterException, CinemaPersistenceException;

	List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException;

	void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException;

	Cinema getCinemaByName(String name) throws CinemaPersistenceException;

	Set<Cinema> getAllCinemas() throws CinemaException;

	CinemaFunction getFunctionsbyCinemaAndDate(String cinema, String date, String movie)throws CinemaPersistenceException;

	void addfuncion(String cinema, String[] funcion) throws CinemaPersistenceException;

	void modFuncion(String cinema, String funcion, String newDate, String oldDate) throws CinemaPersistenceException;

	void eliminarFuncion(String cinema, String[] funcion) throws CinemaPersistenceException; 

}
