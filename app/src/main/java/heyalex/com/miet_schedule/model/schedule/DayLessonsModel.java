package heyalex.com.miet_schedule.model.schedule;

import java.util.ArrayList;
import java.util.List;

import heyalex.com.miet_schedule.LessonModel;

/**
 * One day of student's life
 * Contains all lessons that will be in current day
 * Used in {@link heyalex.com.miet_schedule.schedule_builder.ScheduleBuilder}
 */

public class DayLessonsModel implements Cloneable {

    private String Day;
    private int DayNumber;
    private List<LessonModel> lessons = new ArrayList<>();

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public int getDayNumber() {
        return DayNumber;
    }

    public void setDayNumber(int dayNumber) {
        DayNumber = dayNumber;
    }

    public List<LessonModel> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonModel> lessons) {
        this.lessons = lessons;
    }

    public DayLessonsModel clone() throws CloneNotSupportedException {
        return (DayLessonsModel) super.clone();
    }
}
