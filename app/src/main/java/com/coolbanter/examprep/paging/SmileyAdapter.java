package com.coolbanter.examprep.paging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.emoji.widget.EmojiAppCompatTextView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.coolbanter.examprep.R;
import com.coolbanter.examprep.data.Smiley;
import com.google.android.material.textview.MaterialTextView;


public class SmileyAdapter extends PagedListAdapter<Smiley, SmileyAdapter.SmileyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    Smiley smiley;

    public SmileyAdapter(Context mContext) {
        super(DIFF_CALLBACK);

        context = mContext;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public SmileyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.smiley_child, parent, false);
        return new SmileyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SmileyViewHolder holder, int position) {
        Smiley smiley = getItem(position);
        holder.bindTo(smiley);


    }

    public static class SmileyViewHolder extends RecyclerView.ViewHolder {
        private final EmojiAppCompatTextView mEmoji;
        private final MaterialTextView mName;
        private final MaterialTextView mUnicode;
        private Smiley mSmiley;

        SmileyViewHolder(View itemView) {
            super(itemView);
            mEmoji = itemView.findViewById(R.id.emoji_char);
            mName = itemView.findViewById(R.id.emoji_name);
            mUnicode = itemView.findViewById(R.id.emoji_unicode);





        }


        public Smiley getSmiley() {

            return mSmiley;
        }

         void bindTo(Smiley smiley) {
            mSmiley = smiley;
            mEmoji.setText(smiley.getEmoji());
            mName.setText(smiley.getName());
            mUnicode.setText(smiley.getCode());
        }
    }

//    @Override
//    public int getItemCount() {
//        return mDiffer.getItemCount();
//    }
//    public void submitList(PagedList<User> pagedList) {
//        mDiffer.submitList(pagedList);
//    }
//


    private static final DiffUtil.ItemCallback<Smiley> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Smiley>() {
                @Override
                public boolean areItemsTheSame(@NonNull Smiley oldItem, @NonNull Smiley newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Smiley oldItem,
                                                  @NonNull Smiley newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };
}
