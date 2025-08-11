package ac.cr.uned.nutrimind.resources;



import ac.cr.uned.nutrimind.modelos.Evaluacion;
import ac.cr.uned.nutrimind.modelos.Paciente;
import ac.cr.uned.nutrimind.modelos.PlanAlimentacion;
import ac.cr.uned.nutrimind.modelos.Usuario;
import java.math.BigDecimal;
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

    public static boolean autenticarUsuario(Usuario usuario) throws SQLException {
    String sql = "SELECT COUNT(*) FROM Usuarios WHERE usuario = ? AND contrasena = ?";
    System.out.println("SQL: " + sql);
    System.out.println("Usuario: " + usuario.getUsuario());
    System.out.println("Contrasena: " + usuario.getContrasena());
    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setString(1, usuario.getUsuario());
        ps.setString(2, usuario.getContrasena());
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Count result: " + count);
                return count > 0;
            } else {
                System.out.println("No hay resultados");
                return false;
            }
        }
    }
}


    // =====================
    // MÉTODOS PARA PACIENTES
    // =====================

    public static void insertarPaciente(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO Pacientes (identificacion, nombre, apellidos, fecha_nacimiento, sexo, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            
            // char sexo_char = (paciente.getSexo()).charAt(0);
            ps.setString(1, paciente.getIdentificacion());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellidos());
            ps.setString(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getFechaRegistro());
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

    public static void insertarEvaluacion(Evaluacion evaluacion) throws SQLException {
        String sql = "INSERT INTO Evaluaciones (paciente_identificacion, nutricionista_id, fecha_evaluacion, peso, altura, nivel_actividad_fisica, imc, categoria_imc, recomendaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, evaluacion.getPacienteId());
            ps.setInt(2, evaluacion.getNutricionistaId());
            ps.setString(3, evaluacion.getFechaEvaluacion());
            ps.setDouble(4, evaluacion.getPeso().doubleValue());
            ps.setDouble(5, evaluacion.getAltura().doubleValue());
            ps.setString(6, evaluacion.getNivelActividadFisica());
            ps.setDouble(7, evaluacion.getImc().doubleValue());
            ps.setString(8, evaluacion.getCategoriaImc());
            ps.setString(9, evaluacion.getRecomendaciones());
            ps.executeUpdate();
        }
    }

    // ==============================
    // MÉTODOS PARA PLANES ALIMENTACIÓN
    // ==============================

    public static void insertarPlan(PlanAlimentacion plan) throws SQLException {
        String sql = "INSERT INTO Planes_Alimentacion (paciente_identificacion, nutricionista_id, fecha_inicio, fecha_final, plan_texto, macronutrientes, comidas_dia, alimentos_recomendados, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, plan.getPacienteId());
            ps.setInt(2, plan.getNutricionistaId());
            ps.setString(3, plan.getFechaInicio());
            ps.setString(4, plan.getFechaFinal());
            ps.setString(5, plan.getPlanTexto());
            ps.setString(6, plan.getMacronutrientes());
            ps.setInt(7, plan.getComidasDia());
            ps.setString(8, plan.getAlimentosRecomendados());
            ps.setString(9, plan.getObservaciones());
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
    
    
    public static String obtenerRolPorUsuario(String nombreUsuario) throws SQLException {
    String rol = null;

    // Obtener el id del usuario
    String sqlUsuario = "SELECT id FROM Usuarios WHERE usuario = ?";
    try (PreparedStatement psUsuario = getConnection().prepareStatement(sqlUsuario)) {
        psUsuario.setString(1, nombreUsuario);
        try (ResultSet rsUsuario = psUsuario.executeQuery()) {
            if (rsUsuario.next()) {
                int usuarioId = rsUsuario.getInt("id");

                // Obtener el rol para el usuario
                String sqlRol = "SELECT nombre FROM Roles WHERE usuario_id = ?";
                try (PreparedStatement psRol = getConnection().prepareStatement(sqlRol)) {
                    psRol.setInt(1, usuarioId);
                    try (ResultSet rsRol = psRol.executeQuery()) {
                        if (rsRol.next()) {
                            rol = rsRol.getString("nombre");
                        }
                    }
                }
            } else {
                System.out.println("Usuario no encontrado: " + nombreUsuario);
            }
        }
    }

    return rol;
    }
    
    public static Integer obtenerRolIdPorUsuario(String nombreUsuario) throws SQLException {
    String sql = """
        SELECT r.id
        FROM Roles r
        INNER JOIN Usuarios u ON r.usuario_id = u.id
        WHERE u.usuario = ?
    """;

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setString(1, nombreUsuario.trim());
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id"); // devuelve el rol_id
            }
        }
    }
    return null; // Si no se encuentra
    }
    
    public static double calcularIMC(double pesoKg, double alturaCm) {
    double alturaM = alturaCm / 100.0;
    return pesoKg / (alturaM * alturaM);
}
    
    public static String clasificarIMC(double imc) {
    if (imc < 18.5) {
        return "Bajo peso";
    } else if (imc < 25) {
        return "Normal";
    } else if (imc < 30) {
        return "Sobrepeso";
    } else if (imc < 35) {
        return "Obesidad I";
    } else if (imc < 40) {
        return "Obesidad II";
    } else {
        return "Obesidad III";
    }
}
    
    public static String recomendacionesPorIMC(double imc) {
    if (imc < 18.5) {
        return """
            Promover aumento de peso saludable con alimentos densos en nutrientes.
            • Aumentar consumo de carbohidratos complejos como arroz integral, avena y papas.
            • Incluir fuentes saludables de grasas: aguacate, nueces, aceite de oliva.
            • Incorporar 5 comidas al día con refrigerios entre cada comida principal.
            • Priorizar proteínas de calidad: huevos, pollo, pescado y legumbres.
            • Evitar saltarse comidas.
        """;
    } else if (imc < 25) {
        return """
            Mantener el estado nutricional óptimo.
            • Mantener una dieta balanceada: 50% carbohidratos, 20% proteínas, 30% grasas saludables.
            • Incluir variedad de frutas y vegetales diariamente (al menos 5 porciones).
            • Beber al menos 8 vasos de agua al día.
            • Controlar porciones y evitar consumo excesivo de azúcares simples.
            • Continuar con actividad física regular.
        """;
    } else if (imc < 30) {
        return """
            Reducir peso de forma progresiva.
            • Disminuir consumo de harinas refinadas y azúcares procesados.
            • Limitar frituras, refrescos y comida rápida.
            • Aumentar la ingesta de fibra (avena, vegetales crudos, frutas con cáscara).
            • Realizar al menos 30 minutos de actividad física diaria.
            • Comer porciones pequeñas con mayor frecuencia (4 a 5 comidas).
        """;
    } else if (imc < 35) {
        return """
            Reducir peso y prevenir complicaciones metabólicas.
            • Priorizar preparación de alimentos al vapor, horneados o hervidos.
            • Eliminar bebidas azucaradas y jugos procesados.
            • Llevar registro de comidas y horarios.
            • Evitar cenas pesadas o tardías.
            • Consultar con nutricionista si es posible.
        """;
    } else {
        return """
            Intervención activa para reducción de peso controlada.
            • Requiere acompañamiento profesional (nutricionista o médico).
            • Reducción calórica gradual y controlada.
            • Fraccionar la alimentación en 5 tiempos con énfasis en vegetales.
            • Fomentar actividad física ligera progresiva (caminar, bicicleta estática).
            • Evaluar condiciones médicas asociadas (hipertensión, diabetes, etc.).
        """;
    }
}
    
public static String clasificarNivelActividadFisica(double imc) {
    if (imc < 18.5) {
        return "Sedentario";
    } else if (imc < 30) {
        return "Moderadamente activo";
    } else {
        return "Muy activo";
    }
}

public static Paciente obtenerPacientePorIdentificacion(String identificacion) throws SQLException {
    String sql = "SELECT identificacion, nombre, apellidos, fecha_nacimiento, sexo, fecha_registro FROM Pacientes WHERE identificacion = ?";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setString(1, identificacion.trim());
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdentificacion(rs.getString("identificacion"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellidos(rs.getString("apellidos"));
                paciente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setFechaRegistro(rs.getString("fecha_registro"));
                return paciente;
            }
        }
    }
    return null; 
}




public static List<Evaluacion> obtenerEvaluacionesPorIdentificacion(String identificacion) throws SQLException {
    String sql = """
        SELECT e.paciente_identificacion, e.fecha_evaluacion, e.peso, e.altura, e.nivel_actividad_fisica,
               e.imc, e.categoria_imc, e.recomendaciones
        FROM Evaluaciones e
        INNER JOIN Pacientes p ON e.paciente_identificacion = p.identificacion
        WHERE p.identificacion = ?
        ORDER BY e.fecha_evaluacion DESC
    """;

    List<Evaluacion> lista = new ArrayList<>();
    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setString(1, identificacion.trim());
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Evaluacion eval = new Evaluacion();
                eval.setPacienteId(rs.getString("paciente_identificacion"));
                eval.setFechaEvaluacion(rs.getString("fecha_evaluacion"));
                eval.setPeso(BigDecimal.valueOf(rs.getDouble("peso")));
                eval.setAltura(BigDecimal.valueOf(rs.getDouble("altura")));
                eval.setNivelActividadFisica(rs.getString("nivel_actividad_fisica"));
                eval.setImc(BigDecimal.valueOf(rs.getDouble("imc")));
                eval.setCategoriaImc(rs.getString("categoria_imc"));
                eval.setRecomendaciones(rs.getString("recomendaciones"));
                lista.add(eval);
            }
        }
    }
    return lista;
}







}

