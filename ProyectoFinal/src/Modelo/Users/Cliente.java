
package Modelo.Users;

public class Cliente extends Usuario{
    private String direccion;
    private int CP;
    private String ciudad;
    private String telefono;

    public Cliente(String direccion, int CP, String ciudad, String telefono, String nickname, String pass, String nombre, String apellido) {
        super(nickname, pass, nombre, apellido);
        this.direccion = direccion;
        this.CP = CP;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }
    
}
