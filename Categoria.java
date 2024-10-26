package almacenElGlobito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Categoria {
    private String id_categoria;
    private String nombre;

    // Constructor
    public Categoria(String id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para agregar una nueva categoría a la base de datos
    public void agregarCategoria() {
        String sql = "INSERT INTO categorias (id_categoria, nombre_categoria) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.id_categoria);
            pstmt.setString(2, this.nombre);
            pstmt.executeUpdate();
            System.out.println("Categoría agregada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar una categoría en la base de datos
    public void actualizarCategoria() {
        String sql = "UPDATE categorias SET nombre_categoria = ? WHERE id_categoria = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.id_categoria);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Categoría actualizada con éxito.");
            } else {
                System.out.println("No se encontró la categoría con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar una categoría por ID
    public static Categoria consultarCategoria(String id_categoria) {
        String sql = "SELECT * FROM categorias WHERE id_categoria = ?";
        Categoria categoria = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id_categoria);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                categoria = new Categoria(id_categoria, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return categoria;
    }

	public static boolean existeCategoria(String idCategoria) {
		// TODO Auto-generated method stub
		return false;
	}
}
