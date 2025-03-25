package com.procedimientos.Repository;

import com.procedimientos.Model.PerdidasResultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Repository
public class PerdidasRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PerdidasResultado calcularPerdidas(
            String materialCalibre, String calibre, String tipoCable, int temperatura,
            double potencia, double tension, int cablePorFase, int fases, double longitud) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CalcularPerdidas")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_material_calibre", Types.VARCHAR),
                        new SqlParameter("p_calibre", Types.VARCHAR),
                        new SqlParameter("p_tipo_cable", Types.VARCHAR),
                        new SqlParameter("p_temperatura", Types.INTEGER),
                        new SqlParameter("p_potencia", Types.DECIMAL),
                        new SqlParameter("p_tension", Types.DECIMAL),
                        new SqlParameter("p_cable_por_fase", Types.INTEGER),
                        new SqlParameter("p_fases", Types.INTEGER),
                        new SqlParameter("p_longitud", Types.DECIMAL),
                        new SqlOutParameter("p_perdidas", Types.DECIMAL),
                        new SqlOutParameter("p_corriente", Types.DECIMAL),
                        new SqlOutParameter("p_perdidas_R", Types.DECIMAL),
                        new SqlOutParameter("p_capacidad_corriente_permisible", Types.INTEGER)
                );

        Map<String, Object> result = jdbcCall.execute(
                materialCalibre, calibre, tipoCable, temperatura,
                potencia, tension, cablePorFase, fases, longitud
        );

        PerdidasResultado resultado = new PerdidasResultado();
        resultado.setPerdidas(((Number) result.get("p_perdidas")).doubleValue());
        resultado.setCorriente(((Number) result.get("p_corriente")).doubleValue());
        resultado.setPerdidasR(((Number) result.get("p_perdidas_R")).doubleValue());
        resultado.setCapacidadCorrientePermisible(((Number) result.get("p_capacidad_corriente_permisible")).intValue());

        return resultado;
    }
}