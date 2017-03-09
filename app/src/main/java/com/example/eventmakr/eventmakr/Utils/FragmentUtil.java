package com.example.eventmakr.eventmakr.Utils;


import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerBudgetFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerInputFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorCategoryFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorProductItemFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorProfileFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.MenuItemFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.CartHomeFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.CartDetailFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.ChatFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.ChatHomeFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.NavBar;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.UserFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorInputFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorProductFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.RecyclerItemsFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.RecyclerVendorFragment;
import com.example.eventmakr.eventmakr.RecyclerFragment.RecyclerVendorProfileProductItemFragment;
import com.google.android.gms.maps.MapFragment;

public class FragmentUtil {

    public static ConsumerInputFragment getConsumerDropdownFragment() {
        ConsumerInputFragment consumerInputFragment = new ConsumerInputFragment();
        return consumerInputFragment;
    }

    public static VendorProductFragment getVendorProductFragment() {
        VendorProductFragment vendorProductFragment = new VendorProductFragment();
        return vendorProductFragment;
    }

    public static ConsumerBudgetFragment getConsumerBudgetFragment() {
        ConsumerBudgetFragment consumerBudgetFragment = new ConsumerBudgetFragment();
        return consumerBudgetFragment;
    }

    public static ConsumerVendorCategoryFragment getConsumerVendorCategoryFragment() {
        ConsumerVendorCategoryFragment consumerVendorCategoryFragment = new ConsumerVendorCategoryFragment();
        return consumerVendorCategoryFragment;
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

    public static ChatHomeFragment getChatHomeFragment () {
        ChatHomeFragment chatHomeFragment = new ChatHomeFragment();
        return chatHomeFragment;
    }

    public static CartHomeFragment getCartFragment () {
        CartHomeFragment cartHomeFragment = new CartHomeFragment();
        return cartHomeFragment;
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

    public static RecyclerVendorFragment getRecyclerVendorFragment () {
        return new RecyclerVendorFragment();
    }

    public static RecyclerVendorProfileProductItemFragment getRecyclerVendorProfileProductItemFragment () {
        return new RecyclerVendorProfileProductItemFragment();
    }

    public static MapFragment getMapFragment () {
        return new MapFragment();
    }

    public static RecyclerItemsFragment getRecyclerItemsFragment () {
        return new RecyclerItemsFragment();
    }


    public static NavBar getNavBar () {
        NavBar navBar = new NavBar();
        return navBar;
    }


}
