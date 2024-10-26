package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Factura {
    private String id_fact;
    private String id_orden;
    private String fecha; // Puede ser DATE o un formato de String

    // Constructor
    public Factura(String id_fact, String id_orden, String fecha) {
        this.id_fact = id_fact;
        this.id_orden = id_orden;
        this.fecha = fecha;
    }

    // Getters y Setters
    public String getIdFact() {
        return id_fact;
    }

    public void setIdFact(String id_fact) {
        this.id_fact = id_fact;
    }

    public String getIdOrden() {
        return id_orden;
    }

    public void setIdOrden(String id_orden) {
        this.id_orden = id_orden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Método para agregar una nueva factura a la base de datos
    public void agregarFactura() {
        String sql = "INSERT INTO facturas (id_fact, id_orden, fecha) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_fact);
            pstmt.setString(2, this.id_orden);
            pstmt.setString(3, this.fecha);
            pstmt.executeUpdate();
            System.out.println("Factura agregada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar una factura en la base de datos
    public void actualizarFactura() {
        String sql = "UPDATE facturas SET id_orden = ?, fecha = ? WHERE id_fact = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_orden);
            pstmt.setString(2, this.fecha);
            pstmt.setString(3, this.id_fact);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Factura actualizada con éxito.");
            } else {
                System.out.println("No se encontró la factura con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar una factura por ID
    public static Factura consultarFactura(String id_fact) {
        String sql = "SELECT * FROM facturas WHERE id_fact = ?";
        Factura factura = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_fact);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String id_orden = rs.getString("id_orden");
                String fecha = rs.getString("fecha");
                factura = new Factura(id_fact, id_orden, fecha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return factura;
    }
    
	public void generarFactura() {
		// TODO Auto-generated method stub
		
	}
}

