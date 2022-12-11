package models;

public class Relacion {

    private int idRelacion;
    private int idPersona;
    private int idPlaca;


    public Relacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Relacion(int idRelacion, int idPersona, int idPlaca) {
        this.idRelacion = idRelacion;
        this.idPersona = idPersona;
        this.idPlaca = idPlaca;
    }

    public Relacion(int idPersona, int idPlaca) {
        this.idPersona = idPersona;
        this.idPlaca = idPlaca;
    }

    public int getIdRelacion() {
        return idRelacion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(int idPlaca) {
        this.idPlaca = idPlaca;
    }

    @Override
    public String toString() {
        return "Relacion "+ idRelacion + "{" +
                "idPersona = " + idPersona +
                ", idPlaca = " + idPlaca +
                '}';
    }
}
