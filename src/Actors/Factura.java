package Actors;

public class Factura {
    Client cliente;
    Habitacion habitacion;
    int subtotal;
    final double iva = 0.15;
    double total;

    public Factura(Client cliente, Habitacion habitacion, int subtotal, double total) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.subtotal = subtotal;
        this.total = total;
    }
    
    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return (subtotal*iva)+subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
