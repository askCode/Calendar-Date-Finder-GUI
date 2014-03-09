/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

 
/**
 *
 * @author
 */
import java.util.*;
 
public class DateAD
{     
        //constants
        public static final short LOWEST_YEAR = 1760;
        public static final short MONTHS_IN_YR = 12;
        public static final short START_DAY_OF_WEEK = 2;
        public static final short DAYS_IN_WEEK = 7;
        public static final short DAYS_IN_LEAPYEAR = 366;
        public static final short DAYS_IN_YEAR = 365;
        public static final int FIRST_DAY_OF_YEAR = 1;
        public static final int FIRST_DAY_OF_WEEK = 0;
        public static final int LAST_DAY_OF_WEEK = 6;
        public static final String MONTHS [] = {"January", "February",
            "March", "April", "May", "June", "July", "August","September",
            "October", "November", "December"};
       
        public static final String DAYINWEEK [] = {"Sunday", "Monday",
            "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        
        //declare variables
        private short year;
        private short month;
        private short dayOfMonth;
        private short dayOfYear;
        private short dayOfWeek;
       
        //constructor default
        public DateAD()
        {
            setCurrentDate();
            
        }
       
        //constructor with 1 argument
        public DateAD(short day)
        {	
            setCurrentDate();
            setDayOfMonth(day);
        }
       
        //constructor with 2 argument
        public DateAD(short day, short month)
        {
            setCurrentDate();
            setMonth(month);
            setDayOfMonth(day);
            
        }
       
        //constructor with 3 argument
        public DateAD(short dayOfMonth, short month, short year)
        {
            setCurrentDate();
            setMonth(month);
            setYear(year);
            setDayOfMonth(dayOfMonth);
            
        }
       
        public static boolean isLeapYear(short year)
        {
            if(year % 400 == 0)
            {
                return true;
            }
            else if((year % 4 == 0) && (year % 100 != 0))
                return true;
            else
            {
                return false;
            }  
        }
       
        public static short daysInMonth(short month, short year)
        {
        switch(month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return (short) ((isLeapYear(year)) ? 29 : 28);
        }
        return 0;
        }
 
        private static short countLeaps(short year)
        {
            short numberOfLeapYears = 0;
            short i;
            for (i = LOWEST_YEAR; i <= year ; i++)
        {
            if (isLeapYear(i))
                numberOfLeapYears++;
        }
 
        return numberOfLeapYears;
        }
       
        public DateAD getYesterday()
    {
        //DateAD yesterday = new DateAD();
        //DateAD yesterday;
        short month;
        short dayOfMonth;
        short year;
        
        if (this.dayOfYear == FIRST_DAY_OF_YEAR) {
            month = 12;
            year = (short) (this.year -1);
            dayOfMonth = DateAD.daysInMonth(month, year);         
        } 
        else if (this.dayOfMonth == 1) {
            month = (short) (this.month - 1);
            year = this.year;
            dayOfMonth = daysInMonth(month, 
                    year);
        } 
        else {
            dayOfMonth = (short) (this.dayOfMonth - 1);
            year = this.year;
            month = this.month;
        }
        return new DateAD(dayOfMonth, month, year);
    }
       
        public DateAD getTomorrow()
       {
        //DateAD tomorrow = new DateAD();
        short tomorrowDayOfMonth = (short) (dayOfMonth + 1);
        short tomorrowDayOfYear = (short) (dayOfYear + 1);
        short tomorrowMonth = month;
        short tomorrowYear = year;
        if(isLeapYear(tomorrowYear))
        {
            if (tomorrowDayOfYear > DAYS_IN_LEAPYEAR)
            {
                tomorrowYear++;
                tomorrowMonth = 1;
                tomorrowDayOfMonth = 1;
                return new DateAD(tomorrowDayOfMonth,tomorrowMonth,tomorrowYear);
            }
            else if (tomorrowDayOfMonth > daysInMonth(tomorrowMonth,
                    tomorrowYear))
            {
                tomorrowMonth++;
                tomorrowDayOfMonth = 1;
                return new DateAD(tomorrowDayOfMonth, tomorrowMonth, tomorrowYear);
            }
        }
        else
        {
            if (tomorrowDayOfYear > DAYS_IN_YEAR)
            {
                tomorrowYear++;
                tomorrowMonth = 1;
                tomorrowDayOfMonth = 1;
                return new DateAD(tomorrowDayOfMonth, tomorrowMonth, tomorrowYear);
            }
            else if (tomorrowDayOfMonth > daysInMonth(tomorrowMonth,
                    tomorrowYear))
            {
                tomorrowMonth++;
                tomorrowDayOfMonth = 1;
                return new DateAD(tomorrowDayOfMonth, tomorrowMonth, tomorrowYear);
            }
        }
        return new DateAD(tomorrowDayOfMonth, tomorrowMonth, tomorrowYear);
    }
       
        public boolean lessThan(DateAD Date)
        {
            if(year < Date.getYear())
            {
                return true;
            }

            else if(year == Date.getYear() && dayOfYear < Date.getDayOfYear())
            {
                return true;
            }
        return false;
        }

        public boolean equals(DateAD date)
        {
            if(year == date.getYear() && dayOfYear == date.getDayOfYear())
            {
                return true;
            }    
            return false;
        }
       
        @Override
		public String toString()
        {
            return  DAYINWEEK[(isLeapYear(this.year)) ? 
            										this.getDayOfWeek() : 
            											((this.getDayOfWeek() + 1) > 6) ? 
            													0 : 
            														(this.getDayOfWeek() + 1) ] 
            		+ ", " 
            		+ this.dayOfMonth 
            		+ " " 
                    + MONTHS[this.month - 1] 
                    + ", " + this.year;             
        }
 
      //Check if the input date is valid
    	public boolean isValid(){
    		if ((this.getMonth() > 12 || this.getMonth() < 1 ))
    			return false;
    		else if	(this.getDayOfMonth() < 1  || this.getDayOfMonth() > DateAD.daysInMonth(this.getMonth(), this.getYear()))
    			return false;
    		if (this.getYear() < 1760)
    			return false;
    		return true;
    	}
    	
        public void setCurrentDate()
        {
            GregorianCalendar cal = new GregorianCalendar();
            month = (short) (cal.get(Calendar.MONTH) + 1);
            year = (short)cal.get(Calendar.YEAR);
            dayOfMonth = (short)cal.get(Calendar.DAY_OF_MONTH);
            // add code to properly set dayOfYear and dayOfWeek
            
           setDayOfYear();
           setDayOfWeek();
        }
               
        
        private void setDayOfYear()
        {
            short numberDay = 0;
            for(short i = 1; i < this.getMonth(); i++)
            {
                numberDay = (short) (numberDay + DateAD.daysInMonth(i, year));
            }
            numberDay = (short) (numberDay + this.getDayOfMonth());
            this.dayOfYear = numberDay;
        }
               
        private void setDayOfWeek() 
        { 
            short sumOfDay = 0; 
            for (short i = LOWEST_YEAR; i < this.getYear(); i++) 
            { 
                sumOfDay++; 
            } 
            sumOfDay = (short) (sumOfDay + countLeaps(this.getYear()));
            this.dayOfWeek = (short) ((sumOfDay + this.getDayOfYear()) % DAYS_IN_WEEK);
        }
        
        //setters
        private void setYear(short yr)
        {
            year = yr;
        }
       
        private void setMonth(short mon)
        {
            month = mon; 
        }
       
        private void setDayOfMonth(short dom)
        {
        	this.dayOfMonth = dom;
        	if(this.isValid()){
        		setDayOfYear();
                setDayOfWeek();
        	} 
        	else{
        		setCurrentDate();
        	}   
        }
       
       
       
        //getters
        public short getYear()
        {
            return year;
        }
       
        public short getMonth()
        {
            return month;
        }
       
        public short getDayOfMonth()
        {
            return dayOfMonth;
        }
       
        public short getDayOfYear()
        {
            return dayOfYear;
        }
       
        public short getDayOfWeek()
        {
            return dayOfWeek;
        }
}