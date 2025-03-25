package com.procedimientos.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.procedimientos.Model.PerdidasResultado;
import com.procedimientos.Service.PerdidasService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perdidas")
public class PerdidasController {
    private final PerdidasService perdidasService;

    public PerdidasController(PerdidasService perdidasService) {
        this.perdidasService = perdidasService;
    }

    @GetMapping("/calcular")
    public PerdidasResultado calcularPerdidas(
            @RequestParam String materialCalibre,
            @RequestParam String calibre,
            @RequestParam String tipoCable,
            @RequestParam int temperatura,
            @RequestParam double potencia,
            @RequestParam double tension,
            @RequestParam int cablePorFase,
            @RequestParam int fases,
            @RequestParam double longitud) {

        return perdidasService.calcularPerdidas(materialCalibre, calibre, tipoCable, temperatura,
                potencia, tension, cablePorFase, fases, longitud);
    }
}
