package com.example.fruit_tourney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;


public class TournoiActivity extends AppCompatActivity {
    private ArrayList<StorageReference> allReferences;
    private ArrayList<StorageReference> selected;
    private int compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournoi);
        this.allReferences = new ArrayList<StorageReference>();
        this.selected = new ArrayList<StorageReference>();
        compteur = 0;

        TextView round = this.findViewById(R.id.round);
        if(getIntent().getIntExtra("tailleTournoi", 8) == 16) {
            round.setText("Round 1 - Huitième de finale");
        }

        initialize();


    }

    public void initialize() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference image : listResult.getItems()) {
                    allReferences.add(image);
                }

                remplirSelected(allReferences);
                loadImage();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("listeAll failed !");
            }
        });
    }

    public void loadImage() {
        ImageView image1 = this.findViewById(R.id.fruit1);
        GlideApp.with(this)
                .load(selected.get(0))
                .into(image1);
        ImageView image2 = this.findViewById(R.id.fruit2);
        GlideApp.with(this)
                .load(selected.get(1))
                .into(image2);
    }

    public void remplirSelected(ArrayList<StorageReference> refs) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<25; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        if(getIntent().getIntExtra("tailleTournoi", 8) == 8) {
            for (int i=0; i<8; i++) {
                selected.add(refs.get(list.get(i)));
            }
        }
        else {
            for (int i=0; i<16; i++) {
                selected.add(refs.get(list.get(i)));
            }
        }

    }

    public void onClickImage(View v) {
        TextView round = this.findViewById(R.id.round);
        compteur++;
        if(getIntent().getIntExtra("tailleTournoi", 8) == 8) {
            switch (compteur){
                case 1:
                    round.setText("Round 2 - Quart de finale");
                    break;
                case 2:
                    round.setText("Round 3 - Quart de finale");
                    break;
                case 3:
                    round.setText("Round 4 - Quart de finale");
                    break;
                case 4:
                    round.setText("Round 1 - Demi-finale");
                    break;
                case 5:
                    round.setText("Round 2 - Demi-finale");
                    break;
                case 6:
                    round.setText("Finale");
                    break;
                case 7:
                    round.setText("Vainqueur");
                    break;
            }
            if(compteur < 7){
                switch(v.getId()) {
                    case R.id.fruit1:
                        selected.add(selected.get(0));
                        selected.remove(0);
                        selected.remove(0);
                        loadImage();
                        break;
                    case R.id.fruit2:
                        selected.add(selected.get(1));
                        selected.remove(0);
                        selected.remove(0);
                        loadImage();
                        break;
                }
            }
            else {
                ImageView imageFinale = this.findViewById(R.id.vs);
                ImageView image1 = this.findViewById(R.id.fruit1);
                ImageView image2 = this.findViewById(R.id.fruit2);
                Button bouton = this.findViewById(R.id.boutonRetour);
                bouton.setVisibility(View.VISIBLE);
                image1.setVisibility(View.INVISIBLE);
                image2.setVisibility(View.INVISIBLE);
                imageFinale.getLayoutParams().height = 700;
                imageFinale.getLayoutParams().width = 700;
                imageFinale.requestLayout();
                if(v.getId() == R.id.fruit1) {
                    GlideApp.with(this)
                            .load(selected.get(0))
                            .into(imageFinale);
                }
                else {
                    GlideApp.with(this)
                            .load(selected.get(1))
                            .into(imageFinale);
                }
            }
        }
        else {
            switch (compteur){
                case 1:
                    round.setText("Round 2 - Huitième de finale");
                    break;
                case 2:
                    round.setText("Round 3 - Huitième de finale");
                    break;
                case 3:
                    round.setText("Round 4 - Huitième de finale");
                    break;
                case 4:
                    round.setText("Round 5 - Huitième de finale");
                    break;
                case 5:
                    round.setText("Round 6 - Huitième de finale");
                    break;
                case 6:
                    round.setText("Round 7 - Huitième de finale");
                    break;
                case 7:
                    round.setText("Round 8 - Huitième de finale");
                    break;
                case 8:
                    round.setText("Round 1 - Quart de finale");
                    break;
                case 9:
                    round.setText("Round 2 - Quart de finale");
                    break;
                case 10:
                    round.setText("Round 3 - Quart de finale");
                    break;
                case 11:
                    round.setText("Round 4 - Quart de finale");
                    break;
                case 12:
                    round.setText("Round 1 - Demi-finale");
                    break;
                case 13:
                    round.setText("Round 2 - Demi-finale");
                    break;
                case 14:
                    round.setText("Finale");
                    break;
            }
            if(compteur < 15){
                switch(v.getId()) {
                    case R.id.fruit1:
                        selected.add(selected.get(0));
                        selected.remove(0);
                        selected.remove(0);
                        loadImage();
                        break;
                    case R.id.fruit2:
                        selected.add(selected.get(1));
                        selected.remove(0);
                        selected.remove(0);
                        loadImage();
                        break;
                }
            }
            else {
                ImageView imageFinale = this.findViewById(R.id.vs);
                ImageView image1 = this.findViewById(R.id.fruit1);
                ImageView image2 = this.findViewById(R.id.fruit2);
                Button bouton = this.findViewById(R.id.boutonRetour);
                bouton.setVisibility(View.VISIBLE);
                image1.setVisibility(View.INVISIBLE);
                image2.setVisibility(View.INVISIBLE);
                imageFinale.getLayoutParams().height = 700;
                imageFinale.getLayoutParams().width = 700;
                imageFinale.requestLayout();
                if(v.getId() == R.id.fruit1) {
                    GlideApp.with(this)
                            .load(selected.get(0))
                            .into(imageFinale);
                }
                else {
                    GlideApp.with(this)
                            .load(selected.get(1))
                            .into(imageFinale);
                }
            }
        }

    }

    public void onClickBoutonRetour(View v) {
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
