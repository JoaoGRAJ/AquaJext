package dao;

import beans.Qualidade_vida;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Qualidade_vidaDAO {
    private final Conexao conexao;
    private final Connection conn;
    private int id_especie;
    private int nivel_poluicao;
    private int id_qualidade;
    
    public Qualidade_vidaDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Qualidade_vida qualidade_vida){
        String sql = "INSERT INTO qualidade_vida (id_qualidade, id_especie, nivel_poluicao) VALUES (?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, qualidade_vida.getId_qualidade());
            stmt.setInt(2,qualidade_vida.getId_especie());
            stmt.setInt(3,qualidade_vida.getNivel_poluicao());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao inserir qualidade_vida: "+ e.getMessage());
        }
    }
    
    public void alterar(Qualidade_vida qualidade_vida){
        String sql = "UPDATE qualidade_vida SET id_qualidade=?, id_especie=?, nivel_poluicao=? WHERE id_qualidade_vida=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, qualidade_vida.getId_qualidade());
            stmt.setInt(2,qualidade_vida.getId_especie());
            stmt.setInt(3,qualidade_vida.getNivel_poluicao());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao atualizar qualidade_vida: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM qualidade_vida WHERE id_qualidade = ?, id_especie = ?, nivel_poluicao = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id_qualidade);
            stmt.setInt(2,id_especie);
            stmt.setInt(3,nivel_poluicao);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao excluir qualidade_vida: "+ e.getMessage());
        }
    }
    
    public Qualidade_vida getQualidade_vida(int id){
        String sql = "SELECT * FROM qualidade_vida WHERE id_qualidade = ?, id_especie = ?, nivel_poluicao = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Qualidade_vida qualidade_vida = new Qualidade_vida();
            rs.next();
            qualidade_vida.setId_qualidade(rs.getInt("id_qualidade"));
            qualidade_vida.setId_especie(rs.getInt("id_especie"));
            qualidade_vida.setNivel_poluicao(rs.getInt("nivel_poluicao"));
            return qualidade_vida;
        
        }catch(SQLException e){
            System.out.println("Erro ao atualizar qualidade_vida: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Qualidade_vida> getQualidade_vida(){
        String sql = "SELECT * FROM qualidade_vida";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Qualidade_vida> listaQualidade_vida = new ArrayList<>();
          while(rs.next()){
              Qualidade_vida p = new Qualidade_vida();
              p.setId_qualidade(rs.getInt("id_qualidade"));
              p.setId_especie(rs.getInt("id_especie"));
              p.setNivel_poluicao(rs.getInt("nivel_poluicao"));
              listaQualidade_vida.add(p);
          }
          return listaQualidade_vida;
        }catch(SQLException e){
            return null;
        }
    }
}