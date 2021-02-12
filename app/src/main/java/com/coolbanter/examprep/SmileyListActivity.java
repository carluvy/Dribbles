package com.coolbanter.examprep;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.coolbanter.examprep.data.Smiley;
import com.coolbanter.examprep.paging.SmileyAdapter;
import com.coolbanter.examprep.paging.SmileyViewModel;
import com.coolbanter.examprep.paging.SmileyViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * Activity lists all Smileys in RecyclerView using Paging library.
 */
public class SmileyListActivity extends AppCompatActivity {

    private SmileyViewModel mViewModel;
    private RecyclerView mRecycler;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmileyViewModelFactory smileyViewModelFactory = SmileyViewModelFactory.createFactory(this);
        mViewModel = ViewModelProviders.of(this, smileyViewModelFactory).get(SmileyViewModel.class);

        EmojiCompat.init(new BundledEmojiCompatConfig(this));
        setContentView( R.layout.activity_smileys);

        mRecycler = findViewById(R.id.recycler_view);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        SmileyAdapter adapter = new SmileyAdapter(this);





        mRecycler.setAdapter(adapter);
        mViewModel.itemPagedList.observe(this, adapter::submitList);











        initAction();

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddSmileyActivity.class);
            startActivity(intent);
        });
    }

    public void initAction() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                        @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Smiley smiley = ((SmileyAdapter.SmileyViewHolder) viewHolder).getSmiley();
                mViewModel.delete(smiley);

                String text = getString(R.string.undo_deleted, smiley.getEmoji());
                Snackbar.make(mFab, text, Snackbar.LENGTH_LONG)
                        .setAction("Undo", view -> mViewModel.save(smiley)).show();



            }
        });

        itemTouchHelper.attachToRecyclerView(mRecycler);
    }

}
