package ac.cr.uned.nutrimind.resources;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:derby://localhost:1527/NutriMindDB";
    private static final String DB_USER = "nutrimind_admin";
    private static final String DB_PASS = "1234";

    private static Connection connection;

    // Conexión Singleton
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("Conexión establecida con Derby.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se encontró el driver de Derby", e);
            }
        }
        return connection;
    }

    // =====================
    // MÉTODOS PARA USUARIOS
    // =====================

    public static void insertarUsuario(String usuario, String contrasena) throws SQLException {
        String sql = "INSERT INTO Usuarios (usuario, contrasena) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ps.executeUpdate();
        }
    }

    public static List<String> obtenerUsuarios() throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT usuario FROM Usuarios";
        try (Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("usuario"));
            }
        }
        return lista;
    }

    public static boolean autenticarUsuario(String usuario, String contrasena) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE usuario = ? AND contrasena = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    // =====================
    // MÉTODOS PARA PACIENTES
    // =====================

    public static void insertarPaciente(String identificacion, String nombre, String apellidos,
                                        String fechaNacimiento, String sexo, String fechaRegistro) throws SQLException {
        String sql = "INSERT INTO Pacientes (identificacion, nombre, apellidos, fecha_nacimiento, sexo, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, identificacion);
            ps.setString(2, nombre);
            ps.setString(3, apellidos);
            ps.setString(4, fechaNacimiento);
            ps.setString(5, sexo);
            ps.setString(6, fechaRegistro);
            ps.executeUpdate();
        }
    }

    public static List<String> obtenerPacientes() throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre || ' ' || apellidos AS nombre_completo FROM Pacientes";
        try (Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("nombre_completo"));
            }
        }
        return lista;
    }

    // =========================
    // MÉTODOS PARA EVALUACIONES
    // =========================

    public static void insertarEvaluacion(int pacienteId, int nutricionistaId, String fecha,
                                          double pesoKg, double alturaCm, String nivelActividad,
                                          double imc, String categoriaImc, String recomendaciones) throws SQLException {
        String sql = "INSERT INTO Evaluaciones (paciente_id, nutricionista_id, fecha_evaluacion, peso, altura, nivel_actividad_fisica, imc, categoria_imc, recomendaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, pacienteId);
            ps.setInt(2, nutricionistaId);
            ps.setString(3, fecha);
            ps.setDouble(4, pesoKg);
            ps.setDouble(5, alturaCm);
            ps.setString(6, nivelActividad);
            ps.setDouble(7, imc);
            ps.setString(8, categoriaImc);
            ps.setString(9, recomendaciones);
            ps.executeUpdate();
        }
    }

    // ==============================
    // MÉTODOS PARA PLANES ALIMENTACIÓN
    // ==============================

    public static void insertarPlan(int pacienteId, int nutricionistaId, String fechaInicio, String fechaFinal,
                                    String planTexto, String macronutrientes, int comidasDia,
                                    String alimentosRecomendados, String observaciones) throws SQLException {
        String sql = "INSERT INTO Planes_Alimentacion (paciente_id, nutricionista_id, fecha_inicio, fecha_final, plan_texto, macronutrientes, comidas_dia, alimentos_recomendados, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, pacienteId);
            ps.setInt(2, nutricionistaId);
            ps.setString(3, fechaInicio);
            ps.setString(4, fechaFinal);
            ps.setString(5, planTexto);
            ps.setString(6, macronutrientes);
            ps.setInt(7, comidasDia);
            ps.setString(8, alimentosRecomendados);
            ps.setString(9, observaciones);
            ps.executeUpdate();
        }
    }

    // =====================
    // CIERRE DE CONEXIÓN
    // =====================

    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

