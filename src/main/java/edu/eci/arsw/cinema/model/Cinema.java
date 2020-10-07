/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import java.util.List;

/**
 *
 * @author cristian
 */
public class Cinema {
    private String name;
    private List<CinemaFunction> functions;
    private int funcionActual;
    
    
    public Cinema(){}
    
    public Cinema(String name,List<CinemaFunction> functions){

        funcionActual = 0;
        this.name=name;
        this.functions=functions;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CinemaFunction> getFunctions() {
        return this.functions;
    }
    public void addFuncion(String[] funcion) {
    	funcionActual++;
    	System.out.println("llegue");
    	Movie newPelicual = new Movie(funcion[0], funcion[1]);
    	CinemaFunction cinemaFunction = new CinemaFunction(newPelicual, funcion[2]);
        functions.add(cinemaFunction);
        
    }
    public void setFunction(CinemaFunction function){
    	for (CinemaFunction i : functions) {
    		if(function.getMovie().equals(i.getMovie())) {
    			
    		}
    	}
	}
    public void modFunction(String movie, String newDate, String oldData){
        for(int i =0;i<functions.size();i++){
            if(functions.get(i).getMovie().getName().equals(movie) && functions.get(i).getDate().equals(oldData)){
                functions.get(i).setDate(newDate);
            }
        }


    }
    public void setSchedule(List<CinemaFunction> functions) {
        this.functions = functions;
    }
    public void eliminarFuncion(String[] funciones) {
    	for(int i =0;i<functions.size();i++){
    		if(functions.get(i).getMovie().getName().equals(funciones[0]) && functions.get(i).getDate().equals(funciones[1])){
    			functions.remove(i);
    		}
    	}
    }
}
