
package dao;

import beans.Estado_especie;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Estado_especieDAO {
    private final Conexao conexao;
    private final Connection conn;
    private int id_especie;
    private int id_status;
    private String status;
    
    public Estado_especieDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Estado_especie estado_especie){
        String sql = "INSERT INTO estado_especie (id_status, id_especie, status) VALUES (?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, estado_especie.getId_status());
            stmt.setInt(2,estado_especie.getId_especie());
            stmt.setString(2,estado_especie.getStatus());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao inserir estado_especie: "+ e.getMessage());
        }
    }
    
    public void alterar(Estado_especie estado_especie){
        String sql = "UPDATE estado_especie SET id_status=?, id_especie=?, status=? WHERE id_estado_especie=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, estado_especie.getId_status());
            stmt.setInt(2,estado_especie.getId_especie());
            stmt.setString(3,estado_especie.getStatus());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao atualizar estado_especie: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM estado_especie WHERE id_status = ?, id_especie = ?, status = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id_status);
            stmt.setInt(2,id_especie);
            stmt.setString(3,status);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao excluir estado_especie: "+ e.getMessage());
        }
    }
    
    public Estado_especie getEstado_especie(int id){
        String sql = "SELECT * FROM estado_especie WHERE id_estado_especie =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Estado_especie estado_especie = new Estado_especie();
            rs.next();
            estado_especie.setId_status(rs.getInt("id_status"));
            estado_especie.setId_especie(rs.getInt("id_especie"));
            estado_especie.setStatus(rs.getString("status"));
            return estado_especie;
        
        }catch(SQLException e){
            System.out.println("Erro ao atualizar estado_especie: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Estado_especie> getEstado_especie(){
        String sql = "SELECT * FROM estado_especie";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Estado_especie> listaEstado_especie = new ArrayList<>();
          while(rs.next()){
              Estado_especie p = new Estado_especie();
              p.setId_status(rs.getInt("id_status"));
              p.setId_especie(rs.getInt("id_especie"));
              p.setStatus(rs.getString("status"));
              listaEstado_especie.add(p);
          }
          return listaEstado_especie;
        }catch(SQLException e){
            return null;
        }
    }
}