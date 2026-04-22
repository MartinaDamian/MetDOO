package org.arquitectura.figuras;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalFigureClient {

    public FiguraDTO obtenerFiguraExterna(String urlExterna) {
        RestTemplate restTemplate = new RestTemplate();

        // Hace una petición GET a la URL y transforma el JSON automáticamente a tu FiguraDTO
        FiguraDTO figuraRecibida = restTemplate.getForObject(urlExterna, FiguraDTO.class);

        return figuraRecibida;
    }
}