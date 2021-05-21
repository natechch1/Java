package studentSystem;

import java.util.Objects;

public class Course  implements Comparable<Course>{ 
	
	String code;
	String title;
	Prof instructor;
	char term;   // assume 'W' 'S' 'F' only
	int year;
		
	public Course(String code, String title, Prof instructor, int year, char term) {
		super();
		this.code = code;
		this.title = title;
		this.instructor = instructor;
		this.term = term;
		this.year = year;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Prof getInstructor() {
		return instructor;
	}


	public void setInstructor(Prof instructor) {
		this.instructor = instructor;
	}


	public char getTerm() {
		return term;
	}


	public void setTerm(char term) {
		this.term = term;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public String toString() {
		return "<" + this.code + ", " + this.title + ", " + this.year  + this.term +", taught by: " + this.instructor.getName() + ">";
	}
	

		
	/**
	 * This class defines the logical equality as follows:
	 * two Courses are considered equal to each other if they have the same code, same instructor, and same year and term 
	 **/
	
	@Override
	public int hashCode() {
		return Objects.hash(code, instructor, term, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(code, other.code) && Objects.equals(instructor, other.instructor) && term == other.term
				&& year == other.year;
	}
	
	/**
	 * This class defines the natural ordering of Course objects, as follow:
	 * When 'this' course is compared to the specified course, 
	 * 'this' course is "less than" the specified course if its year is earlier than that of the specified course,
	 * and is "greater than" the specified course if its year is later than that of the specified course.
	 * If the years are the same, then they are further compared based on the term, which is either 'W' or 'S' or 'F'.
	 * 'this' course is "less than" the specified course if its term is earlier than 
	 * that of the specified course, and is "greater than" the specified course if the term is later than that of the specified course.
	 * 
	 * Assume term is either 'W' or 'S' or 'F', and we define 'W' term is earlier than 'S', which is earlier than 'F'.
	 * 
	 * If the terms are also same, then the objects are further compared bases on the lexicographical ordering of the course code.
	 * 
	 * 'this' course is "less than" the specified course if its course code lexicographically precedes that of the other course, and is 'greater than' the
	 * specified course if its course code lexicographically follows that of the specified course.  
	 * E.g., EECS1710 lexicographically precedes EECS1720, which precedes EECS2030, which  precedes EECS2031 and so on.
	 * 
	 * The result is 0 is the course codes are (also) also equal.
	 **/
	@Override
	public int compareTo(Course c) {
		if(this.year == c.year) {
			if(this.term == c.term) {
				return this.getCode().compareTo(c.getCode());
			}
			if(this.term == 'W') {
				return -1;
			}
			if(this.term == 'S') {
				if(c.term == 'F') {
					return -1;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
	return Integer.compare(this.year, c.year);
	}
	
}
	


