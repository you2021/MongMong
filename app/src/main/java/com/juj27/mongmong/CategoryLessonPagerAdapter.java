package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryLessonPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "사진", "프로그래밍", "데이터분석", "외국어(영어)"};

    public CategoryLessonPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("레슨 실무교육");
        pages[1] = new CategoryListPage2Fragment("레슨 실무교육", title[1]);
        pages[2] = new CategoryListPage3Fragment("레슨 실무교육", title[2]);
        pages[3] = new CategoryListPage4Fragment("레슨 실무교육", title[3]);
        pages[4] = new CategoryListPage5Fragment("레슨 실무교육", title[4]);
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
