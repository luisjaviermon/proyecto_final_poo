
package Modelo;

import java.awt.Container;
import Controlador.Controller;
import Excepciones.CamposVaciosException;
import Excepciones.UsuarioInexistenteException;
import Modelo.Utilidades.Encripta;
import Vista.Login;
import Vista.Ventana;

public class Model {
    
    private Controller controlador;
    private Login login;
    private Ventana ventana;
    private BaseDeDatos DB = new BaseDeDatos();

    public void setView(Container v) {
        if(v instanceof Ventana){
            this.ventana = (Ventana)v;
        }
        
        if(v instanceof Login){
            this.login = (Login)v;
        }
    }

    public void verificate() {
        DB.verificaAdmin();
        DB.verificaUsuarios();
    }
    
    public void sesionIniciada() {
        if(DB.verificaSesion()){
            System.out.println("sesion aun abierta");
        }else{
            login.setSize(1000, 600);
            ventana.panelPrincipal.removeAll();
            ventana.panelPrincipal.add(login);
            ventana.panelPrincipal.revalidate();
            ventana.panelPrincipal.repaint();
        }
    }

    private void verificaCampos(String usr, String password) throws CamposVaciosException{
        
        if(usr.equals("") || password.equals(Encripta.getMD5(""))){
            throw new CamposVaciosException("Alguno de los campos estan vacios");
        }
        login.LabelAdvertencia.setVisible(false);
    }
    
    public void inicioSesion(String usr, String password) {
        try{
            verificaCampos(usr,password);
            DB.VerificarUsuario(usr);
        }catch(CamposVaciosException e){
            login.LabelAdvertencia.setText("Lenar los capos vacios");
            login.LabelAdvertencia.setVisible(true);
        }
        catch(UsuarioInexistenteException e){
            login.LabelAdvertencia.setText("EL usuario introducido no existe");
            login.LabelAdvertencia.setVisible(true);
        }
    }

}