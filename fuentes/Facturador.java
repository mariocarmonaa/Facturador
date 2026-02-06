public class Facturador {

    private static final String TIPO_HEAVY = "heavy";
    private static final String TIPO_ROCK = "rock";

    private static final Double BASE_HEAVY = 4000.0;
    private static final Double BASE_ROCK = 3000.0;

    private static final Integer UMBRAL_HEAVY = 500;
    private static final Integer UMBRAL_ROCK = 1000;

    private static final Integer EXTRA_HEAVY = 20;
    private static final Integer EXTRA_ROCK = 30;

    private static final Integer DIVISOR_CREDITOS_HEAVY = 5;

    private static final Double IVA = 0.21;
    private static final Double FACTOR_IVA = 1.21;

    static String[][] conciertos = {
            { "Tributo Robe", TIPO_HEAVY },
            { "Homaneje Queen", TIPO_ROCK },
            { "Magia Knoppler", TIPO_ROCK },
            { "Demonios Rojos", TIPO_HEAVY }
    };

    static Integer[][] actuacionesRealizadas = {
            { 0, 2000 }, { 2, 1200 }, { 0, 950 }, { 3, 1140 }
    };

    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) {
        Double totalFactura = 0.0;
        Integer creditosTotales = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (Integer i = 0; i < actuacionesRealizadas.length; i++) {
            Integer indiceConcierto = actuacionesRealizadas[i][0];
            Integer asistentes = actuacionesRealizadas[i][1];
            String tipo = conciertos[indiceConcierto][1];

            Double importeActuacion = calcularImporteActuacion(tipo, asistentes);
            totalFactura += importeActuacion;

            creditosTotales += calcularCreditos(tipo, asistentes);

            System.out.println("\tConcierto: " + conciertos[indiceConcierto][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * FACTOR_IVA);
        System.out.println("Créditos obtenidos: " + creditosTotales);
    }

    public static Double calcularImporteActuacion(String tipo, Integer asistentes) {
        Double importeActuacion;

        switch (tipo) {
            case TIPO_HEAVY:
                importeActuacion = BASE_HEAVY;
                if (asistentes > UMBRAL_HEAVY) {
                    importeActuacion += EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
                }
                break;

            case TIPO_ROCK:
                importeActuacion = BASE_ROCK;
                if (asistentes > UMBRAL_ROCK) {
                    importeActuacion += EXTRA_ROCK * (asistentes - UMBRAL_ROCK);
                }
                break;

            default:
                throw new IllegalArgumentException("Tipo de concierto desconocido: " + tipo);
        }

        return importeActuacion;
    }

    public static Integer calcularCreditos(String tipo, Integer asistentes) {
        Integer creditos = Math.max(asistentes - UMBRAL_HEAVY, 0);

        if (TIPO_HEAVY.equals(tipo)) {
            creditos += asistentes / DIVISOR_CREDITOS_HEAVY;
        }

        return creditos;
    }
}