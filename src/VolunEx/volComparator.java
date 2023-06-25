package VolunEx;

import java.util.Comparator;

public class volComparator implements Comparator<Volunteer>{
	public int compare(Volunteer t1, Volunteer t2) {
		// TODO Auto-generated method stub
		if (t1.actBeginTm<t2.actBeginTm)
			return -1;
		else if (t1.actBeginTm == t2.actBeginTm)
			return 0;
		else 
			return 1;
	}
}
