package com.codizcdp.cutoffbpharmacy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class DataActivity extends AppCompatActivity {

    private SearchView svSearch;
    private TextView tvYear;
    private LinearLayout linearLayout;
    private CircularProgressIndicator pbLoader;
    private View view;
    private AdView mAdView;

    private FirebaseFirestore firebaseFirestore;

    private String year;

    private void initialize() {
        svSearch = findViewById(R.id.svSearch);
        tvYear = findViewById(R.id.tvYear);
        linearLayout = findViewById(R.id.llData);
        pbLoader = findViewById(R.id.pbLoader);

        firebaseFirestore = FirebaseFirestore.getInstance();

        mAdView = findViewById(R.id.adView);

        year = getIntent().getStringExtra("year");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initialize();

        setAds();

        if (year.equals("2021-22")){
            getData_2122();
        }

        tvYear.setText(year);

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                for (int i = 0; i < linearLayout.getChildCount(); i++) {

                    TextView tvInstituteCode = linearLayout.getChildAt(i).findViewById(R.id.tvInstituteCode);
                    TextView tvCollegeName = linearLayout.getChildAt(i).findViewById(R.id.tvCollegeName);
                    CardView cardView = linearLayout.getChildAt(i).findViewById(R.id.cvCardDesign);

                    if (!(tvInstituteCode.getText().toString().toLowerCase().trim().contains(query.toLowerCase())
                            || tvCollegeName.getText().toString().toLowerCase().trim().contains(query.toLowerCase()))) {

                        cardView.setVisibility(View.GONE);
                    } else {
                        cardView.setVisibility(View.VISIBLE);
                    }

                }

                return false;
            }
        });
    }

    private void createCard_2_Cap(String instituteCode, String collegeName, String cap1, String cap2) {
        view = getLayoutInflater().inflate(R.layout.data_card_design_layout, null, false);

        TextView tvInstituteCode = view.findViewById(R.id.tvInstituteCode);
        TextView tvCollegeName = view.findViewById(R.id.tvCollegeName);

        tvInstituteCode.setText(instituteCode);
        tvCollegeName.setText(collegeName);

        Button btnCap1 = view.findViewById(R.id.btnCap1);
        Button btnCap2 = view.findViewById(R.id.btnCap2);

        btnCap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cap1.equals("")) {
                    Intent intent = new Intent(DataActivity.this, PDFActivity.class);
                    intent.putExtra("link", cap1);
                    startActivity(intent);
                } else {
                    Toast.makeText(DataActivity.this, "File not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cap2.equals("")) {
                    Intent intent = new Intent(DataActivity.this, PDFActivity.class);
                    intent.putExtra("link", cap2);
                    startActivity(intent);
                } else {
                    Toast.makeText(DataActivity.this, "File not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        linearLayout.addView(view);
    }

    private void getData_2122() {

        if (MainActivity.value_2122_1 == null) { //1
            firebaseFirestore.collection("2021-22_1")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            MainActivity.value_2122_1 = value;

                            for (DocumentChange documentChange : MainActivity.value_2122_1.getDocumentChanges()) {
                                String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                            }
                        }
                    });
        } else {
            for (DocumentChange documentChange : MainActivity.value_2122_1.getDocumentChanges()) {
                String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
            }
        } //1

        new Handler().postDelayed(new Runnable() { //2
            public void run() {
                if (MainActivity.value_2122_2 == null) {
                    firebaseFirestore.collection("2021-22_2")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_2 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_2.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_2.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 1000); //2

        new Handler().postDelayed(new Runnable() { //3
            public void run() {
                if (MainActivity.value_2122_3 == null) {
                    firebaseFirestore.collection("2021-22_3")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_3 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_3.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_3.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 1500); //3

        new Handler().postDelayed(new Runnable() { //4
            public void run() {
                if (MainActivity.value_2122_4 == null) {
                    firebaseFirestore.collection("2021-22_4")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_4 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_4.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_4.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 2000); //4

        new Handler().postDelayed(new Runnable() { //5
            public void run() {
                if (MainActivity.value_2122_5 == null) {
                    firebaseFirestore.collection("2021-22_5")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_5 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_5.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_5.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 2500); //5

        new Handler().postDelayed(new Runnable() { //6
            public void run() {
                if (MainActivity.value_2122_6 == null) {
                    firebaseFirestore.collection("2021-22_6")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_6 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_6.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_6.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 3000); //6

        new Handler().postDelayed(new Runnable() { //7
            public void run() {
                if (MainActivity.value_2122_7 == null) {
                    firebaseFirestore.collection("2021-22_7")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_7 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_7.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_7.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 3500); //7

        new Handler().postDelayed(new Runnable() { //8
            public void run() {
                if (MainActivity.value_2122_8 == null) {
                    firebaseFirestore.collection("2021-22_8")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    MainActivity.value_2122_8 = value;

                                    for (DocumentChange documentChange : MainActivity.value_2122_8.getDocumentChanges()) {
                                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                                    }
                                }
                            });
                } else {
                    for (DocumentChange documentChange : MainActivity.value_2122_8.getDocumentChanges()) {
                        String cap1 = documentChange.getDocument().getData().get("cap1").toString();
                        String cap2 = documentChange.getDocument().getData().get("cap2").toString();
                        String collegeName = documentChange.getDocument().getData().get("collegeName").toString();
                        String instituteCode = documentChange.getDocument().getData().get("instituteCode").toString();

                        createCard_2_Cap(instituteCode, collegeName, cap1, cap2);
                    }
                }
            }
        }, 4000); //8

        pbLoader.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    private void setAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}