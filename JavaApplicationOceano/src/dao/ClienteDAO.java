
package dao;

import beans.Cliente;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;
    private int id_especie;
    
    public ClienteDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Cliente cliente){
        String sql = "INSERT INTO cliente (nome, email) VALUES (?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2,cliente.getEmail());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao inserir cliente: "+ e.getMessage());
        }
    }
    
    public void alterar(Cliente cliente){
        String sql = "UPDATE cliente SET nome=?, email=? WHERE id_cliente=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2,cliente.getEmail());
            stmt.setInt(3,cliente.getId_cliente());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao atualizar cliente: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao excluir cliente: "+ e.getMessage());
        }
    }
    
    public Cliente getCliente(int id){
        String sql = "SELECT * FROM cliente WHERE id_cliente =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            rs.next();
            cliente.setId_cliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            return cliente;
        
        }catch(SQLException e){
            System.out.println("Erro ao atualizar cliente: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Cliente> getCliente(){
        String sql = "SELECT * FROM cliente";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Cliente> listaCliente = new ArrayList<>();
          while(rs.next()){
              Cliente p = new Cliente();
              p.setId_cliente(rs.getInt("id_cliente"));
              p.setNome(rs.getString("nome"));
              p.setEmail(rs.getString("email"));
              listaCliente.add(p);
          }
          return listaCliente;
        }catch(SQLException e){
            return null;
        }
    }
}