package modelosDAO;

import Modelos.Categoria;
import db.cn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private cn CN;
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public CategoriaDAO() throws SQLException {
        try {
            CN = new cn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria> consultarCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoriaspeliculas"; 
        try {
            con = CN.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
               
                lista.add(categoria);
            }
            System.out.println(lista);
        } catch (SQLException e) {
            System.out.println("error"+ e.getMessage());
        } 
        return lista;
    }

    public boolean agregarCategoria(Categoria categoria) {
       this.sql = "INSERT INTO categoriaspeliculas (nombre_categoria) VALUES (?)";
        try {
            ps = CN.getConnection().prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("no agrega");
        }
        return false;
    }
    
 public boolean eliminarCategoria(int idCategoria) {
  String sql = "DELETE FROM categoriaspeliculas WHERE idCategoria = ?";

        try {
            con = CN.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } 


        return false;
    }
 
 
 public boolean editarCategoria(Categoria categoria) {
   String sql = "UPDATE categoriaspeliculas SET nombre_categoria = ? WHERE idCategoria = ?";
   try {
        Connection con = CN.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, categoria.getNombreCategoria());
        ps.setInt(2, categoria.getIdCategoria());

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            return true;
        }
        
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } 

    return false;
}

    
  public Categoria obtenerCategoriaPorId(int idProveedor) {
        Categoria categoria = null;
         String sql = "SELECT * FROM categoriaspeliculas WHERE idCategoria = ?";

        try {
            con = CN.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);

            rs = ps.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return categoria;
    }
}
