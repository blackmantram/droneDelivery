droneDelivery - Prueba técnica II
===============

segunda parte de la prueba técnica para S4N desarrollada en java por Sergio Alexander Rico Abella

## Estructura

```sh
|--src
|--tests
|--lib
|--in.txt
```
- src: Contiene los archivos fuente de la aplicación. Los paquetes fueron nombrados de acuerdo a la convención de dominio sugerida por oracle https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html 
- tests: Contiene las pruebas unitarias de la aplicación. Han sido separadas por paquetes de acuerdo al ámbito de las pruebas. Actualmente existen 5 paquetes. 
    - **drone**: pruebas de comportamiento del drone 
    - **io**: pruebas del sistema de escritura/lectura 
    - **delivery**: prueba el sistema que controla los envíos
	- **demo**: contiene la prueba unitaria que demuestra el ejemplo enviado en el enunciado de la prueba.

    El porcentaje actual de cubrimiento de pruebas (según EclEmma 2.3.3) es del 96.2%.
- lib: en esta carpeta se incluye el .jar del framework de mockups **mockito** usado para las pruebas y necesaria para que se puedan ejecutar.

## Ejecución del programa y pruebas (IDE Eclipse neon)
- Clonar el repositorio dentro de la carpeta usada para el workspace de eclipse
- Crear un nuevo proyecto de java en eclipse (File > new > Java project) 
- ingresar droneDelivery como nombre de proyecto (o el nombre exacto de la carpeta donde se clonó el repositorio)
- Al hacer click en next Eclipse debe detectar la estructura del proyecto incluyendo la librería de mockito incluída en lib.
- Es normal que Eclipse reporte errores en el paquete de pruebas debido a que por defecto no se incluye la librería de JUnit. Para incluir JUnit se debe seleccionar Project > Properties > Java build Path > Libraries > Add Library > item Junit > Next > Finish > Ok.
- El archivo ```src/DroneDelivery.java``` contiene el main de la aplicación. Si eclipse no ha generado automaticamente la configuración de ejecución, se puede realizar haciendo click en Run > Run as > Java Application y seleccionado el item *DroneDelivery - (default package)* en la lista de selección.
- El archivo ```src/BulkDroneDelivery.java``` contiene el main que ejecuta los envíos múltiples de la parte II de la prueba. Para ejecutarlo se debe realizar el mismo paso anteriormente descrito
- El programa se ejecuta en la raiz del proyecto usando el archivo ```in.txt``` o los archivos ```in01.txt```, ```in02.txt``` ... ```in20.txt``` incluidos en el directorio ```data``` y generando el archivo ```out.txt``` o los archivos ```out01.txt```, ```out02.txt``` ... ```in20.txt``` según el ejecutable que se utilice
- Para ejecutar las pruebas seleccionar Run > Run as > JUnit Test

# Error en el ejemplo del encunciado
Durante el desarrollo de la prueba se detectó un error en el enunciado del documento el cual afirma lo siguiente:

```sh
(...) Un ejemplo del archivo de texto que ingresaría al sistema para las entregas de un día sería:

AAAAIAAD
DDAIAD
AAIADAD

Donde:
- La letra A corresponde a un movimiento hacia adelante.
- La letra I corresponde a un giro de 90 grados del dron a la izquierda.
- La letra D corresponde a un giro de 90 grados del dron a la derecha.

una vez terminada la ejecución de las rutas se le entregue un informe en otro archivo de texto de la posición del dron en el plano cartesiano de cada entrega (...)

(...) para la primera línea del ejemplo del archivo de texto anterior, se espera que el informe se presente así:

== Reporte de entregas ==
(-2, 4) dirección Norte
(-3, 3) dirección Sur
(-4, 2) dirección Oriente
```

Se entiende que cada línea del informe del ejemplo corresponde a la posición en la cual el dron quedó en el plano cartesiano después de cada entrega, sin embargo, realizando el ejercicio, las respuestas dos y tres no son correctas.

asumiendo que el dron no reinicia su posición a (0,0,N) después de cada entrega (esta es la forma como funciona la aplicación actualmente) la respuesta sería de la siguiente forma:

```sh
== Reporte de entregas ==
(-2, 4) dirección Norte
(-1, 3) dirección Sur
(0, 0) dirección Occidente
```
La evidencia de este caso se encuentra en la prueba unitaria **tests/demo/ExampleDemo.java**