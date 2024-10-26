package almacenElGlobito;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu Principal");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Agregar Vendedor");
            System.out.println("4. Agregar Proveedor");
            System.out.println("5. Actualizar Producto");
            System.out.println("6. Consultar Producto");
            System.out.println("7. Crear Pedido");
            System.out.println("8. Generar Factura");
            System.out.println("9. Realizar Venta");
            System.out.println("10. Registrar Compra");
            System.out.println("11. Mostrar Inventario");
            System.out.println("12. Agregar Categoría");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarProducto(scanner);
                    break;
                case 2:
                    agregarCliente(scanner);
                    break;
                case 3:
                    agregarVendedor(scanner);
                    break;
                case 4:
                    agregarProveedor(scanner);
                    break;
                case 5:
                    actualizarProducto(scanner);
                    break;
                case 6:
                    consultarProducto(scanner);
                    break;
                case 7:
                    crearPedido(scanner);
                    break;
                case 8:
                    generarFactura(scanner);
                    break;
                case 9:
                    realizarVenta(scanner);
                    break;
                case 10:
                    manejarCompras(scanner);
                    break;
                case 11:
                    manejarInventario(scanner);
                    break;
                case 12:
                    agregarCategoria(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    // Métodos para cada opción

    private static void agregarProducto(Scanner scanner) {
        System.out.print("ID Producto: ");
        String id = scanner.nextLine();
        System.out.print("Nombre Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("ID Categoría: ");
        String idCategoria = scanner.nextLine();
        
        // Verificar si la categoría existe
      /*  if (!Categoria.existeCategoria(idCategoria)) {
            System.out.println("La categoría no existe. Por favor, añádela primero.");
            return;
        }*/
        
        System.out.print("Estado (DISPONIBLE, EN_FABRICACION, EN_TRANSPORTE): ");
        String estado = scanner.nextLine();

        Producto producto = new Producto(id, nombre, precio, idCategoria, estado);
        producto.agregarProducto();
    }

    private static void agregarCliente(Scanner scanner) {
        System.out.print("ID Cliente: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Cliente cliente = new Cliente(id, nombre, direccion);
        cliente.agregarCliente();
    }

    private static void agregarVendedor(Scanner scanner) {
        System.out.print("ID Vendedor: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Vendedor vendedor = new Vendedor(id, nombre, telefono);
        vendedor.agregarVendedor();
    }

    private static void agregarProveedor(Scanner scanner) {
        System.out.print("ID Proveedor: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        Proveedor proveedor = new Proveedor(id, nombre);
        proveedor.agregarProveedor();
    }

    private static void actualizarProducto(Scanner scanner) {
        System.out.print("ID Producto a actualizar: ");
        String id = scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nueva ID Categoría: ");
        String idCategoria = scanner.nextLine();
        System.out.print("Nuevo Estado: ");
        String estado = scanner.nextLine();

        Producto producto = new Producto(id, nombre, precio, idCategoria, estado);
        producto.actualizarProducto();
    }

    private static void consultarProducto(Scanner scanner) {
        System.out.print("ID Producto a consultar: ");
        String id = scanner.nextLine();
        Producto producto = Producto.consultarProducto(id);
        if (producto != null) {
            System.out.println("Producto encontrado: " + producto.getNombre());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void crearPedido(Scanner scanner) {
        System.out.print("ID Orden: ");
        String idOrden = scanner.nextLine();
        System.out.print("ID Cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("Total: ");
        double total = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        Pedido pedido = new Pedido(idOrden, idCliente, total);
        pedido.agregarPedido();
    }

    private static void generarFactura(Scanner scanner) {
        System.out.print("ID Factura: ");
        String idFact = scanner.nextLine();
        System.out.print("ID Orden: ");
        String idOrden = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        Factura factura = new Factura(idFact, idOrden, fecha);
        factura.agregarFactura();
    }

    private static void realizarVenta(Scanner scanner) {
        System.out.print("ID Venta: ");
        String idVenta = scanner.nextLine();
        System.out.print("ID Orden: ");
        String idOrden = scanner.nextLine();
        System.out.print("ID Factura: ");
        String idFact = scanner.nextLine();
        System.out.print("ID Vendedor: ");
        String idVendedor = scanner.nextLine();

        Venta venta = new Venta(idVenta, idOrden, idFact, idVendedor);
        venta.agregarVenta();
    } 
    
    

    
    

    private static void manejarCompras(Scanner scanner) {
        System.out.println("Ingrese los detalles de la compra:");
        
        System.out.print("ID Compra: ");
        String idCompra = scanner.nextLine();
        
        System.out.print("ID Proveedor: ");
        String idProveedor = scanner.nextLine();
        
        System.out.print("ID Producto: ");
        String idProducto = scanner.nextLine();
        
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        
        Compra nuevaCompra = new Compra(idCompra, idProveedor, idProducto, cantidad);
        nuevaCompra.registrarCompra();
    }

    
   /* private static void registrarCompra(Scanner scanner) {
        System.out.print("ID Compra: ");
        String idCompra = scanner.nextLine();
        System.out.print("ID Proveedor: ");
        String idProveedor = scanner.nextLine();

        Compra compra = new Compra(idCompra, idProveedor);
        compra.agregarCompra();  
    }*/

   /* private static void mostrarInventario() {
        // Lógica para mostrar el inventario
        System.out.println("Inventario mostrado.");
    } */

    private static void manejarInventario(Scanner scanner) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Actualizar Producto");
        System.out.println("3. Mostrar Inventario");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                // Agregar Producto
                System.out.print("ID Producto: ");
                String idProducto = scanner.nextLine();
                System.out.print("Nombre Producto: ");
                String nombreProducto = scanner.nextLine();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                Inventario nuevoProducto = new Inventario(idProducto, nombreProducto, cantidad,null);
                nuevoProducto.agregarInventario();
                break;

            case 2:
                // Actualizar Producto
                System.out.print("ID Producto: ");
                idProducto = scanner.nextLine();
                System.out.print("Nueva Cantidad: ");
                cantidad = scanner.nextInt();
                Inventario productoActualizar = new Inventario(idProducto, null, cantidad,null);
                productoActualizar.actualizarInventario();
                break;

            case 3:
                // Mostrar Inventario
                Inventario.mostrarInventario();
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    
    
    private static void agregarCategoria(Scanner scanner) {
        System.out.print("ID Categoría: ");
        String idCategoria = scanner.nextLine();
        System.out.print("Nombre Categoría: ");
        String nombreCategoria = scanner.nextLine();

        Categoria categoria = new Categoria(idCategoria, nombreCategoria);
        categoria.agregarCategoria();
    }
}
