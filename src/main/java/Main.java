import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // 1. Definimos qué clases queremos y qué valores (Parámetros Dinámicos)
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

        // 2. Procesamos cada clase usando Reflexión
        try {
            for (Map.Entry<String, Map<String, Object>> entrada : configuracionFiguras.entrySet()) {
                String nombreClase = entrada.getKey();
                Map<String, Object> parametros = entrada.getValue();

                // Obtener la clase
                Class<?> clazz = Class.forName(nombreClase);

                // INSTANCIACIÓN DINÁMICA (Usando getConstructor)
                // Esto es más robusto que newInstance()
                Figura fg = (Figura) clazz.getDeclaredConstructor().newInstance();

                // MAPEADO DE ATRIBUTOS mediante Reflexión
                mapearAtributos(fg, parametros);

                // Resultados
                System.out.println("--- Figura: " + clazz.getSimpleName() + " ---");
                System.out.println("Parámetro recibido: " + parametros);
                System.out.println("Perímetro: " + fg.perimetro());
                System.out.println("Área: " + fg.area() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Metodo que busca atributos en la clase y les asigna el valor del Map*/
    private static void mapearAtributos(Object instancia, Map<String, Object> parametros) {
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            try {
                // Buscamos el campo (atributo) por nombre
                Field field = instancia.getClass().getDeclaredField(entry.getKey());

                // Como el atributo es privado, forzamos el acceso
                field.setAccessible(true);

                // Seteamos el valor en la instancia
                field.set(instancia, entry.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Advertencia: No se encontró el atributo '" + entry.getKey() + "'");
            }
        }
    }
}