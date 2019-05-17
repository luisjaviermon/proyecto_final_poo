
package Modelo;

import Excepciones.UsuarioInexistenteException;
import Modelo.Utilidades.Encripta;
import Modelo.Users.Administrador;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BaseDeDatos {
    File f;
    private void creaAdmin(){
        try{
            Administrador admin = new Administrador("01","admin1",Encripta.getMD5("1234"),"ADMINISTRADOR","POR DEFECTO");
            new ObjectOutputStream(new FileOutputStream("src/Modelo/BD/admin/"+ Encripta.getMD5(admin.getNickname().toUpperCase()) + ".obj")).writeObject(admin);
        }catch(IOException e){System.out.println("Error al escribir el archivo");}
    }
    
    public void verificaAdmin(){
        f = new File("src/Modelo/BD/admin");
        if(!f.exists()){
            f.mkdir();
            creaAdmin();
        }else{
            if(!new File("src/Modelo/BD/admin/"+ Encripta.getMD5("ADMIN1") + ".obj").exists()){
                creaAdmin();
            }
        }
        f = null;
    }
    
    public void verificaUsuarios(){
        f = new File("src/Modelo/BD/usuarios");
        if(!f.exists()){
            f.mkdir();
        }
        
        char letra = '\u0041'; //letra A en unicode
        for(int i = 0;i<26; i++){
            f = new File("src/Modelo/BD/usuarios/" + Encripta.getMD5(Character.toString((char)(letra + i))));
            if(!f.exists()){
                f.mkdir();
            }
        }
        f = null;
    }
    
    public boolean verificaSesion(){
        return false;
    }
    
    public void VerificarUsuario(String usr) throws UsuarioInexistenteException{
        
        String subCadenaUsuario = usr.substring(0, 5);
        String ruta;
        
        if(subCadenaUsuario.equals("ADMIN")){
            ruta = "src/Modelo/BD/admin/";
        }else{
            ruta = "src/Modelo/BD/usuarios/";
        }
        
        f = new File(ruta + Encripta.getMD5(usr) + ".obj");
        
        if(!f.exists()){
            throw new UsuarioInexistenteException();
        }
    }
}
