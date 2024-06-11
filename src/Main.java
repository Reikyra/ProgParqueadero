import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar un carro al parqueadero");
            System.out.println("2. Dar salida a un carro del parqueadero");
            System.out.println("3. Informar los ingresos del parqueadero");
            System.out.println("4. Consultar la cantidad de puestos disponibles");
            System.out.println("5. Avanzar el reloj del parqueadero");
            System.out.println("6. Cambiar la tarifa del parqueadero");
            System.out.println("7. Dar tiempo promedio de carros en parqueadero");
            System.out.println("8. Dar carro con más horas en parqueadero");
            System.out.println("9. Verificar si hay carro más de 8 horas");
            System.out.println("10. Listar carros más de 3 horas");
            System.out.println("11. Verificar si hay carros con placa igual");
            System.out.println("12. Contar carros con placa que comienza con PB");
            System.out.println("13. Verificar si hay carro con 24 horas");
            System.out.println("14. Desocupar parqueadero");
            System.out.println("15. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la placa del carro:");
                    String placa = scanner.nextLine();
                    System.out.println("Ingrese la hora de llegada (entre 6 y 21):");
                    int horaLlegada = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    int resultado = parqueadero.entrarCarro(placa, horaLlegada);
                    if (resultado >= 0) {
                        System.out.println("Carro ingresado en el puesto " + resultado);
                    } else {
                        System.out.println("Error al ingresar carro: " + resultado);
                    }
                    break;
                case 2:
                    System.out.println("Ingrese la placa del carro que sale:");
                    placa = scanner.nextLine();
                    int puestoSalida = parqueadero.sacarCarro(placa);
                    if (puestoSalida >= 0) {
                        System.out.println("Carro salido del puesto " + puestoSalida);
                    } else {
                        System.out.println("Error al sacar carro: " + puestoSalida);
                    }
                    break;
                case 3:
                    System.out.println("Ingresos del parqueadero: " + parqueadero.darMontoCaja());
                    break;
                case 4:
                    System.out.println("Cantidad de puestos disponibles: " + parqueadero.calcularPuestosLibres());
                    break;
                case 5:
                    System.out.println("Ingrese la cantidad de horas a avanzar:");
                    int horas = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    parqueadero.avanzarHora(horas);
                    break;
                case 6:
                    System.out.println("Ingrese la nueva tarifa por hora:");
                    int tarifa = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    parqueadero.cambiarTarifa(tarifa);
                    break;
                case 7:
                    System.out.println("Tiempo promedio de carros en parqueadero: " + parqueadero.darTiempoPromedio() + " horas");
                    break;
                case 8:
                    Carro carroMasHoras = parqueadero.darCarroMasDeOchoHoras();
                    System.out.println("Carro con más horas en parqueadero: " + (carroMasHoras != null ? carroMasHoras.darPlaca() : "No hay carros"));
                    break;
                case 9:
                    System.out.println("¿Hay carro más de 8 horas? " + (parqueadero.hayCarroMasDeOchoHoras() ? "Sí" : "No"));
                    break;
                case 10:
                    ArrayList<Carro> carrosMasTresHoras = parqueadero.darCarrosMasDeTresHorasParqueados();
                    System.out.println("Carros más de 3 horas: " + carrosMasTresHoras.size());
                    break;
                case 11:
                    System.out.println("¿Hay carros con placa igual? " + (parqueadero.hayCarrosPlacaIgual() ? "Sí" : "No"));
                    break;
                case 12:
                    System.out.println("Cantidad de carros con placa PB: " + parqueadero.contarCarrosQueComienzanConPlacaPB());
                    break;
                case 13:
                    System.out.println("¿Hay carro con 24 horas? " + (parqueadero.hayCarroCon24Horas() ? "Sí" : "No"));
                    break;
                case 14:
                    System.out.println("Cantidad de carros sacados: " + parqueadero.desocuparParqueadero());
                    break;
                case 15:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }
}

