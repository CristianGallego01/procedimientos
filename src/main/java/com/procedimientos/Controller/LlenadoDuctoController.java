package com.procedimientos.Controller;

import com.procedimientos.Model.LlenadoDuctoResultado;
import com.procedimientos.Service.LlenadoDuctoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/llenado-ducto")
public class LlenadoDuctoController {
    @Autowired
    private LlenadoDuctoService service;

    @GetMapping("/calcular")
    public LlenadoDuctoResultado calcularLlenadoDucto(
            @RequestParam String material,
            @RequestParam String pulgada,
            @RequestParam boolean tipoMetal,
            @RequestParam int fases,
            @RequestParam String calibreFase,
            @RequestParam String calibreNeutro,
            @RequestParam String calibreTierra,
            @RequestParam String tipoCable) {
        return service.calcularLlenadoDucto(material, pulgada, tipoMetal, fases, calibreFase, calibreNeutro, calibreTierra, tipoCable);
    }
}
