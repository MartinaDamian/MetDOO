package org.arquitectura.figuras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/figuras")
public class FiguraController {

    @Autowired
    private FiguraService figuraService;

    @PostMapping("/calcular")
    public Map<String, Double> calcular(@RequestBody FiguraDTO dto) throws Exception {
        return figuraService.calcularFigura(
                dto.getNombre(),
                dto.getParametros(),
                dto.getCodigoFuente()
        );
    }
}