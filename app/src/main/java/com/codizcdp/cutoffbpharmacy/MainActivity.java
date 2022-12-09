package com.codizcdp.cutoffbpharmacy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private CardView cv2122, cv2223, cvImportantDates, cvTelegram;
    private FirebaseFirestore firebaseFirestore;
    static QuerySnapshot value_2122_1 = null, value_2122_2 = null, value_2122_3 = null, value_2122_4 = null, value_2122_5 = null, value_2122_6 = null, value_2122_7 = null, value_2122_8 = null, value_2122_9 = null;

    private void initialize() {
        cv2122 = findViewById(R.id.cv2122);
        cv2223 = findViewById(R.id.cv2223);
        cvImportantDates = findViewById(R.id.cvImportantDates);
        cvTelegram = findViewById(R.id.cvTelegram);

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        getData_2122();

        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });

        cv2122.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                intent.putExtra("year", "2021-22");
                startActivity(intent);
            }
        });

        cv2223.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        cvImportantDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ImportantDatesActivity.class));
            }
        });

        cvTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://t.me/+4jUgPXBMN7gwODJl"));
                startActivity(i);
            }
        });
    }

    private void getData_2122() {

        if (value_2122_1 == null) {
            firebaseFirestore.collection("2021-22_1")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_1 = value;
                        }
                    });
        }

        if (value_2122_2 == null) {
            firebaseFirestore.collection("2021-22_2")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_2 = value;
                        }
                    });
        }

        if (value_2122_3 == null) {
            firebaseFirestore.collection("2021-22_3")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_3 = value;
                        }
                    });
        }

        if (value_2122_4 == null) {
            firebaseFirestore.collection("2021-22_4")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_4 = value;
                        }
                    });
        }

        if (value_2122_5 == null) {
            firebaseFirestore.collection("2021-22_5")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_5 = value;
                        }
                    });
        }

        if (value_2122_6 == null) {
            firebaseFirestore.collection("2021-22_6")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_6 = value;
                        }
                    });
        }

        if (value_2122_7 == null) {
            firebaseFirestore.collection("2021-22_7")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_7 = value;
                        }
                    });
        }

        if (value_2122_8 == null) {
            firebaseFirestore.collection("2021-22_8")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_8 = value;
                        }
                    });
        }

        if (value_2122_9 == null) {
            firebaseFirestore.collection("2021-22_9")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            value_2122_9 = value;
                        }
                    });
        }

    }

}