package VolunEx;

import java.util.Comparator;

public class timeComparator implements Comparator<timeBlock> {

	@Override
	public int compare(timeBlock t1, timeBlock t2) {
		// TODO Auto-generated method stub
		if (t1.eTime<t2.eTime)
			return -1;
		else if (t1.eTime == t2.eTime)
			return 0;
		else 
			return 1;
	}
}
