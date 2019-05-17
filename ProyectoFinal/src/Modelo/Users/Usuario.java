
package Modelo.Users;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String nickname;
    private String pass;
    private String nombre;
    private String apellido;
    private String tipo;
    
    public Usuario(String nickname, String pass, String nombre, String apellido, String tipo) {
        this.nickname = nickname;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
}
