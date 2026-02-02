public class Facturador {

    private static final String TIPO_HEAVY = "heavy";
    private static final String TIPO_ROCK = "rock";

    private static final double BASE_HEAVY = 4000d;
    private static final double BASE_ROCK = 3000d;

    private static final int UMBRAL_HEAVY = 500;
    private static final int UMBRAL_ROCK = 1000;

    private static final int EXTRA_HEAVY = 20;
    private static final int EXTRA_ROCK = 30;

    private static final int DIVISOR_CREDITOS_HEAVY = 5;

    private static final double IVA = 0.21;
    private static final double FACTOR_IVA = 1.21;

    static String[][] repertorio = {
            { "Tributo Robe", TIPO_HEAVY },
            { "Homaneje Queen", TIPO_ROCK },
            { "Magia Knoppler", TIPO_ROCK },
            { "Demonios Rojos", TIPO_HEAVY }
    };

    static Integer[][] actuaciones = {
            { 0, 2000 }, { 2, 1200 }, { 0, 950 }, { 3, 1140 }
    };

    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) {
        Double totalFactura = 0d;
        Integer creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (int i = 0; i < actuaciones.length; i++) {
            Integer iConcierto = actuaciones[i][0];
            Integer asistentes = actuaciones[i][1];
            String tipo = repertorio[iConcierto][1];

            Double importeActuacion = calcularImporteActuacion(tipo, asistentes);
            totalFactura += importeActuacion;

            creditos += calcularCreditos(tipo, asistentes);

            System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * FACTOR_IVA);
        System.out.println("Créditos obtenidos: " + creditos);
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