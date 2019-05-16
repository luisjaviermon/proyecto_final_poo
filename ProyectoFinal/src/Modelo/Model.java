
package Modelo;

import java.awt.Container;
import Controlador.Controller;
import Vista.Login;
import Vista.Ventana;
import java.io.File;

public class Model {
    
    private Controller controlador;
    private Login login;
    private Ventana ventana;
    
    public boolean iniciarSesion(){
        return false;
    }

    public void setView(Container v) {
        if(v instanceof Ventana){
            this.ventana = (Ventana)v;
        }
        
        if(v instanceof Login){
            this.login = (Login)v;
        }
    }

    public void sesionIniciada() {
        if(!true){
            System.out.println("sesion aun abierta");
        }else{
            login.setSize(1000, 600);
            ventana.panelPrincipal.removeAll();
            ventana.panelPrincipal.add(login);
            ventana.panelPrincipal.revalidate();
            ventana.panelPrincipal.repaint();
        }
    }

    public void inicioSesion(String usr, String password) {
        System.out.println("Estamos en el modelo");
        System.out.println("Usuario: " + usr);
        System.out.println("contraseña: " + password);
        
    }

    public void verificate() {
        File f = new File("src/Modelo/prueba");
        System.out.println("Ruta: " + f.exists());
        char letra = '\u0041'; //letra A en unicode
        for(int i = 0;i<26; i++){
            System.out.println(Character.toString((char)(letra + i)));
        }
    }


}