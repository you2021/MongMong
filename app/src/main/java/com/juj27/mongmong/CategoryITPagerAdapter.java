package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryITPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "워드프레스", "웹사이트 개발", "모바일 앱", "게임"};

    public CategoryITPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("IT 프로그래밍");
        pages[1] = new CategoryListPage2Fragment("IT 프로그래밍", title[1]);
        pages[2] = new CategoryListPage3Fragment("IT 프로그래밍", title[2]);
        pages[3] = new CategoryListPage4Fragment("IT 프로그래밍", title[3]);
        pages[4] = new CategoryListPage5Fragment("IT 프로그래밍", title[4]);
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
