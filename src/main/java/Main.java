import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import groovy.lang.GroovyClassLoader;

public class Main {

    public static void main(String[] args) {
        Map<String, Map<String, Object>> configuracionFiguras = new HashMap<>();

        // Datos para un Figuras
        //Cuadrado
        Map<String, Object> paramsCuadrado = new HashMap<>();
        paramsCuadrado.put("lado", 10); // "lado" coincide con el nombre de la variable en Cuadrado.java
        configuracionFiguras.put("Cuadrado", paramsCuadrado);
        //Circulo
        Map<String, Object> pCirculo = new HashMap<>();
        pCirculo.put("radio", 5.0);
        configuracionFiguras.put("Circulo", pCirculo);
        //Triangulo
        Map<String, Object> pTriangulo = new HashMap<>();
        pTriangulo.put("base", 10.0);
        pTriangulo.put("altura", 8.0);
        configuracionFiguras.put("Triangulo", pTriangulo);

        // 2. Figura Dinámica en Groovy
        String codigoPentagono = """
            public class Pentagono extends Figura {
                private Integer lado;
                private Double apotema;
                
                @Override 
                public Double perimetro() { 
                    return (lado != null) ? (double) (lado * 5) : 0.0; 
                }
                
                @Override 
                public Double area() { 
                    // Área = (Perímetro * Apotema) / 2
                    return (lado != null && apotema != null) ? (perimetro() * apotema) / 2 : 0.0;
                }
            }
            """;

        try {
            // Procesar figuras nativas
            for (Map.Entry<String, Map<String, Object>> entrada : configuracionFiguras.entrySet()) {
                procesarFigura(entrada.getKey(), entrada.getValue(), null);
            }

            // Procesar tu figura dinámica
            Map<String, Object> paramsPentagono = new HashMap<>();
            paramsPentagono.put("lado", 5);
            paramsPentagono.put("apotema", 3.44);
            procesarFigura("Pentagono", paramsPentagono, codigoPentagono);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void procesarFigura(String nombreClase, Map<String, Object> parametros, String codigoGroovy) throws Exception {
        Figura fg;

        if (codigoGroovy == null) {
            // Lógica de reflexión
            Class<?> clazz = Class.forName(nombreClase);
            fg = (Figura) clazz.getDeclaredConstructor().newInstance();
        } else {
            // Compilación dinámica
            try (GroovyClassLoader loader = new GroovyClassLoader()) {
                Class<?> clazz = loader.parseClass(codigoGroovy);
                fg = (Figura) clazz.getDeclaredConstructor().newInstance();
            }
        }

        // Mapeado de atributos
        mapearAtributos(fg, parametros);

        System.out.println("--- Figura: " + fg.getClass().getSimpleName() + " ---");
        System.out.println("Perímetro: " + fg.perimetro());
        System.out.println("Área: " + fg.area() + "\n");
    }

    private static void mapearAtributos(Object instancia, Map<String, Object> parametros) {
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            try {
                Field field = instancia.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(instancia, entry.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Advertencia: No se encontró el atributo '" + entry.getKey() + "'");
            }
        }
    }
}