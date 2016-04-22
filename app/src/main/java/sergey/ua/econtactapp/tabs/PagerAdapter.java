/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package sergey.ua.econtactapp.tabs;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sergey.ua.econtactapp.model.StatesTask;


public class PagerAdapter extends FragmentPagerAdapter {
    public static final int COUNT_TABS = 3;
    private String[] mTabs;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mTabs = new String[COUNT_TABS];
        mTabs[0] = StatesTask.getStateName(StatesTask.IN_PROGRESS);
        mTabs[1] = StatesTask.getStateName(StatesTask.COMPLETED);
        mTabs[2] = StatesTask.getStateName(StatesTask.WAITING);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return BaseTabsFragment.getInstance(StatesTask.IN_PROGRESS);
            case 1:
                return BaseTabsFragment.getInstance(StatesTask.COMPLETED);
            case 2:
                return BaseTabsFragment.getInstance(StatesTask.WAITING);
            default:
                return BaseTabsFragment.getInstance(StatesTask.IN_PROGRESS);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return COUNT_TABS;
    }
}
