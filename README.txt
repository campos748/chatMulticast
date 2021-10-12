************************************* Chat Multicast ***************************************
Chat desarrollado en Java con la tecnología de JavaFX para el desarrollo de la GUI.
Este chat utilizada la tecnología multicast, utilizando de serie el puerto 6703 y la dirección IP
multicast 224.0.0.100


Para poder enviar y recibir los mensajes se hace uso de la clase Task de JavaFX. De tal forma que
cuando se genera el controlador de la ventana se entra en un bucle infinito a la espera de recibir
mensajes.

************************************ EJECUCIÓN **********************************************

Al iniciar el programa se muestra directamente la ventana del chat. En ella podemos seleccionar
el nombre con el que queremos que el resto de usuarios nos vean y posteriormente enviar los
mensajes. También se puede no escribir ningún nombre de tal forma que el resto de usuario verían
un "?" en el nombre.
