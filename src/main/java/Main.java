import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        List<String> todasLasClases = new ArrayList<>();
        todasLasClases.add("Cuadrado");
        //todasLasClases.add("Circulo");

        //... todas las clases
        //Leer archivos de la carpeta de scrips y agregar al array
        try {
            for(String nombreClase : todasLasClases){
                //Verificar si es clase nativa
                Class clazz = Class.forName(nombreClase);
                //Arrancar motor de grovvy
                //Compilar clase en tiempo de ejecución
                Figura fg = (Figura) clazz.newInstance();
                fg.setDefaultParams();
                System.out.println(clazz.getName());
                System.out.println("P="+fg.perimetro());
                System.out.println("A="+fg.area());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
