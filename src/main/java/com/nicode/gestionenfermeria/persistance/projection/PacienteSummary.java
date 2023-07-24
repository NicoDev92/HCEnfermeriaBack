package com.nicode.gestionenfermeria.persistance.projection;

import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PacienteSummary {
    //Datos Paciente

    Integer getId();
    String getNombre();
    String getApellido();
    String getDni();
    @Nullable
    LocalDate getFechaDeNacimiento();
    @Nullable
    String getDireccion();
    Integer getHabitacion();
    @Nullable
    Integer getCama();
    @Nullable
    String getServicio();

    //Datos Historia Clinica

    @Nullable
    Integer getIdPaciente();
    @Nullable
    String getSexo();
    @Nullable
    Float getAltura();
    @Nullable
    Float getPeso();
    @Nullable
    String getGrupoSanguineo();
    @Nullable
    String getHistorialFamiliar();
    @Nullable
    String getObservaciones();
    @Nullable 
    String getUltimaModificacion();
}
