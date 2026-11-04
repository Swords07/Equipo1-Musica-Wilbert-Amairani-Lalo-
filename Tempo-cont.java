import java.util.Scanner;


class TempoCont {
    static Scanner sc = new Scanner(System.in); // Scanner global
    static String nombreRegistrado = "";
    static String instrumentoRegistrado = "";
    static String modalidadRegistrada = "";
    static String nivelRegistrado = "";
    static String tramiteRegistrado = "";
    static int pagoRegistrado = 0;
    static boolean inscripcionRegistrada = false;

    static void mostrarMenu(){
        System.out.println("TEMPO CONT");
        System.out.println("1. Registrar cliente");
        System.out.println("2. ver servicios para tramitar");
        System.out.println("3. Imprimir comprobante");
        System.out.println("4. Salir");
    } // Función que sólo muestra el menú

    static String registrarCliente(){
        String nombreCliente = sc.nextLine();
        int largoNombre = nombreCliente.length();
        //System.out.println(largoNombre); Confirmar el largo del nombre (Debug)
                    
        while(nombreCliente == "" || largoNombre < 3){
            if(nombreCliente == ""){
                System.out.println("El campo NO puede estar vacío, intente de nuevo:");
                nombreCliente = sc.nextLine();
                largoNombre = nombreCliente.length();
                //System.out.println(largoNombre); Confirmar el largo del nombre (Debug)
            } else if(largoNombre < 3){
                System.out.println("El campo DEBE tener mínimo 3 letras, intente de nuevo:");
                nombreCliente = sc.nextLine();
                largoNombre = nombreCliente.length();
                //System.out.println(largoNombre); Confirmar el largo del nombre (Debug)
            }
        }
        return nombreCliente;
    } // Función para registrar al cliente

    static String leerInstrumento(){
        String instrumento = "";
        while(instrumento == ""){
            int opcionInstrumento = sc.nextInt();
            switch (opcionInstrumento) {
            case 1:
                instrumento = "Guitarra";
                break;
            case 2:
                instrumento = "Piano";
                break;
            case 3:
                instrumento = "Batería";
                break;
            default:
                System.out.println("Opción inválida");
            }
        }
        return instrumento;
    } // Función para seleccionar el instrumento

    static String leerModalidad(){
        String modalidad = "";
        while(modalidad == ""){
            int opcionModalidad = sc.nextInt();
            switch (opcionModalidad) {
                case 1:
                    modalidad = "Presencial";
                    break;
                case 2:
                    modalidad = "Virtual";
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        return modalidad;
    } // Función para seleccionar la modalidad

    static String leerNivel(){
        String nivel = ""; 
        while(nivel == ""){
            int opcionNivel = sc.nextInt();
            switch (opcionNivel) {
                case 1:
                    nivel = "Principiante";
                    break;
                case 2:
                    nivel = "Intermedio";
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        return nivel;
    } // Función para seleccionar el nivel

    static String leerTramite(){
        String tramite = "";
        while(tramite == ""){
            int opcionTramite = sc.nextInt();
            switch (opcionTramite) {
                case 1:
                    tramite = "Inscripción";
                    break;
                case 2:
                    tramite = "Renovación";
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        return tramite;
    } // Función para seleccionar el trámite
    //modulo proceso principal
    static void procesarInscripcion(String instrumento, String modalidad,
    String nivel, String tramite, int opcionPago) {
    instrumentoRegistrado = instrumento;
    modalidadRegistrada = modalidad;
    nivelRegistrado = nivel;
    tramiteRegistrado = tramite;
    pagoRegistrado = opcionPago;
    inscripcionRegistrada = true;

}
     // ----- cálculo: constantes -----
    static final double CUOTA_SEMESTRAL = 800;
    static final double DESC_TRES_MESES = 0.15;
    static final double DESC_DOS_CURSOS = 0.20;
    static final double DESC_RENOVACION = 0.20;
        // ---- FUNCIONES DE CÁLCULO ----

    // ¿Cuánto cuesta un mes de este curso?
    static double obtenerMensualidad(String instrumento, String modalidad, String nivel){
        boolean esVirtual = modalidad.equals("Virtual");
        boolean esPrincipiante = nivel.equals("Principiante");

        switch (instrumento) {
            case "Guitarra":
                if (esVirtual) {
                    return esPrincipiante ? 500 : 650;
                }
                return 800;
            case "Batería":
                if (esVirtual) {
                    return esPrincipiante ? 600 : 750;
                }
                return 1000;
            case "Piano":
                if (esVirtual) {
                    return esPrincipiante ? 450 : 600;
                }
                return 700;
            default:
                return 0; // instrumento no válido
        }
    }

    // ¿Cuántos meses se pagan según la opción de pago (1, 2 o 3)?
    static int obtenerMeses(int opcionPago){
        switch (opcionPago) {
            case 1:  return 1;   // pago simple
            case 2:  return 3;   // 3 meses por adelantado
            case 3:  return 12;  // pago anual
            default: return 0;   // opción no válida
        }
    }

    // ¿Cuánto es todo sin descuentos? (mensualidades + cuota semestral)
    static double calcularSubtotal(double mensualidadTotal, int meses){
        return mensualidadTotal * meses + CUOTA_SEMESTRAL;
    }

    // ¿Cuánto dinero se descuenta por 3 meses adelantados y/o 2 cursos?
    static double calcularDescuentoPago(double mensualidadTotal, int meses, int cursos){
        double baseMensualidades = mensualidadTotal * meses;
        double porcentaje = 0;

        if (meses == 3) {
            porcentaje += DESC_TRES_MESES;
        }
        if (cursos == 2) {
            porcentaje += DESC_DOS_CURSOS;
        }
        return baseMensualidades * porcentaje;
    }

    // ¿Cuánto se descuenta si renueva? (20% de la mensualidad de ese mes)
    static double calcularDescuentoRenovacion(String tramite, double mensualidadTotal){
        if (tramite.equals("Renovación")) {
            return mensualidadTotal * DESC_RENOVACION;
        }
        return 0;
    }

    // ¿Cuánto se descuenta en total? (suma de ambos descuentos)
    static double calcularDescuentoTotal(double descuentoPago, double descuentoRenov){
        return descuentoPago + descuentoRenov;
    }

    // ¿Cuánto paga al final?
    static double calcularTotal(double subtotal, double descuentoTotal){
        return subtotal - descuentoTotal;
    }

    // ===== CAPTURA: cantidad de cursos (para acordar con tu compañero) =====
    static int leerCursos(){
        int cursos = 0;
        while(cursos != 1 && cursos != 2){
            cursos = sc.nextInt();
            if(cursos != 1 && cursos != 2){
                System.out.println("Opción inválida");
            }
        }
        return cursos;
    }

	public static void main(String[] args) {
        boolean banderaPrincipal = false;
        String saltoLinea = "";
        boolean banderaPago = false;
                // cálculo
        int cursos = 1;
        double mensualidadTotal = 0;
        double subtotal = 0;
        double descuentoPago = 0;
        double descuentoRenov = 0;
        double descuentoTotal = 0;
        double total = 0;
        do{
            mostrarMenu();
            int opcion = sc.nextInt();
            saltoLinea = sc.nextLine();
            
            switch(opcion){
                case 1: // SELECCIONAR OPCIÓN 1
                    System.out.println("====== REGISTRAR CLIENTE ======");
                    System.out.println("Ingrese su nombre (Mínimo 3 letras):");
                    String nombreCliente = registrarCliente();
                    nombreRegistrado = nombreCliente;
                    //System.out.println(nombreCliente); Confirmar que se guarda el texto en la variable (Debug)
                    System.out.println("Éxito!");
                    System.out.println("");
                    break;

                case 2: // SELECCIONAR OPCIÓN 2
                banderaPago = false;    
                System.out.println("====== SERVICIOS DISPONIBLES ======");
                    System.out.println("Cursos: Guitarra, Piano y Batería");
                    System.out.println("Niveles de los cursos: Principiante e Intermedio");
                    System.out.println("Modalidades: Presencial y Virtual");
                    System.out.println("Tipos de trámites: Inscripción y Renovación");
                    System.out.println("Tipos de pagos: Pago simple, 3 meses de adelantado o pago anual");
                    System.out.println("");
                    
                    System.out.println("Seleccione el curso:");
                    System.out.println("1. Guitarra");
                    System.out.println("2. Piano");
                    System.out.println("3. Batería");
                    String instrumento = leerInstrumento(); // Variable para el comprobante
                    
                    System.out.println("Seleccione la modalidad:");
                    System.out.println("1. Presencial");
                    System.out.println("2. Virtual");
                    String modalidad = leerModalidad(); // Variable para el comprobante

                    System.out.println("Seleccione el nivel del curso:");
                    System.out.println("1. Principiante");
                    System.out.println("2. Intermedio");
                    String nivel = leerNivel(); // Variable para el comprobante

                    System.out.println("Tipo de trámite:");
                    System.out.println("1. Inscripción");
                    System.out.println("2. Renovación");
                    String tramite = leerTramite(); // Variable para el comprobante

                    //calculo//
                    System.out.println("¿Cuántos cursos va a tomar? (1 o 2)");
                    cursos = leerCursos();

                    mensualidadTotal = obtenerMensualidad(instrumento, modalidad, nivel);

                    if (cursos == 2) {
                        System.out.println("--- Datos del segundo curso ---");
                        System.out.println("Seleccione el curso:");
                        System.out.println("1. Guitarra");
                        System.out.println("2. Piano");
                        System.out.println("3. Batería");
                        String instrumento2 = leerInstrumento();

                        System.out.println("Seleccione la modalidad:");
                        System.out.println("1. Presencial");
                        System.out.println("2. Virtual");
                        String modalidad2 = leerModalidad();

                        System.out.println("Seleccione el nivel del curso:");
                        System.out.println("1. Principiante");
                        System.out.println("2. Intermedio");
                        String nivel2 = leerNivel();

                        mensualidadTotal += obtenerMensualidad(instrumento2, modalidad2, nivel2);
                    }


                    
                    System.out.println("Tipo de pago:");
                    System.out.println("1. Pago simple");
                    System.out.println("2. 3 meses por adelantado");
                    System.out.println("3. Pago anual");
                    
                    int opcionPago = 0;
                    while(banderaPago == false){
                        opcionPago = sc.nextInt();
                        switch (opcionPago) {
                            case 1:
                                // Cálculos
                                banderaPago = true;
                                break;
                            case 2:
                                // Cálculos
                                banderaPago = true;
                                break;
                            case 3:
                                // Cálculos
                                banderaPago = true;
                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    procesarInscripcion(instrumento, modalidad, nivel, tramite, opcionPago);
                    
                    int meses = obtenerMeses(opcionPago);
                    subtotal = calcularSubtotal(mensualidadTotal, meses);
                    descuentoPago = calcularDescuentoPago(mensualidadTotal, meses, cursos);
                    descuentoRenov = calcularDescuentoRenovacion(tramite, mensualidadTotal);
                    descuentoTotal = calcularDescuentoTotal(descuentoPago, descuentoRenov);
                    total = calcularTotal(subtotal, descuentoTotal);

                    //temporal//

                    //System.out.println("Mensualidad total: " + mensualidadTotal);
                    System.out.println("Subtotal: " + subtotal);
                    System.out.println("Descuento: " + descuentoTotal);             //prueba//
                    System.out.println("Total: " + total);//
 
                    /*System.out.println("");
                    System.out.println(instrumento);
                    System.out.println(modalidad);
                    System.out.println(nivel);                  //Confirmar que las variables para el comprobante sí se guardan (Debug)
                    System.out.println(tramite);
                    System.out.println(banderaPago); */
                    System.out.println("");
                    break;

                case 3: // SELECCIONAR OPCIÓN 3
                    System.out.println("Opción 3");
                    System.out.println("");
                    break;

                case 4: // SELECCIONAR OPCIÓN 4
                    System.out.println("Opción 4");
                    System.out.println("");
                    banderaPrincipal = true;
                    break;

                default:
                    System.out.println("Opción no válida");
                    System.out.println("");
            }
        } while(banderaPrincipal == false);


	}// Final del main

}// Final de la clase/programa
