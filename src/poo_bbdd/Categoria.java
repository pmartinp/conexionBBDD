package poo_bbdd;

public class Categoria {
	int id_categoria;
	boolean activo;
	String fecha_alta;
	String nombre;
	String descripcion;
	String url;
	
	public Categoria(int id_categoria, boolean activo, String fecha_alta, String nombre, String descripcion,
			String url) {
		this.id_categoria = id_categoria;
		this.activo = activo;
		this.fecha_alta = fecha_alta;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "\n\nId_categoria: " + id_categoria + "\nActivo: " + activo + "\nFecha_alta: " + fecha_alta
				+ "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nurl: " + url;
	}
}
