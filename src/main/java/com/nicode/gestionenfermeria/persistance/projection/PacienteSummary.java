package com.nicode.gestionenfermeria.persistance.projection;

public interface PacienteSummary {
    //Datos Paciente

    Integer getId();
    String getNombre();
    String getApellido();
    String getDni();
    String getFechaDeNacimiento();
    String getDireccion();
    Integer getHabitacion();
    Integer getCama();
    String getServicio();

    //Datos Historia Clinica

    Integer getIdPaciente();
    String getSexo();
    Float getAltura();
    Float getPeso();
    String getGrupoSanguineo();
    String getHistorialFamiliar();
    String getObservaciones();
    String getUltimaModificacion();
}
