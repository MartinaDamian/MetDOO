package org.arquitectura.figuras;

import groovy.lang.GroovyClassLoader;
import org.springframework.stereotype.Service;

@Service
public class FiguraGroovyService {

    public Figura cargarFiguraDesdeCodigo(String codigoFuente) throws Exception {
        // Groovy 5 maneja mejor el bytecode de Java 24.
        // Al usar un try-with-resources, cerramos el loader automáticamente.
        try (GroovyClassLoader loader = new GroovyClassLoader(Figura.class.getClassLoader())) {
            Class<?> clazz = loader.parseClass(codigoFuente);

            if (Figura.class.isAssignableFrom(clazz)) {
                return (Figura) clazz.getDeclaredConstructor().newInstance();
            } else {
                throw new RuntimeException("El código proporcionado no es una Figura válida.");
            }
        }
    }
}