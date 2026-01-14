public class Facturador {
    // Tarea 3: Code Smell - Primitive Obsession (Uso de arrays en lugar de objetos)
    static String[][] repertorio = {
        {"Tributo Robe", "heavy"},
        {"Homanaje Queen", "rock"},
        {"Magia Knoppler", "pop"}, // Prueba 3: Provoca "Tipo de concierto desconocido"
        {"Demonios Rojos", "heavy"}
    };

    static Integer[][] actuaciones = {
        {0, 400}, // Prueba 1: Cambio a 400 asistentes
        {2, 1200},
        {0, 950},
        {3, 1140},
        {1, 800} // Prueba 2: Nueva actuación añadida
    };

    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) throws Exception {
        Double totalFactura = 0d;
        Integer creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (int i = 0; i < actuaciones.length; i++) {
            Integer iConcierto = actuaciones[i][0];
            Double importeActuacion = 0d;

            // Tarea 3: Code Smell - Switch Statements (Lógica dependiente de tipos)
            switch (repertorio[iConcierto][1]) {
                case "heavy":
                    importeActuacion = 4000d; // Code Smell: Magic Number
                    if (actuaciones[i][1] > 500) {
                        importeActuacion += 20 * (actuaciones[i][1] - 500);
                    }
                    break;
                case "rock":
                    importeActuacion = 3000d; // Code Smell: Magic Number
                    if (actuaciones[i][1] > 1000) {
                        importeActuacion += 30 * (actuaciones[i][1] - 1000);
                    }
                    break;
                default:
                    // Tarea 3: Code Smell - Excepción genérica
                    throw new Exception("Tipo de concierto desconocido.");
            }

            totalFactura += importeActuacion;

            // Tarea 3: Code Smell - Duplicación de lógica (se vuelve a evaluar el género)
            creditos += Math.max(actuaciones[i][1] - 500, 0);
            if (repertorio[iConcierto][1].equals("heavy")) {
                creditos += actuaciones[i][1] / 5;
            }

            System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
            System.out.println("\t\tAsistentes: " + actuaciones[i][1]);
        }

        // Tarea 3: Code Smell - Magic Numbers en el cálculo de impuestos
        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * 0.21);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * 1.21);
        System.out.println("Créditos obtenidos: " + creditos);
    }
}