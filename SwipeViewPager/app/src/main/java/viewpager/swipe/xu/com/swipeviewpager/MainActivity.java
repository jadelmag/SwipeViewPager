package viewpager.swipe.xu.com.swipeviewpager;

import java.util.List;
import java.util.ArrayList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mSectionsPagerAdapter.addFragment(PlaceholderFragment.newInstance(4, getResources().getColor(R.color.android_blue)));
        mSectionsPagerAdapter.addFragment(PlaceholderFragment.newInstance(5, getResources().getColor(R.color.android_darkpurple)));
        mSectionsPagerAdapter.addFragment(PlaceholderFragment.newInstance(2, getResources().getColor(R.color.android_orange)));

        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        public void addFragment(Fragment newFragment) {
            this.fragments.add(newFragment);
        }
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String BACKGROUND_COLOR = "color";

        private int section_number;
        private int color;

        public static PlaceholderFragment newInstance(int sectionNumber, int color) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //agrego ademas el color de fondo
            args.putInt(BACKGROUND_COLOR, color);

            fragment.setArguments(args);
            //agrego para que no se pierda los valores de la instancia
            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //cuando crea una instancia de tipo PlaceholderFragment
            //si lo enviamos parametros, guarda esos
            //si no le envio nada, toma el color gris y un número aleatroio
            if(getArguments() != null){
                this.section_number = getArguments().getInt(ARG_SECTION_NUMBER);
                this.color = getArguments().getInt(BACKGROUND_COLOR);
            }
            else{
                this.color = Color.GRAY;
                this.section_number = (int)(Math.random() * 5);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            TextView textViewFragment = (TextView) rootView.findViewById(R.id.section_label);
            textViewFragment.setText("" + section_number);
            rootView.setBackgroundColor(color);

            return rootView;
        }
    }

}
