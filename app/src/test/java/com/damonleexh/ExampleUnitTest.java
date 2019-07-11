package com.damonleexh;

import com.damonleexh.util.CalculateManager;
import com.damonleexh.util.DataFormatManager;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void calc() {
        try {
            String d1 = "2019-12-20";
            String d2 = "2020-01-20";
            String d2days = "20";

            int i = CalculateManager.getInstance().differentDays(
                    DataFormatManager.parse(d1),
                    DataFormatManager.parse(d2)
            );
            System.out.println(i);
            String[] pair = CalculateManager.getInstance().calcDiff(
                    d1,
                    d2days
            );
            System.out.println(pair[0]);
            System.out.println(pair[1]);

            String zd = "01";
            String hk = "10";
            int maxFreeTime = CalculateManager.getInstance().getMaxFreeTime(zd, hk);
            System.out.println("多久还款：" + maxFreeTime);
            System.out.println("这一天要还款：" + (maxFreeTime + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void calcCalc() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"支付宝", "01", "10", "-1"});
        list.add(new String[]{"招商银行", "25", "13", "-1"});
        list.add(new String[]{"中信银行", "02", "21", "-1"});
        list.add(new String[]{"平安银行", "20", "08", "-1"});
        list.add(new String[]{"浦发银行", "13", "02", "-1"});
//25+19 = 44
//20+19 = 39
//13 + 20 = 33
        String[] strings = new String[4];


        for (String[] ss : list) {
            int current = -1;
            try {
                current = CalculateManager.getInstance().getMaxFreeTime(ss[1], ss[2], "2019-04-30");
                ss[3] = String.valueOf(current);
                System.out.println("房租消费计算:" + ss[0] + " , " + ss[1] + " , " + ss[2] + " , " + current);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}