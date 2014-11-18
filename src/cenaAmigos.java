import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class cenaAmigos{

	public static void main(String[] args) {

		int cuantos, cuantosIngredientes,gramos,cantidad;
		String nombre, descripcion, nombreIngrediente,opcion;

		Scanner sc = new Scanner(System.in);
		
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		ArrayList<Ingrediente> ingredientes2 = new ArrayList<Ingrediente>();

		System.out.println("Buenas,\nVamos a guardar unas recetas para utilizar ");
		System.out.println("\n¿Cuantas recetas quieres guardar? (num): ");
		cuantos = sc.nextInt();

		for(int i=0; i<cuantos; i++){
			Receta receta = new Receta();
			System.out.println("\nEscribe el nombre de la receta: ");
			nombre = sc.next();
			receta.setNombreReceta(nombre);

			System.out.println("\nDescribe como se realiza (Intro para acabar): ");
			descripcion = sc.next();
			receta.setPreparacion(descripcion);

			System.out.println("\n¿Cuantos ingredientes vas a usar? (num): ");
			cuantosIngredientes = sc.nextInt();
			for(int e=0; e<cuantosIngredientes; e++){
				Ingrediente ingrediente = new Ingrediente();
				System.out.println("\nEscribe el nombre del ingrediente: ");
				nombreIngrediente = sc.next();
				ingrediente.setNombreIngrediente(nombreIngrediente);
				do{
					System.out.println("\n¿Es unitario o va con gramaje? (uni/gr): ");
					opcion = sc.next();

				}while(!opcion.equalsIgnoreCase("uni") && !opcion.equalsIgnoreCase("gr"));
				if(opcion.equalsIgnoreCase("uni")){
					ingrediente.setEnGramos(false);
				}else{
					ingrediente.setEnGramos(true);
				}

				if(ingrediente.getEnGramos()==true){
					System.out.println("\n¿Cuantos gramos vas a utilizar? (num): ");
					gramos = sc.nextInt();
					ingrediente.setCantidadGramos(gramos);
				}else{
					System.out.println("\n¿Cuantos/as vas a utilizar? (num): ");
					cantidad = sc.nextInt();
					ingrediente.setCantidadUnidad(cantidad);
				}

				ingredientes.add(ingrediente);
			}
			
			receta.setIngrediente(ingredientes);
			recetas.add(receta);
		}
		System.out.println(recetas.get(0).getNombreReceta());
		ingredientes2 = recetas.get(0).getIngrediente();
		for(int z=0; z<ingredientes2.size(); z++){
			System.out.println(ingredientes2.get(z).getNombreIngrediente());
		}

		File listaRecetas = new File("./listaRecetas.txt");
		System.out.println("\nCreando archivo con las recetas impresas....");
		try{
			if(listaRecetas.createNewFile()){
				System.out.println("Archivo creado correctamente...");
			}
		}catch(Exception ioe){
			System.out.println("Error: "+ioe);
		}

		try{
			System.out.println("Escribiendo en el archivo listaRecetas.txt...");
			FileWriter escritor = new FileWriter(listaRecetas);

			for(int a=0; a<recetas.size(); a++){
				ingredientes = recetas.get(a).getIngrediente();
				escritor.append(recetas.get(a).getNombreReceta()+";");
				for(int o=0; o<ingredientes.size(); o++){
					escritor.append(ingredientes.get(o).getNombreIngrediente()+"*"+ingredientes.get(o).getCantidadGramos()+"*"+ingredientes.get(o).getCantidadUnidad()+"*"+ingredientes.get(o).getEnGramos()+"#");
				}
				escritor.append(";"+recetas.get(a).getPreparacion()+"\n");
			}escritor.close();
		}catch(Exception z){
			System.out.println("Error: "+z);
		}

		/*try{
			File listaRecetas2 = new File("./listaRecetas.txt");
			FileInputStream fis = new FileInputStream(listaRecetas2);
	        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
	        BufferedReader br = new BufferedReader(isr);

	        String linea;
	        linea = br.readLine();
	        String [] campos = null;
	        while(linea!=null){
	        	 campos = linea.split(";");
	        }
	        System.out.println(campos[0]);
	        System.out.println(campos[1]);
	    }catch(Exception ioe){
	    	System.out.println("Error: "+ioe);
	    }
	    */
	}
}