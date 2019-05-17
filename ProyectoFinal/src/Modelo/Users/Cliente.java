
package Modelo.Users;

public class Cliente extends Usuario{
    private String direccion;
    private int CP;
    private String ciudad;
    private String telefono;

    public Cliente(String direccion, int CP, String ciudad, String telefono, String nickname, String pass, String nombre, String apellido, String tipo) {
        super(nickname, pass, nombre, apellido, tipo);
        this.direccion = direccion;
        this.CP = CP;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
}
