package com.example.mireaapp.Frgaments.Notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mireaapp.MainActivity;
import com.example.mireaapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteSubtitle, inputNoteText;
    private TextView textDateTime;
    private ImageView btnBack;
    private View viewSubtitleIndicator;

    private String selectedNoteColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        inputNoteTitle = findViewById(R.id.inputNoteTitle_notes);
        inputNoteSubtitle = findViewById(R.id.inputNoteSubTitle_notes);
        inputNoteText = findViewById(R.id.inputNote_notes);
        textDateTime = findViewById(R.id.textDateTime_notes);
        btnBack = findViewById(R.id.imgBack_notes);
        viewSubtitleIndicator = findViewById(R.id.viewSubtitleIndicator_notes);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                .format(new Date())
        );

        ImageView imageSave = findViewById(R.id.imageSave_notes);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        selectedNoteColor = "#000000";

        initMiscellaneous();
        setSubtitleIndicatorColor();
    }

    private void saveNote() {
        if (inputNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note title can't be empty!", Toast.LENGTH_SHORT).show();
        } else if (inputNoteSubtitle.getText().toString().trim().isEmpty()
        && inputNoteText.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Note can't be empty!", Toast.LENGTH_SHORT).show();
        } else {

            final Note note = new Note();
            note.setTitle(inputNoteTitle.getText().toString());
            note.setSubTitle(inputNoteSubtitle.getText().toString());
            note.setNoteText(inputNoteText.getText().toString());
            note.setDateTime(textDateTime.getText().toString());

            @SuppressLint("StaticFieldLeak")
            class SaveNoteTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    NotesDatabase.getInstance(getApplicationContext()).noteDao().insertNote(note);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
            new SaveNoteTask().execute();
        }
    }

    private void initMiscellaneous() {
        final LinearLayout layoutMisc = findViewById(R.id.layoutMiscellaneous_note);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutMisc);
        layoutMisc.findViewById(R.id.imageMisc_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final ImageView imageColor1 = layoutMisc.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutMisc.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutMisc.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = layoutMisc.findViewById(R.id.imageColor4);
        final ImageView imageColor5 = layoutMisc.findViewById(R.id.imageColor5);
        final ImageView imageColor6 = layoutMisc.findViewById(R.id.imageColor6);

        layoutMisc.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#000000";
                imageColor1.setImageResource(R.drawable.ic_baseline_done_24);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });
        layoutMisc.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#5E35B1";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.ic_baseline_done_24);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });
        layoutMisc.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#1E88E5";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.ic_baseline_done_24);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });
        layoutMisc.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#43A047";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(R.drawable.ic_baseline_done_24);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });
        layoutMisc.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#FFB300";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(R.drawable.ic_baseline_done_24);
                imageColor6.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });
        layoutMisc.findViewById(R.id.viewColor6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#F4511E";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                imageColor6.setImageResource(R.drawable.ic_baseline_done_24);
                setSubtitleIndicatorColor();
            }
        });
    }

    private void setSubtitleIndicatorColor() {
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNoteColor));
    }
}