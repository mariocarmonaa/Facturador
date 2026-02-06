public class Facturador {

    public enum TipoConcierto {
        HEAVY, ROCK
    }

    private static final Double BASE_HEAVY = 4000.0;
    private static final Double BASE_ROCK = 3000.0;

    private static final Integer UMBRAL_HEAVY = 500;
    private static final Integer UMBRAL_ROCK = 1000;

    private static final Integer EXTRA_HEAVY = 20;
    private static final Integer EXTRA_ROCK = 30;

    private static final Integer DIVISOR_CREDITOS_HEAVY = 5;

    private static final Double IVA = 0.21;
    private static final Double FACTOR_IVA = 1.21;

    static Object[][] conciertos = {
            { "Tributo Robe", TipoConcierto.HEAVY },
            { "Homaneje Queen", TipoConcierto.ROCK },
            { "Magia Knoppler", TipoConcierto.ROCK },
            { "Demonios Rojos", TipoConcierto.HEAVY }
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
            TipoConcierto tipo = (TipoConcierto) conciertos[indiceConcierto][1];

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

    public static Double calcularImporteActuacion(TipoConcierto tipo, Integer asistentes) {
        Double importeActuacion;

        switch (tipo) {
            case HEAVY:
                importeActuacion = BASE_HEAVY;
                if (asistentes > UMBRAL_HEAVY) {
                    importeActuacion += EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
                }
                break;

            case ROCK:
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

    public static Integer calcularCreditos(TipoConcierto tipo, Integer asistentes) {
        Integer creditos = Math.max(asistentes - UMBRAL_HEAVY, 0);

        if (TipoConcierto.HEAVY.equals(tipo)) {
            creditos += asistentes / DIVISOR_CREDITOS_HEAVY;
        }

        return creditos;
    }
}