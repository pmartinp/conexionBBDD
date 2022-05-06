package poo_bbdd;

public class Producto {

	int id_product;
	int id_categoria_default;
	int id_impuesto;
	String ean13;
	int cantidad;
	float precio;
	float precio_fabrica;
	String referencia;
	boolean activo;
	String fecha_alta;
	String nombre;
	String resumen;
	String descripcion;
	String url;

	public Producto(int id_product, int id_categoria_default, int id_impuesto, String ean13, int cantidad, float precio,
			float precio_fabrica, String referencia, boolean activo, String fecha_alta, String nombre, String resumen,
			String descripcion, String url) {
		this.id_product = id_product;
		this.id_categoria_default = id_categoria_default;
		this.id_impuesto = id_impuesto;
		this.ean13 = ean13;
		this.cantidad = cantidad;
		this.precio = precio;
		this.precio_fabrica = precio_fabrica;
		this.referencia = referencia;
		this.activo = activo;
		this.fecha_alta = fecha_alta;
		this.nombre = nombre;
		this.resumen = resumen;
		this.descripcion = descripcion;
		this.url = url;
	}

	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public int getId_categoria_default() {
		return id_categoria_default;
	}
	public void setId_categoria_default(int id_categoria_default) {
		this.id_categoria_default = id_categoria_default;
	}
	public int getId_impuesto() {
		return id_impuesto;
	}
	public void setId_impuesto(int id_impuesto) {
		this.id_impuesto = id_impuesto;
	}
	public String getEan13() {
		return ean13;
	}
	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getPrecio_fabrica() {
		return precio_fabrica;
	}
	public void setPrecio_fabrica(float precio_fabrica) {
		this.precio_fabrica = precio_fabrica;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
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
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
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
		return "\n\nId_product: " + id_product + "\nId_categoria_default: " + id_categoria_default
				+ "\nId_impuesto: " + id_impuesto + "\nEan13: " + ean13 + "\nCantidad: " + cantidad + "\nPrecio: " + precio
				+ "\nPrecio_fabrica: " + precio_fabrica + "\nReferencia: " + referencia + "\nActivo: " + activo
				+ "\nFecha_alta: " + fecha_alta + "\nNombre: " + nombre + "\nResumen: " + resumen + "\nDescripcion: "
				+ descripcion + "\nUrl: " + url;
	}
}
