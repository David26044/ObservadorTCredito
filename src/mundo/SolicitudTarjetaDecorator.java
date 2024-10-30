/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

/**
 *
 * @author ACER
 */
public class SolicitudTarjetaDecorator implements ISolicitudTarjeta, Observer {

    protected ISolicitudTarjeta solicitudDecorada;

    public SolicitudTarjetaDecorator(ISolicitudTarjeta solicitudDecorada) {
        this.solicitudDecorada = solicitudDecorada;
        ((SolicitudTarjeta) solicitudDecorada).setObserver(this);

    }

    @Override
    public TarjetaCredito iniciarSolicitud() {
        return solicitudDecorada.iniciarSolicitud();
    }

    @Override
    public void update() {
        // Llama a notificar cuando el estado de la solicitud cambia
        notificar(((SolicitudTarjeta)solicitudDecorada).getTarjetaCliente());
    }

    private void notificar(TarjetaCredito tarjeta) {
        if (tarjeta != null) {
            System.out.println("Notificación: Su tarjeta de crédito ha sido aprobada.");
        } else {
            System.out.println("Notificación: Lamentablemente, su solicitud de tarjeta de crédito fue rechazada.");
        }
    }
}
