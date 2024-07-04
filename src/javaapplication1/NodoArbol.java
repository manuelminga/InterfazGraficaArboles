package javaapplication1;

import java.awt.Color;

public class NodoArbol extends Nodo {

    private NodoArbol nodoIzquierdo;
    private NodoArbol nodoDerecho;
    private Color color;
    private int ordenPreorden;
    private int ordenInorden;
    private int ordenPostorden;

    public NodoArbol(int valor) {
        super(valor);
        this.nodoIzquierdo = null;
        this.nodoDerecho = null;
        this.color = null;
        this.ordenPreorden = -1; // Inicializamos con un valor no válido
        this.ordenInorden = -1; // Inicializamos con un valor no válido
        this.ordenPostorden = -1; // Inicializamos con un valor no válido
    }

    public NodoArbol getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    public void setNodoIzquierdo(NodoArbol nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    public NodoArbol getNodoDerecho() {
        return nodoDerecho;
    }

    public void setNodoDerecho(NodoArbol nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getOrdenPreorden() {
        return ordenPreorden;
    }

    public void setOrdenPreorden(int ordenPreorden) {
        this.ordenPreorden = ordenPreorden;
    }

    public int getOrdenInorden() {
        return ordenInorden;
    }

    public void setOrdenInorden(int ordenInorden) {
        this.ordenInorden = ordenInorden;
    }

    public int getOrdenPostorden() {
        return ordenPostorden;
    }

    public void setOrdenPostorden(int ordenPostorden) {
        this.ordenPostorden = ordenPostorden;
    }

    public void calcularOrdenes(String tipoRecorrido) {
        reiniciarOrdenes(); // Reiniciamos los órdenes antes de calcular nuevos
        if (tipoRecorrido.equals("Preorden")) {
            calcularOrdenPreorden(1); // Comenzamos el cálculo desde el primer nodo visitado
        } else if (tipoRecorrido.equals("Inorden")) {
            calcularOrdenInorden(1); // Comenzamos el cálculo desde el primer nodo visitado
        } else if (tipoRecorrido.equals("Postorden")) {
            calcularOrdenPostorden(1); // Comenzamos el cálculo desde el primer nodo visitado
        }
    }

    private void reiniciarOrdenes() {
        this.ordenPreorden = -1;
        this.ordenInorden = -1;
        this.ordenPostorden = -1;
    }

    private int calcularOrdenPreorden(int contador) {
        this.setOrdenPreorden(contador++);
        if (this.nodoIzquierdo != null) {
            contador = this.nodoIzquierdo.calcularOrdenPreorden(contador);
        }
        if (this.nodoDerecho != null) {
            contador = this.nodoDerecho.calcularOrdenPreorden(contador);
        }
        return contador;
    }

    private int calcularOrdenInorden(int contador) {
        if (this.nodoIzquierdo != null) {
            contador = this.nodoIzquierdo.calcularOrdenInorden(contador);
        }
        this.setOrdenInorden(contador++);
        if (this.nodoDerecho != null) {
            contador = this.nodoDerecho.calcularOrdenInorden(contador);
        }
        return contador;
    }

    private int calcularOrdenPostorden(int contador) {
        if (this.nodoIzquierdo != null) {
            contador = this.nodoIzquierdo.calcularOrdenPostorden(contador);
        }
        if (this.nodoDerecho != null) {
            contador = this.nodoDerecho.calcularOrdenPostorden(contador);
        }
        this.setOrdenPostorden(contador++);
        return contador;
    }

    public NodoArbol delete(NodoArbol nodoRaiz, int valor) {
        // Caso base: si el nodo raíz es nulo, retornar nulo
        if (nodoRaiz == null) {
            return null;
        }

        // Si el valor a eliminar es menor que el valor del nodo raíz,
        // recurrir al subárbol izquierdo
        if (valor < nodoRaiz.getValor()) {
            nodoRaiz.setNodoIzquierdo(delete(nodoRaiz.getNodoIzquierdo(), valor));
        }
        // Si el valor a eliminar es mayor que el valor del nodo raíz,
        // recurrir al subárbol derecho
        else if (valor > nodoRaiz.getValor()) {
            nodoRaiz.setNodoDerecho(delete(nodoRaiz.getNodoDerecho(), valor));
        }
        // Si el valor a eliminar es igual al valor del nodo raíz,
        // entonces este es el nodo que se eliminará
        else {
            // Caso 1: nodo sin hijos o con un solo hijo
            if (nodoRaiz.getNodoIzquierdo() == null) {
                return nodoRaiz.getNodoDerecho();
            } else if (nodoRaiz.getNodoDerecho() == null) {
                return nodoRaiz.getNodoIzquierdo();
            }

            // Caso 2: nodo con dos hijos, encontrar el sucesor en orden (nodo más pequeño en el subárbol derecho)
            NodoArbol sucesor = encontrarSucesor(nodoRaiz.getNodoDerecho());
            nodoRaiz.setValor(sucesor.getValor());

            // Eliminar el sucesor encontrado
            nodoRaiz.setNodoDerecho(delete(nodoRaiz.getNodoDerecho(), sucesor.getValor()));
        }

        return nodoRaiz;
    }

    // Método auxiliar para encontrar el sucesor en orden en un subárbol dado
    private NodoArbol encontrarSucesor(NodoArbol nodoActual) {
        while (nodoActual.getNodoIzquierdo() != null) {
            nodoActual = nodoActual.getNodoIzquierdo();
        }
        return nodoActual;
    }

    public void insertarNodo(int valor) {
        if (valor < this.getValor()) {   // Se evalúa el nodo "raíz"
            // Insertar en lado Izquierdo
            if (this.getNodoIzquierdo() == null) {
                this.setNodoIzquierdo(new NodoArbol(valor)); // Si el nodo está vacío, se coloca el nuevo nodo
            } else {
                this.getNodoIzquierdo().insertarNodo(valor);    // Aplicación de recursividad
            }
        } else {
            // Insertar en lado Derecho
            if (this.getNodoDerecho() == null) {   // Se evalúa el nodo "raíz"
                this.setNodoDerecho(new NodoArbol(valor));   // Si el nodo está vacío, se coloca el nuevo nodo
            } else {
                this.getNodoDerecho().insertarNodo(valor);  // Aplicación de recursividad
            }
        }
    }
}
