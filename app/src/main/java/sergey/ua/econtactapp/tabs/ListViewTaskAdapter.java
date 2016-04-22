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

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sergey.ua.econtactapp.R;
import sergey.ua.econtactapp.Utils.DateUtils;
import sergey.ua.econtactapp.initilisator.ContextProvider;
import sergey.ua.econtactapp.model.Task;


public class ListViewTaskAdapter extends ArrayAdapter<Task> {

    ListViewTaskAdapter(Activity activity, List<Task> items) {
        super(activity, R.layout.item_task, R.id.title, new ArrayList(items));
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        View row = super.getView(position, convertView, parent);
        Task item = getItem(position);
        ViewHolder holder = (ViewHolder) row.getTag();
        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        holder.icon.setBackgroundResource(R.drawable.ic_map);
        holder.title.setText(item.getCategory());
        holder.address.setText(item.getAddress());
        DateFormat formatter = SimpleDateFormat.getDateInstance();
        holder.creteDate.setText(formatter.format(item.getCreatedDate()));
        Date dueDate = item.getDueDate();
        Date currentDate = Calendar.getInstance().getTime();
        holder.days.setText(
                String.format(ContextProvider.getAppContext().getResources().getString(R.string.days),
                        DateUtils.getDifferenceDays(currentDate, dueDate)));
        holder.plusCount.setText(Integer.toString(item.getPlusCount()));

        return (row);
    }

    class ViewHolder{
        TextView title;
        TextView days;
        ImageView icon;
        TextView address;
        TextView creteDate;
        TextView plusCount;

        ViewHolder(View row) {
            title = (TextView) row.findViewById(R.id.title);
            icon = (ImageView) row.findViewById(R.id.picture);
            address = (TextView) row.findViewById(R.id.adress);
            creteDate = (TextView) row.findViewById(R.id.create_date);
            days = (TextView) row.findViewById(R.id.days);
            plusCount = (TextView) row.findViewById(R.id.plus);
        }
    }
}
