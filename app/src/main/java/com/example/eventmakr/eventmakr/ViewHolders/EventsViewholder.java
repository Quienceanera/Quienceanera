package com.example.eventmakr.eventmakr.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

public class EventsViewholder extends RecyclerView.ViewHolder {

    public CardView mCardViewEvents, mCardViewEventsHelper;
    public ImageView mImageViewEvents;
    public TextView mTextViewEvents, mTextViewEventsDate, mTextViewEventsZip;
    public Button mButtonEdit, mButtonSelect;

    public EventsViewholder(View itemView) {
        super(itemView);
        mCardViewEvents = (CardView) itemView.findViewById(R.id.cardViewEvents);
        mCardViewEventsHelper = (CardView) itemView.findViewById(R.id.cardViewEvents_helper);
        mImageViewEvents = (ImageView) itemView.findViewById(R.id.imageViewEvents);
        mTextViewEvents = (TextView) itemView.findViewById(R.id.textViewEventsType);
        mTextViewEventsDate = (TextView) itemView.findViewById(R.id.textViewEventsDate);
        mTextViewEventsZip = (TextView) itemView.findViewById(R.id.textViewEventsZip);

//        mButtonEdit = (Button) itemView.findViewById(R.id.editButton);
//        mButtonSelect = (Button) itemView.findViewById(R.id.selectButton);
    }
}
