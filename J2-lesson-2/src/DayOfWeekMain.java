import jdk.jfr.DataAmount;

public class DayOfWeekMain {
    public static void main(final String[] args) {
        System.out.println("Осталось отработать: "+getWorkingHours(DayOfWeek.WEDNESDAY)+"ч.\n");

        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println("Сегодня: " + day);
            System.out.println("Осталось отработать: " + getWorkingHours(day) + "ч.");
            System.out.println("--------------------------------");
        }
    }

    public static int getWorkingHours(DayOfWeek currentDay) {
        int res = 0;
        for (int i = DayOfWeek.valueOf(currentDay.name()).ordinal(); i < DayOfWeek.values().length; i++) {
            if (DayOfWeek.values()[i].isWorkDay()) {
                res += 8;
            }
        }
        return res;
    }

}


enum DayOfWeek {

    MONDAY(true), TUESDAY(true), WEDNESDAY(true), THURSDAY(true), FRIDAY(true), SATURDAY(false), SUNDAY(false);

    private boolean isWorkDay;

    DayOfWeek(boolean isWorkDay) {
        this.isWorkDay = isWorkDay;
    }

    public boolean isWorkDay() {
        return isWorkDay;
    }
}