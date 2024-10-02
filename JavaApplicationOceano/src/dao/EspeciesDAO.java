package dao;

import beans.Especies;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspeciesDAO {
    private final Conexao conexao;
    private Connection conn;
    
    public EspeciesDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Especies especies){
        String sql = "INSERT INTO especies (peso, nome_comum) VALUES (?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, especies.getPeso());
            stmt.setString(2,especies.getNome_comum());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao inserir especies: "+ e.getMessage());
        }
    }
    
    public void alterar(Especies especies){
        String sql = "UPDATE especies SET peso=?, nome_comum=? id_especie=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, especies.getPeso());
            stmt.setString(2,especies.getNome_comum());
            stmt.setInt(3,especies.getId_especie());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao atualizar especies: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM especies WHERE id_especie = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            int id_especie = 0;
            stmt.setInt(1,id_especie);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao excluir especies: "+ e.getMessage());
        }
    }
    
    public Especies getEspecies(int id){
        String sql = "SELECT * FROM especies WHERE id_especie =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Especies especies = new Especies();
            rs.next();
            especies.setId_especie(rs.getInt("id_especie"));
            especies.setPeso(rs.getInt("peso"));
            especies.setNome_comum(rs.getString("nome_comum"));
            return especies;
        
        }catch(SQLException e){
            System.out.println("Erro ao atualizar especies: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Especies> getEspecies(){
        String sql = "SELECT * FROM especies";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Especies> listaEspecies = new ArrayList<>();
          while(rs.next()){
              Especies p = new Especies();
              p.setId_especie(rs.getInt("id_especie"));
              p.setPeso(rs.getInt("peso"));
              p.setNome_comum(rs.getString("nome_comum"));
              listaEspecies.add(p);
          }
          return listaEspecies;
        }catch(SQLException e){
            return null;
        }
    }
}