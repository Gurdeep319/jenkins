package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.dao.util.ConnManager;
import com.lti.entity.Product;

//classes which contain DB code are commonly
//referred to as Data Access Objects

public class ProductDao {
	
	public Product select(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = ConnManager.connect();
			//can you tell me what should the select query look like?
			String sql = "SELECT * FROM tbl_product WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				return product;
			}
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
	
	public List<Product> selectAll(){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnManager.connect();
			//can you tell me what should the select query look like?
			
			List<Product> products = new ArrayList<Product>();
			
			String sql = "SELECT * FROM tbl_product";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			Product product;
			while(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				products.add(product);
			}
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
			
	}
	
	public void insert (Product product) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnManager.connect();
			//can you tell me what should the select query look like?
			String sql = "INSERT INTO tbl_product VALUES(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			
		}
		catch(SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
			
	}
		
}
	


