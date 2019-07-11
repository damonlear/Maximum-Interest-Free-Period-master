package com.damonleexh.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CalculateManager implements ICalculate {
    private static volatile CalculateManager manager = null;

    private CalculateManager() {
    }

    public static CalculateManager getInstance() {
        if (manager == null) {
            synchronized (CalculateManager.class) {
                if (manager == null) {
                    manager = new CalculateManager();
                }
            }
        }
        return manager;
    }

    @Override
    public int getMaxFreeTime(String statementDate, String repaymentDate) throws ParseException {
        return getMaxFreeTime(statementDate,
                repaymentDate,
                DataFormatManager.formatDate(new Date())
        );
    }

    @Override
    public int getMaxFreeTime(String statementDate, String repaymentDate, String currentYMD) throws ParseException {
        String[] strings1 = calcDiff(currentYMD, statementDate);
        String[] strings2 = calcDiff(strings1[1], repaymentDate);
        return Integer.parseInt(strings1[0]) + Integer.parseInt(strings2[0]);
    }

    //账单日消费享受最大免息期
    @Override
    public String[] calcDiff(String firstYMD, String secondD) throws ParseException {
        //第一日
        Date firstDate = DataFormatManager.parse(firstYMD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        int firstDay = calendar.get(Calendar.DAY_OF_MONTH);

        //第二日
        int secondDay = Integer.parseInt(secondD);
        calendar.set(Calendar.DAY_OF_MONTH, secondDay);
        //日期小于等于当前日期的，就是下一个月。生成账单那日消费的，算到下一个账单日。
        if (firstDay >= secondDay) {
            calendar.add(Calendar.MONTH, 1);
        }
        Date secondDate = calendar.getTime();

        //日期差
        return new String[]{
                String.valueOf(differentDays(firstDate, secondDate)),
                DataFormatManager.formatDate(secondDate)
        };
    }

    @Override
    public int differentDays(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }
}
