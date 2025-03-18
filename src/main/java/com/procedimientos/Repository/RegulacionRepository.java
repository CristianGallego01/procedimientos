package com.procedimientos.Repository;

import com.procedimientos.Model.RegulacionResultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Repository
public class RegulacionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RegulacionResultado calcularRegulacion(
            int fases, double longitud, double tension, int cablePorFase,
            String calibre, double potencia) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CalcularRegulacion")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_FASES", Types.INTEGER),
                        new SqlParameter("p_LONGITUD", Types.DECIMAL),
                        new SqlParameter("p_TENSION", Types.DECIMAL),
                        new SqlParameter("p_CABLE_POR_FASE", Types.INTEGER),
                        new SqlParameter("p_CALIBRE", Types.VARCHAR),
                        new SqlParameter("p_POTENCIA", Types.DECIMAL),
                        new SqlOutParameter("p_REGULACION", Types.DECIMAL),
                        new SqlOutParameter("p_CORRIENTE_CABLE", Types.DECIMAL)
                );

        Map<String, Object> result = jdbcCall.execute(
                fases, longitud, tension, cablePorFase, calibre, potencia
        );

        System.out.println("Resultado del procedimiento: " + result);

        RegulacionResultado resultado = new RegulacionResultado();
        resultado.setRegulacion(((Number) result.get("p_REGULACION")).doubleValue());
        resultado.setCorrienteCable(((Number) result.get("p_CORRIENTE_CABLE")).doubleValue());

        return resultado;
    }
}

