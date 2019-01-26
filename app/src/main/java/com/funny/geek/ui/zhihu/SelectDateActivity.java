package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.funny.geek.R;
import com.funny.geek.base.RootActivity;
import com.funny.geek.contract.zhihu.SelectDateContract;
import com.funny.geek.presenter.zhihu.SelectDatePresenter;
import com.funny.geek.util.Constants;
import com.jakewharton.rxbinding2.view.RxView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.DayOfWeek;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class SelectDateActivity extends RootActivity<SelectDatePresenter> implements SelectDateContract.View {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.calendar_view)
    MaterialCalendarView mCalendarView;
    @BindView(R.id.confirm_tv)
    TextView mConfirmTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_date;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(SelectDateActivity.this);
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolBar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolBar.setTitle("选择日期");

        mCalendarView.state()
                .edit()
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .setFirstDayOfWeek(DayOfWeek.SUNDAY)
                .setMaximumDate(CalendarDay.from(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.DATE)))
                .commit();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        RxView.clicks(mConfirmTv)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    CalendarDay selectedDate = mCalendarView.getSelectedDate();
                    StringBuilder dateStr = new StringBuilder();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDay());
                    calendar.add(Calendar.DATE, 1);

                    String month = String.valueOf(calendar.get(Calendar.MONTH));
                    if (month.length() == 1) {
                        month = "0" + month;
                    }
                    String date = String.valueOf(calendar.get(Calendar.DATE));
                    if (date.length() == 1) {
                        date = "0" + date;
                    }
                    dateStr.append(calendar.get(Calendar.YEAR)).append(month).append(date);
                    Intent intent = new Intent();
                    intent.putExtra("selectedData", dateStr.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                });
    }

}
