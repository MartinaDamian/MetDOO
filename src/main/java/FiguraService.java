import groovy.lang.GroovyClassLoader;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class FiguraService {

    public Map<String, Double> calcularFigura(String nombre, Map<String, Object> params, String codigoGroovy) throws Exception {
        Figura fg;

        if (codigoGroovy == null || codigoGroovy.isEmpty()) {
            Class<?> clazz = Class.forName(nombre);
            fg = (Figura) clazz.getDeclaredConstructor().newInstance();
        } else {
            try (GroovyClassLoader loader = new GroovyClassLoader()) {
                Class<?> clazz = loader.parseClass(codigoGroovy);
                fg = (Figura) clazz.getDeclaredConstructor().newInstance();
            }
        }

        mapearAtributos(fg, params);

        Map<String, Double> resultados = new HashMap<>();
        resultados.put("area", fg.area());
        resultados.put("perimetro", fg.perimetro());
        return resultados;
    }

    private void mapearAtributos(Object instancia, Map<String, Object> parametros) {
        if (parametros == null) return;
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            try {
                Field field = instancia.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(instancia, entry.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Advertencia: Atributo '" + entry.getKey() + "' no encontrado.");
            }
        }
    }
}