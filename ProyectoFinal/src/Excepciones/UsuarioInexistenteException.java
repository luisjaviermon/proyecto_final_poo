
package Excepciones;

public class UsuarioInexistenteException extends Exception{

    public UsuarioInexistenteException(String message) {
        super(message);
    }
    public UsuarioInexistenteException() {}
}
