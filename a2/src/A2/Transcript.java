package A2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;

/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Hongce Chen
Student Number: 216792327
Course Section: B
*/

/**
 * This class generates a transcript for each student, whose information is in
 * the text file.
 * 
 *
 */

public class Transcript {
	private ArrayList<Object> grade = new ArrayList<Object>();
	private File inputFile;
	private String outputFile;

	/**
	 * This the the constructor for Transcript class that initializes its instance
	 * variables and call readFie private method to read the file and construct
	 * this.grade.
	 * 
	 * @param inFile  is the name of the input file.
	 * @param outFile is the name of the output file.
	 */

	public Transcript(String inFile, String outFile) {
		inputFile = new File(inFile);
		outputFile = outFile;
		grade = new ArrayList<Object>();
		this.readFile();
	}// end of Transcript constructor

	/**
	 * This method reads a text file and add each line as an entry of grade
	 * ArrayList.
	 * 
	 * @exception It throws FileNotFoundException if the file is not found.
	 */

	private void readFile() {
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			while (sc.hasNextLine()) {
				grade.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	} // end of readFile

	public ArrayList<Student> buildStudentArray() {  
		//This method creates and returns an ArrayList (finalTranscript) , whose element is an object of class Student.
		ArrayList<Student> finalTranscript = new ArrayList<>();
		//create this new ArrayList for final transcript
		for (Object row : grade) {
			String[] items = row.toString().split(",");
			//For input file, it go to each row and if read ","  then split to items  
			String courseCode = items[0];     
			//this item is course code (the first item)
			int credit = Integer.parseInt(items[1]);
			//this item is credit (the second item)
			String studentID = items[2];
			//this item is student ID (the third item)
			ArrayList<Assessment> assignments = new ArrayList<>();
			//create this new ArrayList for assignments
			ArrayList<Double> eachScore = new ArrayList<>();
			//create this new ArrayList for each assignment's score
			ArrayList<Integer> totalWeight = new ArrayList<>();
			//create this new ArrayList for each assignment's weight
			
			int i = 0;
			while (true) {
				String[] item2 = items[3 + i].split("[()]");
				//looking for P (practical activities) and E (Exams) and split score and weight.
				if (item2.length <= 1) {
					break;
					//when the length of items is less than and equal to 1, 
					//it will stop store value to score and weight, and the loop will break
				}
				char typeForAssignment = item2[0].charAt(0);
				//store the type for assignment
				double score = Double.parseDouble(item2[1]);
				//store score
				int weight = Integer.parseInt(item2[0].substring(1));
				//store weight
				eachScore.add(score);
				//this score will be add to ArrayList<Double> eachScore
				totalWeight.add(weight);
				//this weight will be add to ArrayList<Integer> totalWeight
				assignments.add(Assessment.getInstance(typeForAssignment, weight));
				//assignments will store with type (typeForAssignment) and weight
				i++;
			}
			
			String name = items[3 + i];
			//this item is student's name
			Course course = new Course(courseCode, assignments, credit);
			//In Course, the student is stored with course code (courseCode) , assignments and credit
			Student thisStudent = finalTranscript.stream().filter(student -> student.getStudentId().equals(studentID)).findAny().orElse(null);
			//its looking for student that ID equal to Variable studentID.
			//If not, set thisStudent to null
			if (thisStudent != null) {
				//if thisStudent is not null which means the student is found
				thisStudent.addCourse(course);
				//store course for this student
				thisStudent.addGrade(eachScore, totalWeight);
				//store score and weight for this student
			} 
			else {
				//if thisStudent is null which means this student didn't found
				Student otherStudent = new Student(studentID, name, new ArrayList<>());
				//store other student's studentID and name
				otherStudent.addCourse(course);
				//store course for otherStudent
				otherStudent.addGrade(eachScore, totalWeight);
				//store score and weight for otherStudent
				finalTranscript.add(otherStudent);
				//store the otherStudent to finalTranscript
			}
		}

		return finalTranscript;
	}

	/**
	 * this method is print the transcript to output file
	 * 
	 * @param students which was need to print the transcript
	 */
	public void printTranscript(ArrayList<Student> students) {
		try (PrintWriter printwriter = new PrintWriter(new File(outputFile))) {
			for (Student student : students) {
				printwriter.write(student.getName() + "\t" + student.getStudentId());
				printwriter.write(System.lineSeparator());
				printwriter.write("--------------------");
				printwriter.write(System.lineSeparator());
				ArrayList<Course> courseTaken = student.getCourseTaken();
				for (int i = 0; i < courseTaken.size(); i++) {
					printwriter.write(courseTaken.get(i).getCode() + "\t" + student.getFinalGrade().get(i));
					printwriter.write(System.lineSeparator());
				}
				printwriter.write("--------------------");
				printwriter.write(System.lineSeparator());
				printwriter.write("GPA: " + student.weightedGPA());
				printwriter.write(System.lineSeparator());
				printwriter.write(System.lineSeparator());
			}
		} 
		catch (IOException e) {
			throw new RuntimeException(e);       //catch the run time exception
		}
	}

	public static void main(String args[]) {
		Transcript transcript = new Transcript("input.txt", "output.txt");
		ArrayList<Student> students = transcript.buildStudentArray();
		transcript.printTranscript(students);

	}
} // end of Transcript
/**
 * Class Student is looking for studentID, name, course taken and their final grade.
 *
 */
class Student {
	private String studentID;         
	private String name;
	private ArrayList<Course> courseTaken;
	private ArrayList<Double> finalGrade;

	/**
	 * Class Student's  default constructor 
	 */
	
	public Student() {
		courseTaken = new ArrayList<>();
		finalGrade = new ArrayList<>();
	}
/**
 * its fields to construct the student ID, student name, the course taken, and final grade
 * 
 * @param studentID
 * @param name is student name
 * @param courseTaken is the course that student taken
 */
	public Student(String studentID, String name, ArrayList<Course> courseTaken) {
		this.studentID = studentID;
		this.name = name;
		this.courseTaken = new ArrayList<Course>();
		this.finalGrade = new ArrayList<>();

		for (Course course : courseTaken) {
			this.courseTaken.add(new Course(course));
		}
		
		for (Course course : courseTaken) {
			finalGrade.add(0.0);
		}
	}
/**
 * it obtains an array list of the grade and its weight, 
 * calculates the true value of the grade according to the weight of the grade,
 * and adds it to the finalGrade attribute. 
 * Level-based weights will be rounded to one decimal place.
 * 
 * @param scores
 * @param weights is the weight of student course taken
 * @throws if total weight is not equal to 100, go InvalidTotalException
 */
	public void addGrade(ArrayList<Double> scores, ArrayList<Integer> weights) {
		int totalWeight = 0;
		int score = 0;
		for (int i = 0; i < scores.size(); i++) {
			int weight = weights.get(i);
			totalWeight += weight;
			score += (int) (scores.get(i) * weight);
		}
		if (totalWeight > 100) {
			throw new InvalidTotalException("Total Course weight is greater than 100");
		}
		if (totalWeight < 100) {
			throw new InvalidTotalException("Total Course weight is less than 100");
		}
		finalGrade.add((score / 10) / 10.0);

	}

	/**
	 * this method output the GPA for course
	 * 
	 * @return GPA (round to one decimal)
	 */
	public double weightedGPA() {
		double total = 0.0;
		double totalcredit = 0.0;
		for (int i = 0; i < courseTaken.size(); i++) {
			int point;
			double grade = finalGrade.get(i);
			if (grade >= 90.0) {
				point = 9;
			}
			else if (grade >= 80.0) {
				point = 8;
			} 
			else if (grade >= 75.0) {
				point = 7;
			} 
			else if (grade >= 70.0) {
				point = 6;
			} 
			else if (grade >= 65.0) {
				point = 5;
			} 
			else if (grade >= 60.0) {
				point = 4;
			} 
			else if (grade >= 55.0) {
				point = 3;
			} 
			else if (grade >= 50.0) {
				point = 2;
			} 
			else if (grade >= 47.0) {
				point = 1;
			} 
			else {
				point = 0;
			}
			double credit = courseTaken.get(i).getCredit();
			total += credit * point;
			totalcredit += credit;
		}
		return Math.round((total / totalcredit) * 10) / 10.0;
	}

	/**
	 * this method add the object course to course taken
	 * 
	 * @param course is input which is course information
	 */
	public void addCourse(Course course) {
		courseTaken.add(new Course(course));
	}
/**
 * get this student's ID
 *  
 * @return studentID
 */
	public String getStudentId() {
		return studentID;
	}

/**
 * get this student's name
 * 
 * @return name which is student name
 */
	public String getName() {
		return name;

	}
	
/**
 * get this student course taken
 * 
 * @return courseTakenOutput which is course taken by each student
 */
	public ArrayList<Course> getCourseTaken() {
		ArrayList<Course> courseTakenOutput = new ArrayList<Course>();
		for (Course course : courseTaken) {
			courseTakenOutput.add(new Course(course));
		}
		return courseTakenOutput;
	}
/**
 * get this student's final grade
 * 
 * @return finalGrade which is student's final grade
 */
	public ArrayList<Double> getFinalGrade() {
		return finalGrade;
	}

/**
 * set next student's ID
 * 
 * @param nextStudentID which is the next student's ID
 */
	public void setStudentId(String nextStudentID) {
		this.studentID = nextStudentID;
	}

/**
 * set next student's name
 * 
 * @param nextName which is next student's name
 */
	public void setName(String nextName) {
		this.name = nextName;
	}

/**
 * set next student's course taken
 * 
 * @param nextCourseTaken which next student's course taken
 */
	public void setCourseTaken(ArrayList<Course> nextCourseTaken) {
		this.courseTaken = new ArrayList<Course>();
		for (Course course : nextCourseTaken) {
			this.courseTaken.add(new Course(course));
		}
	}
/**
 * set next student's final grade
 * 
 * @param nextFinalGrade which is next student's final grade
 */
	public void setFinalGrade(ArrayList<Double> nextFinalGrade) {
		this.finalGrade = nextFinalGrade;
	}
}

/**
 * This exception got when the total weight was not equal to 100, or the final grade was greater 100.
 * InvalidTotalException is extends to RuntimeException and create a new RunTimeException hierarchy.
 *
 */
class InvalidTotalException extends RuntimeException {
	/**
	 * if read the exception, then print this flag  
	 * 
	 * @param flag printed if exception was read
	 */
	public InvalidTotalException(String flag) {
		super(flag);
	}
}


/**
 * Class Course is used to get course code, course assignment, and course credit of each student
 * 
 *
 */
class Course {
	private String code;
	private ArrayList<Assessment> assignment;
	private double credit;

/**
 *  Class Course's  default constructor 
 */
	public Course() {

	}

/**
 * its fields to construct the course code, course assignment, and course credit of each student
 * 
 * @param code which is course code
 * @param assignment which is course assignment
 * @param credit which is credit of course
 */
	public Course(String code, ArrayList<Assessment> assignment, double credit) {
		this.code = code;
		this.assignment = new ArrayList<Assessment>();
		for (Assessment assessment : assignment) {
			this.assignment.add(assessment);
		}
		this.credit = credit;
	}

/**
 * its copy these course
 * 
 * @param course
 */
	public Course(Course course) {
		this.code = course.code;
		this.assignment = new ArrayList<Assessment>();


		for (Assessment assessment : assignment) {
			this.assignment.add(assessment);
		}
		this.credit = course.credit;
	}

/**
 * @return hash code
 */
	@Override
	public int hashCode() {
		return Objects.hash(assignment, code, credit);
	}

/**
 * this method is return if they have the same assignment, same code and same credit.
 * @return true if equal to obj
 *         false 
 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(assignment, other.assignment) && Objects.equals(code, other.code)
				&& Double.doubleToLongBits(credit) == Double.doubleToLongBits(other.credit);
	}

/**
 * get this student's course credit 
 * 
 * @return credit which is this student's credit of course 
 */
	public double getCredit() {
		return this.credit;
	}

/**
 * get this student's assignment of course
 * 
 * @return assignment which is this student's assignment of course
 */
	public ArrayList<Assessment> getAssignment() {
		ArrayList<Assessment> assignmentOuput = new ArrayList<Assessment>();

		for (Assessment assessment : assignment) {
			assignmentOuput.add(assessment);
		}

		return assignmentOuput;
	}
	
/**
 * get the this student's course code
 * 
 * @return code which is this student's course code
 */
	public String getCode() {
		return this.code;
	}

/**
 * set next student's course credit
 * 
 * @param nextCredit which is next student's course credit
 */
	public void setCredit(double nextCredit) {
		this.credit = nextCredit;
	}

/**
 * set next student's course assignment
 * 
 * @param nextAssignment which is next student's course assignment
 */
	public void setAssignment(ArrayList<Assessment> nextAssignment) {
		this.assignment = new ArrayList<Assessment>();

		for (Assessment assessment : assignment) {
			this.assignment.add(assessment);
		}
	}

/**
 * set next student's course code
 * 
 * @param nextCode which is next student's course code
 */
	public void setCode(String nextCode) {
		this.code = nextCode;
	}
}


/**
 * Class Assessment is get the type and weight for the course.
 * 
 */
class Assessment {
	private char type;
	private int weight;

	/**
	 * Class Assessment's  default constructor 
	 */
	private Assessment() {

	}

	/**
	 * its fields to construct the type and weight for the course
	 * 
	 * @param type which is assessment's type
	 * @param weight which is assessment's weight
	 */
	private Assessment(char type, int weight) {
		this.type = type;
		this.weight = weight;
	}

	/**
	 * This is a static factory method for class Assessment. 
	 * 
	 * @param type
	 * @param weight
	 * @return more information of assessment
	 */
	public static Assessment getInstance(char type, int weight) {
		return new Assessment(type, weight);
	}

	
/**
 * @return hash code
 */
	@Override
	public int hashCode() {
		return Objects.hash(type, weight);
	}

/**
 * this method is return if they have the same type, and same weight.
 * @return true if equal to obj
 *         false 
 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessment other = (Assessment) obj;
		return type == other.type && weight == other.weight;
	}

	/**
	 * get assessment type
	 * @return assessment type
	 */
	public char getType() {
		return type;
	}

	/**
	 * get assessment weight
	 * @return assessment weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * set next type of assessment
	 * @param nextType
	 */
	public void setType(char nextType) {
		this.type = nextType;
	}

	/**
	 * set next weight of assessment
	 * @param nextWeight
	 */
	public void setWeight(int nextWeight) {
		this.weight = nextWeight;
	}
}
