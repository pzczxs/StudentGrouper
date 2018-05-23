package cn.edu.bjut.core;

public class Student {
	private String id; 
	private String name; 
	private boolean gender; // true: 男; false: 女
	private String email; 
	
	public Student(final String id, final String name, final boolean gender, final String email) {
		this.id = id.trim(); 
		this.name = name.trim(); 
		this.gender = gender; 
		this.email = email.trim(); 
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(final boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(id + "\t")
		  .append("[[" + email + "|")
		  .append(name + "]]"); 
		if (gender) {
			sb.append("男"); 
		} else {
			sb.append("女"); 
		}
		
		return sb.toString();
	}
}
