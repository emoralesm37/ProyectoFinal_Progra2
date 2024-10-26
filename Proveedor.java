package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proveedor {
    private String id_proveedor;
    private String nombre;

    // Constructor
    public Proveedor(String id_proveedor, String nombre) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getIdProveedor() {
        return id_proveedor;
    }

    public void setIdProveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para agregar un nuevo proveedor a la base de datos
    public void agregarProveedor() {
        String sql = "INSERT INTO proveedores (id_proveedor, nombre_proveedor) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_proveedor);
            pstmt.setString(2, this.nombre);
            pstmt.executeUpdate();
            System.out.println("Proveedor agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un proveedor en la base de datos
    public void actualizarProveedor() {
        String sql = "UPDATE proveedores SET nombre_proveedor = ? WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.id_proveedor);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor actualizado con éxito.");
            } else {
                System.out.println("No se encontró el proveedor con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar un proveedor por ID
    public static Proveedor consultarProveedor(String id_proveedor) {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        Proveedor proveedor = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_proveedor);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                proveedor = new Proveedor(id_proveedor, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return proveedor;
    }
}