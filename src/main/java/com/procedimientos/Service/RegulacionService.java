package com.procedimientos.Service;

import org.springframework.stereotype.Service;
import com.procedimientos.Model.RegulacionResultado;
import com.procedimientos.Repository.RegulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RegulacionService {
    @Autowired
    private RegulacionRepository repository;

    public RegulacionResultado calcularRegulacion(
            int fases, double longitud, double tension, int cablePorFase,
            String calibre, double potencia) {
        return repository.calcularRegulacion(fases, longitud, tension, cablePorFase, calibre, potencia);
    }
}
