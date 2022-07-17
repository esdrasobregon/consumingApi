/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import entity.Unidades;
import entity.Usuario;
import entity.tipoUnidades;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import services.servicesUnidades;

/**
 *
 * @author esdra
 */
public class MantenimientoUnidades extends javax.swing.JFrame {

    ArrayList<tipoUnidades> listaTipos;
    ArrayList<Unidades> listaUn;
    servicesUnidades serv;
    Usuario currentUsser;
    Unidades currentUnidad;

    /**
     * Creates new form MantenimientoUnidades
     */
    public MantenimientoUnidades() {
        initComponents();
        this.currentUsser = new Usuario();
        this.currentUsser.setNombreUsuario("edras");
        this.currentUsser.setContrasenya("12345");
        serv = new servicesUnidades();
        this.currentUsser = serv.login(currentUsser);
        this.listaTipos = services.serviceTipoUnidades.getAllTposFromApi();
        addTiposCmbTipos();
        if (this.currentUsser != null) {
            this.setTitle("Bienvenido: " + this.currentUsser.getNombreUsuario());
        } else {
            JOptionPane.showMessageDialog(null, "Usser not accepted");
        }
        this.listaUn = serv.getAllUnidFromApi();
        for (int i = 0; i < this.listaUn.size(); i++) {
            addRowToUnidades(listaUn.get(i));
        }
        justNumbers(this.txtModelo, 4);
    }

    public MantenimientoUnidades(Usuario us) {
        initComponents();
        this.currentUsser = us;
        this.setTitle("Bienvenido: " + this.currentUsser.getNombreUsuario());
        InitFormVariables();

    }

    private void InitFormVariables() {
        this.serv = new servicesUnidades();
        this.listaTipos = services.serviceTipoUnidades.getAllTposFromApi();
        addTiposCmbTipos();
        this.listaUn = serv.getAllUnidFromApi();
        for (int i = 0; i < this.listaUn.size(); i++) {
            addRowToUnidades(listaUn.get(i));
        }
        justNumbers(this.txtModelo, 4);
        addListenerToTable();
    }

    private void addListenerToTable() {
        ListSelectionModel lisSelectionModel = this.tbUnidades.getSelectionModel();
        lisSelectionModel.addListSelectionListener((e) -> {
            if (!lisSelectionModel.isSelectionEmpty()) {
                int index = lisSelectionModel.getMinSelectionIndex();
                String[] options = new String[]{"Eliminar", "Editar", "Cancelar"};
                int response = JOptionPane.showOptionDialog(null, "Message", "Title",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                Unidades un = this.listaUn.get(index);
                switch (response) {
                    case 0:
                        System.out.print(response);
                        //boolean res = this.crud.delete(un);
                        boolean res = true;
                        if (res) {
                            JOptionPane.showMessageDialog(null, "echo");
                        } else {
                            JOptionPane.showMessageDialog(null, "error");
                        }
                        break;
                    case 1:
                        //System.out.print(response);
                        this.centerPanel.setSelectedIndex(response);
                        Unidades una = getUnidadFromTable(index);
                        this.currentUnidad = una;
                        fillFormToEditUnidad();
                        break;
                    default:
                        System.out.print(response);
                }
            }
        });
    }

    private Unidades getUnidadFromTable(int index) {
        DefaultTableModel model = (DefaultTableModel) this.tbUnidades.getModel();
        Object obj = model.getValueAt(index, 0);
        Unidades un = new Unidades();
        int idBus = Integer.parseInt(obj.toString());
        //JOptionPane.showMessageDialog(null, obj.toString()+" : "+idBus);
        int indexUnidad = getUnidadByIdBus(idBus);
        un = this.listaUn.get(indexUnidad);
        return un;
    }

    private int getUnidadByIdBus(int idBus) {
        int index = 0;
        boolean found = false;
        int count = 0;
        while (!found && count < this.listaUn.size()) {
            if (this.listaUn.get(count).getIdbus() == idBus) {
                found = true;
                index = count;
            }
            count++;
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "not found");
        }
        return index;
    }

    private void justNumbers(JTextField txt, int maxLength) {
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
                if (txt.getText().length() >= maxLength) // limit to 3 characters
                {
                    e.consume();
                }
            }
        });

    }

    private void fillFormToEditUnidad() {
        if (this.currentUnidad != null) {
            this.txtIdBus.setText(this.currentUnidad.getIdbus() + "");
            this.txtMarca.setText(this.currentUnidad.getMarca());
            this.txtModelo.setText(this.currentUnidad.getModelo() + "");
            this.txtPlaca.setText(this.currentUnidad.getPlaca());
            this.callFecha.setDate(this.currentUnidad.getFecha_ingreso());
        } else {
            JOptionPane.showMessageDialog(null, "No hay unidad para editar");
        }
    }

    private void addTiposCmbTipos() {
        this.listaTipos.forEach(t -> {
            this.cmbTipo.addItem(t.getDescripcion());
        });

    }

    private void addRowToUnidades(Unidades un) {
        DefaultTableModel model = (DefaultTableModel) this.tbUnidades.getModel();
        model.addRow(new Object[]{
            un.getIdbus(),
            un.getFecha_ingreso(),
            un.getMarca(),
            un.getModelo(),
            un.getPlaca(),
            un.getTipo(),
            un.getActivo()
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        bottonPanel = new javax.swing.JPanel();
        rigthPanel = new javax.swing.JPanel();
        centerPanel = new javax.swing.JTabbedPane();
        formContainerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUnidades = new javax.swing.JTable();
        serchPanel = new javax.swing.JPanel();
        txtbuscar = new javax.swing.JTextField();
        rbPlaca = new javax.swing.JRadioButton();
        rbIdBus = new javax.swing.JRadioButton();
        btnSerch = new javax.swing.JButton();
        btnCleanSerch = new javax.swing.JButton();
        formPanelLeft = new javax.swing.JPanel();
        formPanelRigth = new javax.swing.JPanel();
        formPanelBotton = new javax.swing.JPanel();
        formPanel = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        callFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtIdBus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbActivo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        northSpaceForm = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rigthFormSpace = new javax.swing.JPanel();
        leftSpaceForm = new javax.swing.JPanel();
        bottonSpaceForm = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        topPanel.setPreferredSize(new java.awt.Dimension(400, 60));

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1095, Short.MAX_VALUE)
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        bottonPanel.setPreferredSize(new java.awt.Dimension(1095, 50));

        javax.swing.GroupLayout bottonPanelLayout = new javax.swing.GroupLayout(bottonPanel);
        bottonPanel.setLayout(bottonPanelLayout);
        bottonPanelLayout.setHorizontalGroup(
            bottonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1095, Short.MAX_VALUE)
        );
        bottonPanelLayout.setVerticalGroup(
            bottonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(bottonPanel, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout rigthPanelLayout = new javax.swing.GroupLayout(rigthPanel);
        rigthPanel.setLayout(rigthPanelLayout);
        rigthPanelLayout.setHorizontalGroup(
            rigthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        rigthPanelLayout.setVerticalGroup(
            rigthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );

        getContentPane().add(rigthPanel, java.awt.BorderLayout.EAST);

        formContainerPanel.setLayout(new java.awt.BorderLayout());

        tbUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID BUS", "FECHA INGRESO", "MARCA", "MODELO", "PLACA", "TIPO", "ACTIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbUnidades);
        if (tbUnidades.getColumnModel().getColumnCount() > 0) {
            tbUnidades.getColumnModel().getColumn(0).setResizable(false);
            tbUnidades.getColumnModel().getColumn(1).setResizable(false);
            tbUnidades.getColumnModel().getColumn(2).setResizable(false);
            tbUnidades.getColumnModel().getColumn(3).setResizable(false);
            tbUnidades.getColumnModel().getColumn(4).setResizable(false);
            tbUnidades.getColumnModel().getColumn(5).setResizable(false);
            tbUnidades.getColumnModel().getColumn(6).setResizable(false);
        }

        formContainerPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        serchPanel.setPreferredSize(new java.awt.Dimension(600, 100));

        rbPlaca.setText("PLACA");

        rbIdBus.setText("ID BUS");

        btnSerch.setText("BUSCAR");

        btnCleanSerch.setText("MOSTRAR TABLA COMPLETA");
        btnCleanSerch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanSerchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout serchPanelLayout = new javax.swing.GroupLayout(serchPanel);
        serchPanel.setLayout(serchPanelLayout);
        serchPanelLayout.setHorizontalGroup(
            serchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serchPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbPlaca)
                .addGap(18, 18, 18)
                .addComponent(rbIdBus)
                .addGap(49, 49, 49)
                .addComponent(btnSerch)
                .addGap(82, 82, 82)
                .addComponent(btnCleanSerch, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        serchPanelLayout.setVerticalGroup(
            serchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serchPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(serchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbPlaca)
                    .addComponent(rbIdBus)
                    .addComponent(btnSerch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCleanSerch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        formContainerPanel.add(serchPanel, java.awt.BorderLayout.NORTH);

        formPanelLeft.setPreferredSize(new java.awt.Dimension(60, 500));

        javax.swing.GroupLayout formPanelLeftLayout = new javax.swing.GroupLayout(formPanelLeft);
        formPanelLeft.setLayout(formPanelLeftLayout);
        formPanelLeftLayout.setHorizontalGroup(
            formPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        formPanelLeftLayout.setVerticalGroup(
            formPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        formContainerPanel.add(formPanelLeft, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout formPanelRigthLayout = new javax.swing.GroupLayout(formPanelRigth);
        formPanelRigth.setLayout(formPanelRigthLayout);
        formPanelRigthLayout.setHorizontalGroup(
            formPanelRigthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        formPanelRigthLayout.setVerticalGroup(
            formPanelRigthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        formContainerPanel.add(formPanelRigth, java.awt.BorderLayout.LINE_END);

        formPanelBotton.setPreferredSize(new java.awt.Dimension(600, 50));

        javax.swing.GroupLayout formPanelBottonLayout = new javax.swing.GroupLayout(formPanelBotton);
        formPanelBotton.setLayout(formPanelBottonLayout);
        formPanelBottonLayout.setHorizontalGroup(
            formPanelBottonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        formPanelBottonLayout.setVerticalGroup(
            formPanelBottonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        formContainerPanel.add(formPanelBotton, java.awt.BorderLayout.SOUTH);

        centerPanel.addTab("LISTA UNIDADES", formContainerPanel);

        formPanel.setLayout(new java.awt.BorderLayout());

        pnlForm.setAutoscrolls(true);

        jLabel7.setText("FECHA DE INGRESO");

        jLabel1.setText("ID BUS");

        jLabel2.setText("MARCA");

        jLabel3.setText("MODELO");

        jLabel4.setText("TIPO");

        jLabel5.setText("ESTADO");

        cmbActivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INACTIVO", "ACTIVO" }));

        jLabel8.setText("PLACA");

        btnAgregar.setText("GUARDAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 208, Short.MAX_VALUE)
                        .addComponent(cmbActivo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 208, Short.MAX_VALUE)
                        .addComponent(callFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addComponent(txtModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addComponent(txtMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addComponent(txtIdBus, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(callFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIdBus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        formPanel.add(pnlForm, java.awt.BorderLayout.CENTER);

        northSpaceForm.setPreferredSize(new java.awt.Dimension(600, 80));

        jLabel6.setText("INGRESE LA INFORMACION SOLICITADA");

        javax.swing.GroupLayout northSpaceFormLayout = new javax.swing.GroupLayout(northSpaceForm);
        northSpaceForm.setLayout(northSpaceFormLayout);
        northSpaceFormLayout.setHorizontalGroup(
            northSpaceFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northSpaceFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(618, Short.MAX_VALUE))
        );
        northSpaceFormLayout.setVerticalGroup(
            northSpaceFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northSpaceFormLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        formPanel.add(northSpaceForm, java.awt.BorderLayout.NORTH);

        rigthFormSpace.setPreferredSize(new java.awt.Dimension(300, 600));

        javax.swing.GroupLayout rigthFormSpaceLayout = new javax.swing.GroupLayout(rigthFormSpace);
        rigthFormSpace.setLayout(rigthFormSpaceLayout);
        rigthFormSpaceLayout.setHorizontalGroup(
            rigthFormSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rigthFormSpaceLayout.setVerticalGroup(
            rigthFormSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        formPanel.add(rigthFormSpace, java.awt.BorderLayout.EAST);

        leftSpaceForm.setDoubleBuffered(false);
        leftSpaceForm.setPreferredSize(new java.awt.Dimension(100, 600));

        javax.swing.GroupLayout leftSpaceFormLayout = new javax.swing.GroupLayout(leftSpaceForm);
        leftSpaceForm.setLayout(leftSpaceFormLayout);
        leftSpaceFormLayout.setHorizontalGroup(
            leftSpaceFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        leftSpaceFormLayout.setVerticalGroup(
            leftSpaceFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        formPanel.add(leftSpaceForm, java.awt.BorderLayout.WEST);

        bottonSpaceForm.setPreferredSize(new java.awt.Dimension(600, 50));

        javax.swing.GroupLayout bottonSpaceFormLayout = new javax.swing.GroupLayout(bottonSpaceForm);
        bottonSpaceForm.setLayout(bottonSpaceFormLayout);
        bottonSpaceFormLayout.setHorizontalGroup(
            bottonSpaceFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bottonSpaceFormLayout.setVerticalGroup(
            bottonSpaceFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        formPanel.add(bottonSpaceForm, java.awt.BorderLayout.SOUTH);

        centerPanel.addTab("FORMULARIO UNIDADES", formPanel);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        serv.Post_JSON01(this.currentUsser, getFormUnidades());
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCleanSerchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanSerchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCleanSerchActionPerformed
    private Unidades getFormUnidades() {
        Date date = new Date(this.callFecha.getDate().getTime());
        Unidades un = new Unidades();
        un.setFecha_ingreso(date);
        un.setActivo(this.cmbActivo.getSelectedIndex());
        un.setMarca(this.txtMarca.getText());
        un.setModelo(Integer.parseInt(this.txtModelo.getText()));
        un.setPlaca(this.txtPlaca.getText());
        un.setTipo(this.listaTipos.get(this.cmbTipo.getSelectedIndex()).getIdtipo());
        //un.setTipo(1);
        return un;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MantenimientoUnidades.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MantenimientoUnidades.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MantenimientoUnidades.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MantenimientoUnidades.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MantenimientoUnidades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottonPanel;
    private javax.swing.JPanel bottonSpaceForm;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCleanSerch;
    private javax.swing.JButton btnSerch;
    private com.toedter.calendar.JDateChooser callFecha;
    private javax.swing.JTabbedPane centerPanel;
    private javax.swing.JComboBox<String> cmbActivo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JPanel formContainerPanel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel formPanelBotton;
    private javax.swing.JPanel formPanelLeft;
    private javax.swing.JPanel formPanelRigth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel leftSpaceForm;
    private javax.swing.JPanel northSpaceForm;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JRadioButton rbIdBus;
    private javax.swing.JRadioButton rbPlaca;
    private javax.swing.JPanel rigthFormSpace;
    private javax.swing.JPanel rigthPanel;
    private javax.swing.JPanel serchPanel;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtIdBus;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
