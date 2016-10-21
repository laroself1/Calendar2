import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class Day {

    static LocalDate dateToday = LocalDate.now();
    static int currentYear = dateToday.getYear();
    static int currentDay = dateToday.getDayOfYear();

    private int printValue;
    private int dayOfYearValue;
    private DayOfWeek dayOfWeek;

    public int getPrintValue() {

        return printValue;
    }
    public void setPrintValue(int printValue) {
        this.printValue = printValue;
    }

    public int getDayOfYearValue() {
        return dayOfYearValue;
    }
    public void setDayOfYearValue(int dayOfYearValue) {
        this.dayOfYearValue = dayOfYearValue;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isSunday(){
        boolean value=false;
        if(dayOfWeek.equals(DayOfWeek.SUNDAY)){
            value=true;
        }
        return  value;}

    public boolean isFirstDayOfMonth(){
        boolean value = false;
        if(printValue==1){
            value=true;}
        return value;
    }
    public void printFirstDayOfMonth(){

        String parameter="%"+dayOfWeek.getValue()*3+"d";
        int switchValue = 0;

        if(isToday()) {switchValue=2;}
        if(isWeekend()) {switchValue=3;}
        if(isToday() & isWeekend()){switchValue=1;}

        switch (switchValue){
            case 0:
                System.out.printf(parameter,printValue);
                break;

            case 1:
                printToday(parameter);
                break;

            case 2:
                printToday(parameter);
                break;

            case 3:
                printWeekend(parameter);
                break;
        }
    }

    public boolean isWeekend(){
        boolean value = false;
        if(dayOfWeek.equals(DayOfWeek.SATURDAY)||dayOfWeek.equals(DayOfWeek.SUNDAY)){
            value=true;
        }
        return value;
    }
    public void printWeekend(String parameter){
        System.out.print("\u001B[31m");
        System.out.printf(parameter,printValue);
        System.out.print("\u001B[0m");
        if(isSunday()){
            System.out.println();
        }
    }

    public boolean isToday(){
        boolean value = false;
        if(dayOfYearValue==currentDay){
            value=true;}
        return value;
    }
    public void printToday(String parameter) {
        System.out.print("\u001B[32m");
        System.out.printf(parameter,printValue);
        System.out.print("\u001B[0m");
        if(isSunday()){System.out.println();}
    }

    public static List<Day> createDays(Month month){

        List<Day> result= new ArrayList<>();

        LocalDate localDatetoPrint = LocalDate.of(currentYear, month,1);

        LocalDate lastDM = localDatetoPrint.with(lastDayOfMonth());

        int monthToPrintLength = lastDM.getDayOfMonth();

        for (int i=1; i<=monthToPrintLength;i++){

            Day day = new Day();

            day.setDayOfYearValue(localDatetoPrint.getDayOfYear());
            day.setDayOfWeek(localDatetoPrint.getDayOfWeek());
            day.setPrintValue(localDatetoPrint.getDayOfMonth());

            result.add(day);

            localDatetoPrint=localDatetoPrint.plusDays(1);
        }
        return result;
    }

}