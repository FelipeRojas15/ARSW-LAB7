package edu.eci.arsw.cinema.filter;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaFilter;
import edu.eci.arsw.cinema.persistence.CinemaFilterException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cinemaFilterGenero")

public class CinemaFilterGenero implements CinemaFilter {
	
	@Override
	public List<CinemaFunction> filtros(List<CinemaFunction> funciones, String genero) throws CinemaFilterException{
		List<CinemaFunction> resultado = new ArrayList<CinemaFunction>();
		for(CinemaFunction i: funciones) {
			if(i.getMovie().getGenre().equals(genero)) {
				resultado.add(i);
			}
		}
		if(resultado.size()==0)throw new CinemaFilterException("No hay funciones para ese dia con ese genero");
		return resultado;
	}
}
