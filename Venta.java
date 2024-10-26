package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Venta {
    private String id_venta;
    private String id_orden;
    private String id_fact;
    private String id_vendedor;

    // Constructor
    public Venta(String id_venta, String id_orden, String id_fact, String id_vendedor) {
        this.id_venta = id_venta;
        this.id_orden = id_orden;
        this.id_fact = id_fact;
        this.id_vendedor = id_vendedor;
    }

    // Getters y Setters
    public String getIdVenta() {
        return id_venta;
    }

    public void setIdVenta(String id_venta) {
        this.id_venta = id_venta;
    }

    public String getIdOrden() {
        return id_orden;
    }

    public void setIdOrden(String id_orden) {
        this.id_orden = id_orden;
    }

    public String getIdFact() {
        return id_fact;
    }

    public void setIdFact(String id_fact) {
        this.id_fact = id_fact;
    }

    public String getIdVendedor() {
        return id_vendedor;
    }

    public void setIdVendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    // Método para verificar si una factura existe
    public static boolean existeFactura(String idFact) {
        String sql = "SELECT COUNT(*) FROM facturas WHERE id_fact = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idFact);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si hubo un error o no existe
    }

    // Método para agregar una nueva venta a la base de datos
    public void agregarVenta() {
        // Verificar si la factura existe antes de agregar la venta
        if (!existeFactura(this.id_fact)) {
            System.out.println("No se puede agregar la venta. La factura con ID " + this.id_fact + " no existe.");
            return;
        }

        String sql = "INSERT INTO ventas (id_venta, id_orden, id_fact, id_vendedor) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.id_venta);
            pstmt.setString(2, this.id_orden);
            pstmt.setString(3, this.id_fact);
            pstmt.setString(4, this.id_vendedor);
            pstmt.executeUpdate();
            System.out.println("Venta agregada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar una venta en la base de datos
    public void actualizarVenta() {
        String sql = "UPDATE ventas SET id_orden = ?, id_fact = ?, id_vendedor = ? WHERE id_venta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.id_orden);
            pstmt.setString(2, this.id_fact);
            pstmt.setString(3, this.id_vendedor);
            pstmt.setString(4, this.id_venta);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Venta actualizada con éxito.");
            } else {
                System.out.println("No se encontró la venta con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar una venta por ID
    public static Venta consultarVenta(String id_venta) {
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        Venta venta = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id_venta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String id_orden = rs.getString("id_orden");
                String id_fact = rs.getString("id_fact");
                String id_vendedor = rs.getString("id_vendedor");
                venta = new Venta(id_venta, id_orden, id_fact, id_vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venta;
    }
}
