
package Modelo.Users;

public class Administrador extends Usuario{
    private String unidad;

    public Administrador(String unidad, String nickname, String pass, String nombre, String apellido) {
        super(nickname, pass, nombre, apellido);
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

}
