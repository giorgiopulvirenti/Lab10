package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

public class Author {

	private int id;
	private String lastname;
	private String firstname;
	List <Paper>papers=new ArrayList <Paper>();
		
	public Author(int id, String lastname, String firstname) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void addPaper(Paper paper) {
		if(!papers.contains(paper))
			papers.add(paper);
		
		
	}
	public List<Paper> getPapers(){
		return papers;
	}
	
}
