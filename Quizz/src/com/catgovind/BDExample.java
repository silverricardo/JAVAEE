package com.catgovind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDExample {
	
	static Connection con = null;
	static Statement stmt = null;
	static final String DB_URL = "jdbc:mysql://localhost:3306/quizz?autoReconnect=true&useSSL=false&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "root";
	
	
	public static int verificaUser(String username, String password){
		
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Sem conecção a base de dados");
		}
		
		String query = "select * from users where email='"+username+"' and password='"+password+"'";
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Sem conecção a base de dados");
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("falha na ligação a base de dados");
		}
		if(rs!= null) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	public static void printClients(){
		
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Sem conecção a base de dados");
		}
		
		
		// Executar a Query
		String query = "select * from CUSTOMERs";
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Sem conecção a base de dados");
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("falha na ligação a base de dados");
		}
		try {
			while (rs.next()) {
			System.out.print("ID Cliente:"+rs.getString("CUSTOMErnumber"));
			System.out.println(" | Nome: "+rs.getString("CUSTOMERNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Não se consegue desligar a base de dados");
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Não se consegue desligar a base de dados");
		}
	}
	
public static void printProdutos(){
		
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Sem conecção a base de dados");
		}
		
		
		// Executar a Query
		String query = "select * from products";
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Sem conecção a base de dados");
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("falha na Execução da Query");
		}
		try {
			while (rs.next()) {
			System.out.print("Preço de venda:"+rs.getString("buyPrice"));
			System.out.println(" | Nome do produto: "+rs.getString("productName"));
			}
		} catch (SQLException e) {
			System.out.println("Nada apresentar");
			//e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Não se consegue desligar a base de dados");
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Não se consegue desligar a base de dados");
		}
	}
	
public static void inserirCliente(String name, int nTelefone, String morada, String cidade, String pais, int nCliente){
	
	try {
		con = DriverManager.getConnection(DB_URL, USER, PASS);
	} catch (SQLException e) {
		System.out.println("Sem conecção a base de dados");
	}
	
	
	// Executar a Query
	String query = "select * from customers where customerName =\""+name+"\"";
	try {
		stmt = con.createStatement();
	} catch (SQLException e) {
		System.out.println("Sem conecção a base de dados");
	}
	ResultSet rs = null;
	try {
		rs = stmt.executeQuery(query);
	} catch (SQLException e) {
		System.out.println("Cliente Já existe");
	}
	if(rs != null) {
		String query1 = "insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)"+" values (\""+nCliente+"\",\""+name+"\", 'Silva', 'Mario',\""+nTelefone+"\",\""+morada+"\", 'casa', \""+cidade+"\", 'orto', 4455, \""+pais+"\", 0, 15)";
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Sem conecção a base de dados");
		}
		try {
			stmt.executeUpdate(query1);
		} catch (SQLException e) {
			System.out.println("falha na Execução da Query");
			//e.printStackTrace();
		}
	}else{
		System.out.println("Cliente já Existe");
		}
	

	try {
		stmt.close();
	} catch (SQLException e) {
		System.out.println("Não se consegue desligar a base de dados");
	}
	try {
		con.close();
	} catch (SQLException e) {
		System.out.println("Não se consegue desligar a base de dados");
	}
}

}
