package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "로고", "이번트 페이지", "인쇄", "모바인 디자인"};

    public CategoryPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("디자인");
        pages[1] = new CategoryListPage2Fragment("디자인", title[1]);
        pages[2] = new CategoryListPage3Fragment("디자인", title[2]);
        pages[3] = new CategoryListPage4Fragment("디자인", title[3]);
        pages[4] = new CategoryListPage5Fragment("디자인", title[4]);
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
