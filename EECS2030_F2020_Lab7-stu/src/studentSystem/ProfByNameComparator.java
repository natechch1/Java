package studentSystem;

import java.util.Comparator;

public class ProfByNameComparator implements Comparator<Prof>{

    @Override
    public int compare(Prof arg0, Prof arg1) {
        
        return arg0.getName().compareTo(arg1.getName());
    }

}