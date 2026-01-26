import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class FacturadorTest {

    @Test
    @DisplayName("Test constructor válido")
    public void testConstructorValido() {
        Facturador facturador = new Facturador();
        assertNotNull(facturador, "El constructor devuelve null.");
    }

    @Test
    @DisplayName("Test cabecera correcta")
    public void testCabeceraCorrecta() {
        PrintStream salidaConsola = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(baos);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        } catch (Exception e) {
            fail("Excepción inesperada.");
        }

        System.setOut(salidaConsola);

        String salida = baos.toString();
        assertTrue(salida.contains("FACTURA DE ACTUACIONES"), "La salida no contiene la cabecera esperada.");
    }

    @Test
    @DisplayName("A2. Test de regresión de salida")
    public void testSalidaRegresion() {
        PrintStream salidaConsola = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(baos);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        } catch (Exception e) {
            fail("Excepción inesperada.");
        }

        System.setOut(salidaConsola);

        String salida = baos.toString();

        // Cabecera y cliente
        assertTrue(salida.contains("FACTURA DE ACTUACIONES"), "La salida no contiene la cabecera esperada.");
        assertTrue(salida.contains("Cliente: Ayuntamiento de Badajoz"), "La salida no contiene el cliente esperado.");

        // Base imponible, IVA y total
        assertTrue(salida.contains("BASE IMPONIBLE: 72800.0"), "No contiene la base imponible esperada.");
        assertTrue(salida.contains("IVA (21%)"), "No contiene la línea del IVA.");
        assertTrue(salida.contains("15288"), "No contiene el valor 15288 en el IVA.");
        assertTrue(salida.contains("TOTAL FACTURA: 88088.00"), "No contiene el total esperado.");

        // Créditos obtenidos
        assertTrue(salida.contains("Créditos obtenidos: 4108"), "No contiene los créditos esperados.");
    }
}