public class Carro {
    private String placa;
    private int horaLlegada;

    public Carro(String placa, int horaLlegada) {
        this.placa = placa;
        this.horaLlegada = horaLlegada;
    }

    public String darPlaca() {
        return placa;
    }

    public int darHoraLlegada() {
        return horaLlegada;
    }

    public boolean tienePlaca(String placa) {
        return this.placa.equalsIgnoreCase(placa);
    }

    public int darTiempoEnParqueadero(int horaActual) {
        return horaActual - horaLlegada + 1;
    }
}


