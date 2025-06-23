
import conexao.ConexaoEstoque;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2830482221001
 */
public class CategoriaDAO {
    private ConexaoEstoque conexaoEstoque;
    private Connection conn;
    
    public CategoriaDAO() {
        this.conexaoEstoque =  new ConexaoEstoque();
        this.conn = this.conexaoEstoque.getConexaoEstoque();
    }
    
    public void inserir(Categoria categoria){
        String sql = "INSERT INTO tb_categoria(cat_nome, cat_descricao) VALUES (?,?);";
        
        try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, categoria.getNome());
                stmt.setString(2, categoria.getDescricao());
               
                
                stmt.execute();
        }catch(SQLException ex){
                       System.out.println("Erro ao inserir dados:"+ex.getMessage());
                        }
            
    }
    
    public void editar(Categoria categoria){
         try{
             String sql = "UPDATE tb_categoria set cat_nome=?, cat_descricao=? WHERE id=?";
             
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setString(1,categoria.getNome());
             stmt.setString(2,categoria.getDescricao()); 
             stmt.setInt(3,categoria.getIdCategoria());
             stmt.execute();
         }catch(SQLException ex){
             System.out.println("Erro ao atualizar categoria: "+ex.getMessage());
         }
     }
    
    public void excluir(int id){
      try{
          String sql = "delete from tb_categoria WHERE id=?";
          
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          stmt.execute();
          
          
      } catch(SQLException ex){
          System.out.println("Erro ao excluir categoria: "+ex.getMessage());
      } 
     }
    public Categoria getCategoria(int id) 
    {
        String sql = "SELECT * FROM tb_categoria WHERE id = ?";
        
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            


            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
            
            Categoria c = new Categoria();
            // Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição da consulta
            // Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            
            c.setIdCategoria(id);
            c.setNome(rs.getString("cat_nome"));
            c.setDescricao(rs.getString("cat_descricao"));
           
            
            return c;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro ao consultar categoria: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Categoria> getCategoria(){
          String sql = "SELECT * FROM tb_categoria";
        
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            
            ResultSet rs = stmt.executeQuery();
            List<Categoria> listaCategoria = new ArrayList<>();
            
        while(rs.next()){
            Categoria c = new Categoria();
            c.setIdCategoria(rs.getInt("id"));
            c.setNome(rs.getString("cat_nome"));
            listaCategoria.add(c);
            
        }
        return listaCategoria;
    }catch(SQLException ex){
            System.out.println("Erro ao consultar categorias: "+ ex.getMessage());
            return null;
    }
    }
    
    
        
}
