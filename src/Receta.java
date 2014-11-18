import java.util.ArrayList;

public class Receta  {
	private String nombreReceta, preparacion;
	private ArrayList<Ingrediente> ingrediente;

	public Receta(){}

	public ArrayList<Ingrediente> getIngrediente(){
		return this.ingrediente;
	}
	public void setIngrediente(ArrayList<Ingrediente> ingrediente){
		this.ingrediente = ingrediente;
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