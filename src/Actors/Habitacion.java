package Actors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Habitacion {
    private String Tipo;
    private String Disponibilidad;
    private int Precio;
    private String Limpieza;
    private String Codigo;
    private int diasRentados; // Número de días rentados

    // Nuevos atributos para las fechas
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    public Habitacion(String Tipo, String Disponibilidad, int Precio, String Limpieza, String Codigo) {
        this.Tipo = Tipo;
        this.Disponibilidad = Disponibilidad;
        this.Precio = Precio;
        this.Limpieza = Limpieza;
        this.Codigo = Codigo;
    }

    // Getters y setters para los nuevos atributos
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        actualizarDiasRentados(); // Actualiza días rentados
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
        actualizarDiasRentados(); // Actualiza días rentados
    }

    // Método para calcular los días rentados automáticamente
    private void actualizarDiasRentados() {
        if (fechaEntrada != null && fechaSalida != null) {
            this.diasRentados = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        } else {
            this.diasRentados = 0; // No se han establecido fechas completas
        }
    }

    public int getDiasRentados() {
        return diasRentados;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public String getLimpieza() {
        return Limpieza;
    }

    public void setLimpieza(String Limpieza) {
        this.Limpieza = Limpieza;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public boolean validateAvailability() {
        return Disponibilidad.equals("Disponible");
    }
    
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formatea las fechas de entrada y salida si no son nulas
        String fechaEntradaStr = (fechaEntrada != null) ? fechaEntrada.format(dateFormatter) : "No definido";
        String fechaSalidaStr = (fechaSalida != null) ? fechaSalida.format(dateFormatter) : "No definido";

        return "Habitacion{" +
                "Tipo='" + Tipo + '\'' +
                ", Disponibilidad='" + Disponibilidad + '\'' +
                ", Precio=" + Precio +
                ", Limpieza='" + Limpieza + '\'' +
                ", Codigo='" + Codigo + '\'' +
                ", diasRentados=" + diasRentados +
                ", fechaEntrada=" + fechaEntradaStr +
                ", fechaSalida=" + fechaSalidaStr +
                '}';
    }
}
