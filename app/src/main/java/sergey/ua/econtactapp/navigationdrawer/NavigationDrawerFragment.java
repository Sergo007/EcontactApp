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
package sergey.ua.econtactapp.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sergey.ua.econtactapp.R;
import sergey.ua.econtactapp.initilisator.FontInitializer;
import sergey.ua.econtactapp.model.Information;

public class NavigationDrawerFragment extends Fragment { //[Comment] Use navigation view

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrowerToggle;
    private DrawerLayout mDrowerLayout;
    private boolean mUserLernedDrawer;
    private boolean mFromSevedInstanseState;
    private TextView mTitle;

    public NavigationDrawerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLernedDrawer = Boolean.valueOf(readFromPreferense(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) mFromSevedInstanseState = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mTitle = (TextView) layout.findViewById(R.id.title_drawer);
        mTitle.setTypeface(FontInitializer.ROBOTO_BOLD);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapterForNavDrawer adapter = new RecyclerAdapterForNavDrawer(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        return layout;
    }

    private List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_doc, R.drawable.ic_map};
        String[] titles = getContext().getResources().getStringArray(R.array.drawer_tabs);
        for (int i = 0; i < titles.length; i++) {
            data.add(new Information(icons[i], titles[i]));
        }
        return data;
    }


    public void setUp(int fragment_navigation_drawer, DrawerLayout viewById, Toolbar toolbar) {
        View conteinerView = getActivity().findViewById(fragment_navigation_drawer);
        mDrowerLayout = viewById;
        mDrowerToggle = new ActionBarDrawerToggle(getActivity(), mDrowerLayout,toolbar, R.string.drower_open, R.string.drower_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //Toast.makeText(getActivity(), "Opened Drawer", Toast.LENGTH_SHORT).show();
                if (!mUserLernedDrawer) {
                    mUserLernedDrawer = true;
                    saveToPreferense(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLernedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                //Toast.makeText(getActivity(), "Closed Drawer", Toast.LENGTH_SHORT).show();
                getActivity().invalidateOptionsMenu();
            }
        };
        if (!mUserLernedDrawer && !mFromSevedInstanseState) {
            mDrowerLayout.openDrawer(conteinerView);
        }
        mDrowerLayout.setDrawerListener(mDrowerToggle);
        mDrowerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrowerToggle.syncState();
            }
        });
    }

    public void saveToPreferense(Context context, String preferenseNeme, String preferenseValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenseNeme, preferenseValue);
        editor.commit();
    }

    public static String readFromPreferense(Context context, String preferenseNeme, String preferenseValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenseNeme, preferenseValue);
    }
}
