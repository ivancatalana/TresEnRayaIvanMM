package com.example.tresenrayaivanmm.model;

public class Persona {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int partidasGanadas;

    public Persona(String name, int partidasGanadas){
        this.name=name;
        this.partidasGanadas=partidasGanadas;
    }
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int compareTo(Persona o2) {
        if(this.getPartidasGanadas()> o2.getPartidasGanadas()) return -1;
        else if (this.getPartidasGanadas() < o2.getPartidasGanadas()) return 1;
        else return 0;
    }
}
