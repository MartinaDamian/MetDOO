package org.arquitectura.figuras;

// org.arquitectura.figuras.Cuadrado.java
public class Cuadrado extends Figura {
    private Integer lado; // El nombre del atributo debe coincidir con la llave del Map

    @Override
    public Double perimetro() {
        return (lado != null) ? (double) (lado * 4) : 0.0;
    }

    @Override
    public Double area() {
        return (lado != null) ? (double) (lado * lado) : 0.0;
    }
}