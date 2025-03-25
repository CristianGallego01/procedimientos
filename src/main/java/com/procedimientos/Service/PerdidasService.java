package com.procedimientos.Service;
import org.springframework.stereotype.Service;
import com.procedimientos.Model.PerdidasResultado;
import com.procedimientos.Repository.PerdidasRepository;

@Service
public class PerdidasService {
    private final PerdidasRepository perdidasRepository;

    public PerdidasService(PerdidasRepository perdidasRepository) {
        this.perdidasRepository = perdidasRepository;
    }

    public PerdidasResultado calcularPerdidas(
            String materialCalibre, String calibre, String tipoCable, int temperatura,
            double potencia, double tension, int cablePorFase, int fases, double longitud) {

        return perdidasRepository.calcularPerdidas(materialCalibre, calibre, tipoCable, temperatura,
                potencia, tension, cablePorFase, fases, longitud);
    }
}
