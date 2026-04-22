package org.arquitectura.figuras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class FiguraService {

    @Autowired
    private FiguraGroovyService figuraGroovyService;

    public Map<String, Double> calcularFigura(String nombre, Map<String, Object> params, String codigoGroovy) throws Exception {
        Figura fg;

        if (codigoGroovy == null || codigoGroovy.isEmpty()) {
            Class<?> clazz = Class.forName(nombre);
            fg = (Figura) clazz.getDeclaredConstructor().newInstance();
        } else {
            fg = figuraGroovyService.cargarFiguraDesdeCodigo(codigoGroovy);
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

                Object valor = entry.getValue();

                // Conversión segura de tipos numéricos provenientes del JSON
                if (valor instanceof Number) {
                    if (field.getType().equals(Double.class)) {
                        valor = ((Number) valor).doubleValue();
                    } else if (field.getType().equals(Integer.class)) {
                        valor = ((Number) valor).intValue();
                    }
                }

                field.set(instancia, valor);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Advertencia: Atributo '" + entry.getKey() + "' no encontrado.");
            }
        }
    }
}