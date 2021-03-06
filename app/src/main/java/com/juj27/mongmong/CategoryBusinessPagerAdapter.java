package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryBusinessPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "사업계획서", "창업컨설팅", "업종별 컨설팅", "크라우드펀딩"};

    public CategoryBusinessPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment("비즈니스컨설팅");
        pages[1] = new CategoryListPage2Fragment("비즈니스컨설팅", title[1]);
        pages[2] = new CategoryListPage3Fragment("비즈니스컨설팅", title[2]);
        pages[3] = new CategoryListPage4Fragment("비즈니스컨설팅", title[3]);
        pages[4] = new CategoryListPage5Fragment("비즈니스컨설팅", title[4]);
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
