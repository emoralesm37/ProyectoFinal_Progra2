package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
    private String id_cliente;
    private String nombre;
    private String direccion;

    // Constructor
    public Cliente(String id_cliente, String nombre, String direccion) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters
    public String getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método para agregar un nuevo cliente a la base de datos
    public void agregarCliente() {
        String sql = "INSERT INTO clientes (id_cliente, nombre_cliente, direccion) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_cliente);
            pstmt.setString(2, this.nombre);
            pstmt.setString(3, this.direccion);
            pstmt.executeUpdate();
            System.out.println("Cliente agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un cliente en la base de datos
    public void actualizarCliente() {
        String sql = "UPDATE clientes SET nombre_cliente = ?, direccion = ? WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.direccion);
            pstmt.setString(3, this.id_cliente);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente actualizado con éxito.");
            } else {
                System.out.println("No se encontró el cliente con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar un cliente por ID
    public static Cliente consultarCliente(String id_cliente) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        Cliente cliente = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_cliente);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                cliente = new Cliente(id_cliente, nombre, direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cliente;
    }
}

