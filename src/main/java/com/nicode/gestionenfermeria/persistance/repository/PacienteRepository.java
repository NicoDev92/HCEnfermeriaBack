package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.persistance.projection.PacienteSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends ListCrudRepository<PacienteEntity, Integer> {

    @Query(value = "SELECT pa.id AS id, pa.nombre AS nombre, pa.apellido AS apellido,"
                            + " pa.dni AS dni, pa.fecha_de_nacimiento AS fechaDeNacimiento, pa.direccion AS direccion,"
                            + " pa.habitacion AS habitacion, pa.cama AS cama, pa.servicio AS servicio,"
                            + " hc.id_paciente AS idPaciente, hc.sexo AS sexo, hc.altura AS altura,"
                            + " hc.peso AS peso, hc.grupo_sanguineo as grupoSanguineo, hc.historial_familiar AS historialFamiliar,"
                            + " hc.observaciones AS observaciones, hc.ultima_modificacion AS ultimaModificacion"
                    + " FROM paciente pa INNER JOIN historia_clinica hc ON pa.id = hc.id_paciente"
                    + " WHERE pa.id=:id", nativeQuery = true)
    Optional<PacienteSummary> getSumaryById(@Param("id") int id);
}
