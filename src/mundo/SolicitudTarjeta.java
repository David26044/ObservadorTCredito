/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import java.util.Date;

/**
 *
 * @author ACER
 */
import java.util.Date;

public class SolicitudTarjeta implements ISolicitudTarjeta, IObservable {

    private String estado;
    private Date fecha;
    private Persona cliente;
    private Asesor asesor;
    private Observer observer; // Único observador (el decorador)
    private TarjetaCredito tarjetaCliente;

    public SolicitudTarjeta(Date fecha, Persona cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = "En espera";
        this.asesor = GestorAsesores.getInstance().getAsesorAleatorio();
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            System.out.println("Desde Observable(SolicitudTarjeta) aviso al observer(SolicitudTarjetaDecorator)");
            observer.update();
        }
    }

    public TarjetaCredito iniciarSolicitud() {
        System.out.println(estado);
        System.out.println("     Se le asignó el asesor: " + asesor.getNombre());

        tarjetaCliente = asesor.manejarSolicitud(cliente.getPuntajeDataCredito());

        if (tarjetaCliente == null) {
            estado = "Rechazado";
            notifyObservers();
            return null;
        }

        estado = "Aprobado";
        notifyObservers();
        return tarjetaCliente;
    }

    public TarjetaCredito getTarjetaCliente() {
        return tarjetaCliente;
    }

    public String getEstado() {
        return estado;
    }

}
