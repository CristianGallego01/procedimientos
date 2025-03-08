package com.procedimientos.Service;
import com.procedimientos.Model.CortoCircuitoResultado;
import com.procedimientos.Repository.CortoCircuitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CortoCircuitoService {

    @Autowired
    private CortoCircuitoRepository repository;

    public CortoCircuitoResultado calcularCortoCircuito(
            String material, String calibreFase, String calibreNeutro, int numeroFasesTrafo,
            double longitud, double iscaFAcomulado, double iscaNAcomulado,
            int cablePorFase, double voltajeTrafo) {

        return repository.calcularCortoCircuito(material, calibreFase, calibreNeutro, numeroFasesTrafo,
                longitud, iscaFAcomulado, iscaNAcomulado, cablePorFase, voltajeTrafo);
    }
}
