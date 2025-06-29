
import conexao.ConexaoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
   public int inserirC(Nota nota) {
    String sql = "INSERT INTO tb_nota_cabecalho(nota_data, nota_valor, cli_id, forn_id) VALUES (?, ?, ?, ?)";
    
    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, nota.getData());
        stmt.setDouble(2, nota.getValor());
        stmt.setInt(3, nota.getCliente());
        stmt.setInt(4, nota.getFornecedor());
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int idGerado = rs.getInt(1);
            nota.setId(idGerado); // opcional: se quiser salvar o ID no objeto Nota
            return idGerado;
        } else {
            return -1; // não conseguiu recuperar o ID
        }

    } catch(SQLException ex) {
        System.out.println("Erro ao inserir dados: " + ex.getMessage());
        return -1;
    }
}

    
    public void inserirI(Nota nota){
        String sql = "INSERT INTO tb_nota_itens(notai_id, prod_id, notai_quantidade, nota_id, notai_unitario) VALUES (?,?,?,?,?);";
        
        try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, nota.getId_itens());
                stmt.setString(2, nota.getProdutos());
                stmt.setInt(3, nota.getQuantidade());
                stmt.setInt(4, nota.getId());
                stmt.setDouble(5, nota.getUnitario());
                
                stmt.execute();
        }catch(SQLException ex){
                       System.out.println("Erro ao inserir dados:"+ex.getMessage());
                        }
            
    }
    
    
    
}//fim da classe
