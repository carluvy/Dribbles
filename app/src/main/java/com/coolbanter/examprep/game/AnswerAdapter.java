package com.coolbanter.examprep.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.coolbanter.examprep.R;
import com.coolbanter.examprep.data.Smiley;

import java.util.List;


public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    List<Smiley> mSmiley;

    public  AnswerAdapter(List<Smiley> smilies, Context mContext) {
        this.context = mContext;
        this.mSmiley = smilies;
    }

//    protected AnswerAdapter(@NonNull DiffUtil.ItemCallback<Smiley> diffCallback) {
//
//        super(diffCallback);
//    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.answer_item, parent, false);
        return new AnswerViewHolder(itemView);

    }


    @Override
    public int getItemCount() {
        return mSmiley.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        Smiley smiley = mSmiley.get(position);
        holder.bindTo(smiley);

    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder {
        private final RadioButton emojiName;
        private Smiley mSmiley;


        AnswerViewHolder(View itemView) {
            super(itemView);
            emojiName = itemView.findViewById(R.id.answer_btn);



        }

//        public Smiley getSmiley() {
//
//            return mSmiley;
//        }

        void bindTo(Smiley smiley) {
            mSmiley = smiley;
            emojiName.setText(mSmiley.getName());
            emojiName.setTag(R.string.answer_tag, smiley.getCode());

        }

    }
    public void setSmiley(List<Smiley> smileyList) {
        this.mSmiley = smileyList;
        notifyDataSetChanged();


    }



//    private static final DiffUtil.ItemCallback<Smiley> DIFF_CALLBACK =
//            new DiffUtil.ItemCallback<Smiley>() {
//                @Override
//                public boolean areItemsTheSame(@NonNull Smiley oldItem, @NonNull Smiley newItem) {
//                    return oldItem.getName().equals(newItem.getName());
//                }
//
//                @Override
//                public boolean areContentsTheSame(@NonNull Smiley oldItem,
//                                                  @NonNull Smiley newItem) {
//                    return oldItem.getName().equals(newItem.getName());
//                }
//            };
}
