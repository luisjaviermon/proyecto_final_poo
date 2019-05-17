
import Controlador.Controller;
import Modelo.Model;
import Vista.Login;
import Vista.PanelDeControl;
import Vista.Ventana;

public class ProyectoFinal {
    public static void main(String[] args){
        //instancio la clase encargada del controlador
        Controller controlador = new Controller();
        
        //Instancio la clase encargada del modelo
        Model modelo = new Model();
        
        //Instancio las clases referentes a las vistas de la aplicacion 
        Ventana ventana = new Ventana();
        Login login = new Login();
        PanelDeControl panelDeControl = new PanelDeControl();
        
        //creando relacion Vista->Controlador
        ventana.setControlador(controlador);
        login.setControlador(controlador);
        
        //Creando relacion Controlador->Modelo
        controlador.setModelo(modelo);
        
        //creando relacion Modelo->Vista
        modelo.setView(ventana);
        modelo.setView(login);
        modelo.setView(panelDeControl);
        
        //Inspeccionamos que la estructura de carpetas dentro de modelo este correcta
        modelo.verificate();
        
        //Verificamos si esta iniciada alguna sesion
        controlador.verificaSesion();
        
        ventana.setVisible(true);
    } 
}
