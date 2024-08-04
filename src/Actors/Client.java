package Actors;

import java.util.regex.Pattern;

public class Client {
    private String nombre;
    private Long cedula;
    private String telefono;
    private String email;

    // Constructor
    public Client(String nombre, Long cedula, String telefono, String email) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        setEmail(email); // Valida y establece el email usando el setter
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    // Establece el email si es válido
    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Formato de email no válido: " + email);
        }
    }

    // Método para validar el formato del correo electrónico
    private boolean validarEmail(String email) {
        // Expresión regular para validar el formato de un email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nombre='" + nombre + '\'' +
                ", cedula=" + cedula +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
