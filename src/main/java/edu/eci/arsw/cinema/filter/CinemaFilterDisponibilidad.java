package edu.eci.arsw.cinema.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaFilter;
import edu.eci.arsw.cinema.persistence.CinemaFilterException;

@Component
@Qualifier("cinemaFilterDisponibilidad")

public class CinemaFilterDisponibilidad implements CinemaFilter {
	@Override
	public List<CinemaFunction> filtros(List<CinemaFunction> funciones, String filtro ) throws CinemaFilterException{
		int asientosRequeridos;
		try {
			asientosRequeridos = Integer.parseInt(filtro);
		}catch (Exception e) {
			throw new CinemaFilterException("El numero de asientos tiene que ser un numero");
		}
		List<CinemaFunction> resultado = new ArrayList<CinemaFunction>();
		for(CinemaFunction i : funciones) {
			int asientosFuncion=0; 
			for(int j=0; j < i.getSeats().size(); j++) {
				for (int k = 0; k < i.getSeats().get(j).size(); k++) {
					if (i.getSeats().get(j).get(k)) {
						asientosFuncion++;
					}
				}
			}
			if(asientosFuncion > asientosRequeridos) {
				resultado.add(i);
			}
		}
		return resultado;
	}
}
