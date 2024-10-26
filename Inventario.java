package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private String id_producto;
    private String nombre_producto;
    private int cantidad;
    private String fecha_actualizacion;

    // Constructor
    public Inventario(String id_producto, String nombre_producto, int cantidad, String fecha_actualizacion) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    // Getters y Setters
    public String getIdProducto() {
        return id_producto;
    }

    public void setIdProducto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombreProducto() {
        return nombre_producto;
    }

    public void setNombreProducto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaActualizacion() {
        return fecha_actualizacion;
    }

    public void setFechaActualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    // Método para agregar un nuevo inventario a la base de datos
    public void agregarInventario() {
        String sql = "INSERT INTO inventario (id_producto, nombre_producto, cantidad, fecha_actualizacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_producto);
            pstmt.setString(2, this.nombre_producto);
            pstmt.setInt(3, this.cantidad);
            pstmt.setString(4, this.fecha_actualizacion);
            pstmt.executeUpdate();
            System.out.println("Inventario agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un inventario en la base de datos
    public void actualizarInventario() {
        String sql = "UPDATE inventario SET cantidad = ?, fecha_actualizacion = ? WHERE id_producto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, this.cantidad);
            pstmt.setString(2, this.fecha_actualizacion);
            pstmt.setString(3, this.id_producto);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inventario actualizado con éxito.");
            } else {
                System.out.println("No se encontró el producto con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Método para consultar un inventario por ID de producto
   /* public static Inventario consultarInventario(String id_producto) {
        String sql = "SELECT * FROM inventario WHERE id_producto = ?";
        Inventario inventario = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_producto);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre_producto = rs.getString("nombre_producto");
                int cantidad = rs.getInt("cantidad");
                String fecha_actualizacion = rs.getString("fecha_actualizacion");
                inventario = new Inventario(id_producto, nombre_producto, cantidad, fecha_actualizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return inventario;
    }*/
    // Método para consultar todo el inventario
    public static List<Inventario> consultarInventario() {
        List<Inventario> inventarioList = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id_producto = rs.getString("id_producto");
                String nombre_producto = rs.getString("nombre_producto");
                int cantidad = rs.getInt("cantidad");
              // String fecha_actualizacion = rs.getString("Fecha Actualizada");
                inventarioList.add(new Inventario(id_producto, nombre_producto, cantidad, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarioList;
    } 
    
    // Método para mostrar el inventario
    public static void mostrarInventario() {
        List<Inventario> inventarioList = consultarInventario();

        if (inventarioList.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario:");
            for (Inventario item : inventarioList) {
                System.out.println("ID Producto: " + item.getIdProducto() +
                                   ", Nombre: " + item.getNombreProducto() +
                                   ", Cantidad: " + item.getCantidad()+
                                   ", Fecha Actualización: " + item.getFechaActualizacion());
                
            }
        }
    }    
    
}
