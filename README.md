# InterfazGraficaArboles
---

**Participantes del grupo:**

- Dilan Chamba

- Roberto Lliguin

- Sebastian Narvaez

- Benito Minga

---
![Arbol1](https://github.com/manuelminga/InterfazGraficaArboles/assets/166522911/dbe3f0a1-3313-4376-8133-689864ec6529)

---

**FLUJO DE LA APLICACION**

El usuario ingresa un valor en el campo de texto y hace clic en "Añadir Nodo" o "Eliminar Nodo".

El árbol binario se actualiza en consecuencia.
    
El área de texto muestra los recorridos actualizados del árbol.
    
La vista del árbol se repinta para reflejar los cambios.
    
Los botones de recorrido permiten al usuario ver el árbol en diferentes órdenes de recorrido (preorden, inorden, postorden).
    
![ARBOL BINARIO](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523151/721753cb-53bf-458b-8224-30a6f900c6cc)

---
**Codigo**

- Tenemos diferentes clases, los cuales cumplen una determinada funcion, ya que el mismo nos ayuda a poder simplificar la complejidad del programa
  
  ![image](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523434/175baf6b-9173-4ee3-9063-a60bdfc9b884)


---

  Este código Java implementa una interfaz gráfica para gestionar un árbol binario. A continuación se presenta una breve explicacion del código en cuanto a la vista de la como es el funcionamiento de la interfaz:
  ![image](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523434/47b53db9-de62-48c3-a416-631211a5765d)
  ![image](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523434/c04ab3a6-b9a1-4c9b-a422-4c96662864cb)
  ![image](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523434/e0bb7fcc-caf1-40d1-97c7-231af4ba04a4)
  ![image](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523434/783e243a-9d83-49b6-9eb3-8ff6b383f36d)
  ![image](https://github.com/manuelminga/InterfazGraficaArboles/assets/166523434/0c57d439-c639-4aa6-8c22-6ffe54a83c8e)

---
1. **Importaciones**:
   - Importamos las librerías necesarias para crear la interfaz gráfica (`JFrame`, `JPanel`, `JLabel`, `JButton`, `JTextArea`, etc.) y para poder manejar eventos (`ActionEvent`, `ActionListener`).
     
2. **Clase principal `ejemplo`**:
   - Dentro del método `main` se creo un objeto `ArbolBinario` y se agregan nodos con valores específicos.
   - Se creo un `JFrame` (ventana) principal titulado "Árbol Binario" con un tamaño de 800x600 píxeles.
   - Se añade un panel (`VistaArbol`) al `JFrame` para visualizar el árbol binario.
   - Se configuro un `JPanel` de control con un diseño de rejilla (`GridBagLayout`) que contiene etiquetas, un campo de texto para ingresar valores, y botones para añadir y eliminar nodos, así como mostrar los recorridos Preorden, Inorden y Postorden del árbol.

3. **Componentes del panel de control**:
   - Un `JLabel` para el título "Gestión del Árbol Binario".
   - Un `JTextField` para ingresar el valor del nodo a agregar o eliminar.
   - Botones "Añadir Nodo" y "Eliminar Nodo" para agregar o eliminar nodos en el árbol.
   - Un `JTextArea` no editable para mostrar los recorridos del árbol (Preorden, Inorden, Postorden).
   - Botones "Preorden", "Inorden", y "Postorden" para mostrar los diferentes recorridos del árbol.

4. **Acciones de los botones**:
   - El botón "Añadir Nodo" agrega un nodo con el valor ingresado en el `JTextField`, actualiza los recorridos y repinta la vista del árbol.
   - El botón "Eliminar Nodo" elimina un nodo con el valor ingresado en el `JTextField`, actualiza los recorridos y repinta la vista del árbol.
   - Los botones "Preorden", "Inorden", y "Postorden" actualizan el tipo de recorrido mostrado en la vista del árbol y actualizan el `JTextArea` con el recorrido correspondiente.

5. **Método `actualizarRecorridos`**:
   - Actualiza el `JTextArea` con los recorridos actuales del árbol binario en las tres formas: Preorden, Inorden y Postorden, obtenidos de los métodos correspondientes del objeto `ArbolBinario`.

Este programa proporciona una interfaz gráfica interactiva para gestionar un árbol binario, permitiendo agregar y eliminar nodos, y visualizar los recorridos del árbol en diferentes órdenes.

