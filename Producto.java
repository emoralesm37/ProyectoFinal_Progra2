package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
    private String id_producto;
    private String nombre;
    private double precio;
    private String id_categoria;
    private String estado; // Puede ser un ENUM, aquí se utiliza String para simplificar

    // Constructor
    public Producto(String id_producto, String nombre, double precio, String id_categoria, String estado) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdProducto() {
        return id_producto;
    }

    public void setIdProducto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para agregar un nuevo producto a la base de datos
    public void agregarProducto() {
        String sql = "INSERT INTO productos (id_producto, nombre_producto, precio, id_categoria, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_producto);
            pstmt.setString(2, this.nombre);
            pstmt.setDouble(3, this.precio);
            pstmt.setString(4, this.id_categoria);
            pstmt.setString(5, this.estado);
            pstmt.executeUpdate();
            System.out.println("Producto agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un producto en la base de datos
    public void actualizarProducto() {
        String sql = "UPDATE productos SET nombre_producto = ?, precio = ?, id_categoria = ?, estado = ? WHERE id_producto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.nombre);
            pstmt.setDouble(2, this.precio);
            pstmt.setString(3, this.id_categoria);
            pstmt.setString(4, this.estado);
            pstmt.setString(5, this.id_producto);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto actualizado con éxito.");
            } else {
                System.out.println("No se encontró el producto con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar un producto por ID
    public static Producto consultarProducto(String id_producto) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        Producto producto = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_producto);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String id_categoria = rs.getString("id_categoria");
                String estado = rs.getString("estado");
                producto = new Producto(id_producto, nombre, precio, id_categoria, estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return producto;
    }
}
