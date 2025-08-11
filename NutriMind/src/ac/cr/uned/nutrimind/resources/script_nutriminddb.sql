-- CREACIÓN DE TABLAS

CREATE TABLE Usuarios (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE Roles (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    usuario_id INT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(200),
    CONSTRAINT fk_roles_usuario
        FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

CREATE TABLE Pacientes (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    identificacion VARCHAR(25) NOT NULL UNIQUE,
    nombre VARCHAR(80) NOT NULL,
    apellidos VARCHAR(120) NOT NULL,
    fecha_nacimiento VARCHAR(20),
    sexo CHAR(1),
    fecha_registro VARCHAR(20) NOT NULL
);

CREATE TABLE Evaluaciones (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    paciente_identificacion VARCHAR(25) NOT NULL,
    nutricionista_id INT NOT NULL,
    fecha_evaluacion VARCHAR(20) NOT NULL,
    peso DECIMAL(6,2) NOT NULL,
    altura DECIMAL(5,2) NOT NULL, 
    nivel_actividad_fisica VARCHAR(20) NOT NULL,
    imc DECIMAL(5,2) NOT NULL,
    categoria_imc VARCHAR(20) NOT NULL,
    recomendaciones VARCHAR(4000),
    CONSTRAINT fk_eval_paciente
        FOREIGN KEY (paciente_identificacion) REFERENCES Pacientes(identificacion),
    CONSTRAINT fk_eval_nutricionista
        FOREIGN KEY (nutricionista_id) REFERENCES Usuarios(id)
);

CREATE INDEX idx_eval_paciente_fecha
    ON Evaluaciones (paciente_identificacion, fecha_evaluacion);

CREATE TABLE Planes_Alimentacion (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    paciente_identificacion VARCHAR(25) NOT NULL,
    nutricionista_id INT NOT NULL,
    fecha_inicio VARCHAR(20) NOT NULL,
    fecha_final VARCHAR(20),
    plan_texto VARCHAR(8000),
    macronutrientes VARCHAR(100),
    comidas_dia INT,
    alimentos_recomendados VARCHAR(8000),
    observaciones VARCHAR(2000),
    CONSTRAINT fk_plan_paciente
        FOREIGN KEY (paciente_identificacion) REFERENCES Pacientes(identificacion),
    CONSTRAINT fk_plan_nutricionista
        FOREIGN KEY (nutricionista_id) REFERENCES Usuarios(id)
);
