package models;

public class Persona {

    private int idPersona;
    private String nombre;
    private int cedula;

    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(String nombre, int cedula) {
        this.nombre = nombre;
        this.cedula = cedula;

    }

    public Persona(int idPersona, String nombre, int cedula) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Persona " + idPersona + " {" +
                "nombre = '" + nombre + '\'' +
                ", cedula = " + cedula +
                '}';
    }
}
