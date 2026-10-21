
package entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Empleado {

    private int idEmpleado;
    private String nombres;
    private String apellidos;
    private String dni;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private double sueldo;
    private TipoEmpleado tipoEmpleado;
    private int estado;
}
