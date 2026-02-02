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

                System.out.println("=== DEPURACIÓN: Salida del programa ===");
                System.out.println(salida);
                System.out.println("=== FIN DEPURACIÓN ===");

                assertTrue(salida.contains("FACTURA DE ACTUACIONES"),
                                "Falta: FACTURA DE ACTUACIONES");
                assertTrue(salida.contains("Cliente: Ayuntamiento de Badajoz"),
                                "Falta: Cliente");

                // Verificar los números clave (más flexible)
                assertTrue(salida.contains("72800.0") || salida.contains("72800,0"),
                                "Falta base imponible 72800.0");
                assertTrue(salida.contains("15288.00") || salida.contains("15288,00"),
                                "Falta IVA 15288.00");
                assertTrue(salida.contains("88088.00") || salida.contains("88088,00"),
                                "Falta total 88088.00");
                assertTrue(salida.contains("4108"),
                                "Falta créditos 4108");
        }

        @Test
        void testCalcularImporte() {
                assertEquals(4000.0, Facturador.calcularImporteActuacion("heavy", 400));
                assertEquals(4000.0 + 20 * (1000 - 500),
                                Facturador.calcularImporteActuacion("heavy", 1000));
                assertEquals(3000.0, Facturador.calcularImporteActuacion("rock", 800));
                assertEquals(3000.0 + 30 * (1500 - 1000),
                                Facturador.calcularImporteActuacion("rock", 1500));
        }

        @Test
        void testCalcularCreditos() {
                assertEquals(400 / 5, Facturador.calcularCreditos("heavy", 400));
                assertEquals((1000 - 500) + (1000 / 5),
                                Facturador.calcularCreditos("heavy", 1000));
                assertEquals(0, Facturador.calcularCreditos("rock", 400));
                assertEquals(1000 - 500,
                                Facturador.calcularCreditos("rock", 1000));
        }
}