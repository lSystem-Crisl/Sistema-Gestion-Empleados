
package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Empleado;
import entity.TipoEmpleado;
import model.EmpleadoModel;
import model.TipoEmpleadoModel;
import util.ValidateUtil;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmCrudEmpleado extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDNI;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtFechaNacimiento;
    private JTextField txtSueldo;
    private JTextField txtBuscar;

    private JComboBox<String> cboTipo;
    private JCheckBox chkEstado;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminarLogico;
    private JButton btnEliminarFisico;
    private JButton btnLimpiar;
    private JButton btnBuscar;
    private JButton btnListar;

    private EmpleadoModel empleadoModel = new EmpleadoModel();

    private int idEmpleadoSeleccionado = -1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmCrudEmpleado frame = new FrmCrudEmpleado();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmCrudEmpleado() {
        setTitle("Mantenimiento de Empleados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 1180, 760);
        setLocationRelativeTo(null);
        setResizable(true);

        contentPane = new JPanel();
        contentPane.setForeground(new Color(0, 0, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("MANTENIMIENTO DE EMPLEADOS");
        lblTitulo.setForeground(new Color(0, 0, 0));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(175, 11, 830, 30);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(25, 55, 100, 25);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 55, 100, 25);
        txtId.setEditable(false);
        contentPane.add(txtId);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(25, 90, 100, 25);
        contentPane.add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(130, 90, 220, 25);
        contentPane.add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(380, 90, 100, 25);
        contentPane.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(480, 90, 220, 25);
        contentPane.add(txtApellidos);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(730, 90, 100, 25);
        contentPane.add(lblDni);

        txtDNI = new JTextField();
        txtDNI.setBounds(810, 90, 180, 25);
        contentPane.add(txtDNI);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(25, 130, 100, 25);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(130, 130, 220, 25);
        contentPane.add(txtEmail);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(380, 130, 100, 25);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(480, 130, 220, 25);
        contentPane.add(txtTelefono);

        JLabel lblFecha = new JLabel("Fecha nacimiento:");
        lblFecha.setBounds(730, 130, 110, 25);
        contentPane.add(lblFecha);

        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setBounds(845, 130, 145, 25);
        contentPane.add(txtFechaNacimiento);

        JLabel lblSueldo = new JLabel("Sueldo:");
        lblSueldo.setBounds(25, 170, 100, 25);
        contentPane.add(lblSueldo);

        txtSueldo = new JTextField();
        txtSueldo.setBounds(130, 170, 220, 25);
        contentPane.add(txtSueldo);

        JLabel lblTipo = new JLabel("Tipo empleado:");
        lblTipo.setBounds(380, 170, 100, 25);
        contentPane.add(lblTipo);

        cboTipo = new JComboBox<String>();
        cboTipo.setBounds(480, 170, 220, 25);
        contentPane.add(cboTipo);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(730, 170, 100, 25);
        contentPane.add(lblEstado);

        chkEstado = new JCheckBox("Activo");
        chkEstado.setSelected(true);
        chkEstado.setBounds(810, 170, 100, 25);
        contentPane.add(chkEstado);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\001-agregar.png"));
        btnRegistrar.setBounds(25, 215, 140, 30);
        btnRegistrar.addActionListener(this);
        contentPane.add(btnRegistrar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\editar.png"));
        btnActualizar.setBounds(175, 215, 135, 30);
        btnActualizar.addActionListener(this);
        contentPane.add(btnActualizar);

        btnEliminarLogico = new JButton("Eliminar Lógico");
        btnEliminarLogico.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\remove.gif"));
        btnEliminarLogico.setBounds(320, 215, 160, 30);
        btnEliminarLogico.addActionListener(this);
        contentPane.add(btnEliminarLogico);

        btnEliminarFisico = new JButton("Eliminar Físico");
        btnEliminarFisico.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\eliminar.png"));
        btnEliminarFisico.setBounds(490, 215, 160, 30);
        btnEliminarFisico.addActionListener(this);
        contentPane.add(btnEliminarFisico);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\005-limpiar.png"));
        btnLimpiar.setBounds(660, 215, 145, 30);
        btnLimpiar.addActionListener(this);
        contentPane.add(btnLimpiar);

        JLabel lblBuscar = new JLabel("Buscar por DNI:");
        lblBuscar.setBounds(25, 270, 130, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(155, 270, 220, 25);
        contentPane.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\004-buscar.png"));
        btnBuscar.setBounds(385, 270, 144, 25);
        btnBuscar.addActionListener(this);
        contentPane.add(btnBuscar);

        btnListar = new JButton("Listar todos");
        btnListar.setIcon(new ImageIcon("D:\\WS_ELITEC\\Evaluacion_T2\\icons\\005-reporte-de-negocios.png"));
        btnListar.setBounds(539, 270, 145, 25);
        btnListar.addActionListener(this);
        contentPane.add(btnListar);

        String[] columnas = {
            "ID", "Nombres", "Apellidos", "DNI", "Email",
            "Teléfono", "Fecha nacimiento", "Sueldo",
            "Tipo empleado", "Estado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(25, 315, 1110, 370);
        contentPane.add(scroll);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                cargarFilaSeleccionada();
            }
        });

        cargarComboTipo();
        listarEmpleados();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            registrar();
        } else if (e.getSource() == btnActualizar) {
            actualizar();
        } else if (e.getSource() == btnEliminarLogico) {
            eliminarLogico();
        } else if (e.getSource() == btnEliminarFisico) {
            eliminarFisico();
        } else if (e.getSource() == btnLimpiar) {
            limpiar();
        } else if (e.getSource() == btnBuscar) {
            buscar();
        } else if (e.getSource() == btnListar) {
            txtBuscar.setText("");
            listarEmpleados();
        }
    }

    private Empleado obtenerDatosFormulario(boolean requiereId) {
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String dni = txtDNI.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String fecha = txtFechaNacimiento.getText().trim();
        String sueldoTexto = txtSueldo.getText().trim();

        if (requiereId && idEmpleadoSeleccionado == -1) {
            mostrarError("Seleccione un empleado de la tabla.");
            return null;
        }

        if (nombres.isEmpty() || apellidos.isEmpty() || dni.isEmpty()
                || email.isEmpty() || telefono.isEmpty()
                || fecha.isEmpty() || sueldoTexto.isEmpty()) {
            mostrarError("Todos los campos son obligatorios.");
            return null;
        }

        if (!nombres.matches(ValidateUtil.TEXTO_40)) {
            mostrarError("Los nombres deben contener solo letras y espacios, con máximo 40 caracteres.");
            txtNombres.requestFocus();
            return null;
        }

        if (!apellidos.matches(ValidateUtil.TEXTO_40)) {
            mostrarError("Los apellidos deben contener solo letras y espacios, con máximo 40 caracteres.");
            txtApellidos.requestFocus();
            return null;
        }

        if (!dni.matches(ValidateUtil.DNI)) {
            mostrarError("El DNI debe contener exactamente 8 dígitos.");
            txtDNI.requestFocus();
            return null;
        }

        if (!email.matches(ValidateUtil.EMAIL)) {
            mostrarError("Ingrese un correo electrónico válido.");
            txtEmail.requestFocus();
            return null;
        }

        if (!telefono.matches("\\d{9,15}")) {
            mostrarError("El teléfono debe contener entre 9 y 15 dígitos.");
            txtTelefono.requestFocus();
            return null;
        }

        LocalDate fechaNacimiento;

        try {
            fechaNacimiento = LocalDate.parse(fecha);

            if (!fechaNacimiento.isBefore(LocalDate.now())) {
                mostrarError("La fecha de nacimiento debe ser anterior a hoy.");
                txtFechaNacimiento.requestFocus();
                return null;
            }
        } catch (DateTimeParseException ex) {
            mostrarError("Fecha inválida. Use el formato YYYY-MM-DD.");
            txtFechaNacimiento.requestFocus();
            return null;
        }

        if (!sueldoTexto.matches(ValidateUtil.REAL_CON_O_SIN_DECIMALES)) {
            mostrarError("Ingrese un sueldo válido, con máximo dos decimales.");
            txtSueldo.requestFocus();
            return null;
        }

        double sueldo;

        try {
            sueldo = Double.parseDouble(sueldoTexto);

            if (!Double.isFinite(sueldo) || sueldo < 0) {
                mostrarError("El sueldo debe ser un número positivo o cero.");
                txtSueldo.requestFocus();
                return null;
            }
        } catch (NumberFormatException ex) {
            mostrarError("El sueldo no es válido.");
            return null;
        }

        if (cboTipo.getSelectedIndex() <= 0
                || cboTipo.getSelectedItem() == null) {
            mostrarError("Seleccione un tipo de empleado.");
            cboTipo.requestFocus();
            return null;
        }

        int idExcluir = requiereId ? idEmpleadoSeleccionado : 0;

        if (empleadoModel.existeDni(dni, idExcluir)) {
            mostrarError("Ya existe otro empleado con ese DNI.");
            txtDNI.requestFocus();
            return null;
        }

        if (empleadoModel.existeEmail(email, idExcluir)) {
            mostrarError("Ya existe otro empleado con ese correo.");
            txtEmail.requestFocus();
            return null;
        }

        String tipoSeleccionado = cboTipo.getSelectedItem().toString();
        int idTipo = Integer.parseInt(tipoSeleccionado.split(" - ")[0]);

        TipoEmpleado tipo = new TipoEmpleado();
        tipo.setIdTipoEmpleado(idTipo);

        Empleado empleado = new Empleado();

        if (requiereId) {
            empleado.setIdEmpleado(idEmpleadoSeleccionado);
        }

        empleado.setNombres(nombres);
        empleado.setApellidos(apellidos);
        empleado.setDni(dni);
        empleado.setEmail(email);
        empleado.setTelefono(telefono);
        empleado.setFechaNacimiento(fechaNacimiento);
        empleado.setSueldo(sueldo);
        empleado.setTipoEmpleado(tipo);
        empleado.setEstado(chkEstado.isSelected() ? 1 : 0);

        return empleado;
    }

    private void registrar() {
        Empleado empleado = obtenerDatosFormulario(false);

        if (empleado == null) {
            return;
        }

        int resultado = empleadoModel.insertaEmpleado(empleado);

        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Empleado registrado correctamente.");
            listarEmpleados();
            limpiar();
        } else {
            mostrarError("No se pudo registrar el empleado. Revise la consola de Eclipse.");
        }
    }

    private void actualizar() {
        Empleado empleado = obtenerDatosFormulario(true);

        if (empleado == null) {
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "¿Desea actualizar este empleado?",
                "Confirmar actualización",
                JOptionPane.YES_NO_OPTION);

        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        int resultado = empleadoModel.actualizaEmpleado(empleado);

        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente.");
            listarEmpleados();
            limpiar();
        } else {
            mostrarError("No se pudo actualizar el empleado.");
        }
    }


private void eliminarLogico() {
    if (idEmpleadoSeleccionado == -1) {
        mostrarError("Seleccione un empleado de la tabla.");
        return;
    }

    int fila = tabla.getSelectedRow();

    if (fila < 0) {
        mostrarError("Seleccione un empleado de la tabla.");
        return;
    }

    int estadoActual = modeloTabla.getValueAt(fila, 9).toString().equals("Activo") ? 1 : 0;
    int nuevoEstado = estadoActual == 1 ? 0 : 1;

    String mensaje = nuevoEstado == 0
            ? "¿Desea cambiar al empleado a INACTIVO?"
            : "¿Desea volver a activar al empleado?";

    int confirmar = JOptionPane.showConfirmDialog(
            this,
            mensaje,
            "Cambiar estado",
            JOptionPane.YES_NO_OPTION);

    if (confirmar != JOptionPane.YES_OPTION) {
        return;
    }

    int resultado = empleadoModel.actualizarEstado(
            idEmpleadoSeleccionado, nuevoEstado);

    if (resultado > 0) {
        JOptionPane.showMessageDialog(
                this,
                nuevoEstado == 1
                        ? "Empleado activado correctamente."
                        : "Empleado desactivado correctamente.");

        listarEmpleados();
        limpiar();
    } else {
        mostrarError("No se pudo cambiar el estado del empleado.");
    }
}


    private void eliminarFisico() {
        if (idEmpleadoSeleccionado == -1) {
            mostrarError("Seleccione un empleado de la tabla.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar el empleado seleccionado? Esta acción no se puede deshacer.",
                "Confirmar eliminación física",
                JOptionPane.YES_NO_OPTION);

        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        int resultado = empleadoModel.eliminarFisico(idEmpleadoSeleccionado);

        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Empleado eliminado físicamente.");
            listarEmpleados();
            limpiar();
        } else {
            mostrarError("No se pudo eliminar el empleado. Compruebe si otra tabla lo referencia.");
        }
    }

    private void buscar() {
        String dni = txtBuscar.getText().trim();

        if (dni.isEmpty()) {
            mostrarError("Ingrese el DNI que desea buscar.");
            txtBuscar.requestFocus();
            return;
        }

        if (!dni.matches(ValidateUtil.DNI)) {
            mostrarError("El DNI debe contener exactamente 8 dígitos.");
            txtBuscar.requestFocus();
            return;
        }

        llenarTabla(empleadoModel.listaPorDni(dni));

        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontró un empleado con ese DNI.");
        }
    }

    private void listarEmpleados() {
        llenarTabla(empleadoModel.listaTodos());
    }

    private void llenarTabla(List<Empleado> lista) {
        modeloTabla.setRowCount(0);

        for (Empleado empleado : lista) {
            Object[] fila = {
                empleado.getIdEmpleado(),
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getDni(),
                empleado.getEmail(),
                empleado.getTelefono(),
                empleado.getFechaNacimiento(),
                empleado.getSueldo(),
                empleado.getTipoEmpleado().getDescripcion(),
                empleado.getEstado() == 1 ? "Activo" : "Inactivo"
            };

            modeloTabla.addRow(fila);
        }
    }

    private void cargarFilaSeleccionada() {
        int fila = tabla.getSelectedRow();

        if (fila < 0) {
            return;
        }

        idEmpleadoSeleccionado =
                Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());

        txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
        txtNombres.setText(modeloTabla.getValueAt(fila, 1).toString());
        txtApellidos.setText(modeloTabla.getValueAt(fila, 2).toString());
        txtDNI.setText(modeloTabla.getValueAt(fila, 3).toString());
        txtEmail.setText(modeloTabla.getValueAt(fila, 4).toString());
        txtTelefono.setText(modeloTabla.getValueAt(fila, 5).toString());
        txtFechaNacimiento.setText(modeloTabla.getValueAt(fila, 6).toString());
        txtSueldo.setText(modeloTabla.getValueAt(fila, 7).toString());

        String descripcion = modeloTabla.getValueAt(fila, 8).toString();

        for (int i = 1; i < cboTipo.getItemCount(); i++) {
            String item = cboTipo.getItemAt(i);

            if (item.endsWith(" - " + descripcion)) {
                cboTipo.setSelectedIndex(i);
                break;
            }
        }

        chkEstado.setSelected(
                modeloTabla.getValueAt(fila, 9).toString().equals("Activo"));
    }

    private void cargarComboTipo() {
        cboTipo.removeAllItems();
        cboTipo.addItem("[ Seleccione un tipo ]");

        TipoEmpleadoModel tipoModel = new TipoEmpleadoModel();
        List<TipoEmpleado> tipos = tipoModel.listarTipoEmpleado();

        for (TipoEmpleado tipo : tipos) {
            cboTipo.addItem(
                    tipo.getIdTipoEmpleado() + " - " + tipo.getDescripcion());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDNI.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtFechaNacimiento.setText("");
        txtSueldo.setText("");
        txtBuscar.setText("");

        if (cboTipo.getItemCount() > 0) {
            cboTipo.setSelectedIndex(0);
        }

        chkEstado.setSelected(true);
        idEmpleadoSeleccionado = -1;
        tabla.clearSelection();
        txtNombres.requestFocus();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Validación",
                JOptionPane.WARNING_MESSAGE);
    }
}
