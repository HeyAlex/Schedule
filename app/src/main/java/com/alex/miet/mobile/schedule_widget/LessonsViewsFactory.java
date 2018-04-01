package com.alex.miet.mobile.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.alex.miet.mobile.data.lessons.LessonsRepository;
import com.alex.miet.mobile.util.VectorUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.alex.miet.mobile.entities.LessonItem;
import com.alex.miet.mobile.R;
import com.alex.miet.mobile.ScheduleApp;

/**
 * A factory for list view in widget
 */
public class LessonsViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    @Inject
    LessonsRepository lessonsRepository;
    private List<LessonItem> lessons = new ArrayList<>();
    private Context context;
    private String group;
    private int widgetId;
    private int week;
    private int day;

    public LessonsViewsFactory(Context context, Intent adapter) {
        this.group = adapter.getStringExtra("group");
        this.day = adapter.getIntExtra("day", 0);
        this.week = adapter.getIntExtra("week", 0);
        this.widgetId = adapter.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        this.context = context;
    }

    @Override
    public void onCreate() {
        ScheduleApp.get(context)
                .getApplicationComponent()
                .inject(this);

        this.lessons = lessonsRepository.getLessonsByWeekAndDay(group, week, day).blockingGet();
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return (lessons.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final LessonItem lesson = lessons.get(position);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),
                R.layout.widget_lesson_item);
        remoteView.setTextViewText(R.id.itogname_schedule, lesson.getDisciplineName());
        remoteView.setTextViewText(R.id.room_schedule, lesson.getRoom());
        remoteView.setTextViewText(R.id.prep_schedule, lesson.getTeacher());
        remoteView.setTextViewText(R.id.timeTo_schedule, lesson.getTimeTo());
        remoteView.setTextViewText(R.id.timeFrom_schedule, lesson.getTimeFrom());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            remoteView.setImageViewResource(R.id.room_icon, R.drawable.login);
            remoteView.setImageViewResource(R.id.teacher_icon,
                    R.drawable.ic_perm_identity_black_48dp);
        } else {
            remoteView.setImageViewBitmap(R.id.room_icon, VectorUtil
                    .vectorToBitmap(context, R.drawable.login));
            remoteView.setImageViewBitmap(R.id.teacher_icon, VectorUtil
                    .vectorToBitmap(context, R.drawable.ic_perm_identity_black_48dp));
        }
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(context.getPackageName(), R.layout.widget_loading);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
