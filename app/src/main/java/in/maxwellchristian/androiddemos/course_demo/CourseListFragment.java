package in.maxwellchristian.androiddemos.course_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseListFragment extends Fragment {

    private RecyclerView rvCourseList;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> courses;

    private CourseSelectedListener courseSelectedListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseListFragment() {
        // Required empty public constructor

        courses = new ArrayList<>();

        courses.add(new Course("ANDROID", "COSC195", 8, 3, "A short description of COSC195"));
        courses.add(new Course("System Project", "COSA195", 8, 3, "A short description of COSA195"));
        courses.add(new Course("Project Management", "CPMG195", 8, 3, "A short description of CPMG195"));
        courses.add(new Course("UX", "CWEB195", 8, 3, "A short description of CWEB195"));

    }

    public void setCourseSelectedListener(CourseSelectedListener courseSelectedListener) {
        this.courseSelectedListener = courseSelectedListener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseListFragment newInstance(String param1, String param2) {
        CourseListFragment fragment = new CourseListFragment();
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
        rvCourseList = (RecyclerView) inflater.inflate(R.layout.fragment_course_list, container, false);

        // set the appropriate layout
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        rvCourseList.setLayoutManager(llManager);

        // set the adapter
        courseAdapter = new CourseAdapter(getContext(), courses, courseSelectedListener);
        rvCourseList.setAdapter(courseAdapter);

        return rvCourseList;
    }
}