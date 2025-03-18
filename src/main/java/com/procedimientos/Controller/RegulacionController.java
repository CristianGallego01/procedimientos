package com.procedimientos.Controller;
import com.procedimientos.Model.RegulacionResultado;
import com.procedimientos.Service.RegulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regulacion")
public class RegulacionController {

    @Autowired
    private RegulacionService service;

    @GetMapping("/calcular")
    public RegulacionResultado calcularRegulacion(
            @RequestParam int fases,
            @RequestParam double longitud,
            @RequestParam double tension,
            @RequestParam int cablePorFase,
            @RequestParam String calibre,
            @RequestParam double potencia) {
        return service.calcularRegulacion(fases, longitud, tension, cablePorFase, calibre, potencia);
    }

}
