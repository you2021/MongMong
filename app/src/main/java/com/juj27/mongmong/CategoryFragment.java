package com.juj27.mongmong;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CategoryFragment extends Fragment {

    ImageView ivDe, ivIt, ivPho, ivMa, ivTran, ivDocu, ivOr, ivLe, ivBook, ivJob, ivBu;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivDe = view.findViewById(R.id.de);
        ivIt = view.findViewById(R.id.it);
        ivPho = view.findViewById(R.id.pho);
        ivMa = view.findViewById(R.id.ma);
        ivTran = view.findViewById(R.id.tran);
        ivDocu = view.findViewById(R.id.docu);
        ivOr = view.findViewById(R.id.or);
        ivLe = view.findViewById(R.id.le);
        ivBook = view.findViewById(R.id.book);
        ivJob = view.findViewById(R.id.job);
        ivBu = view.findViewById(R.id.bu);

        ivDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListActivity.class);
                startActivity(intent);
            }
        });

        ivIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListITActivity.class);
                startActivity(intent);
            }
        });

        ivPho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListPictureActivity.class);
                startActivity(intent);
            }
        });

        ivMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListMarketingActivity.class);
                startActivity(intent);
            }
        });

        ivTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListTranslationActivity.class);
                startActivity(intent);
            }
        });

        ivDocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListDocumentActivity.class);
                startActivity(intent);
            }
        });

        ivOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListOrderActivity.class);
                startActivity(intent);
            }
        });

        ivLe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListLessonActivity.class);
                startActivity(intent);
            }
        });

        ivBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListBookActivity.class);
                startActivity(intent);
            }
        });

        ivJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListJobActivity.class);
                startActivity(intent);
            }
        });

        ivBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryListBusinessActivity.class);
                startActivity(intent);
            }
        });
    }
}
