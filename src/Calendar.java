import java.time.*;
import java.util.List;


public class Calendar {

    static LocalDate dateToday = LocalDate.now();

    public static void printCalendar(String monthToPrintStr){

        Month monthToPrint = dateToday.getMonth();

        if (!monthToPrintStr.isEmpty()) {
            monthToPrint = Month.valueOf(monthToPrintStr);
        }
        List<Day> printList = Day.createDays(monthToPrint);

        System.out.println(" Mo Tu We Th Fr" + "\u001B[31m" + " Sa Su" + "\u001B[0m");
        String defaultPrintParameter = "%3d";

        for (Day day:printList) {

            if(day.isFirstDayOfMonth()){
                day.printFirstDayOfMonth();
                continue;
            }
            if(day.isToday()){
                day.printToday(defaultPrintParameter);
                continue;
            }
            if (day.isWeekend()){
                day.printWeekend(defaultPrintParameter);
                continue;
            }
            System.out.printf(defaultPrintParameter,day.getPrintValue());


        }


    }

}