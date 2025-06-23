/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 2830482221001
 */
public class ConexaoEstoque {
    public Connection getConexaoEstoque (){
    try{
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_estoque?useTimezone=true&serverTimezone=UTC",
                    "root","root1234");
            System.out.println("Conexao realizada com sucesso");
            return conn;
        }catch(Exception e){
            System.out.println("Erro ao conectar no BD"+e.getMessage());
            return null;
        }
}
}
