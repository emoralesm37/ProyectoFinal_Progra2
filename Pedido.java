package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pedido {
    private String id_orden;
    private String id_cliente;
    private double total;

    // Constructor
    public Pedido(String id_orden, String id_cliente, double total) {
        this.id_orden = id_orden;
        this.id_cliente = id_cliente;
        this.total = total;
    }

    // Getters y Setters
    public String getIdOrden() {
        return id_orden;
    }

    public void setIdOrden(String id_orden) {
        this.id_orden = id_orden;
    }

    public String getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Método para agregar un nuevo pedido a la base de datos
    public void agregarPedido() {
        String sql = "INSERT INTO pedidos (id_orden, id_cliente, total) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_orden);
            pstmt.setString(2, this.id_cliente);
            pstmt.setDouble(3, this.total);
            pstmt.executeUpdate();
            System.out.println("Pedido agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un pedido en la base de datos
    public void actualizarPedido() {
        String sql = "UPDATE pedidos SET id_cliente = ?, total = ? WHERE id_orden = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_cliente);
            pstmt.setDouble(2, this.total);
            pstmt.setString(3, this.id_orden);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido actualizado con éxito.");
            } else {
                System.out.println("No se encontró el pedido con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar un pedido por ID
    public static Pedido consultarPedido(String id_orden) {
        String sql = "SELECT * FROM pedidos WHERE id_orden = ?";
        Pedido pedido = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_orden);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String id_cliente = rs.getString("id_cliente");
                double total = rs.getDouble("total");
                pedido = new Pedido(id_orden, id_cliente, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pedido;
    }
}
