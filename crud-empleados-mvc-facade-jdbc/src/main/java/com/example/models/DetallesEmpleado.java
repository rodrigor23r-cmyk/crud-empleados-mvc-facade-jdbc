package com.example.models;

import java.util.Set;

public record DetallesEmpleado(String nombreDpto, Set<String> direccionesCorreo, Set<String> numerosTelefono) {

}
