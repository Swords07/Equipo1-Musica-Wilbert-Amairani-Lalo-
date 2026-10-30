import java.util.Scanner;


class TempoCont {
    static Scanner sc = new Scanner(System.in); // Scanner global

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


	public static void main(String[] args) {
        boolean banderaPrincipal = false;
        String saltoLinea = "";
        boolean banderaPago = false;

        do{
            mostrarMenu();
            int opcion = sc.nextInt();
            saltoLinea = sc.nextLine();
            
            switch(opcion){
                case 1: // SELECCIONAR OPCIÓN 1
                    System.out.println("====== REGISTRAR CLIENTE ======");
                    System.out.println("Ingrese su nombre (Mínimo 3 letras):");
                    String nombreCliente = registrarCliente();
                    //System.out.println(nombreCliente); Confirmar que se guarda el texto en la variable (Debug)
                    System.out.println("Éxito!");
                    System.out.println("");
                    break;

                case 2: // SELECCIONAR OPCIÓN 2
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

                    System.out.println("Tipo de pago:");
                    System.out.println("1. Pago simple");
                    System.out.println("2. 3 meses por adelantado");
                    System.out.println("3. Pago anual");
                    
                    while(banderaPago == false){
                        int opcionPago = sc.nextInt();
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
