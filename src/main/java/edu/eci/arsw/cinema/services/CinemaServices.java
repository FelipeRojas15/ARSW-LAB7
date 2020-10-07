/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaFilter;
import edu.eci.arsw.cinema.persistence.CinemaFilterException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.CinemaServicesInterface;

/**
 *
 * @author cristian
 */
@Service("cinemaServices")
public class CinemaServices implements CinemaServicesInterface {
    @Autowired
    @Qualifier("inMemoryCinemaPersistence")
    
    CinemaPersitence cps ;
    
    @Autowired
    @Qualifier("cinemaFilterGenero")
    
    CinemaFilter cfg;
    
    @Autowired
    @Qualifier("cinemaFilterDisponibilidad")
    
    CinemaFilter cfd;
    @Override
    public Set<Cinema> getAllCinemas() throws CinemaException{
        return cps.getAllCinemas();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    @Override
    public Cinema getCinemaByName(String name) throws CinemaPersistenceException{
    	Cinema resultado= null;	
        resultado = cps.getCinema(name);
        return resultado;
    }
    
    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{
        cps.buyTicket(row, col, cinema, date, movieName);
    }
    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException {
        return cps.getFunctionsbyCinemaAndDate(cinema, date);
    }
    @Override
    public CinemaFunction getFunctionsbyCinemaAndDate(String cinema, String date, String movie) throws CinemaPersistenceException {
        return cps.getFunctionsbyCinemaAndDate(cinema, date, movie);
    }
    @Override
    public List<CinemaFunction> filtroGenero(String fecha, String cinema, String genero) throws CinemaFilterException, CinemaPersistenceException {
    	List<CinemaFunction> funciones = getFunctionsbyCinemaAndDate(cinema, fecha);
		return cfg.filtros(funciones, genero);
	}
    @Override
    public List<CinemaFunction> filtroDisponibilida(String name, String fecha, String asientos) throws CinemaFilterException, CinemaPersistenceException{
    	List<CinemaFunction> funciones = getFunctionsbyCinemaAndDate(name, fecha);
    	return cfd.filtros(funciones, asientos);
    }
	@Override
	public void addfuncion(String cinema, String[] funcion) throws CinemaPersistenceException {
		cps.addFuncion(cinema, funcion);
	}
	@Override
	public void modFuncion(String cinema, String funcion, String newDate, String oldDate) throws CinemaPersistenceException {
		cps.modfuncion(cinema, funcion, newDate, oldDate);
	}
	@Override
	public void eliminarFuncion(String cinema, String[] funcion) throws CinemaPersistenceException {
		cps.eliminarFuncion(cinema, funcion);
		
	}
	
	
}
