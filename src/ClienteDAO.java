
import conexao.ConexaoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2830482221001
 */
public class ClienteDAO {
    private ConexaoEstoque conexaoEstoque;
    private Connection conn;
    
    public ClienteDAO(){
        this.conexaoEstoque =  new ConexaoEstoque();
        this.conn = this.conexaoEstoque.getConexaoEstoque();
    }
    
    public void inserir(Cliente cliente){
        String sql = "INSERT INTO tb_cliente(cli_nome, cli_email, cli_cpf) VALUES (?,?,?);";
        
        try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getCpf());
               
                
                stmt.execute();
        }catch(SQLException ex){
                       System.out.println("Erro ao inserir dados:"+ex.getMessage());
                        }
            
    }
    
    public void editar(Cliente cliente){
         try{
             String sql = "UPDATE tb_cliente set cli_nome=?, cli_email=?,  cli_cpf=? WHERE cli_id=?";
             
              PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getCpf());
                stmt.setInt(4, cliente.getId());
             stmt.execute();
         }catch(SQLException ex){
             System.out.println("Erro ao atualizar cliente: "+ex.getMessage());
         }
     }
    
    public void excluir(int id){
      try{
          String sql = "delete from tb_cliente WHERE cli_id=?";
          
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          stmt.execute();
          
          
      } catch(SQLException ex){
          System.out.println("Erro ao excluir cliente: "+ex.getMessage());
      } 
     }
    public Cliente getCliente(int id) 
    {
        String sql = "SELECT * FROM tb_cliente WHERE cli_id = ?";
        
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            


            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
            
            Cliente cli = new Cliente();
            // Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição da consulta
            // Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            
            cli.setId(id);
            cli.setNome(rs.getString("cli_nome"));
            cli.setEmail(rs.getString("cli_email"));
            cli.setCpf(rs.getString("cli_cpf"));
            
            return cli;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro ao consultar cliente: " + ex.getMessage());
            return null;
        }
    }
    public List<Cliente> getCliente(){
          String sql = "SELECT * FROM tb_cliente";
        
        try 
        {
            PreparedStatement stmt = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listaCliente = new ArrayList<>();
            
        while(rs.next()){
            Cliente c = new Cliente();
            c.setId(rs.getInt("cli_id"));
            c.setNome(rs.getString("cli_nome"));
            listaCliente.add(c);
            
        }
        return listaCliente;
    }catch(SQLException ex){
            System.out.println("Erro ao consultar clientes: "+ ex.getMessage());
            return null;
    }
    }
}
