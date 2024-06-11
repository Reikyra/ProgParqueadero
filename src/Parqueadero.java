import java.util.ArrayList;
import java.util.HashSet;

public class Parqueadero {
    public static final int TAMANO = 40;
    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_NO_EXISTE = -3;
    public static final int CARRO_YA_EXISTE = -4;
    public static final int HORA_INICIAL = 6;
    public static final int HORA_CIERRE = 20;
    public static final int TARIFA_INICIAL = 1200;

    private Puesto[] puestos;
    private int tarifa;
    private int caja;
    private int horaActual;
    private boolean abierto;

    public Parqueadero() {
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;
        puestos = new Puesto[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            puestos[i] = new Puesto(i);
        }
    }

    public String darPlacaCarro(int pPosicion) {
        if (estaOcupado(pPosicion)) {
            return "Placa: " + puestos[pPosicion].darCarro().darPlaca();
        } else {
            return "No hay un carro en esta posición";
        }
    }

    public int entrarCarro(String pPlaca, int pHoraLlegada) {
        if (!abierto || pHoraLlegada < HORA_INICIAL || pHoraLlegada > HORA_CIERRE) {
            return PARQUEADERO_CERRADO;
        }

        if (buscarCarro(pPlaca) != null) {
            return CARRO_YA_EXISTE;
        }

        int puestoLibre = buscarPuestoLibre();
        if (puestoLibre == NO_HAY_PUESTO) {
            return NO_HAY_PUESTO;
        }

        Carro nuevoCarro = new Carro(pPlaca, pHoraLlegada);
        puestos[puestoLibre].parquearCarro(nuevoCarro);
        return puestoLibre;
    }

    public int sacarCarro(String pPlaca) {
        Carro carro = buscarCarro(pPlaca);
        if (carro == null) {
            return CARRO_NO_EXISTE;
        }

        int horasParqueado = carro.darTiempoEnParqueadero(horaActual);
        caja += tarifa * horasParqueado;

        for (Puesto puesto : puestos) {
            if (puesto.tieneCarroConPlaca(pPlaca)) {
                puesto.sacarCarro();
                return puesto.darNumeroPuesto();
            }
        }
        return CARRO_NO_EXISTE;
    }

    public int darMontoCaja() {
        return caja;
    }

    public int calcularPuestosLibres() {
        int puestosLibres = 0;
        for (Puesto puesto : puestos) {
            if (!puesto.estaOcupado()) {
                puestosLibres++;
            }
        }
        return puestosLibres;
    }

    public void cambiarTarifa(int nuevaTarifa) {
        tarifa = nuevaTarifa;
    }

    public void avanzarHora(int horas) {
        horaActual += horas;
        if (horaActual >= HORA_CIERRE) {
            abierto = false;
        }
    }

    public int darHoraActual() {
        return horaActual;
    }

    public int darTarifa() {
        return tarifa;
    }

    public boolean estaOcupado(int puesto) {
        return puestos[puesto].estaOcupado();
    }

    public boolean estaAbierto() {
        return abierto;
    }

    private Carro buscarCarro(String placa) {
        for (Puesto puesto : puestos) {
            if (puesto.tieneCarroConPlaca(placa)) {
                return puesto.darCarro();
            }
        }
        return null;
    }

    private int buscarPuestoLibre() {
        for (int i = 0; i < puestos.length; i++) {
            if (!puestos[i].estaOcupado()) {
                return i;
            }
        }
        return NO_HAY_PUESTO;
    }

    public double darTiempoPromedio() {
        double totalHoras = 0;
        int carrosParqueados = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                totalHoras += puesto.darCarro().darTiempoEnParqueadero(horaActual);
                carrosParqueados++;
            }
        }
        return carrosParqueados > 0 ? totalHoras / carrosParqueados : 0;
    }

    public Carro darCarroMasDeOchoHoras() {
        Carro carroMax = null;
        int maxHoras = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                Carro carro = puesto.darCarro();
                int horas = carro.darTiempoEnParqueadero(horaActual);
                if (horas > maxHoras) {
                    maxHoras = horas;
                    carroMax = carro;
                }
            }
        }
        return carroMax;
    }

    public boolean hayCarroMasDeOchoHoras() {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                if (puesto.darCarro().darTiempoEnParqueadero(horaActual) > 8) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Carro> darCarrosMasDeTresHorasParqueados() {
        ArrayList<Carro> carros = new ArrayList<>();
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darTiempoEnParqueadero(horaActual) > 3) {
                carros.add(puesto.darCarro());
            }
        }
        return carros;
    }

    public boolean hayCarrosPlacaIgual() {
        HashSet<String> placas = new HashSet<>();
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                String placa = puesto.darCarro().darPlaca();
                if (!placas.add(placa)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int contarCarrosQueComienzanConPlacaPB() {
        int count = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().startsWith("PB")) {
                count++;
            }
        }
        return count;
    }

    public boolean hayCarroCon24Horas() {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darTiempoEnParqueadero(horaActual) >= 24) {
                return true;
            }
        }
        return false;
    }

    public int desocuparParqueadero() {
        int count = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                puesto.sacarCarro();
                count++;
            }
        }
        return count;
    }

    public String metodo1() {
        int cantidadPB = contarCarrosQueComienzanConPlacaPB();
        boolean hayCarro24Horas = hayCarroCon24Horas();
        return "Cantidad de carros con placa PB: " + cantidadPB + " – Hay carro parqueado por 24 o más horas: " + (hayCarro24Horas ? "Sí" : "No");
    }

    public String metodo2() {
        int cantidadCarrosSacados = desocuparParqueadero();
        return "Cantidad de carros sacados: " + cantidadCarrosSacados;
    }
}


