package com.procedimientos.Controller;
import com.procedimientos.Model.CortoCircuitoResultado;
import com.procedimientos.Service.CortoCircuitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corto-circuito")
public class CortoCircuitoController {

    @Autowired
    private CortoCircuitoService service;

    @GetMapping("/calcular")
    public CortoCircuitoResultado calcular(
            @RequestParam String material,
            @RequestParam String calibreFase,
            @RequestParam String calibreNeutro,
            @RequestParam int numeroFasesTrafo,
            @RequestParam double longitud,
            @RequestParam double iscaFAcomulado,
            @RequestParam double iscaNAcomulado,
            @RequestParam int cablePorFase,
            @RequestParam double voltajeTrafo) {

        return service.calcularCortoCircuito(material, calibreFase, calibreNeutro, numeroFasesTrafo,
                longitud, iscaFAcomulado, iscaNAcomulado, cablePorFase, voltajeTrafo);
    }
}
