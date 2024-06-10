public class Puesto {
    private int numeroPuesto;
    private Carro carro;

    public Puesto(int numeroPuesto) {
        this.numeroPuesto = numeroPuesto;
        this.carro = null;
    }

    public Carro darCarro() {
        return carro;
    }

    public boolean estaOcupado() {
        return carro != null;
    }

    public void parquearCarro(Carro carro) {
        this.carro = carro;
    }

    public void sacarCarro() {
        this.carro = null;
    }

    public int darNumeroPuesto() {
        return numeroPuesto;
    }

    public boolean tieneCarroConPlaca(String placa) {
        return carro != null && carro.tienePlaca(placa);
    }
}
