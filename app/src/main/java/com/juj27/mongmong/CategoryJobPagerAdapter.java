package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryJobPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "직무 멘토링", "자소서", "이력서(외국계)", "이력서(국내기업)"};

    public CategoryJobPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("취업 투잡");
        pages[1] = new CategoryListPage2Fragment("취업 투잡", title[1]);
        pages[2] = new CategoryListPage3Fragment("취업 투잡", title[2]);
        pages[3] = new CategoryListPage4Fragment("취업 투잡", title[3]);
        pages[4] = new CategoryListPage5Fragment("취업 투잡", title[4]);
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
