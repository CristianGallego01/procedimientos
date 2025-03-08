package com.procedimientos.Repository;

import com.procedimientos.Model.CortoCircuitoResultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Repository
public class CortoCircuitoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CortoCircuitoResultado calcularCortoCircuito(
            String material, String calibreFase, String calibreNeutro, int numeroFasesTrafo,
            double longitud, double iscaFAcomulado, double iscaNAcomulado,
            int cablePorFase, double voltajeTrafo) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CalcularCortoCircuito")
                .withoutProcedureColumnMetaDataAccess() //obliga a mantener los nombres de la bs
                .declareParameters(
                        new SqlParameter("p_material", Types.VARCHAR),
                        new SqlParameter("p_calibre_fase", Types.VARCHAR),
                        new SqlParameter("p_calibre_neutro", Types.VARCHAR),
                        new SqlParameter("p_numero_fases_trafo", Types.INTEGER),
                        new SqlParameter("p_longitud", Types.DECIMAL),
                        new SqlParameter("p_isca_f_acomulado", Types.DECIMAL),
                        new SqlParameter("p_isca_n_acomulado", Types.DECIMAL),
                        new SqlParameter("p_cable_por_fase", Types.INTEGER),
                        new SqlParameter("p_voltaje_trafo", Types.DECIMAL),
                        new SqlOutParameter("p_LL", Types.DECIMAL),
                        new SqlOutParameter("p_LN", Types.DECIMAL),
                        new SqlOutParameter("p_FC", Types.INTEGER),
                        new SqlOutParameter("p_NC", Types.INTEGER)
                );

        Map<String, Object> result = jdbcCall.execute(
                material, calibreFase, calibreNeutro, numeroFasesTrafo,
                longitud, iscaFAcomulado, iscaNAcomulado, cablePorFase, voltajeTrafo

        );


        CortoCircuitoResultado resultado = new CortoCircuitoResultado();
        resultado.setLL(((Number) result.get("p_LL")).doubleValue());
        resultado.setLN(((Number) result.get("p_LN")).doubleValue());
        resultado.setFC(((Number) result.get("p_FC")).intValue());
        resultado.setNC(((Number) result.get("p_NC")).intValue());

        return resultado;

    }
}
