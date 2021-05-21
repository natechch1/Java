package studentSystem;

import java.util.Comparator;

public class ProfByYearComparator implements Comparator<Prof> {

	public ProfByYearComparator() {
		// TODO Auto-generated constructor stub
	}

	
		 	/**
	 	   * Compares two Profs by their year, in ascending order.
	 	   * 
	 	   *  
	 	   */
	@Override
	public int compare(Prof p1, Prof p2) {
		return Integer.compare(p1.getYear(), p2.getYear());
	}

}
