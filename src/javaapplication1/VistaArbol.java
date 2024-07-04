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
    private Map<NodoArbol, Color> originalColorMap; // Mapa para almacenar colores originales
    private Color[] colorPalette;
    private Random random;
    private String tipoRecorrido; // Tipo de recorrido actual
    private boolean recorriendo; // Estado de recorrido en curso
    private int delay = 1000; // Retardo entre pasos del recorrido en milisegundos

    public VistaArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        this.colorMap = new HashMap<>();
        this.originalColorMap = new HashMap<>();
        this.random = new Random();
        this.colorPalette = new Color[]{
            new Color(255, 255, 0), // Sky Blue
            // Plum
        };
        this.tipoRecorrido = "Preorden"; // Tipo de recorrido inicial
        this.recorriendo = false; // Inicialmente no se está recorriendo
        guardarColoresOriginales(arbol.getNodoRaiz()); // Guardar los colores originales al inicio
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol.getNodoRaiz() != null) {
            dibujarArbol(g, arbol.getNodoRaiz(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    public void setTipoRecorrido(String tipoRecorrido) {
        this.tipoRecorrido = tipoRecorrido;
        iniciarCalculoOrdenes(); // Reinicia el cálculo de los órdenes con el nuevo tipo de recorrido
        iniciarRecorrido(); // Inicia el recorrido con el nuevo tipo de recorrido
    }

    // Método para guardar los colores originales del árbol
    private void guardarColoresOriginales(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }
        originalColorMap.put(nodo, getRandomColor()); // Color aleatorio inicial
        guardarColoresOriginales(nodo.getNodoIzquierdo());
        guardarColoresOriginales(nodo.getNodoDerecho());
    }

    // Método para iniciar el cálculo de los órdenes
    private void iniciarCalculoOrdenes() {
        if (arbol != null && arbol.getNodoRaiz() != null) {
            NodoArbol raiz = arbol.getNodoRaiz();
            raiz.calcularOrdenes(tipoRecorrido); // Inicia el cálculo de los órdenes según el tipo de recorrido actual
        }
    }

    // Método para iniciar el recorrido del árbol
    private void iniciarRecorrido() {
        recorriendo = true;
        restaurarColoresOriginales(); // Restaurar colores originales antes de iniciar el recorrido
        new Thread(() -> {
            switch (tipoRecorrido) {
                case "Preorden":
                    recorrerPreorden(arbol.getNodoRaiz());
                    break;
                case "Inorden":
                    recorrerInorden(arbol.getNodoRaiz());
                    break;
                case "Postorden":
                    recorrerPostorden(arbol.getNodoRaiz());
                    break;
            }
            recorriendo = false;
            restaurarColoresOriginales(); // Restaurar colores originales al finalizar el recorrido
        }).start();
    }

    // Método para recorrer en preorden y colorear los nodos
    private void recorrerPreorden(NodoArbol nodo) {
        if (nodo == null || !recorriendo) {
            return;
        }
        colorearNodo(nodo, Color.green); // Colorear nodo visitado en preorden
        try {
            Thread.sleep(delay); // Pausa para efecto visual
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recorrerPreorden(nodo.getNodoIzquierdo());
        recorrerPreorden(nodo.getNodoDerecho());
    }

    // Método para recorrer en inorden y colorear los nodos
    private void recorrerInorden(NodoArbol nodo) {
        if (nodo == null || !recorriendo) {
            return;
        }
        recorrerInorden(nodo.getNodoIzquierdo());
        colorearNodo(nodo, Color.pink); // Colorear nodo visitado en inorden
        try {
            Thread.sleep(delay); // Pausa para efecto visual
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recorrerInorden(nodo.getNodoDerecho());
    }

    // Método para recorrer en postorden y colorear los nodos
    private void recorrerPostorden(NodoArbol nodo) {
        if (nodo == null || !recorriendo) {
            return;
        }
        recorrerPostorden(nodo.getNodoIzquierdo());
        recorrerPostorden(nodo.getNodoDerecho());
        colorearNodo(nodo, Color.RED); // Colorear nodo visitado en postorden
        try {
            Thread.sleep(delay); // Pausa para efecto visual
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para restaurar los colores originales de todos los nodos del árbol
    private void restaurarColoresOriginales() {
        colorMap.clear();
        colorMap.putAll(originalColorMap); // Restaurar colores originales
        repaint(); // Volver a pintar para reflejar los cambios
    }

    // Método para colorear un nodo específico
    private void colorearNodo(NodoArbol nodo, Color color) {
        colorMap.put(nodo, color); // Asignar color al nodo
        repaint(); // Volver a pintar para reflejar el cambio de color
    }

    // Método para dibujar el árbol recursivamente
    private void dibujarArbol(Graphics g, NodoArbol nodo, int x, int y, int hSeparation) {
        if (nodo == null) {
            return;
        }

        // Asignar color al nodo
        Color nodoColor = colorMap.get(nodo);
        if (nodoColor == null) {
            nodoColor = getRandomColor(); // Color aleatorio si no está coloreado
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

    // Método para dibujar la flecha entre nodos
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

    // Método para obtener un color aleatorio de la paleta
    private Color getRandomColor() {
        return colorPalette[random.nextInt(colorPalette.length)];
    }
}
