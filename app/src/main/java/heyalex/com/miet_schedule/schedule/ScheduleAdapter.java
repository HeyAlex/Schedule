package heyalex.com.miet_schedule.schedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;

/**
 * Created by mac on 10.05.17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>  {

    private final List<DayLessonsModel> items = new ArrayList<DayLessonsModel>();
    private Context context;
    private OnLessonClicked onLessonClickedListener;

    public ScheduleAdapter(OnLessonClicked listener){
        this.onLessonClickedListener = listener;
    }

    public interface OnLessonClicked {
        void onLessonClickedListener(LessonModel lesson);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<DayLessonsModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setItem(DayLessonsModel item) {
        this.items.clear();
        this.items.add(item);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header)
        TextView dateTextView;

        @BindView(R.id.lessons_root)
        LinearLayout lessonsRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DayLessonsModel item){
            dateTextView.setText(item.getDay());
            int i = 0;
            lessonsRoot.removeAllViews();
            if (item.getLessons() != null) {
                for (LessonModel lesson:item.getLessons()) {
                    lessonsRoot.addView(new LessonViewHolder().bind(i, lesson));
                    i++;
                }
            }
        }

        public class LessonViewHolder {
            @BindView(R.id.timeFrom_schedule)
            TextView timeFrom;

            @BindView(R.id.timeTo_schedule)
            TextView timeTo;

            @BindView(R.id.room_schedule)
            TextView room;

            @BindView(R.id.itogname_schedule)
            TextView disciplineName;

            @BindView(R.id.prep_schedule)
            TextView teacher;

            private View bind(int i, final LessonModel lesson){
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.lesson_item, (ViewGroup)itemView, false);
                ButterKnife.bind(this, view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLessonClickedListener.onLessonClickedListener(lesson);
                    }
                });
                timeFrom.setText(lesson.getTimeFrom());
                timeTo.setText(lesson.getTimeTo());
                teacher.setText(lesson.getTeacher());
                disciplineName.setText(lesson.getDisciplineName());
                room.setText(lesson.getRoom());
                return view;
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.context = recyclerView.getContext();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
}