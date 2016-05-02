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
package yalantis.ua.econtactapp.tabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import yalantis.ua.econtactapp.R;
import yalantis.ua.econtactapp.utils.Utils;
import yalantis.ua.econtactapp.details.TaskDetailsActivity;
import yalantis.ua.econtactapp.initilisator.ContextProvider;
import yalantis.ua.econtactapp.model.Task;


public class RecyclerListViewAdapter extends RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private int mLayoutInformationID;
    private List<Task> mData = Collections.emptyList();

    public RecyclerListViewAdapter(Context context, List<Task> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    public void setLayoutInformationID(int layoutInformationID) {
        this.mLayoutInformationID = layoutInformationID;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutInformationID, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task current = mData.get(position);
        holder.title.setText(current.getCategory());
        holder.icon.setBackgroundResource(R.drawable.ic_doc);
        holder.address.setText(current.getAddress());
        DateFormat formatter = SimpleDateFormat.getDateInstance();
        holder.creteDate.setText(formatter.format(current.getCreatedDate()));
        Date dueDate = current.getDueDate();
        Date currentDate = Calendar.getInstance().getTime();
        holder.days.setText(
                String.format(ContextProvider.getAppContext().getResources().getString(R.string.days),
                        Utils.diffDays(currentDate, dueDate)));
        holder.likesCount.setText(Integer.toString(current.getPlusCount()));
        holder.setItem(current);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Task mItem;
        TextView title;
        TextView address;
        TextView creteDate;
        TextView days;
        ImageView icon;
        TextView likesCount;

        public ViewHolder(View row) {
            super(row);
            title = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.adress);
            creteDate = (TextView) row.findViewById(R.id.create_date);
            days = (TextView) row.findViewById(R.id.days);
            icon = (ImageView) row.findViewById(R.id.picture);
            likesCount = (TextView) row.findViewById(R.id.likes);
            row.setOnClickListener(this);
        }

        public void setItem(Task item) {
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ContextProvider.getAppContext(), TaskDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(TaskDetailsActivity.KEY_TASK, new Gson().toJson(mItem));
            ContextProvider.getAppContext().startActivity(intent);
        }
    }
}