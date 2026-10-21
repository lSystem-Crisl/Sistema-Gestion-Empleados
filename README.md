# Sistema de Gestión de Empleados

## Descripción

Aplicación de escritorio desarrollada en Java Swing para la gestión de empleados, conectada a una base de datos MySQL mediante JDBC.

## Funcionalidades

- Registro de empleados.
- Actualización de información.
- Búsqueda de empleados por DNI.
- Listado de empleados.
- Eliminación lógica mediante el cambio de estado activo/inactivo.
- Eliminación física de registros.
- Validación de los datos ingresados.
- Gestión de tipos de empleados mediante una clave foránea.

## Tecnologías utilizadas

- Java
- Java Swing
- JDBC
- MySQL
- Eclipse IDE
- Git y GitHub

## Base de datos

El archivo `BD_empleado.sql` contiene el script SQL de la base de datos del proyecto.

La tabla `empleado` incluye el campo `estado`, que permite identificar si un empleado está activo o inactivo.

## Estructura del proyecto

- `entity`: entidades del sistema.
- `model`: operaciones de acceso a datos.
- `util`: conexión a MySQL y utilidades.
- `vista`: interfaces gráficas.

## Configuración

1. Instalar Java y MySQL.
2. Ejecutar el script `BD_EMPLEADO_EVT2.sql` en MySQL Workbench.
3. Configurar las credenciales de conexión en `MySqlDBConexion.java`.
4. Abrir el proyecto en Eclipse y ejecutar el formulario principal.

Autor
Cristhian Elfer Ñahui Navarro

Profesor: Jacinto Gutarra Jorge Lorenzo

Curso: Implementación de Sistemas de Información

Evaluación T03