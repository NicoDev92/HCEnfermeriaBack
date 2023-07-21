package com.nicode.gestionenfermeria.service.dto;

import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class UpdatePacienteDto {
    private PacienteEntity paciente;
    private HistoriaClinicaEntity hc;
}
