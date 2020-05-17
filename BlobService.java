package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BlobService {

//	private final static String SELECTALL = "SELECT * FROM products";
	private final static String INSERT = "INSERT INTO images(image) values (?)";
	private final static String RETRIEVE = "SELECT * FROM images WHERE id=?";

	public void insertImage() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/users?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT);
			File image = new File ("C:\\Users\\user\\Desktop\\images\\pudge.jpg");
			FileInputStream fis = new FileInputStream(image);
			stmt.setBinaryStream(1, fis);
			stmt.execute();
			fis.close();
			
//			while (rs.next()) {
//				Product product = new Product();
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				product.setPrice(rs.getInt("price"));
//				product.setDescription(rs.getString("description"));
//				product.setCategory(rs.getString("category"));
//				productList.add(product);
//			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException sqlEx) {
//					sqlEx.printStackTrace();
//				}
//
//				rs = null;
//			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
//	
	public InputStream getImage(int id) {
		InputStream in = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/users?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.prepareStatement(RETRIEVE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				in = rs.getBinaryStream("image");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return in;
	}
//	
//	public List<Product> getProductsCategory(String category) {
//		List<Product> productList = new ArrayList<Product>();
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		Connection conn = null;
//
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteaweb?" + "user=root&password=");
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			stmt = conn.prepareStatement(GET_CATEGORY);
//			stmt.setString(1, category);
//			rs = stmt.executeQuery();
//			if(rs.next()) {
//				Product product = new Product();
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				product.setPrice(rs.getInt("price"));
//				product.setDescription(rs.getString("description"));
//				product.setCategory(rs.getString("category"));
//				productList.add(product);
//			}
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException sqlEx) {
//					sqlEx.printStackTrace();
//				}
//
//				rs = null;
//			}
//
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException sqlEx) {
//					sqlEx.printStackTrace();
//				}
//
//				stmt = null;
//			}
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return productList;
//	}
//	

}
