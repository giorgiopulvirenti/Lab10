package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {
	TreeMap<Integer,Author> autori=new TreeMap<Integer,Author>();
	TreeMap<Integer,Paper> papers=new TreeMap<Integer,Paper>();

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public Collection<Author> listaAutori(){
		
		final String sql = "SELECT * FROM author";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
		

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				if (!autori.containsKey(rs.getInt("id")))
				autori.put(rs.getInt("id"), autore);
			}

			

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
		return  autori.values();
	}
public void listaConnessioni(){
		
		final String sql = "SELECT a.id, a.lastname, a.firstname, p.eprintid, p.title, p.issn, p.publication, p.type, p.types "
				+ "FROM creator c, author a, paper p "
				+ "WHERE c.eprintid=p.eprintid "
				+ "AND c.authorid=a.id";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
		

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if (!autori.containsKey(rs.getInt("id")))
				autori.put(rs.getInt("id"), new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"))) ;
				if (!papers.containsKey(rs.getInt("eprintid")))
				papers.put(rs.getInt("eprintid"), new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),	rs.getString("publication"), rs.getString("type"), rs.getString("types")));
					
			autori.get(rs.getInt("id")).addPaper(papers.get(rs.getInt("eprintid")));
			papers.get(rs.getInt("eprintid")).addAutore(autori.get(rs.getInt("id")));
			
			}

			

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}
}