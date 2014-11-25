import java.util.ArrayList;

public class Receta  {
	private String nombreReceta, preparacion;
	private ArrayList<Ingrediente> ingredientes;

	public Receta(){}

	public ArrayList<Ingrediente> getIngredientes(){
		return this.ingredientes;
	}
	public void setIngredientes(ArrayList<Ingrediente> ingredientes){
		this.ingredientes = ingredientes;
	}

	public String getNombreReceta(){
		return nombreReceta;
	}
	public void setNombreReceta(String nombre){
		this.nombreReceta = nombre;
	}

	public String getPreparacion(){
		return preparacion;
	}
	public void setPreparacion(String preparar){
		this.preparacion = preparar;
	}
}