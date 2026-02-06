import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class FacturadorTest {

        @Test
        void testSalidaCompleta() {
                PrintStream originalOut = System.out;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                System.setOut(new PrintStream(baos));

                Facturador.main(new String[] {});

                System.setOut(originalOut);
                String salida = baos.toString();

                assertTrue(salida.contains("FACTURA DE ACTUACIONES"));
                assertTrue(salida.contains("Cliente: Ayuntamiento de Badajoz"));
                assertTrue(salida.contains("72800.0") || salida.contains("72800,0"));
                assertTrue(salida.contains("15288.00") || salida.contains("15288,00"));
                assertTrue(salida.contains("88088.00") || salida.contains("88088,00"));
                assertTrue(salida.contains("4108"));
        }

        @Test
        void testCalcularImporte() {
                assertEquals(4000.0, Facturador.calcularImporteActuacion(Facturador.TipoConcierto.HEAVY, 400));
                assertEquals(14000.0, Facturador.calcularImporteActuacion(Facturador.TipoConcierto.HEAVY, 1000));
                assertEquals(3000.0, Facturador.calcularImporteActuacion(Facturador.TipoConcierto.ROCK, 800));
                assertEquals(18000.0, Facturador.calcularImporteActuacion(Facturador.TipoConcierto.ROCK, 1500));
        }

        @Test
        void testCalcularCreditos() {
                assertEquals(80, Facturador.calcularCreditos(Facturador.TipoConcierto.HEAVY, 400));
                assertEquals(700, Facturador.calcularCreditos(Facturador.TipoConcierto.HEAVY, 1000));
                assertEquals(0, Facturador.calcularCreditos(Facturador.TipoConcierto.ROCK, 400));
                assertEquals(500, Facturador.calcularCreditos(Facturador.TipoConcierto.ROCK, 1000));
        }
}