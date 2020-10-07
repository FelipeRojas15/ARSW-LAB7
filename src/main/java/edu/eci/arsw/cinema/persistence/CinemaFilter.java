package edu.eci.arsw.cinema.persistence;

import java.util.List;

import edu.eci.arsw.cinema.model.CinemaFunction;

public interface CinemaFilter {

	List<CinemaFunction> filtros(List<CinemaFunction> funciones, String filtro) throws CinemaFilterException;
	

}
