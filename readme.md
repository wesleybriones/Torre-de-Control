# Torre de Control

El nuevo Aueropuerto Internacional de Chipiona nos encarga desarrollar una aplicación para gestionar el control del tráfico aéreo de las aeronaves con destino al mismo.

Como todas las torres de control, cuando el sistema de radar detecta un avión lo registra en su base de datos anotando la información proporcionada por los transceptores:

* Código de vuelo.
* Aerolínea.
* Velocidad de la aeronave en el momento de la detección (en Km/h)
* Fecha y hora del momento de la detección.
* Distancia al aeropuerto.

En nuestra aplicación, como no disponemos del sistema de teledetección automático, simularemos la detección automática de las aeronaves dándolos de alta a mano. Es decir, programando un mecanismo que nos solicite los datos tal como hemos hecho desde que empezó el curso.

El sistema de gestión, una vez tenga aeronaves registradas, debe ofrecer una opción para listar los aviones registrados en el sistema. Dicha lista debe ser mostrada en orden de proximidad al aeropuerto, es decir, los aviones más cercanos deben aparecer primero.

Como un avión cuando está en el aire está en movimiento, nuestro sistema deberá calcular la distancia transcurrida desde que fue registrado inicialmente hasta que se ha solicitado el listado, actualizando su distancia al aeropuerto y utilizándola para genera la nueva lista de proximidad. Supondremos (aunque no es real) que las aeronaves van a velocidad constante.
