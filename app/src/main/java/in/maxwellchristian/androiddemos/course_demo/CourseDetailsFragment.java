package in.maxwellchristian.androiddemos.course_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import in.maxwellchristian.androiddemos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailsFragment extends Fragment implements CourseSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDetailsFragment newInstance(String param1, String param2) {
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_details, container, false);
    }

    @Override
    public void setSelectedCourse(Course course) {
        //Request the Course object from the data provider for the given index

        //Obtain the View which represents this Fragment. This will be the
        //view originally returned by the onCreateView lifecycle method.
        View detailsView = this.getView();

        //Obtain references to all of the detail TextViews
        TextView name = (TextView)detailsView.findViewById(R.id.tvName);
        TextView code = (TextView)detailsView.findViewById(R.id.tvCode);
        TextView credits = (TextView)detailsView.findViewById(R.id.tvCredits);
        TextView hours = (TextView)detailsView.findViewById(R.id.tvHours);
        TextView description = (TextView)detailsView.findViewById(R.id.tvDescription);

        //Set the text of each TextView to match the selected Course
        name.setText(course.name);
        code.setText(course.code);
        description.setText(course.description);

//        credits.setText(Integer.toString(course.credits));
//        hours.setText(Integer.toString(course.hours));

        credits.setText(String.format(Locale.getDefault(), "%s", course.credits));
        hours.setText(String.format(Locale.getDefault(), "%s", course.hours));

    }
}