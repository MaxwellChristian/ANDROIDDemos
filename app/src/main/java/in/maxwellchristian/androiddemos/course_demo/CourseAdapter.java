package in.maxwellchristian.androiddemos.course_demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    private ArrayList<Course> courses;
    private LayoutInflater layoutInflater;

    private CourseSelectedListener courseSelectedListener;

    public CourseAdapter(Context context, ArrayList<Course> courses, CourseSelectedListener courseSelectedListener) {
        this.courses = courses;
        layoutInflater = LayoutInflater.from(context);
        this.courseSelectedListener = courseSelectedListener;
    }

    public void setCourseSelectedListener(CourseSelectedListener courseSelectedListener) {
        this.courseSelectedListener = courseSelectedListener;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.tvCourse.setText(course.code + " : " + course.name);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCourse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourse = itemView.findViewById(R.id.tvCourse);
            tvCourse.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           courseSelectedListener.setSelectedCourse(courses.get(getBindingAdapterPosition()));
            Log.d("Selected course", courses.get(getBindingAdapterPosition()).code);
        }
    }

}
