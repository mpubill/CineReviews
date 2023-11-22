package modelosDAO;

import Modelos.Usuario;
import db.cn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private cn CN;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public UsuarioDAO() throws SQLException {
        try {
            CN = new cn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> consultaGeneral() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario, correo_electronico, contrasena, imagen_perfil, nombre, apellido, fecha_nacimiento, pais, rol FROM usuarios";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setImagenPerfil(rs.getString("imagen_perfil"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setPais(rs.getString("pais"));
                usuario.setRol(rs.getString("rol"));

                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregarUsuario(Usuario usuario) {
        this.sql = "INSERT INTO usuarios (nombre_usuario, correo_electronico, contrasena, imagen_perfil, nombre, apellido, fecha_nacimiento, pais, rol) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = CN.getConnection().prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getCorreoElectronico());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getImagenPerfil());
            ps.setString(5, usuario.getNombre());
            ps.setString(6, usuario.getApellido());
            ps.setString(7, usuario.getFechaNacimiento());
            ps.setString(8, usuario.getPais());
            ps.setString(9, usuario.getRol());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
        this.sql = "UPDATE usuarios SET nombre_usuario=?, correo_electronico=?, nombre=?, apellido=?, fecha_nacimiento=?, pais=?, rol=? WHERE id_usuario=?";

        try {
            ps = CN.getConnection().prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getCorreoElectronico());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getFechaNacimiento());
            ps.setString(6, usuario.getPais());
            ps.setString(7, usuario.getRol());
            ps.setInt(8, usuario.getIdUsuario());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        this.sql = "SELECT * FROM usuarios WHERE id_usuario=?";

        try {
            ps = CN.getConnection().prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setPais(rs.getString("pais"));
                usuario.setRol(rs.getString("rol"));
                // Añadir otros campos según la estructura de tu base de datos

                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Usuario iniciarSesion(String correo, String contrasena) {
        this.sql = "SELECT * FROM usuarios WHERE correo_electronico = ? AND contrasena = ?";

        try {
            ps = CN.getConnection().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setImagenPerfil(rs.getString("imagen_perfil"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setPais(rs.getString("pais"));
                usuario.setRol(rs.getString("rol"));

                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
