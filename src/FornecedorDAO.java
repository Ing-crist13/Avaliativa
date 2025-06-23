
import conexao.ConexaoEstoque;
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
public class FornecedorDAO {
     private ConexaoEstoque conexaoEstoque;
    private Connection conn;
    
    public FornecedorDAO(){
        this.conexaoEstoque =  new ConexaoEstoque();
        this.conn = this.conexaoEstoque.getConexaoEstoque();
    }
    
    public void inserir(Fornecedor fornecedor){
        String sql = "INSERT INTO tb_fornecedores(forn_nome, forn_fantasia, forn_cnpj) VALUES (?,?,?);";
        
        try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, fornecedor.getNome());
                stmt.setString(2, fornecedor.getNomeFantasia());
                stmt.setString(3, fornecedor.getCnpj());
               
                
                stmt.execute();
        }catch(SQLException ex){
                       System.out.println("Erro ao inserir dados:"+ex.getMessage());
                        }
            
    }
    
    public void editar(Fornecedor fornecedor){
         try{
             String sql = "UPDATE tb_fornecedores set forn_nome=?, forn_fantasia=?,  forn_cnpj=? WHERE forn_id=?";
             
              PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, fornecedor.getNome());
                stmt.setString(2, fornecedor.getNomeFantasia());
                stmt.setString(3, fornecedor.getCnpj());
                 stmt.setInt(4, fornecedor.getId());
             stmt.execute();
         }catch(SQLException ex){
             System.out.println("Erro ao atualizar fornecedor: "+ex.getMessage());
         }
     }
    
    public void excluir(int id){
      try{
          String sql = "delete from tb_fornecedores WHERE forn_id=?";
          
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          stmt.execute();
          
          
      } catch(SQLException ex){
          System.out.println("Erro ao excluir fornecedor: "+ex.getMessage());
      } 
     }
    public Fornecedor getFornecedor(int id) 
    {
        String sql = "SELECT * FROM tb_fornecedores WHERE forn_id = ?";
        
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            


            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
            
            Fornecedor f = new Fornecedor();
            // Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição da consulta
            // Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            
            f.setId(id);
            f.setNome(rs.getString("forn_nome"));
            f.setNomeFantasia(rs.getString("forn_fantasia"));
            f.setCnpj(rs.getString("forn_cnpj"));
            
            return f;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }
}
