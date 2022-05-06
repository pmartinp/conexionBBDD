package poo_bbdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static Conexion c = new Conexion();
	
	public static final String SEPARATOR=";";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leer= new Scanner(System.in);
		
		boolean menu=true;
		int opcion;
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		while(menu) {
			mostrarMenu();
			try {
				opcion=leer.nextInt();
				
				switch(opcion) {
				//Importar categorías
				case 1:
					leerCategorias(categorias);
					for(Categoria x: categorias) {
						insertarCategoria(x);
					}
					break;
				//Importar productos
				case 2:
					leerProductos(productos);
					for(Producto x: productos) {
						insertarProducto(x);
					}
					break;
				//Crear categoría
				case 3:
					insertarCategoria(crearCategoria());
					break;
				//Editar producto
				case 4:
					editarProducto();
					break;
				//Salir
				case 5:
					menu=false;
					System.out.println("Programa finalizado. Actividad 13: Pedro Manuel Martín Pérez. 17 de mayo de 2022");
					break;
				default:
					System.out.println("Introduzca una opción válida");
				}
			}catch(Exception e) {
				System.out.println("Introduzca una opción válida."+ e.getMessage());
				leer.next();
			}
		}
	}
	public static void mostrarMenu() {//función para mostrar el menú por pantalla
		System.out.println("1. Importar categorías");
		System.out.println("2. Importar productos");
		System.out.println("3. Crear categoría");
		System.out.println("4. Editar producto");
		System.out.println("5. Salir");
	}
	
	public static void leerCategorias(ArrayList<Categoria> categorias) {//función para leer el archivo categorias.csv
		BufferedReader br = null;
		try {
	         br =new BufferedReader(new FileReader("files/categorias.csv"));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            if(fields[0].matches("[\\d]*")){
	            	String [] arregloFecha = fields[2].split("/");
	            	String fecha = arregloFecha[2]+"-"+arregloFecha[1]+"-"+arregloFecha[0];
	            	Categoria categoria= new Categoria(Integer.valueOf(fields[0]), convertirBoolean(fields[1]), fecha, fields[3], fields[4], fields[5]);
		            categorias.add(categoria);
	            }
	            
	            line = br.readLine();
	         }
	         br.close();
	      } catch (Exception e) {
	    	  System.out.println("Error al leer el csv. "+ e.getMessage());
	      }
	}
	
	public static void insertarCategoria(Categoria categoria) {//función para insertar categorías en la BBDD
		Connection conec = c.conexionBBDD();
		if (conec != null) {
			try {
				//codigo de mysql para insertar datos en la tabla categoría
				String insert = "INSERT INTO categoria (id_categoria, activo, fecha_alta, nombre, descripcion, url) "
						+ "VALUES("+ categoria.getId_categoria() +", "+ categoria.isActivo() +", '"+ categoria.getFecha_alta()
						+"', '"+ categoria.getNombre() +"', '"+ categoria.getDescripcion()+"', '"+ categoria.getUrl()+"');";
				
				Statement ins = conec.createStatement();
				
				ins.executeUpdate(insert);
				System.out.println("\nDatos insertados correctamente");
				ins.close();
			} catch(SQLException e) {
				System.out.println("Se ha producido un error al insertar en la Base de datos.\n"+ e);
			} finally {
				c.cerrarConexion(conec);
			}
		}
	}
	
	public static boolean convertirBoolean(String str) {//función auxiliar para convertir los valores 0 a false y 1 a true. (nos ayuda a leer el archivo categorias.csv)
		if(str.equals("1")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void leerProductos(ArrayList<Producto> productos) {//función para leer el archivo productos.csv
		BufferedReader br = null;
		try {
	         br =new BufferedReader(new FileReader("files/productos.csv"));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            if(fields[0].matches("[\\d]*")){
	            	//a continuación le damos la vuelta a la fecha para ponerla en el formato correcto (YYYY-MM-DD)
	            	String [] arregloFecha = fields[9].split("/");
	            	String fecha = arregloFecha[2]+"-"+arregloFecha[1]+"-"+arregloFecha[0];
	            	
	            	Producto producto= new Producto(Integer.valueOf(fields[0]), Integer.valueOf(fields[1]), Integer.valueOf(fields[2]), fields[3],
	            			Integer.valueOf(fields[4]), Float.valueOf(fields[5]), Float.valueOf(fields[6]), fields[7], convertirBoolean(fields[8]),
	            			fecha, fields[10], fields[11], fields[12], fields[13]);
		            productos.add(producto);
	            }
	            
	            line = br.readLine();
	         }
	         br.close();
	      } catch (Exception e) {
	    	  System.out.println("Error al leer el csv. "+ e.getMessage());
	      }
	}
	
	public static void insertarProducto(Producto producto) {//función para insertar productos en la BBDD
		Connection conec = c.conexionBBDD();
		if (conec != null) {
			try {
				//codigo de mysql para insertar datos en la tabla producto
				String insert = "INSERT INTO producto (id_producto, id_categoria_default, id_impuesto, ean13, cantidad, precio, precio_fabrica, referencia, activo, fecha_alta, nombre, resumen, descripcion, url) "
						+ "VALUES("+ producto.getId_product() +", "+ producto.getId_categoria_default() +", "+ producto.getId_impuesto() +", '"+ producto.getEan13() +"', "+ producto.getCantidad() +", "
						+ producto.getPrecio() +", "+ producto.getPrecio_fabrica() +", '"+ producto.getReferencia() +"', "+ producto.isActivo() +", '"+ producto.getFecha_alta() +"', '"+ producto.getNombre()
						+"', '"+ producto.getResumen() +"', '"+ producto.getDescripcion() +"', '"+ producto.getUrl()+"');";
				
				Statement ins = conec.createStatement();
				
				ins.executeUpdate(insert);
				System.out.println("\nDatos insertados correctamente");
				ins.close();
			} catch(SQLException e) {
				System.out.println("Se ha producido un error al insertar en la Base de datos.\n"+ e);
			} finally {
				c.cerrarConexion(conec);
			}
		}
	}
	
	public static Categoria crearCategoria() {//función para crear un objeto de tipo categoria
		int id=0;
		boolean activo=false;
		boolean comprobanteActivo=false;
		boolean comprobanteID=false;
		ArrayList<Integer> ids = consultarID_Categoria();
		Scanner leer= new Scanner(System.in);
		
		System.out.println("IDs restringidos: "+ ids.toString().replaceAll("[\\[\\]]",""));//mostramos por pantalla los ids ya existentes
		//pedimos los datos necesarios para crear un objeto de tipo categoria
		System.out.print("\nId_categoria: ");
		while(!comprobanteID) {//Comprobante para que los usuarios solo puedan introducir un valor numérico
			try {
				id=leer.nextInt();
				while(ids.contains(id)) {//comprobamos que el ID introducido no sea uno ya existente dado que estos son únicos
					System.out.println("Id_categoria introducido no válido. Por favor introduzca otro.");
					id=leer.nextInt();
				}
				comprobanteID=true;
			}catch(Exception e) {
				System.out.println("Introduzca un valor numérico.");
				leer.next();
			}
		}
		
		System.out.print("\nActivo: ");
		
		while(!comprobanteActivo) {//Comprobante para que los usuarios solo puedan introducir un valor booleano
			try {
				activo=leer.nextBoolean();
				comprobanteActivo=true;
			}catch(Exception e) {
				System.out.println("Introduzca una opción válida (true o false).");
				leer.next();
			}
		}
		
		System.out.print("\nFecha de alta (YYYY-MM-DD): ");
		String fecha_alta=leer.next();
		while(!fecha_alta.matches("^[\\d]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {//Comprobante para  que los usuarios solo puedan introducir una fecha en el formato YYYY-MM-DD
			System.out.println("Fecha introducida incorrecta. Por favor introduzca una fecha correcta");
			fecha_alta=leer.next();
		}
		System.out.print("\nNombre: ");
		String nombre=leer.nextLine();
		nombre=leer.nextLine();
		System.out.print("\nDescripcion: ");
		String descripcion=leer.nextLine();
		System.out.print("\nUrl: ");
		String url=leer.next();
		
		Categoria categoria = new Categoria(id, activo, fecha_alta, nombre, descripcion, url);//creamos un objeto categoria con los datos que hemos pedido anteriormente
		return categoria;
	}
	
	public static ArrayList<Integer> consultarID_Categoria() {//función para consultar los ids existentes en la base de datos
		Connection conec = c.conexionBBDD();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if (conec != null) {
			try {
				//codigo de mysql para consultar los ids de la tabla categoria
				String consulta = "SELECT id_categoria FROM categoria;";
				
				Statement cons = conec.createStatement();
				
				if(cons.execute(consulta)) {
					ResultSet resultado = cons.getResultSet();
					while(resultado.next()) {
						ids.add(resultado.getInt(1));
					}
				}
			} catch(SQLException e) {
				System.err.println(e);
			} finally {
				c.cerrarConexion(conec);
			}
		}
		return ids;
	}
	
	public static void editarProducto() {//función para editar un producto en una base de datos (función principal)
		Scanner sc = new Scanner(System.in);
		
		int id = 0;
		boolean comprobanteID=false;
		ArrayList<Producto> productos = consultarProducto();//guardamos los productos de la base de datos en una lista para trabajar más comodamente con ellos
		ArrayList<Integer> ids = new ArrayList<Integer>();//
		
		for(Producto x: productos) {//guardamos los ids de los productos en otra lista para poder consultar más adelante el id que elegiremos del producto que editaremos
			ids.add(x.getId_product());
		}
		
		System.out.println("\nIntroduce el ID del producto que quieras editar");
		while(!comprobanteID) {//Comprobante para que los usuarios solo puedan introducir un valor numérico
			try {
				id=sc.nextInt();
				//arreglar el while de abajo
				while(!ids.contains(id)) {//comprobamos que el ID introducido sea uno ya existente.
					System.out.println("Id_categoria introducido no válido. Por favor introduzca otro.");
					id=sc.nextInt();
				}
				comprobanteID=true;//cuando el id introducido sea correcto salimos del bucle
			}catch(Exception e) {
				System.out.println("Introduzca un valor numérico.");
				sc.next();
			}
		}
		
		actualizarProducto(productos.get(id-1));
	}
	
	public static ArrayList<Producto> consultarProducto() {//función para consultar los productos existentes en la base de datos
		Connection conec = c.conexionBBDD();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		if (conec != null) {
			try {
				//codigo de mysql para consultar los datos de la tabla producto
				String consulta = "SELECT * FROM producto;";
				
				Statement cons = conec.createStatement();
				
				if(cons.execute(consulta)) {
					ResultSet res = cons.getResultSet();
					while(res.next()) {
						System.out.println("\nID_Producto: "+ res.getInt(1) + "\nNombre: "+ res.getString(11));
						//creamos un objeto producto con los datos de la BBDD
						Producto producto= new Producto(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getInt(5), res.getFloat(6),
								res.getFloat(7), res.getString(8), res.getBoolean(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13), res.getString(14));
						productos.add(producto);
					}
				}
			} catch(SQLException e) {
				System.err.println(e);
			} finally {
				c.cerrarConexion(conec);
			}
		}
		return productos;
	}
	
	public static void actualizarProducto(Producto producto) {//función para pedir actualizar(setear) los datos de un objeto producto
		Scanner sc = new Scanner(System.in);
		
		//pedimos los datos que vamos a actualizar del producto
		System.out.println("Introduce el nuevo valor para: id_categoria["+ producto.getId_categoria_default()+"]");
		int categoria = sc.nextInt();
		System.out.println("Introduce el nuevo valor para: id_impuesto["+ producto.getId_impuesto()+"]");
		int impuesto = sc.nextInt();
		System.out.println("Introduce el nuevo valor para: ean13["+ producto.getEan13()+"]");
		String ean = sc.next();
		System.out.println("Introduce el nuevo valor para: cantidad["+ producto.getCantidad()+"]");
		int cantidad = sc.nextInt();
		System.out.println("Introduce el nuevo valor para: precio["+ producto.getPrecio()+"]");
		float precio = sc.nextFloat();
		System.out.println("Introduce el nuevo valor para: precio_fabrica["+ producto.getPrecio_fabrica()+"]");
		float preciofab = sc.nextFloat();
		System.out.println("Introduce el nuevo valor para: referencia["+ producto.getReferencia()+"]");
		String ref = sc.nextLine();
		ref = sc.nextLine();
		System.out.println("Introduce el nuevo valor para: activo["+ producto.isActivo()+"]");
		boolean activo = sc.nextBoolean();
		System.out.println("Introduce el nuevo valor para: fecha_alta["+ producto.getFecha_alta()+"]");
		String fecha = sc.next();
		System.out.println("Introduce el nuevo valor para: nombre["+ producto.getNombre()+"]");
		String nombre = sc.nextLine();
		nombre = sc.nextLine();
		System.out.println("Introduce el nuevo valor para: resumen["+ producto.getResumen()+"]");
		String resumen = sc.nextLine();
		System.out.println("Introduce el nuevo valor para: descripcion["+ producto.getDescripcion()+"]");
		String desc = sc.nextLine();
		System.out.println("Introduce el nuevo valor para: url["+ producto.getUrl()+"]");
		String url = sc.next();
		
		//seteamos los datos
		producto.setId_categoria_default(categoria);
		producto.setId_impuesto(impuesto);
		producto.setEan13(ean);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setPrecio_fabrica(preciofab);
		producto.setReferencia(ref);
		producto.setActivo(activo);
		producto.setFecha_alta(fecha);
		producto.setNombre(nombre);
		producto.setResumen(resumen);
		producto.setDescripcion(desc);
		producto.setUrl(url);
		
		updateProducto(producto);
	}
	
	public static void updateProducto(Producto producto) {//funcion para actualizar los datos de un producto en una BBDD
		Connection conec = c.conexionBBDD();
		if (conec != null) {
			try {
				//codigo de mysql para actualizar el producto
				String update = "UPDATE producto SET id_categoria_default="+ producto.getId_categoria_default() +", id_impuesto="+ producto.getId_impuesto() +", ean13='"+ producto.getEan13() +"', cantidad="+ producto.getCantidad() +", precio="
						+ producto.getPrecio() +", precio_fabrica="+ producto.getPrecio_fabrica() +", referencia='"+ producto.getReferencia() +"', activo="+ producto.isActivo() +", fecha_alta='"+ producto.getFecha_alta() +"', nombre='"+ producto.getNombre()
						+"', resumen='"+ producto.getResumen() +"', descripcion='"+ producto.getDescripcion() +"', url='"+ producto.getUrl()+"' WHERE id_producto="+  producto.getId_product()+";";
				
				Statement upd = conec.createStatement();
				
				upd.executeUpdate(update);
				System.out.println("\nDatos actualizados correctamente");
				upd.close();
			} catch(SQLException e) {
				System.out.println("Se ha producido un error al actualizar el producto en la Base de datos.\n"+ e);
			} finally {
				c.cerrarConexion(conec);
			}
		}
	}
}