package com.example.android.tablesortdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button addButton;
    private Button sortScrip;
    private Button sortClose;
    private Button sortChg;
    private Button sortVol;
    private ScripListAdapter adapter;
    private String TAG = "newF";
    private Boolean isSortScripAsec = false;
    private Boolean isSortCloseAsec = false;
    private Boolean isSortChgAsec = false;
    private Boolean isSortVolAsec = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViewProperties();
    }


    private void initViews() {
        recyclerView = findViewById(R.id.recycleListView);
        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.addButton);
        sortScrip = findViewById(R.id.sortScrip);
        sortClose = findViewById(R.id.sortClose);
        sortChg = findViewById(R.id.sortChg);
        sortVol = findViewById(R.id.sortVol);
    }

    private void setViewProperties() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(editText.getText().toString());
                ArrayList<Scrip> data = generateRandomWords(number);
                updateView(data);
                removeArrows();
            }
        });

        sortScrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter != null && adapter.dataList != null && adapter.dataList.size() > 0) {
                    sortByScripName(isSortScripAsec);
                    int resId = (isSortScripAsec) ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down;
                    Drawable top = getResources().getDrawable(resId);
                    removeArrows();
                    sortScrip.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    isSortScripAsec = !isSortScripAsec;
                }else{
                    showErrorMessage(view);
                }
            }
        });

        sortClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter != null && adapter.dataList != null && adapter.dataList.size() > 0) {
                    sortByClose(isSortCloseAsec);
                    int resId = (isSortCloseAsec) ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down;
                    Drawable top = getResources().getDrawable(resId);
                    removeArrows();
                    sortClose.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    isSortCloseAsec = !isSortCloseAsec;
                }else{
                    showErrorMessage(view);
                }
            }
        });

        sortChg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter != null && adapter.dataList != null && adapter.dataList.size() > 0) {
                    sortByChange(isSortChgAsec);
                    int resId = (isSortChgAsec) ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down;
                    Drawable top = getResources().getDrawable(resId);
                    removeArrows();
                    sortChg.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    isSortChgAsec = !isSortChgAsec;
                }else{
                    showErrorMessage(view);
                }
            }
        });

        sortVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter != null && adapter.dataList != null && adapter.dataList.size() > 0) {
                    sortByVolume(isSortVolAsec);
                    int resId = (isSortVolAsec) ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down;
                    Drawable top = getResources().getDrawable(resId);
                    removeArrows();
                    sortVol.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                    isSortVolAsec = !isSortVolAsec;
                }else{
                    showErrorMessage(view);
                }
            }
        });
    }

    private void showErrorMessage(View view) {
        Snackbar snackbar = Snackbar
                .make(view, "List empty", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void removeArrows() {
        sortScrip.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        sortClose.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        sortChg.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        sortVol.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    private void sortByScripName(Boolean isReverse) {
        ArrayList<Scrip> dataList = adapter.getDataList();
        Collections.sort(dataList, new Comparator<Scrip>() {
            @Override
            public int compare(Scrip o1, Scrip o2) {
                return o1.getScrip().compareTo(o2.getScrip());
            }
        });
        if (isReverse) {
            Collections.reverse(dataList);
        }
        updateView(dataList);
    }

    private void sortByClose(Boolean isReverse) {
        ArrayList<Scrip> dataList = adapter.getDataList();
        Collections.sort(dataList, new Comparator<Scrip>() {
            @Override
            public int compare(Scrip o1, Scrip o2) {
                int a = o1.getClose();
                int b = o2.getClose();
                return a < b ? -1 : a > b ? 1 : 0;
            }
        });
        if (isReverse) {
            Collections.reverse(dataList);
        }
        updateView(dataList);
    }

    private void sortByChange(Boolean isReverse) {
        ArrayList<Scrip> dataList = adapter.getDataList();
        Collections.sort(dataList, new Comparator<Scrip>() {
            @Override
            public int compare(Scrip o1, Scrip o2) {
                int a = o1.getChange();
                int b = o2.getChange();
                return a < b ? -1 : a > b ? 1 : 0;
            }
        });
        if (isReverse) {
            Collections.reverse(dataList);
        }
        updateView(dataList);
    }

    private void sortByVolume(Boolean isReverse) {
        ArrayList<Scrip> dataList = adapter.getDataList();
        Collections.sort(dataList, new Comparator<Scrip>() {
            @Override
            public int compare(Scrip o1, Scrip o2) {
                int a = o1.getVolume();
                int b = o2.getVolume();
                return a < b ? -1 : a > b ? 1 : 0;
            }
        });
        if (isReverse) {
            Collections.reverse(dataList);
        }
        updateView(dataList);
    }

    private void updateView(ArrayList<Scrip> data) {
        adapter = new ScripListAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Scrip> generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        ArrayList<Scrip> dataList = new ArrayList<>();
        Random random = new Random();
        Log.d(TAG, "generateRandomWords: " + numberOfWords);
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(5) + 5]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }

        for (String scrip : randomStrings) {
            int chg = generateRandomNumber(-19, 20);
            int close = generateRandomNumber(100, 1000);
            int vol = generateRandomNumber(0, 100);
            Scrip scrips = new Scrip(scrip, close, chg, vol);
            dataList.add(scrips);
        }
        Log.d(TAG, "generateRandomWords: " + dataList.size());
        return dataList;
    }

    private int generateRandomNumber(int Low, int High) {
        Random r = new Random();
        return r.nextInt(High - Low) + Low;
    }
}
