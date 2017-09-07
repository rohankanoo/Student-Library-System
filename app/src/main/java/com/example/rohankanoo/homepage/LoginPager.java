package com.example.rohankanoo.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Rohan Kanoo on 30-06-2017.
 */
//Extending FragmentStatePagerAdapter
public class LoginPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public LoginPager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                EmployeeLoginActivity tab1 = new EmployeeLoginActivity();
                return tab1;
            case 1:
                StudentLoginActivity tab2 = new StudentLoginActivity();
                return tab2;
            /*case 2:
                Tab3 tab3 = new Tab3();
                return tab3;*/
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}