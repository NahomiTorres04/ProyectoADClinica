/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicioSesion;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import rojerusan.RSPanelsSlider;
import controlador.UsuarioJpaController;
import controlador.Conexion;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Nahomi
 */
public class Login extends javax.swing.JFrame {

    FabricaUsuario fabrica;
    private static Login login = null;
    /**
     * Creates new form login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        jPanel2.setBackground(new Color(0,0,0,0));
        cmbusuario.setBackground(new Color(0,0,0,0));
        UsuarioJpaController usuarioControlador = new UsuarioJpaController(Conexion.getInstancia().getEntityManager());
        List<vista.Usuario> usuarios = usuarioControlador.findUsuarioEntities();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cmbusuario.getModel();
        for(vista.Usuario usuario : usuarios)
        {
            model.addElement(usuario.getNombreUsuario());
        }
    }
    
    public static Login getInstancia()
    {
        if(login == null) login = new Login();
        return login;
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
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        rSLabelFecha1 = new rojeru_san.RSLabelFecha();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        RSPPrincipal = new rojerusan.RSPanelsSlider();
        jpingresar = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pswcon = new javax.swing.JPasswordField();
        lbIngresar = new javax.swing.JLabel();
        cmbusuario = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jpRegistrar = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtUsuarioN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pswVContrasenia = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        pswContrasenia = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        txtApellidoN = new javax.swing.JTextField();
        txtNombreN = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        lbRegistrar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        RSPRegistrar = new rojerusan.RSPanelsSlider();
        p2registrar = new javax.swing.JPanel();
        btnregistrar1 = new javax.swing.JButton();
        btningresar1 = new javax.swing.JButton();
        cbtning1 = new rojerusan.RSMaterialButtonCircle();
        jLabel3 = new javax.swing.JLabel();
        cbtnreg1 = new rojerusan.RSMaterialButtonCircle();
        jLabel6 = new javax.swing.JLabel();
        p1registrar = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        RSPIngresar = new rojerusan.RSPanelsSlider();
        p2ingresar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        p1ingresar = new javax.swing.JPanel();
        btnregistrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbtnreg = new rojerusan.RSMaterialButtonCircle();
        jLabel4 = new javax.swing.JLabel();
        btningresar2 = new javax.swing.JButton();
        cbtning2 = new rojerusan.RSMaterialButtonCircle();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 52, 102));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelHora1.setFont(new java.awt.Font("Roboto Bold", 1, 48)); // NOI18N
        jPanel1.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 640, 350, 100));

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelFecha1.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        jPanel1.add(rSLabelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, -1, -1));

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Bienvenido");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 290, 120));

        jLabel18.setFont(new java.awt.Font("Tempus Sans ITC", 1, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-24131_960_720.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(-400, -10, 1040, 780));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0ff4a61bd6b090ea9e8d50d498eca17f.jpg"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 750));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new java.awt.CardLayout());

        RSPPrincipal.setMinimumSize(new java.awt.Dimension(517, 700));

        jpingresar.setBackground(new java.awt.Color(255, 255, 255));
        jpingresar.setName("jpingresar"); // NOI18N
        jpingresar.setPreferredSize(new java.awt.Dimension(517, 700));
        jpingresar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel16.setText("Usuario");
        jpingresar.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic Light", 1, 18)); // NOI18N
        jLabel17.setText("Contraseña");
        jpingresar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        pswcon.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        pswcon.setText("jPasswordField2");
        pswcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pswcon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pswconFocusGained(evt);
            }
        });
        pswcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pswconMouseClicked(evt);
            }
        });
        pswcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswconActionPerformed(evt);
            }
        });
        pswcon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pswconKeyPressed(evt);
            }
        });
        jpingresar.add(pswcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 320, 30));

        lbIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbIngresarMouseClicked(evt);
            }
        });
        jpingresar.add(lbIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 250, 50));

        cmbusuario.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        cmbusuario.setForeground(new java.awt.Color(0, 52, 102));
        cmbusuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpingresar.add(cmbusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 320, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/login2.png"))); // NOI18N
        jpingresar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 450, 680));

        RSPPrincipal.add(jpingresar, "card2");

        jpRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        jpRegistrar.setName("jpRegistrar"); // NOI18N
        jpRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("Usuario");
        jpRegistrar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        txtUsuarioN.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtUsuarioN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpRegistrar.add(txtUsuarioN, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 320, 30));

        jLabel2.setFont(new java.awt.Font("Yu Gothic Light", 1, 18)); // NOI18N
        jLabel2.setText("Verificar contraseña");
        jpRegistrar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, -1, -1));

        pswVContrasenia.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        pswVContrasenia.setText("jPasswordField2");
        pswVContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pswVContrasenia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pswVContraseniaFocusGained(evt);
            }
        });
        jpRegistrar.add(pswVContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 320, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Apellidos");
        jpRegistrar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, -1, -1));

        pswContrasenia.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        pswContrasenia.setText("jPasswordField2");
        pswContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pswContrasenia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pswContraseniaFocusGained(evt);
            }
        });
        jpRegistrar.add(pswContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 320, 30));

        jLabel14.setFont(new java.awt.Font("Yu Gothic Light", 1, 18)); // NOI18N
        jLabel14.setText("Contraseña");
        jpRegistrar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        txtApellidoN.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtApellidoN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpRegistrar.add(txtApellidoN, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 160, 30));

        txtNombreN.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtNombreN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpRegistrar.add(txtNombreN, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 150, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Nombres");
        jpRegistrar.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        lbRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegistrarMouseClicked(evt);
            }
        });
        jpRegistrar.add(lbRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 240, 50));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro.png"))); // NOI18N
        jpRegistrar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 450, 680));

        RSPPrincipal.add(jpRegistrar, "card3");

        jPanel3.add(RSPPrincipal, "card2");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p2registrar.setBackground(new java.awt.Color(0, 52, 102));
        p2registrar.setName("p2registrar"); // NOI18N
        p2registrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnregistrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Up_52px_1.png"))); // NOI18N
        btnregistrar1.setBorder(null);
        btnregistrar1.setBorderPainted(false);
        btnregistrar1.setContentAreaFilled(false);
        btnregistrar1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Up_62px_2.png"))); // NOI18N
        btnregistrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrar1MouseClicked(evt);
            }
        });
        p2registrar.add(btnregistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 100, 90, 50));

        btningresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Down_52px_1.png"))); // NOI18N
        btningresar1.setBorder(null);
        btningresar1.setBorderPainted(false);
        btningresar1.setContentAreaFilled(false);
        btningresar1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Down_62px_1.png"))); // NOI18N
        p2registrar.add(btningresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 135, 80, 50));

        cbtning1.setBackground(new java.awt.Color(53, 190, 226));
        p2registrar.add(cbtning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1reg.png"))); // NOI18N
        jLabel3.setOpaque(true);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        p2registrar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 200, 180));

        cbtnreg1.setBackground(new java.awt.Color(255, 255, 255));
        cbtnreg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbtnreg1MouseClicked(evt);
            }
        });
        p2registrar.add(cbtnreg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 90, -1, -1));

        jLabel6.setBackground(new java.awt.Color(53, 190, 226));
        jLabel6.setOpaque(true);
        p2registrar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 140));

        RSPRegistrar.add(p2registrar, "card3");

        p1registrar.setBackground(new java.awt.Color(255, 255, 255));
        p1registrar.setName("p1registrar"); // NOI18N
        p1registrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 28)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reg.png"))); // NOI18N
        p1registrar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -15, 200, -1));

        RSPRegistrar.add(p1registrar, "card2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(RSPRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RSPRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 200, 320));

        p2ingresar.setBackground(new java.awt.Color(53, 190, 226));
        p2ingresar.setName("p2ingresar"); // NOI18N
        p2ingresar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 34)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ing.png"))); // NOI18N
        p2ingresar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 200, 480));

        RSPIngresar.add(p2ingresar, "card3");

        p1ingresar.setBackground(new java.awt.Color(255, 255, 255));
        p1ingresar.setName("p1ingresar"); // NOI18N
        p1ingresar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Up_52px_1.png"))); // NOI18N
        btnregistrar.setBorder(null);
        btnregistrar.setBorderPainted(false);
        btnregistrar.setContentAreaFilled(false);
        btnregistrar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Up_62px_2.png"))); // NOI18N
        btnregistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrarMouseClicked(evt);
            }
        });
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });
        p1ingresar.add(btnregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 140, 90, 50));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setOpaque(true);
        p1ingresar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 110, 100));

        cbtnreg.setBackground(new java.awt.Color(255, 255, 255));
        cbtnreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbtnregMouseClicked(evt);
            }
        });
        p1ingresar.add(cbtnreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 140, -1, -1));

        jLabel4.setBackground(new java.awt.Color(53, 190, 226));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1ing.png"))); // NOI18N
        jLabel4.setOpaque(true);
        p1ingresar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 190));

        btningresar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sort_Down_52px_1.png"))); // NOI18N
        btningresar2.setBorder(null);
        btningresar2.setBorderPainted(false);
        btningresar2.setContentAreaFilled(false);
        btningresar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btningresar2MouseClicked(evt);
            }
        });
        p1ingresar.add(btningresar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 190, 80, 50));

        cbtning2.setBackground(new java.awt.Color(53, 190, 226));
        p1ingresar.add(cbtning2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jLabel7.setText("jLabel7");
        p1ingresar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        RSPIngresar.add(p1ingresar, "card2");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RSPIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(RSPIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1252, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(231, 231, 231)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

    private void pswconFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswconFocusGained
        pswcon.setText("");
    }//GEN-LAST:event_pswconFocusGained

    private void pswconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pswconMouseClicked

    }//GEN-LAST:event_pswconMouseClicked

    private void pswconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswconActionPerformed

    private void pswconKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pswconKeyPressed

    }//GEN-LAST:event_pswconKeyPressed

    private void lbIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIngresarMouseClicked
        String nombreUsuario = cmbusuario.getSelectedItem().toString();
        UsuarioJpaController usuarioControlador = new UsuarioJpaController(Conexion.getInstancia().getEntityManager());
        vista.Usuario usuario = usuarioControlador.findUsuario(nombreUsuario);
        CreadorUsuario creador = new CreadorUsuario(fabrica);
        Usuario usuarioActual = creador.crear(usuario.getTipo());
        usuarioActual.activarPermisos();
        this.dispose();
    }//GEN-LAST:event_lbIngresarMouseClicked

    private void pswVContraseniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswVContraseniaFocusGained
        pswVContrasenia.setText("");
    }//GEN-LAST:event_pswVContraseniaFocusGained

    private void pswContraseniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswContraseniaFocusGained
        pswContrasenia.setText("");
    }//GEN-LAST:event_pswContraseniaFocusGained

    private void lbRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegistrarMouseClicked
        /**
         * Se solicitará la contraseña del administrador, para poder confirmar
         * el nuevo ingreso
         */
        if(pswContrasenia.getText().equals(pswVContrasenia.getText()))
        {
            String[] arreglo = {"empleado", "bodeguero", "visitante"};
            Object[] objeto = arreglo;
            String tipo = (String) JOptionPane.showInputDialog(this, "Seleccione el tipo de usuario", "Ingreso", JOptionPane.PLAIN_MESSAGE, null, objeto, null);
            try
            {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Ingrese la contraseña del administrador:");
                JPasswordField campoContraseña = new JPasswordField(45);
                panel.add(label);
                panel.add(campoContraseña);
                String[] opciones = new String[]{"OK", "Cancel"};
                int opcion = JOptionPane.showOptionDialog(null, panel, "Contraseña",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opciones, opciones[1]);
                if(opcion == JOptionPane.OK_OPTION) //Si se acepta la contraseña del administrador
                {
                    String contraseña = campoContraseña.getText();
                    String contraseñaCifrada = DigestUtils.md5Hex(contraseña);
                    //al tener la contraseña cifrada, se procede a la comparación
                    UsuarioJpaController usuarioControlador = new UsuarioJpaController(Conexion.getInstancia().getEntityManager());
                    vista.Usuario usuario = usuarioControlador.findUsuario(1);
                    if(contraseñaCifrada.equals(usuario.getPassword())) //si la contraseña es igual
                    {
                        //se procede a ingresar el nuevo usuario
                        //CONSIDERAR PONER ESTO EN UNA CLASE DIFERENTE AL FRAME, RESPETANTO SRP
                        vista.Usuario usuarioIngresado = new vista.Usuario();
                        usuarioIngresado.setNombre(txtNombreN.getText());
                        usuarioIngresado.setApellido(txtApellidoN.getText());
                        usuarioIngresado.setNombreUsuario(txtUsuarioN.getText());
                        usuarioIngresado.setPassword(DigestUtils.md5Hex(pswContrasenia.getText()));
                        if(tipo.equals("empleado")) usuarioIngresado.setTipo(Usuario.EMPLEADO);
                        if(tipo.equals("bodeguero")) usuarioIngresado.setTipo(Usuario.BODEGUERO);
                        if(tipo.equals("visitante")) usuarioIngresado.setTipo(Usuario.VISITANTE);
                        usuarioControlador = new UsuarioJpaController(Conexion.getInstancia().getEntityManager());
                        usuarioControlador.create(usuarioIngresado);
                        JOptionPane.showMessageDialog(this, "Usuario Ingresado", "Éxito", JOptionPane.PLAIN_MESSAGE);
                    }
                }    
            } catch(Exception ex)
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else JOptionPane.showMessageDialog(null, "Verifique contraseñas");
    }//GEN-LAST:event_lbRegistrarMouseClicked

    private void btnregistrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrar1MouseClicked
        RSPRegistrar.setPanelSlider((int) 1.2,p1registrar, RSPanelsSlider.DIRECT.UP);
        p2ingresar.setBackground(new Color(53, 190, 226));
        RSPIngresar.setPanelSlider((int)2.6,p1ingresar, RSPanelsSlider.DIRECT.UP);
        RSPPrincipal.setPanelSlider((int)2.6,jpRegistrar, RSPanelsSlider.DIRECT.UP);
    }//GEN-LAST:event_btnregistrar1MouseClicked

    private void cbtnreg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbtnreg1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbtnreg1MouseClicked

    private void btnregistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrarMouseClicked

    }//GEN-LAST:event_btnregistrarMouseClicked

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void cbtnregMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbtnregMouseClicked

    }//GEN-LAST:event_cbtnregMouseClicked

    private void btningresar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btningresar2MouseClicked

        RSPIngresar.setPanelSlider((int) 1.2,p2ingresar, RSPanelsSlider.DIRECT.DOWN);
        p1registrar.setBackground(Color.white);
        RSPRegistrar.setPanelSlider((int) 2.6,p2registrar, RSPanelsSlider.DIRECT.DOWN);
        RSPPrincipal.setPanelSlider((int) 2.6,jpingresar, RSPanelsSlider.DIRECT.DOWN);
    }//GEN-LAST:event_btningresar2MouseClicked

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged

    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSPanelsSlider RSPIngresar;
    private rojerusan.RSPanelsSlider RSPPrincipal;
    private rojerusan.RSPanelsSlider RSPRegistrar;
    private javax.swing.JButton btningresar1;
    private javax.swing.JButton btningresar2;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnregistrar1;
    private rojerusan.RSMaterialButtonCircle cbtning1;
    private rojerusan.RSMaterialButtonCircle cbtning2;
    private rojerusan.RSMaterialButtonCircle cbtnreg;
    private rojerusan.RSMaterialButtonCircle cbtnreg1;
    private javax.swing.JComboBox<String> cmbusuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jpRegistrar;
    private javax.swing.JPanel jpingresar;
    private javax.swing.JLabel lbIngresar;
    private javax.swing.JLabel lbRegistrar;
    private javax.swing.JPanel p1ingresar;
    private javax.swing.JPanel p1registrar;
    private javax.swing.JPanel p2ingresar;
    private javax.swing.JPanel p2registrar;
    private javax.swing.JPasswordField pswContrasenia;
    private javax.swing.JPasswordField pswVContrasenia;
    private javax.swing.JPasswordField pswcon;
    private rojeru_san.RSLabelFecha rSLabelFecha1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private javax.swing.JTextField txtApellidoN;
    private javax.swing.JTextField txtNombreN;
    private javax.swing.JTextField txtUsuarioN;
    // End of variables declaration//GEN-END:variables
}
