# java-jdbc-ejercicios

Ejercicios de clase del ciclo de DAM (2023) sobre acceso a bases de datos con JDBC y MySQL. La interfaz son diálogos de `JOptionPane`.

## Qué hay

| Ejercicio | Qué hace |
| --- | --- |
| `Ejercicio1` | Alta y listado de clientes en la base `EJERCICIO1_CLIENTES`. |
| `Ejercicio3` | Alta de empleados y proyectos, y asignación de empleados a proyectos, en `GESTOR_PROYECTOS`. |
| `Ejercicio4` | Lo mismo que el 3, separado en clases de modelo (`Empleados`, `Proyectos`, `Asig_Proyecto`), más un listado de empleados asignados. |
| `Ejercicio6` | Lee un fichero de texto delimitado e inserta cada línea como cliente. |

Los scripts `Gestor_proyectos.sql` y `Ejercicio!_NavClientes.sql` crean las dos bases de datos. La memoria de la práctica está en `Memoria Brad Lopez.pdf`.

## Cómo ejecutarlo

1. Un MySQL local en el puerto 3306 con usuario `root` sin contraseña (la configuración de la práctica, en las clases `Conexion`).
2. Ejecutar los dos scripts `.sql`.
3. Descargar el conector [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) y añadirlo al classpath; ya no se incluye en el repositorio.
4. Lanzar el `Main` del ejercicio que quieras.

## Limitaciones conocidas

- Credenciales escritas en el código, válidas solo para una máquina de prácticas.
- Las consultas usan `PreparedStatement`, salvo el nombre de la tabla en `Ejercicio6`, que se concatena.
- Las conexiones no se cierran con `try-with-resources`; `desconectar()` solo pone la referencia a `null`.
- Los modelos del `Ejercicio4` guardan sus campos como `static`, así que todas las instancias comparten los mismos datos.
