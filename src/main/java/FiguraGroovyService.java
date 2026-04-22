import groovy.lang.GroovyClassLoader;

public class FiguraGroovyService {
    // este cargador permite definir nuevas clases en tiempo de ejecución
    private final GroovyClassLoader loader = new GroovyClassLoader();

    public Figura cargarFiguraDesdeCodigo(String codigoFuente) throws Exception {
        // compila el String y lo convierte en una clase (.class) en memoria
        Class<?> clazz = loader.parseClass(codigoFuente);

        // verifica que la nueva clase sea realmente una Figura
        if (Figura.class.isAssignableFrom(clazz)) {
            return (Figura) clazz.getDeclaredConstructor().newInstance();
        } else {
            throw new RuntimeException("El código proporcionado no es una Figura válida.");
        }
    }
}