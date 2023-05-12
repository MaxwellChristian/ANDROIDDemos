package in.maxwellchristian.androiddemos.course_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import in.maxwellchristian.androiddemos.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Obtain the Fragment Manager
        FragmentManager manager = getSupportFragmentManager();

        // Obtain references to the two Fragments
        CourseListFragment listFragment = (CourseListFragment) manager.findFragmentById(R.id.listFragment);
        CourseDetailsFragment detailsFragment = (CourseDetailsFragment) manager.findFragmentById(R.id.detailsFragment);

        //Because CourseListFragment depends on CourseSelectedListener rather than directly on
        //CourseDetailsFragment, the details Fragment could be replaced without needing
        //to change code in the list Fragment
        assert listFragment != null;
        listFragment.setCourseSelectedListener(detailsFragment);

    }
}