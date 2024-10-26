package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vendedor {
    private String id_vendedor;
    private String nombre;
    private String telefono;

    // Constructor
    public Vendedor(String id_vendedor, String nombre, String telefono) {
        this.id_vendedor = id_vendedor;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getIdVendedor() {
        return id_vendedor;
    }

    public void setIdVendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método para agregar un nuevo vendedor a la base de datos
    public void agregarVendedor() {
        String sql = "INSERT INTO vendedores (id_vendedor, nombre_vendedor, telefono) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_vendedor);
            pstmt.setString(2, this.nombre);
            pstmt.setString(3, this.telefono);
            pstmt.executeUpdate();
            System.out.println("Vendedor agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un vendedor en la base de datos
    public void actualizarVendedor() {
        String sql = "UPDATE vendedores SET nombre_vendedor = ?, telefono = ? WHERE id_vendedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.telefono);
            pstmt.setString(3, this.id_vendedor);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vendedor actualizado con éxito.");
            } else {
                System.out.println("No se encontró el vendedor con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar un vendedor por ID
    public static Vendedor consultarVendedor(String id_vendedor) {
        String sql = "SELECT * FROM vendedores WHERE id_vendedor = ?";
        Vendedor vendedor = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_vendedor);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                vendedor = new Vendedor(id_vendedor, nombre, telefono);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vendedor;
    }
}

