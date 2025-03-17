package com.procedimientos.Service;

import com.procedimientos.Model.LlenadoDuctoResultado;
import com.procedimientos.Repository.LlenadoDuctoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlenadoDuctoService {
    @Autowired
    private LlenadoDuctoRepository repository;

    public LlenadoDuctoResultado calcularLlenadoDucto(
            String material, String pulgada, boolean tipoMetal, int fases,
            String calibreFase, String calibreNeutro, String calibreTierra, String tipoCable) {
        return repository.calcularLlenadoDucto(material, pulgada, tipoMetal, fases, calibreFase, calibreNeutro, calibreTierra, tipoCable);
    }
}
