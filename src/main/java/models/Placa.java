package models;

public class Placa {

    private int idPlacaVehiculo;
    private String placa;

    public Placa(int idPlacaVehiculo, String placa) {
        this.idPlacaVehiculo = idPlacaVehiculo;
        this.placa = placa;
    }

    public Placa(int idPlacaVehiculo) {
        this.idPlacaVehiculo = idPlacaVehiculo;
    }

    public Placa(String placa) {
        this.placa = placa;
    }

    public int getIdPlacaVehiculo() {
        return idPlacaVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Placa " + idPlacaVehiculo + " {" +
                "placa = '" + placa + '\'' +
                '}';
    }
}
