package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;

public class VistaArbol extends JPanel {

    private ArbolBinario arbol;
    private int radio = 20;
    private int verticalSeparation = 50;
    private Map<NodoArbol, Color> colorMap;
    private Color[] colorPalette;
    private Random random;
    private String tipoRecorrido; // Tipo de recorrido actual

    public VistaArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        this.colorMap = new HashMap<>();
        this.random = new Random();
        this.colorPalette = new Color[]{
            new Color(135, 206, 250), // Sky Blue
            new Color(255, 182, 193), // Light Pink
            new Color(152, 251, 152), // Pale Green
            new Color(240, 230, 140), // Khaki
            new Color(255, 160, 122), // Light Salmon
            new Color(221, 160, 221)  // Plum
        };
        this.tipoRecorrido = "Preorden"; // Tipo de recorrido inicial
        iniciarCalculoOrdenes(); // Inicia el cálculo de los órdenes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g, arbol.getNodoRaiz(), getWidth() / 2, 30, getWidth() / 4);
    }

    public void setTipoRecorrido(String tipoRecorrido) {
        this.tipoRecorrido = tipoRecorrido;
        iniciarCalculoOrdenes(); // Reinicia el cálculo de los órdenes con el nuevo tipo de recorrido
        repaint(); // Vuelve a dibujar el árbol con el nuevo tipo de recorrido
    }

    private void iniciarCalculoOrdenes() {
        if (arbol != null && arbol.getNodoRaiz() != null) {
            NodoArbol raiz = arbol.getNodoRaiz();
            raiz.calcularOrdenes(tipoRecorrido); // Inicia el cálculo de los órdenes según el tipo de recorrido actual
        }
    }

    private void dibujarArbol(Graphics g, NodoArbol nodo, int x, int y, int hSeparation) {
        if (nodo == null) {
            return;
        }

        // Asignar color al nodo si no tiene uno asignado
        if (!colorMap.containsKey(nodo)) {
            colorMap.put(nodo, getRandomColor());
        }

        // Dibujar la línea hacia el nodo izquierdo
        if (nodo.getNodoIzquierdo() != null) {
            int x1 = x - radio;
            int y1 = y;
            int x2 = x - hSeparation + radio;
            int y2 = y + verticalSeparation - radio;
            dibujarFlecha(g, x1, y1, x2, y2);
            dibujarArbol(g, nodo.getNodoIzquierdo(), x - hSeparation, y + verticalSeparation, hSeparation / 2);
        }

        // Dibujar la línea hacia el nodo derecho
        if (nodo.getNodoDerecho() != null) {
            int x1 = x + radio;
            int y1 = y;
            int x2 = x + hSeparation - radio;
            int y2 = y + verticalSeparation - radio;
            dibujarFlecha(g, x1, y1, x2, y2);
            dibujarArbol(g, nodo.getNodoDerecho(), x + hSeparation, y + verticalSeparation, hSeparation / 2);
        }

        // Dibujar el nodo
        Color nodoColor = colorMap.get(nodo);
        g.setColor(nodoColor);
        g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
        g.setColor(Color.BLACK);
        g.drawOval(x - radio, y - radio, 2 * radio, 2 * radio);
        g.drawString(Integer.toString(nodo.getValor()), x - 6, y + 4);

        // Dibujar el número de orden según el tipo de recorrido
        g.setFont(new Font("Arial", Font.BOLD, 12));
        if (tipoRecorrido.equals("Preorden")) {
            g.drawString(Integer.toString(nodo.getOrdenPreorden()), x + 10, y - 15);
        } else if (tipoRecorrido.equals("Inorden")) {
            g.drawString(Integer.toString(nodo.getOrdenInorden()), x + 10, y - 15);
        } else if (tipoRecorrido.equals("Postorden")) {
            g.drawString(Integer.toString(nodo.getOrdenPostorden()), x + 10, y - 15);
        }
    }

    private void dibujarFlecha(Graphics g, int x1, int y1, int x2, int y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowLength = 10;
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, (int) (x2 - arrowLength * Math.cos(angle - Math.PI / 6)),
                (int) (y2 - arrowLength * Math.sin(angle - Math.PI / 6)));
        g.drawLine(x2, y2, (int) (x2 - arrowLength * Math.cos(angle + Math.PI / 6)),
                (int) (y2 - arrowLength * Math.sin(angle + Math.PI / 6)));
    }

    private Color getRandomColor() {
        return colorPalette[random.nextInt(colorPalette.length)];
    }
}
