package objects;

public class Cliente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String correo;

    public Cliente(String cedula, String nombre, String telefono, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Objects.Cliente{");
        sb.append("Cedula='").append(getCedula()).append('\'');
        sb.append(", nombre='").append(getNombre()).append('\'');
        sb.append(", telefono='").append(getTelefono()).append('\'');
        sb.append(", email='").append(getCorreo()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
