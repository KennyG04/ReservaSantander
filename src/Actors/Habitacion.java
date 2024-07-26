package Actors;

public class Habitacion {
    String Tipo;
    String Disponibilidad;
    int Precio;
    String Limpieza;
    String Codigo;

    public Habitacion(String Tipo, String Disponibilidad, int Precio, String Limpieza, String Codigo) {
        this.Tipo = Tipo;
        this.Disponibilidad = Disponibilidad;
        this.Precio = Precio;
        this.Limpieza = Limpieza;
        this.Codigo = Codigo;
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
    
    public boolean validateAvailability(){
        if(Disponibilidad.equals("Disponible")){
            return true;
        }else if(Disponibilidad.equals("Ocupado")){
            return false;
        }else if(Disponibilidad.equals("Reservado")){
            return false;
        }
        return false;
    }
}
