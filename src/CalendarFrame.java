import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class CalendarFrame extends JFrame{
	
	private static final int X = 100, Y = 100, WIDTH = 400, HEIGHT = 400;
	
	private static final String FONT_NAME = "Courier New";
    private static final int FONT_STYLE = Font.PLAIN;
    private static final int FONT_SIZE = 18;
    private static final Font FONT = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);
    
    private static final int YR_LIMIT = 12;
    private short CURRENT_YR;
	
	private BasicCalendar cal;
    private short month;
    private short year;
    private short dayOfMonth;
    private DateAD date;
    
    static JTextField day, setDateTitle;
    static JLabel lblMon, lblYr;
    
    static JComboBox<String> cmbMon;
    static JComboBox<Short> cmbYr;
    static JTextArea txtArDate;
    static JTextArea calendarTxt;
    
    private JPanel setDatePanel;
    private JPanel calendarPanel;
    private JPanel datePanel;
    private JPanel calendarFrame;
    
    private listener Listener = new listener();

    //Constructor
    public CalendarFrame(){
    	//super("Calendar by Author");
    	//setLayout(new FlowLayout());
    	
    	//item1 = new JLabel("This is item1");
    	//item1.setToolTipText("On Hover - Tool Tip Text");
    	
    	cal = new BasicCalendar();
    	setDate(new DateAD());
    	
    	calendarFrame	= new JPanel();
    	calendarFrame.setLayout(new BorderLayout());
   
    	
    	//add panels
    	calendarFrame.add(createSetDatePanel(), BorderLayout.NORTH);
    	calendarFrame.add(createCalendarPanel(), BorderLayout.CENTER);
    	calendarFrame.add(createDatePanel(), BorderLayout.SOUTH);
    	
    	this.add(calendarFrame);
    	this.setBounds(X, Y, WIDTH, HEIGHT);
       	
    }
    
    public void setDate(DateAD date){
    	this.date = date;
    	month = date.getMonth();
    	year = date.getYear();
    	dayOfMonth = date.getDayOfMonth();
    }
    
    private JPanel createSetDatePanel(){
    	JPanel pnl = new JPanel();
    	BorderLayout borderLayout = new BorderLayout();
    	pnl.setLayout(borderLayout);
    	
    	//create controls
    	setDateTitle = new JTextField("Select a date:");
    	pnl.add(setDateTitle, BorderLayout.NORTH);
    	
    	
    	JPanel datePanel = new JPanel();
    	day = new JTextField(2);
    	day.setText("" + this.dayOfMonth);
    	day.setFont(FONT);
    	day.addActionListener(Listener);
    	
    	cmbMon = new JComboBox<String>();
    	for (String month : DateAD.MONTH_NAMES){
    		cmbMon.addItem(month);
    	}
    	cmbMon.setSelectedItem(DateAD.MONTH_NAMES[month-1]);
    	cmbMon.setFont(FONT);
    	//cmbMon.setEditable(true);
    	cmbMon.addActionListener(Listener);
    	
    	CURRENT_YR = this.year;
    	cmbYr = new JComboBox<Short>();
    	for (short i = (short) (CURRENT_YR - YR_LIMIT); i <= (short) (this.year + YR_LIMIT); i++ ){
    		cmbYr.addItem(i);
    	}
    	cmbYr.setSelectedItem(year);
    	cmbYr.setFont(FONT);
    	cmbYr.addActionListener(Listener);
    	
    	datePanel.add(day);
    	datePanel.add(cmbMon);
    	datePanel.add(cmbYr);
    	
    	pnl.add(datePanel, BorderLayout.SOUTH);
    	
    	return pnl;
    }
    
    
    private JPanel createCalendarPanel(){
    	JPanel pnl = new JPanel();
    	calendarTxt = new JTextArea();
    	calendarTxt.append(cal.toString());
    	calendarTxt.setFont(FONT);
    	
    	pnl.add(calendarTxt);
    	
    	return pnl;
    	
    }
    
    private JPanel createDatePanel(){
    	JPanel pnl = new JPanel();
    	txtArDate = new JTextArea();
    	txtArDate.setFont(FONT);
    	txtArDate.setText(date.toString());
    	
    	pnl.add(txtArDate);
    	
    	return pnl;
    	
    }
    
    private void updateDatePanel(){
    	txtArDate.setText(date.toString());
    }
    
    private void updateCalendarPanel(){
    	calendarTxt.setText(cal.toString());
    }
    
    private class listener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if (e.getSource() == cmbMon){
    			String mon = (String)cmbMon.getSelectedItem();
    			month = date.getMonthFromLiteral(mon);
    			
//    			System.out.println("Listener activated: Cmb mon changed");
//    			System.out.println("Month: " + month);
    			
    		}
    		else if(e.getSource() == cmbYr){
    			year = Short.parseShort(cmbYr.getSelectedItem().toString());
//    			System.out.println("Listener activated: Cmb yr changed!");
//    			System.out.println("Year: " + year);
    		}
    		else if(e.getSource() == day){
    			dayOfMonth = Short.parseShort(day.getText());
//    			System.out.println("Listener activated: day field changed!");
//    			System.out.println("dayOfMonth: " + dayOfMonth);
    		}
    		
    		refreshCalendar();
    	}
    }
    
    private void validate_date(short dayOfMonth){
    	
    	if (dayOfMonth > DateAD.daysInMonth(this.month, this.year) || dayOfMonth < 0){
    		CalendarFrame.day.setText(Short.toString(DateAD.daysInMonth(this.month, this.year)));
    		this.dayOfMonth = DateAD.daysInMonth(this.month, this.year);
    	}
    	
    }
    
    private void refreshCalendar(){
    	validate_date(dayOfMonth);
    	System.out.printf("%d %d %d%n", dayOfMonth, month, year);
    	setDate(new DateAD(dayOfMonth, month, year));
    	cal = new BasicCalendar(month, year);
    	
    	//createSetDatePanel();
    	updateCalendarPanel();
    	updateDatePanel();
    	
    	calendarFrame.repaint();
    	
    }
    
    public static void main (String [] args){
    	
    	CalendarFrame calendarGUI = new CalendarFrame();
    	calendarGUI.setVisible(true);
    	calendarGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    }
}
