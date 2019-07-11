package com.damonleexh.util;

import java.text.ParseException;
import java.util.Date;

public interface ICalculate {
    String XFR = "CONSUMPTION_DAY";
    String ZDR = "STATEMENT_DATE";
    String HKR = "REPAYMENT_DATE";
    String DIFF = "DATE_DIFFERENCE";

    /**
     * 计算最大免息时间
     *
     * @param statementDate 账单日
     * @param repaymentDate 还款日
     * @return 今天消费的最大免息时间
     */
    int getMaxFreeTime(String statementDate, String repaymentDate) throws ParseException;

    /**
     * 计算最大免息时间
     *
     * @param statementDate  账单日
     * @param repaymentDate  还款日
     * @param consumptionDay 消费日
     * @return 消费日消费的最大免息时间
     */
    int getMaxFreeTime(String statementDate, String repaymentDate, String consumptionDay) throws ParseException;

    /**
     * @param firstYMD yyyy-MM-dd
     * @param secondD  dd
     * @return int:日期差 String:secondData日期
     */
    String[] calcDiff(String firstYMD, String secondD) throws ParseException;

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1 日期一
     * @param date2 日期二
     * @return 两个时间相差的天数
     */
    int differentDays(Date date1, Date date2);
}
