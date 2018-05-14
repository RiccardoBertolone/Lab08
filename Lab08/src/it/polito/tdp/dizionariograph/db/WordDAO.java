package it.polito.tdp.dizionariograph.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public Set<String> getAllWordsFixedLength(int length) {

		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		Set<String> parole = new HashSet<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			st.setInt(1, length);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				parole.add(res.getString("nome"));
			}
			
			conn.close();
			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}

	public Set<String> getVicini(String s) {
		
		String sql = "SELECT nome FROM parola WHERE nome LIKE ? AND nome <> ? AND nome > ? AND LENGTH(nome) = ? " ;
		Set<String> vicini = new HashSet<String>();
		
		

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				for(int i=0; i<s.length(); i++) {
					
					StringBuilder sb = new StringBuilder(s);
					sb.setCharAt(i, '_');
					String daProvare = sb.toString();

				st.setString(1, daProvare);
				st.setString(2, s);
				st.setString(3, s);
				st.setInt(4, s.length());
				
				ResultSet res = st.executeQuery();

				while (res.next()) {
					vicini.add(res.getString("nome"));
				}
				
			}
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Error Connection Database");
			}
			
		return vicini;
	}

}
