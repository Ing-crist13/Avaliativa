    
import conexao.ConexaoEstoque;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2830482221001
 */
public class ProdutoDAO {
     private ConexaoEstoque conexaoEstoque;
    private Connection conn;
    
    public ProdutoDAO(){
        this.conexaoEstoque =  new ConexaoEstoque();
        this.conn = this.conexaoEstoque.getConexaoEstoque();
    }
    
    public void inserir(Produtos produtos){
        String sql = "INSERT INTO tb_produto(prod_nome, prod_preco, prod_cod, id) VALUES (?,?,?,?);";
        
        try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, produtos.getNome());
                stmt.setDouble(2, produtos.getPreco());
                stmt.setInt(3, produtos.getCodBarras());
                stmt.setInt(4, produtos.getCategoria());
                
                stmt.execute();
        }catch(SQLException ex){
                       System.out.println("Erro ao inserir dados:"+ex.getMessage());
                        }
            
    }
    
    public void editar(Produtos produtos){
         try{
             String sql = "UPDATE tb_produto set prod_nome=?, prod_preco=?, prod_cod=?, id=? WHERE prod_id=?";
             
             PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setString(1, produtos.getNome());
                stmt.setDouble(2, produtos.getPreco());
                stmt.setInt(3, produtos.getCodBarras());
                stmt.setInt(4, produtos.getCategoria());
                stmt.setInt(5, produtos.getId());
                
             stmt.execute();
         }catch(SQLException ex){
             System.out.println("Erro ao atualizar produto: "+ex.getMessage());
         }
     }
    
    public void excluir(int id){
      try{
          String sql = "delete from tb_produto WHERE prod_id=?";
          
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          stmt.execute();
          
          
      } catch(SQLException ex){
          System.out.println("Erro ao excluir produto: "+ex.getMessage());
      } 
     }
    public Produtos getProdutos(int id) 
    {
        String sql = "SELECT * FROM tb_produto WHERE prod_id = ?";
        
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            


            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
            
            Produtos p = new Produtos();
            // Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição da consulta
            // Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            
            p.setId(id);
            p.setNome(rs.getString("prod_nome"));
            p.setPreco(rs.getDouble("prod_preco"));
            p.setCategoria(rs.getInt("id"));
            p.setCodBarras(rs.getInt("prod_cod"));
            
            
            return p;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }
    
}
