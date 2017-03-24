package com.example.eventmakr.eventmakr.ViewHolders;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Viewholder extends RecyclerView.ViewHolder {
    public TextView mTextViewVendorName, mTextViewVendorPopularItems, mTextViewVendorAddress, mTextViewVendorCategory, mTextViewChat, mTextViewChatUser, mTextViewChatDetailUser, mTextViewChatDate, mTextViewMenuItemName, mTextViewMenuItemDescription, mTextViewMenuItemPrice, mTextViewVendorProductItemName, mTextViewVendorProductItemPrice, mTextViewItemsName, mTextViewItemsQuantity, mTextViewItemsPrice;
    public CircleImageView mCircleImageViewChat;
    public ImageView mImageViewVendor, mImageViewMenuItem, mImageViewVendorProductItem, mImageViewItems;
    public CardView mCardViewChat, mCardViewChatUser, mCardViewChatDelete, mCardViewChatDetail, mCardViewVendorItem, mCardViewMenuItem;
    public FloatingActionButton mFabDelete, mFabCancel, mFabSend;
    public RelativeLayout mLayoutDeleteComment, mLayoutChatItem;
    public EditText mEditTextChat;
    public ImageView mImageViewPrice1, mImageViewPrice2, mImageViewPrice3;

    public Viewholder(View itemView) {
        super(itemView);
        //Layout
        mLayoutDeleteComment = (RelativeLayout) itemView.findViewById(R.id.layout_deleteComment);
        mLayoutChatItem = (RelativeLayout) itemView.findViewById(R.id.layoutChatItems);
        //CircleImageView
        mCircleImageViewChat = (CircleImageView) itemView.findViewById(R.id.chatImageView);
        //ImageView
        mImageViewVendor = (ImageView) itemView.findViewById(R.id.imageViewVendor);
        mImageViewMenuItem = (ImageView) itemView.findViewById(R.id.imageViewProductItem);
        mImageViewVendorProductItem = (ImageView) itemView.findViewById(R.id.imageViewVendorProductItem);
        mImageViewItems = (ImageView) itemView.findViewById(R.id.imageViewItems);
        //CardView
        mCardViewChat = (CardView) itemView.findViewById(R.id.chatCardView);
        mCardViewChatUser = (CardView) itemView.findViewById(R.id.chatCardViewUser);
        mCardViewChatDelete = (CardView) itemView.findViewById(R.id.chatCardViewDelete);
        mCardViewChatDetail = (CardView) itemView.findViewById(R.id.chatCardViewDetail);
        mCardViewVendorItem = (CardView) itemView.findViewById(R.id.cardViewVendor);
        //TextView
        mTextViewChat = (TextView) itemView.findViewById(R.id.chatTextView);
        mTextViewChatUser = (TextView) itemView.findViewById(R.id.chatTextViewUser);
        mTextViewChatDetailUser = (TextView) itemView.findViewById(R.id.chatUserTextView);
        mTextViewChatDate = (TextView) itemView.findViewById(R.id.chatDateTextView);

        mTextViewVendorName = (TextView) itemView.findViewById(R.id.textViewVendor);
        mTextViewVendorPopularItems = (TextView) itemView.findViewById(R.id.textViewPopularItems);
        mTextViewVendorAddress = (TextView) itemView.findViewById(R.id.textViewVendor_address);

        mTextViewMenuItemName = (TextView) itemView.findViewById(R.id.textViewProductItemName);
        mTextViewMenuItemDescription = (TextView) itemView.findViewById(R.id.textViewMenuItemDescription);
        mTextViewMenuItemPrice = (TextView) itemView.findViewById(R.id.textViewMenuItemPrice);
        mCardViewMenuItem = (CardView) itemView.findViewById(R.id.cardViewMenuItem);

        mTextViewVendorProductItemName = (TextView) itemView.findViewById(R.id.textViewVendorProductItemName);
        mTextViewVendorProductItemPrice = (TextView) itemView.findViewById(R.id.textViewVendorProductItemPrice);

        mTextViewItemsName = (TextView) itemView.findViewById(R.id.textViewItemsName);
        mTextViewItemsQuantity = (TextView) itemView.findViewById(R.id.textViewItemsQuantity);
        mTextViewItemsPrice = (TextView) itemView.findViewById(R.id.textViewItemsPrice);

        //FloatingActionButtons
        mFabCancel = (FloatingActionButton) itemView.findViewById(R.id.fabCancel);
        mFabDelete = (FloatingActionButton) itemView.findViewById(R.id.fabDelete);
//        mFabSend = (FloatingActionButton) itemView.findViewById(R.id.fabSend);
        //EditText
//        mEditTextChat = (EditText) itemView.findViewById(R.id.chatEditText);

        mImageViewPrice1 = (ImageView) itemView.findViewById(R.id.imageViewPrice1);
        mImageViewPrice2 = (ImageView) itemView.findViewById(R.id.imageViewPrice2);
        mImageViewPrice3 = (ImageView) itemView.findViewById(R.id.imageViewPrice3);



    }
}
