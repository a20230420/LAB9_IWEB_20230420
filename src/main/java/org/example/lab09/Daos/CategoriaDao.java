package org.example.lab09.Daos;

import org.example.lab09.Beans.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDao extends BaseDao{
    public ArrayList<Categoria> listar(){
        ArrayList<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre, descripcion FROM categoria ORDER BY nombre";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return categorias;
    }
}
