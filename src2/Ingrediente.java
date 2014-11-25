public class Ingrediente {

	private String nombreIgrediente;
	private int cantidadGramos;
	private int cantidadUnidad;
	private boolean enGramos;

	public Ingrediente(){}

	public String getNombreIngrediente(){
		return nombreIgrediente;
	}
	public void setNombreIngrediente(String nombre){
		this.nombreIgrediente = nombre;
	}

	public int getCantidadGramos(){
		return cantidadGramos;
	}
	public void setCantidadGramos(int cantidad){
		this.cantidadGramos = cantidad;
	}

	public int getCantidadUnidad(){
		return cantidadUnidad;
	}
	public void setCantidadUnidad(int cantidad){
		this.cantidadUnidad = cantidad;
	}

	public boolean getEnGramos(){
		return enGramos;
	}
	public void setEnGramos(boolean enGramos){
		this.enGramos = enGramos;
	}
}