package org.example.lab09.Daos;

import org.example.lab09.DTOs.ProductoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDao extends BaseDao{
    public ArrayList<ProductoDTO> listarParaUsuario(int idUsuario) {
        ArrayList<ProductoDTO> productos = new ArrayList<>();

        String sql = """
        SELECT  p.id_producto AS id,
                p.nombre,
                c.nombre AS categoriaNombre,
                p.precio,
                (p.stock - IFNULL(ci.sum_cant, 0)) AS stock
        FROM producto p
        JOIN categoria c ON p.id_categoria = c.id_categoria
        LEFT JOIN (
            SELECT id_producto, SUM(cantidad) AS sum_cant
            FROM carrito_item
            WHERE id_usuario = ?
            GROUP BY id_producto
        ) ci ON ci.id_producto = p.id_producto
        ORDER BY p.id_producto
    """;

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);  // 🔥 faltaba esto

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ProductoDTO producto = new ProductoDTO();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setCategoriaNombre(rs.getString("categoriaNombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));
                    productos.add(producto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void crear(int idCategoria, String nombre,String descripcion, double precio, int stock){
        String sql = "INSERT INTO producto (id_categoria, nombre, descripcion, precio, stock) VALUES (?,?,?,?,?)";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setInt(1,idCategoria);
            pstmt.setString(2,nombre);
            pstmt.setString(3,descripcion);
            pstmt.setDouble(4,precio);
            pstmt.setInt(5,stock);

            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
