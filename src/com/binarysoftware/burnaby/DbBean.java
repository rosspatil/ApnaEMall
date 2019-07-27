package com.binarysoftware.burnaby;

import java.util.Hashtable; 
import java.util.ArrayList; 
import java.util.Enumeration; 
import java.sql.*; 

public class DbBean {

	public Hashtable<String,String> getCategories() {
		Hashtable<String,String> categories = new  Hashtable<String,String>(); 
		try {
			Connection connection = Database.getConnection(); 
			Statement s = connection.createStatement(); 
			String sql = "SELECT CatagoryId, Catagory FROM Catagory;" ;
			ResultSet rs = s.executeQuery(sql); 
			while (rs.next()) {
				categories.put(rs.getString(1), rs.getString(2) ); 
			} 
			rs.close(); 
			s.close(); 
			connection.close(); 
		} 
		catch (SQLException e) {} 
		return categories; 
	} 

	public  ArrayList<Product> getSearchResults(String keyword) {
		ArrayList<Product> products = new  ArrayList<Product>(); 
		try {
			Connection connection = Database.getConnection(); 
			Statement s = connection.createStatement(); 
			String sql = "SELECT productId, name, descriptionr, price FROM product" + 
					" WHERE name LIKE '%" + keyword.trim() + "%'" + 
					" OR descriptionr LIKE '%" + keyword.trim() + "%'"; 
			ResultSet rs = s.executeQuery(sql); 
			while (rs.next()) {
				Product product = new Product(); 
				product.id = rs.getInt(1); 
				product.name = rs.getString(2); 
				product.description = rs.getString(3); 
				product.price = rs.getDouble(4); 
				products.add(product); 
			} 
			rs.close(); 
			s.close(); 
			connection.close(); 
		} 
		catch (SQLException e) {} 
		return products; 
	} 

	public ArrayList<Product> getProductsInCategory(String categoryId) {
		ArrayList<Product> products = new ArrayList<Product>(); 
		Product product=null;
		try {
			Connection connection = Database.getConnection(); 
			Statement s = connection.createStatement(); 
			String sql = "SELECT productId, name, descriptionr, price FROM product" + 
					" WHERE catagoryId=" + Integer.parseInt(categoryId); 
			ResultSet rs = s.executeQuery(sql); 
			while (rs.next()) {
				product = new Product(); 
				product.id = rs.getInt(1); 
				product.name = rs.getString(2); 
				product.description = rs.getString(3); 
				product.price = rs.getFloat(4); 
				products.add(product); 
			} 
			rs.close(); 
			s.close(); 
			connection.close(); 
		} 
		catch (SQLException e) {} 
		return products; 
	} 

	public Product getProductDetails(int productId) {

		Product product=null;
		try {
			Connection connection = Database.getConnection(); 
			Statement s = connection.createStatement(); 
			String sql = "SELECT productId, name, descriptionr, price FROM product" + 
					" WHERE productId=" + Integer.toString(productId); 
			ResultSet rs = s.executeQuery(sql); 
			if (rs.next()) { 
				product=new Product();
				product.id = rs.getInt(1); 
				product.name = rs.getString(2); 
				product.description = rs.getString(3); 
				product.price = rs.getDouble(4); 
			} 
			rs.close(); 
			s.close(); 
			connection.close(); 
		} 
		catch (SQLException e) {} 
		return product;

	} 

	public boolean insertOrder(String contactName, String deliveryAddress, 
			String ccName, String ccNumber, String ccExpiryDate, Hashtable shoppingCart) {
		boolean returnValue = false; 
		
		Connection connection = null; 
		try {
			int orderId = (int)System.currentTimeMillis(); 
			connection = Database.getConnection(); 
			connection.setAutoCommit(false); 
			Statement s = connection.createStatement(); 
			String sql = "INSERT INTO orders" + 
					" (orderId, contactName, deliveryAddress, CCName, CCNumber, CCEXpiry)" + 
					" VALUES" + 
					" (" + orderId + ",'" + contactName + "','" + deliveryAddress + "'," + 
					"'" + ccName + "','" + ccNumber + "','" + ccExpiryDate + "')"; 
			s.executeUpdate(sql); 
			// now insert items into OrderDetails table 
			Enumeration enu= shoppingCart.elements(); 
			while (enu.hasMoreElements()) {
				ShoppingItem item = (ShoppingItem) enu.nextElement(); 
				sql = "INSERT INTO orderDetails (orderId, productId, quantity, price)" + 
						" VALUES (" + orderId + "," + item.productId + "," + 
						item.quantity + "," + item.price + ")"; 
				s.executeUpdate(sql); 
			} 

			s.close(); 
			connection.commit(); 
			connection.close(); 
			returnValue = true; 
		} 
		catch (SQLException e) {
			try {
				e.getStackTrace();
				connection.rollback(); 
				connection.close(); 
			} 
			catch (SQLException se) {
				se.getSQLState();
			} 
		} 
		return returnValue; 
	} 
}