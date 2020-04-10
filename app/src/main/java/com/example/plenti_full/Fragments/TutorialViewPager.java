package com.example.plenti_full.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plenti_full.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialViewPager extends Fragment {


    public TutorialViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tutorial_view_pager, container, false);
        final ViewPager viewPager = view.findViewById(R.id.tutViewPager);
        final TutorialViewPagerAdapter adapter = new TutorialViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, new CubeOutDepthTransformation());





        return view;
    }






    class TutorialViewPagerAdapter extends FragmentPagerAdapter {

        public TutorialViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        int realSize = 5;

        @Override
        public Fragment getItem(int position) {
            position = position % realSize;
            switch (position){
                case 0:
                    return TutorialLayoutFragment.newInstance(R.drawable.tutscreenone);
                case 1:
                    return TutorialLayoutFragment.newInstance(R.drawable.tutscreentwo);
                case 2:
                    return TutorialLayoutFragment.newInstance(R.drawable.tutscreenthreefinal);
                case 3:
                    return TutorialLayoutFragment.newInstance(R.drawable.roulettetutorial);
                default:
                    return TutorialLayoutFragment.newInstance(R.drawable.tutscreenone);
            }
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
    }

    public class CubeOutDepthTransformation implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {

            if (position < -1){    // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);

            }
            else if (position <= 0) {    // [-1,0]
                page.setAlpha(1);
                page.setPivotX(page.getWidth());
                page.setRotationY(-90 * Math.abs(position));

            }
            else if (position <= 1){    // (0,1]
                page.setAlpha(1);
                page.setPivotX(0);
                page.setRotationY(90 * Math.abs(position));

            }
            else {    // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);

            }



            if (Math.abs(position) <= 0.5){
                page.setScaleY(Math.max(0.4f,1-Math.abs(position)));
            }
            else if (Math.abs(position) <= 1){
                page.setScaleY(Math.max(0.4f,1-Math.abs(position)));
            }


        }
    }
}
