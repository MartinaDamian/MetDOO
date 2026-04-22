package org.arquitectura.figuras;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ExternalFigureClientTest {

    @Autowired
    private ExternalFigureClient client;

    @Test
    public void probarConsumoExtermo(){
        String urlMock = "https://gist.githubusercontent.com/Anthonny-Ll/775355f5f404c6e04650524e82baec44/raw/9bebc5ca7ca1eb13d9954ec043eb305ea1348e19/figura.json";

        FiguraDTO resultado = client.obtenerFiguraExterna(urlMock);

        // Verificamos que no sea nulo y que sea un Pentágono
        assertNotNull(resultado, "El JSON no pudo ser procesado o la URL es incorrecta");
        assertEquals("Pentagono", resultado.getNombre(), "El nombre de la figura no coincide");

        System.out.println("¡Éxito! Se descargó el " + resultado.getNombre() + " con lado " + resultado.getParametros().get("lado"));
        System.out.println("Código descargado:\n" + resultado.getCodigoFuente());
    }
}