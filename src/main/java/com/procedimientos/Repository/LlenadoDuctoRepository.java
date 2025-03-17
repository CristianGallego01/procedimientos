package com.procedimientos.Repository;

import com.procedimientos.Model.LlenadoDuctoResultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Repository
public class LlenadoDuctoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LlenadoDuctoResultado calcularLlenadoDucto(
            String material, String pulgada, boolean tipoMetal, int fases,
            String calibreFase, String calibreNeutro, String calibreTierra, String tipoCable) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CalcularLlenadoDucto")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_material", Types.VARCHAR),
                        new SqlParameter("p_pulgada", Types.VARCHAR),
                        new SqlParameter("p_tipo_metal", Types.BOOLEAN),
                        new SqlParameter("p_fases", Types.INTEGER),
                        new SqlParameter("p_calibre_fase", Types.VARCHAR),
                        new SqlParameter("p_calibre_neutro", Types.VARCHAR),
                        new SqlParameter("p_calibre_tierra", Types.VARCHAR),
                        new SqlParameter("p_tipo_cable", Types.VARCHAR),
                        new SqlOutParameter("p_Area_Ducto", Types.DECIMAL),
                        new SqlOutParameter("p_Area_Fase", Types.DECIMAL),
                        new SqlOutParameter("p_Area_Neutro", Types.DECIMAL),
                        new SqlOutParameter("p_Area_Tierra", Types.DECIMAL),
                        new SqlOutParameter("p_Area_Total", Types.DECIMAL),
                        new SqlOutParameter("p_Llenado", Types.DECIMAL)
                );

        Map<String, Object> result = jdbcCall.execute(
                material, pulgada, tipoMetal, fases, calibreFase, calibreNeutro, calibreTierra, tipoCable
        );

        System.out.println("Resultado del procedimiento: " + result);

        LlenadoDuctoResultado resultado = new LlenadoDuctoResultado();
        resultado.setAreaDucto(((Number) result.get("p_Area_Ducto")).doubleValue());
        resultado.setAreaFase(((Number) result.get("p_Area_Fase")).doubleValue());
        resultado.setAreaNeutro(((Number) result.get("p_Area_Neutro")).doubleValue());
        resultado.setAreaTierra(((Number) result.get("p_Area_Tierra")).doubleValue());
        resultado.setAreaTotal(((Number) result.get("p_Area_Total")).doubleValue());
        resultado.setLlenado(((Number) result.get("p_Llenado")).doubleValue());

        return resultado;
    }
}
