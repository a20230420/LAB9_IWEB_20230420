package org.example.lab09.Daos;

import org.example.lab09.Beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao extends BaseDao{
    public Usuario validarUsuarioPassword(String email, String password) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE email = ? AND password_hash = SHA2(?,256) AND estado = 'ACTIVO'";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, email);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery();) {
                if(rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombres(rs.getString("nombres"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setEstado(rs.getString("estado"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
