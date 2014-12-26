package NotifyUser;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.aptosstbbq.bbqapp.Utils;

public class Event {
	public Event(String s){
		s = "Last updated on " + Utils.time() + "/n" + s;
	}
}
