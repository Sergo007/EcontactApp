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
package yalantis.ua.econtactapp.details;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yalantis.ua.econtactapp.R;


public class TaskDetailsActivity extends AppCompatActivity {
    public static final String KEY_TASK = "KEY_TASK";
    @Bind(R.id.recycler_view_picture)
    RecyclerView recyclerView;
    @Bind(R.id.create_date)
    TextView createdDate;
    @Bind(R.id.registrate_date)
    TextView registeredDate;
    @Bind(R.id.solved_date)
    TextView solvedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(getResources().getString(R.string.title));
        setDate();
        setupRecyclerView();
    }

    private void setDate() {
        DateFormat formatter = SimpleDateFormat.getDateInstance();
        Calendar calendar = Calendar.getInstance();
        createdDate.setText(formatter.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 2);
        registeredDate.setText(formatter.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, 1);
        solvedDate.setText(formatter.format(calendar.getTime()));
    }

    @OnClick({
            R.id.create_date,
            R.id.registrate_date,
            R.id.solved_date,
            R.id.line_1,
            R.id.line_2,
            R.id.line_3,
            R.id.line_4,
            R.id.line,
            R.id.create_date_info,
            R.id.description,
            R.id.number_task_text,
            R.id.registrate_date_info,
            R.id.responsible,
            R.id.responsible_text,
            R.id.status_task
    })
    public void onClick(View view) {
        Toast.makeText(this, view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PictureAdapter(Arrays.asList(getResources().getStringArray(R.array.picture_urls))));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
