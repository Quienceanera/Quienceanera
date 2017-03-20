package com.example.eventmakr.eventmakr.Utils;


import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerBudgetFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorCategoryFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorProductItemFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorProfileFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.EventsFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.MenuItemFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartDetailFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartHomeFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatHomeFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.NavBar;
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
        ConsumerBudgetFragment consumerBudgetFragment = new ConsumerBudgetFragment();
        return consumerBudgetFragment;
    }

    public static ConsumerVendorCategoryFragment getConsumerVendorCategoryFragment() {
        return new ConsumerVendorCategoryFragment();
    }

    public static ConsumerVendorProfileFragment getConsumerVendorProfileFragment () {
        ConsumerVendorProfileFragment consumerVendorProfileFragment = new ConsumerVendorProfileFragment();
        return consumerVendorProfileFragment;
    }

    public static MenuItemFragment getMenuItemFragment () {
        MenuItemFragment menuItemFragment = new MenuItemFragment();
        return menuItemFragment;
    }

    public static ContactVendorFragment getContactVendorFragment () {
        ContactVendorFragment contactVendorFragment = new ContactVendorFragment();
        return contactVendorFragment;
    }

    public static UserFragment getUserFragment () {
        UserFragment userFragment = new UserFragment();
        return userFragment;
    }

    public static ChatFragment getChatFragment () {
        ChatFragment chatFragment = new ChatFragment();
        return chatFragment;
    }

    public static ChatRecyclerFragment getChatHomeItemFragment(){
        return new ChatRecyclerFragment();
    }

    public static MessageRecyclerFragment getChatItemFragment () {
        return new MessageRecyclerFragment();
    }

    public static ChatHomeFragment getChatHomeFragment () {
        ChatHomeFragment chatHomeFragment = new ChatHomeFragment();
        return chatHomeFragment;
    }

    public static CartHomeFragment getCartFragment () {
        CartHomeFragment cartHomeFragment = new CartHomeFragment();
        return cartHomeFragment;
    }

    public static CartRecyclerFragment getCartHomeItemFragment () {
        return new CartRecyclerFragment();
    }

    public static OrderListRecyclerFragment getOrderListItemFragment(){
        return new OrderListRecyclerFragment();
    }

    public static CartDetailFragment getCartDetailFragment() {
        return new CartDetailFragment();
    }

    public static VendorInputFragment getVendorInputFragment () {
        VendorInputFragment vendorInputFragment = new VendorInputFragment();
        return vendorInputFragment;
    }

    public static ConsumerVendorProductItemFragment getConsumerVendorProductItemFragment () {
        ConsumerVendorProductItemFragment consumerVendorProductItemFragment = new ConsumerVendorProductItemFragment();
        return consumerVendorProductItemFragment;
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


    public static NavBar getNavBar () {
        NavBar navBar = new NavBar();
        return navBar;
    }

    //TODO: Vendor Fragments

    public static VendorMenuFragment getVendorMenuFragment(){
        return new VendorMenuFragment();
    }

    public static VendorProductRecyclerFragment getVendorProductRecyclerFragment(){
        return new VendorProductRecyclerFragment();
    }



}
