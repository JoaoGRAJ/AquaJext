package dao;

import beans.Pagamento;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    private final Conexao conexao;
    private final Connection conn;
    private int valor;
    private int id_pagamento;
    private int id_cliente;
    
    public PagamentoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Pagamento pagamento){
        String sql = "INSERT INTO pagamento (id_pagamento, id_cliente, valor) VALUES (?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, pagamento.getId_pagamento());
            stmt.setInt(2,pagamento.getId_cliente());
            stmt.setInt(3,pagamento.getValor());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao inserir pagamento: "+ e.getMessage());
        }
    }
    
    public void alterar(Pagamento pagamento){
        String sql = "UPDATE pagamento SET id_pagamento=?, id_cliente=?, valor=? WHERE id_pagamento=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, pagamento.getId_pagamento());
            stmt.setInt(2,pagamento.getId_cliente());
            stmt.setInt(3,pagamento.getValor());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao atualizar pagamento: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM pagamento WHERE id_pagamento = ?, id_cliente = ?, valor = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id_pagamento);
            stmt.setInt(2,id_cliente);
            stmt.setInt(3,valor);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao excluir pagamento: "+ e.getMessage());
        }
    }
    
    public Pagamento getPagamento(int id){
        String sql = "SELECT * FROM pagamento WHERE id_pagamento = ?, id_cliente = ?, valor = ?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pagamento pagamento = new Pagamento();
            rs.next();
            pagamento.setId_pagamento(rs.getInt("id_pagamento"));
            pagamento.setId_cliente(rs.getInt("id_cliente"));
            pagamento.setValor(rs.getInt("valor"));
            return pagamento;
        
        }catch(SQLException e){
            System.out.println("Erro ao atualizar pagamento: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Pagamento> getPagamento(){
        String sql = "SELECT * FROM pagamento";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Pagamento> listaPagamento = new ArrayList<>();
          while(rs.next()){
              Pagamento p = new Pagamento();
              p.setId_pagamento(rs.getInt("id_pagamento"));
              p.setId_cliente(rs.getInt("id_cliente"));
              p.setValor(rs.getInt("valor"));
              listaPagamento.add(p);
          }
          return listaPagamento;
        }catch(SQLException e){
            return null;
        }
    }
}
