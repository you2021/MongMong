package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryDocumentPagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages = new Fragment[5];
    String[] title = new String[]{"전체", "기업 명", "제품 카피라이팅", "광고 마케라이팅", "마케팅 글작성"};

    public CategoryDocumentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0] = new CategoryListPage1Fragment();
        pages[1] = new CategoryListPage2Fragment();
        pages[2] = new CategoryListPage3Fragment();
        pages[3] = new CategoryListPage4Fragment();
        pages[4] = new CategoryListPage5Fragment();
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
