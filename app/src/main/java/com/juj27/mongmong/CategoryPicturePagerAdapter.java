package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryPicturePagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "영상촬영", "유부트 제작", "애니메이션", "3D"};

    public CategoryPicturePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("영상 사진 음량");
        pages[1] = new CategoryListPage2Fragment("영상 사진 음량", title[1]);
        pages[2] = new CategoryListPage3Fragment("영상 사진 음량", title[2]);
        pages[3] = new CategoryListPage4Fragment("영상 사진 음량", title[3]);
        pages[4] = new CategoryListPage5Fragment("영상 사진 음량", title[4]);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    public CharSequence getPageTitle(int position){
        return  title[position];
    }
}
