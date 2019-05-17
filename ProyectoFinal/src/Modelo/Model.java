
package Modelo;

import java.awt.Container;
import Controlador.Controller;
import Excepciones.CamposVaciosException;
import Excepciones.PasswordIncorrectoException;
import Excepciones.UsuarioInexistenteException;
import Modelo.Utilidades.Encripta;
import Vista.Login;
import Vista.PanelDeControl;
import Vista.Ventana;

public class Model {
    
    private Controller controlador;
    private Login login;
    private Ventana ventana;
    private BaseDeDatos DB = new BaseDeDatos();
    private PanelDeControl panelDeControl = new PanelDeControl();

    public void setView(Container v) {
        if(v instanceof Ventana){
            this.ventana = (Ventana)v;
        }
        
        if(v instanceof Login){
            this.login = (Login)v;
        }
        if(v instanceof PanelDeControl){
            this.panelDeControl = (PanelDeControl)v;
        }
    }

    public void verificate() {
        DB.verificaAdmin();
        DB.verificaUsuarios();
    }
    
    private void cargaLogin(){
        login.setSize(1000, 600);
        ventana.panelPrincipal.removeAll();
        ventana.panelPrincipal.add(login);
        ventana.panelPrincipal.revalidate();
        ventana.panelPrincipal.repaint();
    } 
    
    private void cargaPanelControl(){
        panelDeControl.setSize(1000, 600);
        ventana.panelPrincipal.removeAll();
        ventana.panelPrincipal.add(panelDeControl);
        ventana.panelPrincipal.revalidate();
        ventana.panelPrincipal.repaint();
    }
    
    public void sesionIniciada() {
        if(DB.verificaSesion()){
            System.out.println("sesion aun abierta");
        }else{
            cargaLogin();
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
            DB.verificarUsuario(usr);
            DB.verificaPass(usr,password);
            cargaPanelControl();
        }catch(CamposVaciosException e){
            login.LabelAdvertencia.setText("Llenar los capos vacios");
            login.LabelAdvertencia.setVisible(true);
        }
        catch(UsuarioInexistenteException e){
            login.LabelAdvertencia.setText("El usuario introducido no existe");
            login.LabelAdvertencia.setVisible(true);
        }
        catch(PasswordIncorrectoException e){
            login.LabelAdvertencia.setText("Contraseña incorrecta. Intentelo de nuevo");
            login.LabelAdvertencia.setVisible(true);
        }
    }

}