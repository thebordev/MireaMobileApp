package com.example.mireaapp.Frgaments.Notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mireaapp.MainActivity;
import com.example.mireaapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteSubtitle, inputNoteText;
    private TextView textDateTime;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        inputNoteTitle = findViewById(R.id.inputNoteTitle_notes);
        inputNoteSubtitle = findViewById(R.id.inputNoteSubTitle_notes);
        inputNoteText = findViewById(R.id.inputNote_notes);
        textDateTime = findViewById(R.id.textDateTime_notes);
        btnBack = findViewById(R.id.imgBack_notes);

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
}