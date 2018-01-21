/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.matteo.datamodel;

/**
 *
 * @author matteo.minardi
 */
public class Notebook implements Item {
    
    private String name;

    public Notebook(String name) {
        this.name = name;
    }   

    @Override
    public String printName() {
        return this.name;
    }

    @Override
    public void use() {
        System.out.println("Il notebook " + this.name + " viene usato");
    }
    
}
