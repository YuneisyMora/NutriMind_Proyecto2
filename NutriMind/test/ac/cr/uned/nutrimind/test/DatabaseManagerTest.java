package ac.cr.uned.nutrimind.test;

import ac.cr.uned.nutrimind.modelos.Usuario;
import ac.cr.uned.nutrimind.resources.DatabaseManager;
import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

    @BeforeClass
    public static void setupAll() {
        System.out.println(">>> Iniciando pruebas de DatabaseManager...");
    }

    @Before
    public void setup() throws SQLException {
        // Aquí podrías limpiar tablas o preparar datos de prueba si quieres
        DatabaseManager.cerrarConexion();
    }

    @Test
    public void testCalcularIMC() {
        double imc = DatabaseManager.calcularIMC(70, 175);
        assertEquals(22.86, imc, 0.01); // tolerancia de 0.01
    }

    @Test
    public void testClasificarIMC() {
        assertEquals("Normal", DatabaseManager.clasificarIMC(22.0));
        assertEquals("Bajo peso", DatabaseManager.clasificarIMC(17.0));
        assertEquals("Sobrepeso", DatabaseManager.clasificarIMC(28.0));
    }

    @Test
    public void testInsertarYObtenerUsuario() throws SQLException {
        DatabaseManager.insertarUsuario("testuser", "1234");
        var usuarios = DatabaseManager.obtenerUsuarios();
        assertTrue(usuarios.contains("testuser"));
    }

    @Test
    public void testAutenticarUsuario() throws SQLException {
        Usuario u = new Usuario();
        u.setUsuario("testuser");
        u.setContrasena("1234");
        assertTrue(DatabaseManager.autenticarUsuario(u));
    }

    @After
    public void tearDown() throws SQLException {
        DatabaseManager.cerrarConexion();
    }

    @AfterClass
    public static void tearDownAll() {
        System.out.println(">>> Pruebas finalizadas.");
    }
}
