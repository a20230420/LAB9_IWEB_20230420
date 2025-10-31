package org.example.lab09.Daos;

import jakarta.servlet.http.HttpServlet;
import org.example.lab09.DTOs.CarritoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CarritoDao extends BaseDao {
    public void addItem(int idUsuario, int idProducto){
        String sql = "INSERT INTO carrito_item (id_usuario, id_producto, cantidad) VALUES (?, ?, 1)";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<CarritoDTO> listarPorUsuario(int idUsuario) {
        ArrayList<CarritoDTO> items = new ArrayList<>();

        String sql = """
            SELECT ci.id_item,
                   p.id_producto,
                   p.nombre AS nombreProducto,
                   CONCAT(u.nombres,' ',u.apellidos) AS nombreUsuario,
                   p.precio AS precioUnit,
                   ci.cantidad,
                   (p.precio * ci.cantidad) AS subtotal
            FROM carrito_item ci
            JOIN producto p ON p.id_producto = ci.id_producto
            JOIN usuario  u ON u.id_usuario  = ci.id_usuario
            WHERE ci.id_usuario = ?
            ORDER BY ci.id_item
        """;

        try (Connection conn = this.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CarritoDTO d = new CarritoDTO();
                    d.setIdItem(rs.getInt("id_item"));
                    d.setIdProducto(rs.getInt("id_producto"));
                    d.setNombreProducto(rs.getString("nombreProducto"));
                    d.setNombreUsuario(rs.getString("nombreUsuario"));
                    d.setPrecioUnit(rs.getDouble("precioUnit"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setSubtotal(rs.getDouble("subtotal"));
                    items.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}
