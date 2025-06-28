
import conexao.ConexaoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Classe para 
 * @author ingridsouza
 * @since 27 de jun. de 2025
 */
public class NotaDAO {
  private ConexaoEstoque conexaoEstoque;
    private Connection conn;
    
    public NotaDAO(){
        this.conexaoEstoque =  new ConexaoEstoque();
        this.conn = this.conexaoEstoque.getConexaoEstoque();
    }
    
    public void inserirC(Nota nota){
        String sql = "INSERT INTO tb_nota_cabecalho(nota_id, nota_data, nota_valor, cli_id, forn_id) VALUES (?,?,?,?,?);";
        
        try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, nota.getId());
                stmt.setString(2, nota.getData());
                stmt.setDouble(3, nota.getValor());
                stmt.setInt(4, nota.getCliente());
                stmt.setInt(5, nota.getFornecedor());
                
                stmt.execute();
        }catch(SQLException ex){
                       System.out.println("Erro ao inserir dados:"+ex.getMessage());
                        }
            
    }
}//fim da classe
