package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.newsapp.showData.showTopHeadlines;
import static com.example.newsapp.showData.showTopHeadlinesSerbian;

public class BaseFragment extends Fragment implements View.OnClickListener {

    public String category;
    public int title;

    public BaseFragment() {

    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setTitle(int title){
        this.title = title;
    }

    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompat) getActivity()).getSupportActionBar().setTitle(this.title);

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initNews(v);

        bottomNavigationView = v.findViewById(R.id.bottom_navigation);
        HelpersFunctions hp = new HelpersFunctions(getActivity());
        hp.bottomNavigationFunction(bottomNavigationView);

        return v;
    }

    private void initNews(View v) {

        LayoutInflater inflater = (LayoutInflater) requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainScrollView = v.findViewById(R.id.mainScrollView);
        TextView labelJson = v.findViewById(R.id.labelJson);
        labelJson.setText(R.string.loading_news);

        LocaleHelper lh = new LocaleHelper(getActivity());
        String lang = lh.getLang();

        if(lang.equals("sr")){
            showTopHeadlinesSerbian(mainScrollView, labelJson, BaseFragment.this, inflater, this.category);
        }else{
            showTopHeadlines(mainScrollView, labelJson,BaseFragment.this, inflater, this.category);
        }

    }

    @Override
    public void onClick(View v) {

        HelpersFunctions hp = new HelpersFunctions(getActivity());
        hp.clickOnOneItem(v);
    }
}
