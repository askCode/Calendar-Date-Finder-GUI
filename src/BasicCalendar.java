import javax.swing.text.html.StyleSheet;

class BasicCalendar{
	
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	public static final int PADDING = 3;
	public static final String FORMAT = "%-" + PADDING + "s";
	
	private DateAD startDay;
	private int[][] calendarArray = new int[ROWS][COLUMNS];
	
	
	public BasicCalendar(){
		startDay = new DateAD((short) 1);
		setArray();
	}
	
	public BasicCalendar(short month){
		startDay = new DateAD((short) 1 , month);
		setArray();
	}
	
	public BasicCalendar(short month, short year){
		startDay = new DateAD((short) 1, month, year);
		setArray();
	}
	

	
	private void setArray(){
		short firstDayOfMonth = startDay.getDayOfWeek();
		short lastDayOfMonth = (short) (firstDayOfMonth  + DateAD.daysInMonth((short) (startDay.getMonth()), startDay.getYear()));

		//short start = (short) (dayOfWeek);
		//short stop = (short) (dayOfWeek + daysInMonth);
		short dayCount = 1;
		short counter = 0;

		
		for (int i = 0; i< ROWS; i++){
			for(int j = 0 ; j< COLUMNS; j++){
				if (counter >= firstDayOfMonth && counter <= lastDayOfMonth){
					calendarArray[i][j] = dayCount;
					dayCount++;
				}
				else
					calendarArray[i][j] = 0;
				counter++;
				}
			}
		}
	
	public int at(int n, int m){
		
		int dayOfMonth;
		try{
			dayOfMonth = calendarArray[n][m];
		}
		catch (Exception e){
			return 0;
		}
		return dayOfMonth;
	}
	
	public int[][] getCalendarArray(){
		return calendarArray;
	}
	
	@Override
	public String toString(){
		
		String monYrTxt = DateAD.MONTH_NAMES[startDay.getMonth() - 1] + "  " + startDay.getYear();
		
		String weekDayHeader = "";
		
		String monthString = "";
		
		String[] weekArray = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
		
		for (String day : weekArray){
			//weekDayHeader += String.format(FORMAT, day);
			weekDayHeader += day + "  " ;
		}
		
		
		for( int[] week : getCalendarArray()){
			for (int day : week){
				String days = " ";
				if (day == 0 ){
					//days = String.format(FORMAT,"");
					days += " ";
				}
				else{
					//days = String.format(FORMAT,String.valueOf(day)) ;
					days += day;
				}
				
				if(day < 10){
					days += "  ";
				}
				else{
					days += " ";
				}
				monthString += days;
			}
			monthString += "\n";
		}
		
		String calendarText = String.format("%s%n%n %s%n %s%n",monYrTxt, weekDayHeader, monthString);
		
		return calendarText;
	}
	
}