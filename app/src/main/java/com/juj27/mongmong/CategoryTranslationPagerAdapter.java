package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryTranslationPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "산업별 전문번역", "일반 번역", "통역", "영상번역"};

    public CategoryTranslationPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("번역 통역");
        pages[1] = new CategoryListPage2Fragment("번역 통역", title[1]);
        pages[2] = new CategoryListPage3Fragment("번역 통역", title[2]);
        pages[3] = new CategoryListPage4Fragment("번역 통역", title[3]);
        pages[4] = new CategoryListPage5Fragment("번역 통역", title[4]);
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
