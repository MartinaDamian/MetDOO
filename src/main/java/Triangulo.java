public class Triangulo extends Figura {
    private Double base;   // Key: "base"
    private Double altura; // Key: "altura"

    @Override
    public Double perimetro() {
        // Lo tomaremos como un triángulo equilátero
        return (base != null) ? base * 3 : 0.0;
    }

    @Override
    public Double area() {
        return (base != null && altura != null) ? (base * altura) / 2 : 0.0;
    }
}