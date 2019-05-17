
package Modelo;

import Excepciones.PasswordIncorrectoException;
import Excepciones.UsuarioInexistenteException;
import Modelo.Utilidades.Encripta;
import Modelo.Users.Administrador;
import Modelo.Users.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BaseDeDatos {
    private File f;
    private static int intentos = 0;
    private static String passTmp;
    private static String usrTmp;
    
    private void creaAdmin(){
        try{
            Administrador admin = new Administrador("01","admin1",Encripta.getMD5("1234"),"ADMINISTRADOR","POR DEFECTO","administrador");
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
    
    public String estableceRuta(String usr){
        String subCadenaUsuario = usr.substring(0, 5);
        String ruta;
        
        if(subCadenaUsuario.equals("ADMIN")){
            return "src/Modelo/BD/admin/";
        }
        
        return "src/Modelo/BD/usuarios/";
       
    }
    
    public void verificarUsuario(String usr) throws UsuarioInexistenteException{
        
        String ruta = estableceRuta(usr);
        
        f = new File(ruta + Encripta.getMD5(usr) + ".obj");
        
        if(!f.exists()){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void verificaPass(String usr,String pass) throws PasswordIncorrectoException{
        String ruta = estableceRuta(usr);
        if(intentos != 0 && intentos <= 3 && usrTmp.toUpperCase().equals(usr)){
            if(!passTmp.equals(pass)){
                intentos ++;
                System.out.println("DEntro de cache: " + intentos);
                throw new PasswordIncorrectoException();
            }else{
                return;
            }
        }
        if(intentos>3){
            intentos = 0;
            usrTmp = "";
            passTmp = "";
        }
        try{
            Usuario posibleUsuario;
            ObjectInputStream f = new ObjectInputStream(new FileInputStream(ruta + Encripta.getMD5(usr) + ".obj"));
            posibleUsuario = (Usuario)f.readObject();
            f.close();
            if(!pass.equals(posibleUsuario.getPass())){
                intentos = 1;
                passTmp = posibleUsuario.getPass();
                usrTmp = posibleUsuario.getNickname();
                throw new PasswordIncorrectoException();
            }
        }catch(IOException e){System.out.println("Error" + e.getMessage());}
        catch(ClassNotFoundException e){System.out.println("Error" + e.getMessage());}
    }
}
