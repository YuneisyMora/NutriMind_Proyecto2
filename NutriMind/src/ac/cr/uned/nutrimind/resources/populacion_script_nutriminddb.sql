-- DATOS DE PRUEBA

-- Usuarios
INSERT INTO Usuarios (usuario, contrasena) VALUES
('admin', '1234'),
('nutricionista1', 'abcd'),
('asistente1', 'xyz');

-- Roles
INSERT INTO Roles (usuario_id, nombre, descripcion) VALUES
(1, 'ADMIN', 'Administrador del sistema'),
(2, 'PROF', 'Profesional de Nutrición'),
(3, 'ASIS', 'Asistente');

-- Pacientes
INSERT INTO Pacientes (identificacion, nombre, apellidos, fecha_nacimiento, sexo, fecha_registro) VALUES
('101010101', 'Juan', 'Pérez López', '1990-05-12', 'M', '2025-08-10'),
('202020202', 'María', 'Gómez Ruiz', '1985-08-25', 'F', '2025-08-10');

-- Evaluaciones 
INSERT INTO Evaluaciones (paciente_identificacion, nutricionista_id, fecha_evaluacion, peso, altura, nivel_actividad_fisica, imc, categoria_imc, recomendaciones) VALUES
('101010101', 2, '2025-08-10', 70.00, 175.00, 'Moderado', 22.86, 'Normal', 'Mantener dieta balanceada y ejercicio regular.'),
('202020202', 2, '2025-08-10', 85.00, 165.00, 'Sedentario', 31.22, 'Obesidad I', 'Reducir azúcares y aumentar actividad física.');

-- Planes de Alimentación
INSERT INTO Planes_Alimentacion (paciente_identificacion, nutricionista_id, fecha_inicio, fecha_final, plan_texto, macronutrientes, comidas_dia, alimentos_recomendados, observaciones) VALUES
('101010101', 2, '2025-08-10', NULL, 'Plan equilibrado con proteínas, carbohidratos y grasas saludables.', '50C-20P-30G', 5, 'Avena, pollo, verduras, frutas', 'Seguir por 3 meses'),
('202020202', 2, '2025-08-10', NULL, 'Plan hipocalórico con alto contenido de fibra.', '40C-30P-30G', 4, 'Ensaladas, pescado, legumbres', 'Evaluar en 1 mes');
