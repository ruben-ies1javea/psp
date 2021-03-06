package org.psp.clienteftpbasico;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Interfaz simple para operaciones con un servidor FTP
 *
 * @author ruben
 */
public class InterfazClienteFTP extends javax.swing.JFrame implements ProtocolCommandListener {

    //cliente FTP para interactuar con el servidor
    private FTPClient ftpClient;
    //indica que el usuario está logueado
    private boolean logueado;

    //directorio de partida
    private String directorioInicial;
    //directorio seleccionado en el interfaz
    private String directorioSeleccionado;
    //fichero seleccionado en el interfaz
    private String ficheroSeleccionado;

    /**
     * Constructor
     */
    public InterfazClienteFTP() {
        super("CLIENTE FTP BÁSICO");

        initComponents();
    }

    /**
     * Conexión con el servidor FTP
     *
     * @param servidor nombre del host servidor
     * @param usuario usuario para conectar
     * @param password contraseña del usuario para conectar
     * @param directorioInicial directorio inicial al que navegar
     */
    public void conectar(String servidor, String usuario, String password, String directorioInicial) {
        this.directorioInicial = directorioInicial;

        //usamos esta propia clase command listener para mostrar en consola todos los mensajes comando/respuesta con el servidor
        ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(this);
        try {
            //conectar con el servidor FTP
            ftpClient.connect(servidor);
            jLabelServidor.setText("Servidor: " + servidor);
            ftpClient.enterLocalPassiveMode();
            //loguearse con el usuario indicado
            logueado = ftpClient.login(usuario, password);
            jLabelUsuario.setText("Conectado como " + usuario);
            nuevoMensaje("Conectado a " + servidor + " como " + usuario, true);
            //navegar al directorio inicial
            navegarDirectorio(directorioInicial);
        } catch (IOException ex) {
            nuevoMensaje(ex.getMessage(),true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCabecera = new javax.swing.JPanel();
        jLabelServidor = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        jPanelLista = new javax.swing.JPanel();
        jLabelDirectorio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFicheros = new javax.swing.JList<>();
        jLabelFicheros = new javax.swing.JLabel();
        jPanelAcciones = new javax.swing.JPanel();
        jButtonSubir = new javax.swing.JButton();
        jButtonDescargar = new javax.swing.JButton();
        jButtonCrearCarpeta = new javax.swing.JButton();
        jButtonEliminarCarpeta = new javax.swing.JButton();
        jButtonEliminarFichero = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMensajes = new javax.swing.JTextArea();
        jLabelUltimo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelCabecera.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelServidor.setText("No conectado");

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        jLabelUsuario.setText("No conectado");

        javax.swing.GroupLayout jPanelCabeceraLayout = new javax.swing.GroupLayout(jPanelCabecera);
        jPanelCabecera.setLayout(jPanelCabeceraLayout);
        jPanelCabeceraLayout.setHorizontalGroup(
            jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCabeceraLayout.createSequentialGroup()
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanelCabeceraLayout.createSequentialGroup()
                        .addComponent(jLabelServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelCabeceraLayout.setVerticalGroup(
            jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSalir)
                    .addComponent(jLabelUsuario))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelLista.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelDirectorio.setText("Directorio actual");

        jListFicheros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListFicheros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListFicherosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListFicheros);

        jLabelFicheros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFicheros.setText("sin ficheros");
        jLabelFicheros.setToolTipText("");

        javax.swing.GroupLayout jPanelListaLayout = new javax.swing.GroupLayout(jPanelLista);
        jPanelLista.setLayout(jPanelListaLayout);
        jPanelListaLayout.setHorizontalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jLabelDirectorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelListaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelFicheros, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanelListaLayout.setVerticalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabelDirectorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFicheros)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanelAcciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSubir.setText("Subir fichero");
        jButtonSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubirActionPerformed(evt);
            }
        });

        jButtonDescargar.setText("Descargar fichero");
        jButtonDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescargarActionPerformed(evt);
            }
        });

        jButtonCrearCarpeta.setText("Crear carpeta");
        jButtonCrearCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearCarpetaActionPerformed(evt);
            }
        });

        jButtonEliminarCarpeta.setText("Eliminar carpeta");
        jButtonEliminarCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCarpetaActionPerformed(evt);
            }
        });

        jButtonEliminarFichero.setText("Eliminar fichero");
        jButtonEliminarFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarFicheroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAccionesLayout = new javax.swing.GroupLayout(jPanelAcciones);
        jPanelAcciones.setLayout(jPanelAccionesLayout);
        jPanelAccionesLayout.setHorizontalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSubir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCrearCarpeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDescargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarFichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarCarpeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAccionesLayout.setVerticalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButtonCrearCarpeta)
                .addGap(18, 18, 18)
                .addComponent(jButtonSubir)
                .addGap(18, 18, 18)
                .addComponent(jButtonDescargar)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarCarpeta)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarFichero)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTextAreaMensajes.setEditable(false);
        jTextAreaMensajes.setColumns(20);
        jTextAreaMensajes.setLineWrap(true);
        jTextAreaMensajes.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMensajes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jLabelUltimo.setText("...");
        jLabelUltimo.setMaximumSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabelUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Pulsación del botón salir
     *
     * @param evt
     */
    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        try {
            if (logueado) {
                ftpClient.logout();
            }
            ftpClient.disconnect();
        } catch (IOException ex) {
            nuevoMensaje(ex.getMessage(),true);
        }
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed
    /**
     * Cambio en la selección de un elemento en la lista
     *
     * @param evt
     */
    private void jListFicherosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListFicherosValueChanged
        if (!evt.getValueIsAdjusting()) {

            ficheroSeleccionado = "";

            //elemento seleccionado de la lista
            String elementoSeleccionado = jListFicheros.getSelectedValue();

            if (jListFicheros.getSelectedIndex() == 0) {
                //Se hace clic en el primer elemento del JList
                if (!elementoSeleccionado.equals(directorioInicial)) {
                    //si no estamos en el dictorio inicial, hay que subir al directorio padre
                    try {
                        ftpClient.changeToParentDirectory();
                        navegarDirectorio(ftpClient.printWorkingDirectory());
                    } catch (IOException ex) {
                        nuevoMensaje(ex.getMessage(),true);
                    }
                }
            } //No se hace clic en el primer elemento del JList
            //Puede ser un fichero o un directorio
            else {
                if (elementoSeleccionado.substring(0, 6).equals("(DIR) ")) {
                    //SE TRATA DE UN DIRECTORIO
                    try {
                        elementoSeleccionado = elementoSeleccionado.substring(6);
                        navegarDirectorio(normalizarRuta(directorioSeleccionado, elementoSeleccionado));
                    } catch (IOException ex) {
                        nuevoMensaje(ex.getMessage(),true);
                    }
                } else {
                    // SE TRATA DE UN FICHERO 
                    ficheroSeleccionado = normalizarRuta(directorioSeleccionado, elementoSeleccionado);
                }
            }
        }
    }//GEN-LAST:event_jListFicherosValueChanged

    /**
     * Gestión del botón nueva carpeta
     *
     * @param evt
     */
    private void jButtonCrearCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearCarpetaActionPerformed

        String nombreCarpeta = JOptionPane.showInputDialog(null, "Introduce el nombre del directorio", "carpeta");
        if (nombreCarpeta != null) {
            String directorio = normalizarRuta(directorioSeleccionado, nombreCarpeta.trim());

            try {
                if (ftpClient.makeDirectory(directorio)) {
                    String mensaje = nombreCarpeta.trim() + " => Se ha creado correctamente ...";
                    nuevoMensaje(mensaje,true);
                    navegarDirectorio(directorioSeleccionado);

                } else {
                    JOptionPane.showMessageDialog(null, nombreCarpeta.trim()
                            + " => No se ha podido crear ...");
                }

            } catch (IOException ex) {
                nuevoMensaje(ex.getMessage(),true);
            }
        }
    }//GEN-LAST:event_jButtonCrearCarpetaActionPerformed
/**
 * Gestión del evento de eliminar una carpeta
 * @param evt 
 */
    private void jButtonEliminarCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarCarpetaActionPerformed
        //indicar la carpeta a eliminar
        String nombreCarpeta = JOptionPane.showInputDialog(null, "Introduce el nombre del directorio a eliminar", "carpeta");
        if (nombreCarpeta != null) {
            String directorio = normalizarRuta(directorioSeleccionado, nombreCarpeta.trim());

            try {
                //eliminación de un directorio
                if (ftpClient.removeDirectory(directorio)) {
                    String mensaje = nombreCarpeta.trim() + " => Se ha eliminado correctamente ...";
                    nuevoMensaje(mensaje,true);
                    navegarDirectorio(directorioSeleccionado);

                } else {
                    JOptionPane.showMessageDialog(null, nombreCarpeta.trim()
                            + " => No se ha podido eliminar ...");
                }

            } catch (IOException ex) {
                nuevoMensaje(ex.getMessage(),true);
            }
        }
    }//GEN-LAST:event_jButtonEliminarCarpetaActionPerformed
/**
 * Gestión del evento de subir un fichero
 * @param evt 
 */
    private void jButtonSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubirActionPerformed
        //usamos un selector de ficheros para indicar el fichero a subir
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.setDialogTitle("Selecciona el Fichero a SUBIR AL SERVIDOR FTP");
        int returnVal = f.showDialog(f, "Cargar");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //obtener nombre y ruta absoluta del fichero en nuestro disco
            File file = f.getSelectedFile();
            String rutaArchivo = file.getAbsolutePath();
            String nombreArchivo = file.getName();
            try {
                //subida del fichero
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(rutaArchivo));
                if (ftpClient.storeFile(nombreArchivo, in)) {
                    nuevoMensaje(nombreArchivo + " => Subido correctamente... ",true);
                    navegarDirectorio(directorioSeleccionado);
                } else {
                    nuevoMensaje("No se ha podido subir... " + nombreArchivo,true);
                }
                in.close();
            } catch (IOException ex) {
                nuevoMensaje(ex.getMessage(),true);
            }
        }
    }//GEN-LAST:event_jButtonSubirActionPerformed
/**
 * Gestión del evento de descarga de un fichero
 * @param evt 
 */
    private void jButtonDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescargarActionPerformed
        if (ficheroSeleccionado.equals("")) {
            return;
        }
        //extraemos el nombre del fichero seleccionado
        String nombreFicheroSeleccionado = new File(ficheroSeleccionado).getName();

        //seleccionamos la carpeta en la que queremos descargar el fichero
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Selecciona el Directorio donde DESCARGAR el fichero");
        int returnVal = f.showDialog(null, "Descargar");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //obtener la carpeta seleccionada y componer ruta del fichero a crear en el disco local, 
            //usamos File.Separator para que funcione en cualquier sistema operativo
            File file = f.getSelectedFile();
            String rutaFicheroDescargado = (file.getAbsolutePath()) + File.separator + nombreFicheroSeleccionado;
            try {
                //descarga del fichero
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(rutaFicheroDescargado));
                if (ftpClient.retrieveFile(ficheroSeleccionado, out)) {
                    nuevoMensaje(nombreFicheroSeleccionado + " => Se ha descargado correctamente ...",true);
                } else {
                    nuevoMensaje(nombreFicheroSeleccionado + " => No se ha podido descargar ...",true);
                }

                out.close();
            } catch (IOException ex) {
                nuevoMensaje(ex.getMessage(),true);
            }
        }
    }//GEN-LAST:event_jButtonDescargarActionPerformed

    private void jButtonEliminarFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarFicheroActionPerformed
        if (ficheroSeleccionado.equals("")) {
            return;
        }
        //extraemos el nombre del fichero seleccionado
        String nombreFicheroSeleccionado = new File(ficheroSeleccionado).getName();
        //pide confirmacion de la eliminación
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el fichero " + nombreFicheroSeleccionado + " ?");
        if (seleccion == JOptionPane.OK_OPTION) {
            try {
                //eliminación del fichero
                if (ftpClient.deleteFile(ficheroSeleccionado)) {
                    nuevoMensaje(nombreFicheroSeleccionado + " => Eliminado correctamente... ",true);
                    navegarDirectorio(directorioSeleccionado);
                } else {
                    nuevoMensaje(nombreFicheroSeleccionado + " => No se ha podido eliminar ...",true);
                }

            } catch (IOException ex) {
                nuevoMensaje(ex.getMessage(),true);
            }
        }
    }//GEN-LAST:event_jButtonEliminarFicheroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrearCarpeta;
    private javax.swing.JButton jButtonDescargar;
    private javax.swing.JButton jButtonEliminarCarpeta;
    private javax.swing.JButton jButtonEliminarFichero;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSubir;
    private javax.swing.JLabel jLabelDirectorio;
    private javax.swing.JLabel jLabelFicheros;
    private javax.swing.JLabel jLabelServidor;
    private javax.swing.JLabel jLabelUltimo;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JList<String> jListFicheros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaMensajes;
    // End of variables declaration//GEN-END:variables

    /**
     * Navega a un directorio concreto en el servidor FTP y actualiza la lista
     * de ficheros
     *
     * @param directorio directorio al que se quiere navegar
     * @throws IOException
     */
    private void navegarDirectorio(String directorio) throws IOException {

        //navegar al directorio indicado
        ftpClient.changeWorkingDirectory(directorio);
        directorioSeleccionado = directorio;
        jLabelDirectorio.setText(directorio);

        //obtener los ficheros y llenar el componente lista
        FTPFile[] files = ftpClient.listFiles();
        if (files == null) {
            return;
        }
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        modeloLista.addElement(directorio);
        for (int i = 0; i < files.length; i++) {
            if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) {
                String f = files[i].getName();
                if (files[i].isDirectory()) {
                    f = "(DIR) " + f;
                }
                modeloLista.addElement(f);
            }
        }

        //vaciar la lista de ficheros
        jListFicheros.removeAll();
        //el establecimiento del modelo esta disparando una excepción que no afecta al funcionamiento
        try {
            jListFicheros.setModel(modeloLista);
        } catch (Exception ex) {
        }

        jLabelFicheros.setText(files.length + " ficheros");
    }

    /**
     * Compone una ruta absoluta de fichero o carpeta teniendo en cuenta los
     * separadores de fichero
     *
     * @param base parte izquierda de la ruta
     * @param elementoFinal parte derecha de la ruta
     * @return
     */
    private String normalizarRuta(String base, String elementoFinal) {
        if (base.endsWith("/")) {
            return base + elementoFinal;
        } else {
            return base + "/" + elementoFinal;
        }
    }

    /**
     * Implementación del interfaz ProtocolCommandListener 
     * @param event 
     */
    @Override
    public void protocolCommandSent(ProtocolCommandEvent event) {
        nuevoMensaje(event.getMessage(), false);
    }

    /**
     * Implementación del interfaz ProtocolCommandListener 
     * @param event 
     */
    @Override
    public void protocolReplyReceived(ProtocolCommandEvent event) {
        nuevoMensaje(event.getMessage(), false);
    }

    /**
     * Añade un nuevo mensaje al visualidor de mensajes
     *
     * @param mensaje mensaje a añadir
     * @param destacado además deberá aparecer destacado
     */
    private void nuevoMensaje(String mensaje, boolean destacado) {
        if (destacado) {
            jLabelUltimo.setText(mensaje);
        }
        jTextAreaMensajes.append("- " + mensaje);
    }

}
