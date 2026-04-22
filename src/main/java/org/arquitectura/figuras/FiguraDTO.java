package org.arquitectura.figuras;

import java.util.Map;

public class FiguraDTO {
    private String nombre;
    private Map<String, Object> parametros;
    private String codigoFuente;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Map<String, Object> getParametros() { return parametros; }
    public void setParametros(Map<String, Object> parametros) { this.parametros = parametros; }
    public String getCodigoFuente() { return codigoFuente; }
    public void setCodigoFuente(String codigoFuente) { this.codigoFuente = codigoFuente; }
}