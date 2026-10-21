
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Empleado;
import entity.TipoEmpleado;
import util.MySqlDBConexion;

public class EmpleadoModel {

    public int insertaEmpleado(Empleado empleado) {
        int insertados = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "INSERT INTO empleado "
                    + "(nombres, apellidos, dni, email, telefono, fechaNacimiento, sueldo, idTipoEmpleado, estado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, empleado.getNombres());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getDni());
            ps.setString(4, empleado.getEmail());
            ps.setString(5, empleado.getTelefono());
            ps.setDate(6, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            ps.setDouble(7, empleado.getSueldo());
            ps.setInt(8, empleado.getTipoEmpleado().getIdTipoEmpleado());
            ps.setInt(9, empleado.getEstado());

            System.out.println("SQL: " + ps);
            insertados = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(null, ps, conn);
        }

        return insertados;
    }

    public List<Empleado> listaTodos() {
        List<Empleado> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "SELECT e.*, t.descripcion "
                    + "FROM empleado e "
                    + "INNER JOIN tipoempleado t "
                    + "ON e.idTipoEmpleado = t.idTipoEmpleado "
                    + "ORDER BY e.idEmpleado";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearEmpleado(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, conn);
        }

        return lista;
    }

    public List<Empleado> listaPorDni(String dni) {
        List<Empleado> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "SELECT e.*, t.descripcion "
                    + "FROM empleado e "
                    + "INNER JOIN tipoempleado t "
                    + "ON e.idTipoEmpleado = t.idTipoEmpleado "
                    + "WHERE e.dni = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearEmpleado(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, conn);
        }

        return lista;
    }

    public List<Empleado> listaPorPk(int idEmpleado) {
        List<Empleado> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "SELECT e.*, t.descripcion "
                    + "FROM empleado e "
                    + "INNER JOIN tipoempleado t "
                    + "ON e.idTipoEmpleado = t.idTipoEmpleado "
                    + "WHERE e.idEmpleado = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearEmpleado(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, conn);
        }

        return lista;
    }

    public int actualizaEmpleado(Empleado empleado) {
        int actualizados = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "UPDATE empleado SET nombres=?, apellidos=?, dni=?, "
                    + "email=?, telefono=?, fechaNacimiento=?, sueldo=?, "
                    + "idTipoEmpleado=?, estado=? WHERE idEmpleado=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, empleado.getNombres());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getDni());
            ps.setString(4, empleado.getEmail());
            ps.setString(5, empleado.getTelefono());
            ps.setDate(6, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            ps.setDouble(7, empleado.getSueldo());
            ps.setInt(8, empleado.getTipoEmpleado().getIdTipoEmpleado());
            ps.setInt(9, empleado.getEstado());
            ps.setInt(10, empleado.getIdEmpleado());

            System.out.println("SQL: " + ps);
            actualizados = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(null, ps, conn);
        }

        return actualizados;
    }


public int actualizarEstado(int idEmpleado, int estado) {
    int actualizados = 0;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = MySqlDBConexion.getConexion();

        String sql = "UPDATE empleado SET estado=? WHERE idEmpleado=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, estado);
        ps.setInt(2, idEmpleado);

        actualizados = ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(null, ps, conn);
    }

    return actualizados;
}


    public int eliminarFisico(int idEmpleado) {
        int eliminados = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "DELETE FROM empleado WHERE idEmpleado=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);

            eliminados = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(null, ps, conn);
        }

        return eliminados;
    }

    public boolean existeDni(String dni) {
        return existeDni(dni, 0);
    }

    public boolean existeDni(String dni, int idEmpleadoExcluir) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "SELECT COUNT(*) AS cantidad FROM empleado WHERE dni=? "
                    + "AND idEmpleado<>?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setInt(2, idEmpleadoExcluir);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = rs.getInt("cantidad") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, conn);
        }

        return existe;
    }

    public boolean existeEmail(String email) {
        return existeEmail(email, 0);
    }

    public boolean existeEmail(String email, int idEmpleadoExcluir) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "SELECT COUNT(*) AS cantidad FROM empleado WHERE email=? "
                    + "AND idEmpleado<>?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, idEmpleadoExcluir);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = rs.getInt("cantidad") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, conn);
        }

        return existe;
    }

    private Empleado mapearEmpleado(ResultSet rs) throws Exception {
        Empleado empleado = new Empleado();

        empleado.setIdEmpleado(rs.getInt("idEmpleado"));
        empleado.setNombres(rs.getString("nombres"));
        empleado.setApellidos(rs.getString("apellidos"));
        empleado.setDni(rs.getString("dni"));
        empleado.setEmail(rs.getString("email"));
        empleado.setTelefono(rs.getString("telefono"));
        empleado.setFechaNacimiento(
                rs.getDate("fechaNacimiento").toLocalDate());
        empleado.setSueldo(rs.getDouble("sueldo"));
        empleado.setEstado(rs.getInt("estado"));

        TipoEmpleado tipo = new TipoEmpleado();
        tipo.setIdTipoEmpleado(rs.getInt("idTipoEmpleado"));
        tipo.setDescripcion(rs.getString("descripcion"));

        empleado.setTipoEmpleado(tipo);

        return empleado;
    }

    private void cerrarRecursos(ResultSet rs, PreparedStatement ps,
            Connection conn) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

