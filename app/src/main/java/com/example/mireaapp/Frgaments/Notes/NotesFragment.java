package com.example.mireaapp.Frgaments.Notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mireaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesFragment extends Fragment {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    public NotesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabCreateDeadline_deadline);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateNote.class);
                startActivity(intent);
            }
        });

        
        // Inflate the layout for this fragment
        return view;
    }

    private void getNotes() {

        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase
                        .getInstance(getContext())
                        .noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                Log.d("MY_NOTES", notes.toString());
            }
        }
        new GetNotesTask().execute();
    }
}