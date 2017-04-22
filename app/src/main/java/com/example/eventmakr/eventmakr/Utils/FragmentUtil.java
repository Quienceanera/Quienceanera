package com.example.eventmakr.eventmakr.Utils;


import android.os.Bundle;

import com.example.eventmakr.eventmakr.Activities.PayFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerBudgetFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorCategoryFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorProductItemFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorProfileFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.MenuItemFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartDetailFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartHomeFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.EventsFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.UserFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.OrderDetailFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorInputFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorMenuFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorProductFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.CartRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.ChatRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.EventRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.MessageRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.OrderListRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.OrderRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.VendorOrderHomeListFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.VendorOrderListFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.VendorProductRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.VendorProfileProductRecyclerFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.VendorRecyclerFragment;
import com.google.android.gms.maps.MapFragment;

public class FragmentUtil {

    public static PayFragment getPayFragment(){
        return new PayFragment();
    }

    public static VendorOrderHomeListFragment getVendorOrderHomeListFragment() {
        return new VendorOrderHomeListFragment();
    }

    public static OrderDetailFragment getOrderDetailFragment(){
        return new OrderDetailFragment();
    }

    public static VendorOrderListFragment getVendorOrderListFragment() {
        return new VendorOrderListFragment();
    }

    public static EventsFragment getEventBannerFragment() {
        return new EventsFragment();
    }

    public static EventsFragment getEventsFragment() {
        return new EventsFragment();
    }

    public static EventRecyclerFragment getEventsList() {
        return new EventRecyclerFragment();
    }

    public static VendorProductFragment getVendorProductFragment() {
        return new VendorProductFragment();
    }

    public static ConsumerBudgetFragment getConsumerBudgetFragment() {
        return new ConsumerBudgetFragment();
    }

    public static ConsumerVendorCategoryFragment getConsumerVendorCategoryFragment() {

        return new ConsumerVendorCategoryFragment();
    }

    public static ConsumerVendorProfileFragment getConsumerVendorProfileFragment (Bundle bundle) {
        ConsumerVendorProfileFragment fragment = new ConsumerVendorProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MenuItemFragment getMenuItemFragment () {
        return new MenuItemFragment();
    }

    public static ContactVendorFragment getContactVendorFragment () {
        return new ContactVendorFragment();
    }

    public static UserFragment getUserFragment () {
        return new UserFragment();
    }

    public static ChatFragment getChatFragment () {
        return new ChatFragment();
    }

    public static ChatRecyclerFragment getChatHomeItemFragment(){
        return new ChatRecyclerFragment();
    }

    public static MessageRecyclerFragment getChatItemFragment () {
        return new MessageRecyclerFragment();
    }

    public static CartFragment getChatHomeFragment () {
        return new CartFragment();
    }

    public static CartHomeFragment getCartFragment () {
        return new CartHomeFragment();
    }

    public static CartRecyclerFragment getCartHomeItemFragment () {
        return new CartRecyclerFragment();
    }

    public static OrderListRecyclerFragment getOrderListItemFragment(){
        return new OrderListRecyclerFragment();
    }

    public static CartDetailFragment getCartDetailFragment(Bundle bundle) {
        CartDetailFragment fragment = new CartDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static VendorInputFragment getVendorInputFragment () {
        return new VendorInputFragment();
    }

    public static ConsumerVendorProductItemFragment getConsumerVendorProductItemFragment (Bundle bundle) {
        ConsumerVendorProductItemFragment fragment = new ConsumerVendorProductItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static VendorRecyclerFragment getRecyclerVendorFragment () {
        return new VendorRecyclerFragment();
    }

    public static VendorProfileProductRecyclerFragment getRecyclerVendorProfileProductItemFragment () {
        return new VendorProfileProductRecyclerFragment();
    }

    public static MapFragment getMapFragment () {
        return new MapFragment();
    }

    public static OrderRecyclerFragment getOrderRecyclerFragment() {
        return new OrderRecyclerFragment();
    }


    //TODO: Vendor Fragments

    public static VendorMenuFragment getVendorMenuFragment(){
        return new VendorMenuFragment();
    }

    public static VendorProductRecyclerFragment getVendorProductRecyclerFragment(){
        return new VendorProductRecyclerFragment();
    }



}
