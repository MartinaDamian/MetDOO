package org.arquitectura.figuras;

import groovy.lang.GroovyClassLoader;
import org.springframework.stereotype.Service;

@Service
public class FiguraGroovyService {

    // ESTA ES LA LÍNEA QUE DEBES CAMBIAR
    private final GroovyClassLoader loader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());

    public Figura cargarFiguraDesdeCodigo(String codigoFuente) throws Exception {
        Class<?> clazz = loader.parseClass(codigoFuente);

        if (Figura.class.isAssignableFrom(clazz)) {
            return (Figura) clazz.getDeclaredConstructor().newInstance();
        } else {
            throw new RuntimeException("El código proporcionado no es una org.arquitectura.figuras.Figura válida.");
        }
    }
}