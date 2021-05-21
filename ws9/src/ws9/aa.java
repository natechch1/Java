package ws9;

public class aa {
	
	
	
class Date{
	int year;
	int month;
	int day;
	public  Date() {
		this.year = 0;
		this.month = 0;
		this.day = 0;
	}
public Date(int year, int month, int day) {
	this.year = year;
	this.month = month;
	this.day = day;
}
public Date(Date date) {
	this(date.year,date.month, date.day);
}
public String toString() {
	return Integer.toString(year) + ":" + Integer.toString(month) + ":" + Integer.toString(day);
}
}




}

}

public String toString() {
	String out = "";
	for(int i = 0; i<this.submitDate.length; i++) {
		out = out + "\n" +this.submitDate[i].year + ":" +this.submitDate[i].month + ":"  +this.submitDate[i].day;
	}
	return out;
}



