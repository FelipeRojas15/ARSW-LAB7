/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

/**
 *
 * @author cristian
 */
public class Movie {
    private String name;
    private String genre;
    
    public Movie(){}
    
    public Movie(String name, String genre){
        this.name=name;
        this.genre=genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
