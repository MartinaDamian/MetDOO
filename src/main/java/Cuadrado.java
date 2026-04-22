public class Cuadrado extends Figura{


    private int lado=0;

    @Override
    public Long perimetro() {
        return Long.valueOf(lado*4);
    }

    @Override
    public Long area() {
        return Long.valueOf(lado*lado);
    }

    @Override
    public void setDefaultParams() {
        lado = 4;
    }
}
