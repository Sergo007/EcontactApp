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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sergey.ua.econtactapp.customviews.CustomButton;
import sergey.ua.econtactapp.R;
import sergey.ua.econtactapp.RecyclerListViewAdapter;
import sergey.ua.econtactapp.details.TaskDetailsActivity;
import sergey.ua.econtactapp.initilisator.ContextProvider;
import sergey.ua.econtactapp.model.StatesTask;
import sergey.ua.econtactapp.model.Task;


public class BaseTabsFragment extends Fragment {
    public static final String KEY_TABS = "position";
    private RecyclerView mRecyclerViewInProgress;
    private RecyclerView mRecyclerViewCompleted;
    private ListView mListViewWarning;
    private CustomButton mFaButton;

    public static BaseTabsFragment getInstance(String position) {
        BaseTabsFragment myFragment = new BaseTabsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TABS, position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.other_page, container, false);

        Bundle bundle = getArguments();
        switch (bundle.getString(KEY_TABS)) {
            case StatesTask.IN_PROGRESS: {
                RecyclerListViewAdapter adapterTrending = new RecyclerListViewAdapter(getActivity(), getFikeData());
                layout = inflater.inflate(R.layout.fragment_in_progress, container, false);
                mFaButton = (CustomButton) layout.findViewById(R.id.fab_button);
                mRecyclerViewInProgress = (RecyclerView) layout.findViewById(R.id.recycler_in_progress);
                mFaButton.attachToRecyclerView(mRecyclerViewInProgress);
                mRecyclerViewInProgress.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapterTrending.setLayoutInformationID(R.layout.item_task);
                mRecyclerViewInProgress.setAdapter(adapterTrending);

                break;
            }
            case StatesTask.WAITING: {
                layout = inflater.inflate(R.layout.fragment_completed, container, false);
                mFaButton = (CustomButton) layout.findViewById(R.id.fab_button);
                mListViewWarning = (ListView) layout.findViewById(R.id.task_listview);
                mFaButton.attachToListView(mListViewWarning);
                mListViewWarning.setAdapter(new ListViewTaskAdapter(getActivity(), getFikeData()));
                mListViewWarning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ContextProvider.getAppContext(), TaskDetailsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(TaskDetailsActivity.KEY_TASK, new Gson().toJson(getFikeData().get(position)));
                        ContextProvider.getAppContext().startActivity(intent);
                    }
                });

                break;
            }
            case StatesTask.COMPLETED: {
                RecyclerListViewAdapter adaptertopFree = new RecyclerListViewAdapter(getActivity(), getFikeData());
                layout = inflater.inflate(R.layout.fragment_in_progress, container, false);
                mFaButton = (CustomButton) layout.findViewById(R.id.fab_button);
                mRecyclerViewCompleted = (RecyclerView) layout.findViewById(R.id.recycler_in_progress);
                mFaButton.attachToRecyclerView(mRecyclerViewCompleted);
                mRecyclerViewCompleted.setLayoutManager(new LinearLayoutManager(getActivity()));
                adaptertopFree.setLayoutInformationID(R.layout.item_task);
                mRecyclerViewCompleted.setAdapter(adaptertopFree);

                break;
            }
        }
        return layout;
    }

    private List<Task> getFikeData() {
        List<Task> data = new ArrayList<>();
        Task task = new Task();
        task.setAddress("Гагарина 25");
        task.setCreatedDate(new Date(2016-1900, 3, 5));
        task.setCategory("Экология");
        task.setDescription("vnfhsuih rfe nie nvrhief rihnfri nruino gjirngui g hruieg nrign rign riengrji");
        task.setDueDate(new Date(2016-1900, 5, 5));
        task.setRegisteredDate(new Date(2016-1900, 2, 5));
        task.setPictures(getResources().getStringArray(R.array.picture_urls));
        task.setPlusCount(12);
        task.setState(StatesTask.IN_PROGRESS);
        task.setTitle("CH-123423");
        for (int i = 0; i < 10; i++) {
            data.add(task);
        }
        return data;
    }
}
