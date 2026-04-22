package org.arquitectura.figuras;

public class Circulo extends Figura {
    private Double radio; // El nombre del parámetro en el Map será "radio"

    @Override
    public Double perimetro() {
        return (radio != null) ? 2 * Math.PI * radio : 0.0;
    }

    @Override
    public Double area() {
        return (radio != null) ? Math.PI * Math.pow(radio, 2) : 0.0;
    }
}