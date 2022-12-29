
  * Revisión y actualización del CM a la versión 1.0.1
  
  * Implementacion del método crearNuevoCurso de la clase CursoPropioDAO. En este método, se insertan los valores de los campos en la base de datos.
 
  * Implementacion (beta) de las interfaces del proyecto:
    - PantallaLogin -> En esta pantalla nos pedira que ingresemos el DNI y una contraseña. Además habra un icono de ayuda que pondra "f.e 03435754P". A continuacion hay otro boton que nos dirigira a la pestaña correspondiente dependiendo el tipo de usuario que haya iniciado sesion.
    
    - PantallaMatriculacion -> En esta pantalla nos pedira que escojamos dos opciones (botones) que son:
      - Matriculate -> Este boton nos dirigira hacia la ventana PantallaMatriculationProcess, la cual tendrá campos para seleccionar el tipo de curso e introducir   el dia de que se realiza la matricula. Una vez introducidos esos campos, se pulsa el boton que dice "Pass to payment" y automáticamente, iremos a la pantalla del pago de la matricula (en el hipotetico caso que no se quiera pagar en ese momento, se puede dar en el boton de arriba a la derecha "Go back" y se podrá pagar más adelante).
      - Pay a tuition -> Este boton nos dirigira hacia la ventana PantallaMatriculationProcess, la cual tendráá dos opciones(botones) que serán pago con tarjeta o por transferencia bancaria (y se tendrán que rellenar dichos campos).
      
    - PantallaJefeGabineteVicerrectorado -> En esta pantalla, habrá que seleccionar un curso y, una vez seleccionado, se podrá a dar a dos botones, uno en verde, que aprobara el curso y otro en rojo que lo rechazara.
      
    - PantallaDireccionCurso -> Esta pantalla, nos pide que elijamos tres opciones(botones) los cuales son:
      - Edit a course proposal -> En esta pantalla, habra que seleccionar el nombre del curso, y rellenar todos los campos. Una vez rellenados los campos, se pulsara el boton verde y automaticamente, se actualizara la informacion de dicho curso.
      - Register a course proposal ->  En esta pantalla, habra que rellenar todos los campos. Una vez rellenados los campos, se pulsara el boton verde y automaticamente, se incluiran los campos en la BBDD.
    
    - PantallaEmpleadosVicerrectorado -> En esta pantalla nos pedira que escojamos tres opciones (botones) los cuales son:
      - Check incomes -> Este boton nos dirigira hacia la ventana PantallaCheckIncomes, la cual tendrá campos para seleccionar el tipo de curso, introducir el dia de inicio y el dia que acaba. Una vez introducidos esos campos, se pulsa el boton que dice "See incomes" y automáticamente, aparecerán los ingresos con esos filtros.
      - List Course Editions -> Este boton nos dirigira hacia la ventana PantallaListEditions, la cual tendrá campos para introducir el dia de inicio y el dia que acaba. Una vez introducidos esos campos, se pulsa el boton que dice "See Course Editions" y automáticamente, aparecerán los cursos con esos filtros.
      - Check the status of a course -> Este boton nos dirigira hacia la ventana PantallaStatusCourse, la cual tendrá campos para introducir el dia de inicio y el dia que acaba. Una vez introducidos esos campos, se pulsa el boton que dice "See Course Status" y automáticamente, aparecerán los cursos con esos filtros.
    
    - Las pantallas tendrán dos botones en la esquina derecha los cuales serán de log out (el cual nos dirigirá a la pantalla de inicio) y otro de Go Back que nos dirigira a la pestaña anterior.
    

## Aclaraciones adicionales

 * Se ha utilizado la **rama GestionCursos** para implementar el caso de uso Realizar Propuesta de Curso
 * Cambio en la clase CursoPropio el atributo secretario de tipo ProfesorUCLM a Profesor, ya que se especifica que el profesor secretario puede ser de UCLM o externo.
 * Para el acceso a la BD, no se utiliza la Clase AbstractEntityDAO
