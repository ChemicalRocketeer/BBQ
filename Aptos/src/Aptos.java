import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import aptosAppClasses.Menu;
import aptosAppClasses.Time;

public class Aptos{
	public static void main(String[] args){
		Time now = new Time();
		System.out.println(now.getTime());
	}
}
