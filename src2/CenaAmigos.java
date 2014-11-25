//importamos paquetes necesarios
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

//clase principal
public class CenaAmigos {

//clase main
	public static void main(String[] args) {

//declaración de variables		
		int cuantas, cuantosIngredientes, gramos, cantidad;
		String nombre, descripcion, nombreIngrediente, opcion;

//Crear objeto de tipo Scanner
		Scanner sc = new Scanner(System.in);
//ArrayList de tipo objeto Receta
		ArrayList<Receta> recetas = new ArrayList<Receta>();
//ArrayList de tipo objeto Ingrediente
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

//Comenzamos pidiendo el numero de recetas a guardar
		System.out.println("Buenas,\nVamos a guardar unas recetas.");
		System.out.println("¿Cuantas recetas quieres guardar?: ");
		cuantas = sc.nextInt();
//Por cada receta que quiera guardar, pediremos nombre y descripcion
		for(int i=0; i<cuantas; i++) {
//creamos un objeto de tipo Receta para guardar los atributos introducidos por teclado
			Receta receta = new Receta();
			System.out.println("\nEscribe el nombre de la receta: ");
			nombre = sc.next();
			receta.setNombreReceta(nombre);

			System.out.println("\nDescribe como se realiza la receta: ");
			descripcion = sc.next();
			receta.setPreparacion(descripcion);
//Pedimos que ingrese el número de ingredientes a utilizar en cada receta
			System.out.println("\n¿Cuantos ingredientes vas a usar?: ");
			cuantosIngredientes = sc.nextInt();
//Declaramos un ArrayList de tipo Ingrediente para cada receta
			ingredientes = new ArrayList<Ingrediente>();
//Por cada ingrediente de una receta pediremos que ingrese el valor de los atributos de éste
			for(int e=0; e<cuantosIngredientes; e++) {
//Creamos un objeto de tipo Ingrediente para dar valor a los atributos de un ingrediente completo
				Ingrediente ingrediente = new Ingrediente();
				System.out.println("\nEscribe el nombre del ingrediente: ");
				nombreIngrediente = sc.next();
				ingrediente.setNombreIngrediente(nombreIngrediente);
//Mientras no escriba "uni" ni "gr" volverá a pedir que ingresemos
				do {
					System.out.println("\n¿Es unitario? o ¿va con gramaje? (uni/gr): ");
					opcion = sc.next();
				} while(!opcion.equalsIgnoreCase("uni") && !opcion.equalsIgnoreCase("gr"));
//Si escribe "uni" al atributo "enGramos" le dará el valor false si no true
				if(opcion.equalsIgnoreCase("uni")) {
					ingrediente.setEnGramos(false);
				}else{
					ingrediente.setEnGramos(true);
				}
//Si "enGramos" tiene el valor true, le pediremos cuantos gramos va utilizar y le daremos ese valor a cantidadGramos
				if(ingrediente.getEnGramos() == true){
					System.out.println("\n¿Cuantos gramos vas a utilizar?: ");
					gramos = sc.nextInt();
					ingrediente.setCantidadGramos(gramos);
//si no, le pediremos cuantas unidades va a utilizar
				}else{
					System.out.println("\n¿Cuantos/as vas a utilizar?: ");
					cantidad = sc.nextInt();
					ingrediente.setCantidadUnidad(cantidad);
				}
//metemos el ingrediente con sus valores en el ArrayList de Ingredientes
				ingredientes.add(ingrediente);
			}
//le damos el valor de Ingredientes al objeto recets con el ArrayList de Ingredientes
			receta.setIngredientes(ingredientes);
//metemos la receta completa (con valores e ingredientes) en el ArrayList de tipo Receta
			recetas.add(receta);
		}
//Creamos un objeto de tipo File con el archivo a crear
		File listaRecetas = new File("./listaRecetas.txt");
		System.out.println("\nCreando el archivo listaRecetas.txt...");
//Con una excepción controlamos que el archivo se cree si no está creado
		try{
			if(listaRecetas.createNewFile()) {
				System.out.println("\nArchivo creado correctamente.");
			}
		}catch(Exception ioe) {
			System.out.println("Error creando el archivo: "+ioe);
		}
//Con otra excepción controlamos que se pueda escribir en el fichero
		try {
			System.out.println("\nEscribiendo en el fichero listaRecetas.txt...");
//Creamos un objeto de tipo FileWriter para poder escribir en el fichero
			FileWriter escritor = new FileWriter(listaRecetas);
//Hasta la cantidad de recetas guardada en el ArrayList de Recetas, escribirá el nombre de la receta separado por ;
			for(int a=0; a<recetas.size(); a++) {
				escritor.append(recetas.get(a).getNombreReceta()+";");
//en el ArrayList de tipo ingrediente metemos el atributo ingredientes que es un ArrayList de tipo ingrediente
				ingredientes = recetas.get(a).getIngredientes();
/*Hasta el numero maximo de ingredientes en el ArrayList, escribirá en el fichero los valores de cada ingrediente
			separados por * y los ingredientes completos separados por # 
			*/
				for(int o=0; o<ingredientes.size(); o++){
					escritor.append(ingredientes.get(o).getNombreIngrediente()+"*"+ingredientes.get(o).getCantidadGramos()+"*"+ingredientes.get(o).getCantidadUnidad()+"*"+ingredientes.get(o).getEnGramos()+"#");
				}
//Por último, después de los ingredientes, en la misma línea, escribirá separado por ; la descripción de la receta y un salto de línea para la siguiente receta
				escritor.append(";"+recetas.get(a).getPreparacion()+"\n");
			}
//cerramos el FileWriter para liberar recursos y que el fichero quede bien cerrado
			escritor.close();
		}catch(Exception z) {
			System.out.println("Error, no se puede escribir en el fichero: "+z);
		}
//Con otra excepción, controlamos el encontrar el fichero y que se pueda leer
		try {
			FileInputStream fis = new FileInputStream("./listaRecetas.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF8");
			BufferedReader br = new BufferedReader(isr);
//Declaramos una variable de tipo String para guardar cada línea leida desde el fichero
			String linea;
			linea = br.readLine();
//Declaramos un array de tipo String
			String [] campos = null;
			System.out.println("\nTus recetas: ");
//Mientras la siguiente línea a leer no esté vacía
			while(linea != null) {
//En el array de Strings guardamos lo leido en la primera línea separados los valores por ;
				campos = linea.split(";");
				System.out.println("**************************************");
//Imprimimos los campos conocidos. 0=nombre y 2=descripcion
				System.out.println("Nombre: "+campos[0]);
				System.out.println("Descripción: "+campos[2]);
//Metemos en una variable de tipo String la línea con todos los ingredientes de una receta
				String ingre = campos[1];
//Separamos los diferentes ingredientes en el array de String utilizando el delimitador #
				campos = ingre.split("#");
//por cada ingrediente realizará un bucle
				for(int x=0; x<campos.length; x++) {
//Metemos en una variable de tipo String un ingrediente completo de una receta
					String ingreAtribSeparados = campos[x];
//Metemos en un array de tipo String los atributos de un ingrediente ya separados por el demilitador *
					String [] campos2 = ingreAtribSeparados.split("\\*");
//Imprimimos los atributos del ingrediente
					System.out.println("\nIngredientes: ");
					System.out.println("Nombre: "+campos[0]);
					System.out.println("Gramos: "+campos[1]);
					System.out.println("Unidades: "+campos[2]);
					System.out.println("**************************************");
				}
//al acabar cada for y dentro del while le volvemos a decir que lea una línea 
				linea = br.readLine();
			}
		}catch(Exception ioe) {
			System.out.println("Error: "+ioe);
		}
	}
}