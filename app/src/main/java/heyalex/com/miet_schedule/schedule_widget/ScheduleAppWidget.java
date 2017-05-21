package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import heyalex.com.miet_schedule.util.PrefUtils;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ScheduleAppWidgetConfigureActivity ScheduleAppWidgetConfigureActivity}
 */
public class ScheduleAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
       // RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.schedule_app_widget);
        // Instruct the widget manager to update the widget
       // appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int widgetId :appWidgetIds) {
            PrefUtils.removeFromPrefs(context,String.valueOf(widgetId));
        }

        // When the user deletes the widget, delete the preference associated with it.
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            if(intent.getAction() != null){
                if(intent.getAction().equals(ScheduleUpdateService.TOMORROW_ACTION)){

                }
            }
        }
    }
}
