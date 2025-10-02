Ejercicio: Captura de tecla "C" en Java Swing

Proyecto para aprender a capturar eventos de teclado en Java Swing. Básicamente es una lista de tareas donde puedes marcar/desmarcar items presionando la tecla C.

A) ¿Qué hace?

Es una app con una lista de tareas. Seleccionas una tarea y presionas la tecla **C** para marcarla como completada (aparece un ✓). Si vuelves a presionar C, se desmarca. También tiene un botón para agregar nuevas tareas.

Lo hice porque estaba aprendiendo sobre KeyListeners y Key Bindings en Swing, y quería ver la diferencia entre ambos métodos

B) ¿Cómo usarlo?

1. Clona el repo o descarga el archivo `CapturaTeclaApp.java`
2. Ábrelo en tu IDE
3. Ejecuta el archivo (Shift+F6 en NetBeans)
4. Selecciona una tarea con el mouse
5. Presiona la tecla **C** para marcar/desmarcar

También puedes agregar tareas nuevas con el botón de abajo.

C)Lo que aprendí

Este proyecto me sirvió para entender:

- Cómo usar Key Bindings en Swing (que es mejor que KeyListener)
- La diferencia entre InputMap y ActionMap
- Cómo funciona JList con DefaultListModel
- Por qué hay que usar SwingUtilities.invokeLater() para crear interfaces

-Key Bindings vs KeyListener

Al principio usé KeyListener pero me di cuenta que Key Bindings es mejor porque:
- No necesitas que el componente tenga focus
- El código queda más limpio
- Es más fácil manejar varias teclas

Key Bindings usa tres cosas:
1. **InputMap** - mapea la tecla que quieres capturar
2. **ActionMap** - define qué hacer cuando se presiona
3. **Scope** - define cuándo debe funcionar (usé WHEN_IN_FOCUSED_WINDOW)

D) Estructura

```
src/
└── CapturaTeclaApp.java  # Todo está aquí
```

Todo el código está en un solo archivo para que sea fácil de entender. Tiene comentarios explicando las partes importantes.

Hecho como proyecto de práctica para la clase de programación orientada a objetos.

<img width="845" height="404" alt="Captura de pantalla 2025-10-02 135535" src="https://github.com/user-attachments/assets/60b3abf2-bf63-4f8f-9442-b95a5941050d" />

