package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Compra {
    private String id_compra;
    private String id_proveedor;
    private String id_producto;
    private int cantidad;

    // Constructor
    public Compra(String id_compra, String id_proveedor, String id_producto, int cantidad) {
        this.id_compra = id_compra;
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getIdCompra() {
        return id_compra;
    }

    public void setIdCompra(String id_compra) {
        this.id_compra = id_compra;
    }

    public String getIdProveedor() {
        return id_proveedor;
    }

    public void setIdProveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getIdProducto() {
        return id_producto;
    }

    public void setIdProducto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método para registrar una compra y actualizar el inventario
    public void registrarCompra() {
        String sqlCompra = "INSERT INTO compras (id_compra, id_proveedor) VALUES (?, ?)";
        String sqlInventario = "UPDATE inventario SET cantidad = cantidad + ? WHERE id_producto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtCompra = conn.prepareStatement(sqlCompra);
             PreparedStatement pstmtInventario = conn.prepareStatement(sqlInventario)) {
             
            // Registrar la compra
            pstmtCompra.setString(1, this.id_compra);
            pstmtCompra.setString(2, this.id_proveedor);
            pstmtCompra.executeUpdate();

            // Actualizar el inventario
            pstmtInventario.setInt(1, this.cantidad);
            pstmtInventario.setString(2, this.id_producto);
            pstmtInventario.executeUpdate();

            System.out.println("Compra registrada y inventario actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
