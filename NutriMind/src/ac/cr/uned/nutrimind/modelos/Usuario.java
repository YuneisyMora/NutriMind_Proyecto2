
package ac.cr.uned.nutrimind.modelos;

import java.math.BigDecimal;

public class Usuario {
    
    private String usuario;
    private String contrasena;

    // Constructor, getters y setters
    public Usuario() {}
    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    //public int getId() { return id; }
    //public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
