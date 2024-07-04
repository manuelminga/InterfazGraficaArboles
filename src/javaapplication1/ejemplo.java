package javaapplication1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ejemplo {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
 

        // Creación del JFrame principal
        JFrame frame = new JFrame("Árbol Binario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        // Panel para la visualización del árbol
        VistaArbol vistaArbol = new VistaArbol(arbol);
        frame.add(vistaArbol, BorderLayout.CENTER);
        vistaArbol.setBackground(Color.white);
        

        // Panel de control para botones y entrada de datos
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Etiqueta de título
         JLabel titleLabel = new JLabel("Gestión del Árbol Binario");
         titleLabel.setForeground(Color.yellow);
        titleLabel.setFont(new Font("Ser", Font.BOLD, 20));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(titleLabel, gbc);
      

        // Campo de texto y botones para agregar y eliminar nodos
        JTextField valorField = new JTextField(5);
        JButton addButton = new JButton("Añadir Nodo");
        addButton.setBackground(Color.yellow);
        JButton deleteButton = new JButton("Eliminar Nodo");
        deleteButton.setBackground(Color.yellow);
        JTextArea recorridosTextArea = new JTextArea(10, 50);
        recorridosTextArea.setEditable(false);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
         JLabel val = new JLabel("Valor");
        val.setForeground(Color.yellow);
        controlPanel.add(val, gbc);
        gbc.gridx = 1;
        controlPanel.add(valorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(addButton, gbc);

        gbc.gridx = 1;
        controlPanel.add(deleteButton, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        
        JLabel recorr = new JLabel("Recorridos");
        recorr.setForeground(Color.yellow);
        controlPanel.add(recorr, gbc);

        gbc.gridy = 4;
        controlPanel.add(new JScrollPane(recorridosTextArea), gbc);

        // Botones para mostrar recorridos Preorden, Inorden y Postorden
        JButton preordenButton = new JButton("Preorden");
        preordenButton.setBackground(Color.yellow);
        JButton inordenButton = new JButton("Inorden");
        inordenButton.setBackground(Color.yellow);
        JButton postordenButton = new JButton("Postorden");
        postordenButton.setBackground(Color.yellow);

        gbc.gridwidth = 1;
        gbc.gridx = -1;
        gbc.gridy = 5;
        controlPanel.add(preordenButton, gbc);

        gbc.gridx = 1;
        controlPanel.add(inordenButton, gbc);

        gbc.gridx = 2;
        
        controlPanel.add(postordenButton, gbc);

        // Acción para el botón de agregar nodo
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int valor = Integer.parseInt(valorField.getText());
                    arbol.agregarNodo(valor);
                    valorField.setText("");
                    actualizarRecorridos(recorridosTextArea, arbol);
                    vistaArbol.repaint(); // Actualiza la vista del árbol
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un valor válido.");
                }
            }
        });

        // Acción para el botón de eliminar nodo
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int valor = Integer.parseInt(valorField.getText());
                    arbol.borrarNodo(valor);
                    valorField.setText("");
                    actualizarRecorridos(recorridosTextArea, arbol);
                    vistaArbol.repaint(); // Actualiza la vista del árbol
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un valor válido.");
                }
            }
        });

        // Acción para el botón de mostrar recorrido Preorden
        preordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaArbol.setTipoRecorrido("Preorden");
                actualizarRecorridos(recorridosTextArea, arbol);
                vistaArbol.repaint(); // Actualiza la vista del árbol
            }
        });

        // Acción para el botón de mostrar recorrido Inorden
        inordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaArbol.setTipoRecorrido("Inorden");
                actualizarRecorridos(recorridosTextArea, arbol);
                vistaArbol.repaint(); // Actualiza la vista del árbol
            }
        });

        // Acción para el botón de mostrar recorrido Postorden
        postordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaArbol.setTipoRecorrido("Postorden");
                actualizarRecorridos(recorridosTextArea, arbol);
                vistaArbol.repaint(); // Actualiza la vista del árbol
            }
        });

        // Agregar los paneles al JFrame y hacerlo visible
        frame.add(controlPanel, BorderLayout.NORTH);
        controlPanel.setBackground(Color.black);
        frame.setVisible(true);

        // Mostrar los recorridos iniciales
        actualizarRecorridos(recorridosTextArea, arbol);
    }

    // Método para actualizar el JTextArea con los recorridos actuales del árbol
    private static void actualizarRecorridos(JTextArea textArea, ArbolBinario arbol) {
        String preOrden = arbol.recorrerPreOrdenString();
        String inOrden = arbol.recorrerInOrdenString();
        String postOrden = arbol.recorrerPostOrdenString();
        textArea.setText("Preorden: " + preOrden + "\n"
                + "Inorden: " + inOrden + "\n"
                + "Postorden: " + postOrden);
    }
}
