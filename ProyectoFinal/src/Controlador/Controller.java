
package Controlador;

import Modelo.Encripta;
import Modelo.Model;

public class Controller {
    
    private Model modelo;
    
    public void setModelo(Model modelo){
        this.modelo = modelo;
    }

    public void verificaSesion() {
        modelo.sesionIniciada();
    }

    public void datosInicioSesion(String usr, char[] password) {
        modelo.inicioSesion(usr.toUpperCase(), Encripta.getMD5(String.valueOf(password)));
    }

   
}
