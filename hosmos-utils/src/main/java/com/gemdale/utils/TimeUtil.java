package com.gemdale.utils;

 

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class TimeUtil {

    private final static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = null;

    private static SimpleDateFormat getFormat() {
        if (sdf == null) {
            sdf = new SimpleDateFormat(FORMAT);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        }
        return sdf;
    }

    /**
     * ��ȡ��������������
     * yyyy��MM��dd��
     *
     * @return
     */
    public static final String getDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * ��ȡ��������������
     *"yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static final String getDateTimeNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * �������ڵ�ʱ���ַ��� <br/>
     * FORMAT = "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static final String dateTimeNow() {
        return getFormat().format(new Date());
    }

    public static final String TimesTamp() {
        String str = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = String.valueOf(sdf.parse(dateTimeNow()).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static final Date now() {
        return new Date();
    }

    /**
     * ʱ��Ƚϴ�С true == ���� �������
     */
    public static final boolean setTimeComparison(String serverTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(dateTimeNow()));
            c2.setTime(df.parse(serverTime));
        } catch (ParseException e) {
            return true;
        }
        int result = c1.compareTo(c2);
        if (result == 0)
            return true;
        else if (result > 0)
            return false;
        else
            return true;
    }

    /**
     * ���ַ���ʱ��ת��ΪDate
     *
     * @return ��ת��ʧ�ܣ�����null
     */
    public static final Date parseDate(String dateStr) {
        try {
            return getFormat().parse(dateStr);
        } catch (ParseException e) {
            //LogUtil.e("ת��ʱ��ʧ��,dateStr: " + dateStr);
            return null;
        }
    }

    /**
     * ����ָ�����ھ������ڵ�ʱ�䳤������
     *
     * @param dateStr
     * @return
     */
    public static final String descToNow(String dateStr) {
        Date endDate = parseDate(dateStr);
        if (endDate == null) {
            return "�ո�";
        }
        long seconds = (System.currentTimeMillis() - endDate.getTime()) / 1000;
        if (seconds < 0) {
            return "�ո�";
        }
        int s = (int) (seconds % 60);
        seconds /= 60; // ��ʱ��ת��Ϊ��
        int m = (int) (seconds % 60);
        seconds /= 60; // ��ʱ��ת��Ϊʱ
        int h = (int) (seconds % 24);
        seconds /= 24; // ��ʱ��ת��Ϊ��
        int d = (int) (seconds % 30);
        seconds /= 30; // ��ʱ��ת��Ϊ��
        int mo = (int) (seconds % 12);
        int y = (int) (seconds /= 12); // ��ʱ��ת��Ϊ��
        if (y > 0) {
            return y + "��ǰ";
        } else if (mo > 0) {
            return mo + "��ǰ";
        } else if (d > 0) {
            return d + "��ǰ";
        } else if (h > 0) {
            return h + "Сʱǰ";
        } else if (m > 0) {
            return m + "��ǰ";
        } else if (s > 30) {
            return "�����ǰ";
        } else {
            return "�ո�";
        }
    }

    /**
     * ����ָ�����ڣ�������С�ڵ���ĳЩ��ľ���ʱ�򷵻ؾ�������ʱ�䳤��������������ĳЩ�췵�ص����ڵ�ʱ��
     *
     * @param dateStr //ʱ��
     * @param data    //ĳЩ�ٽ�����
     * @return
     */

    public static final String descToNowAfterOthers(String dateStr, int data) {
        String s;
        Date endDate = parseDate(dateStr);
        long seconds = (System.currentTimeMillis() - endDate.getTime()) / 1000;
        seconds /= 60; // ��ʱ��ת��Ϊ��
        seconds /= 60; // ��ʱ��ת��Ϊʱ
        seconds /= 24; // ��ʱ��ת��Ϊ��
        int d = (int) (seconds % 30);
        seconds /= 30; // ��ʱ��ת��Ϊ��
        int mo = (int) (seconds % 12);
        int y = (int) (seconds /= 12);
        if ((d <= data) && (mo == 0) && (y == 0)) {
            s = descToNow(dateStr);
        } else {
            s = getMonthAndDay(dateStr);
        }
        return s;
    }


    /**
     * ����ָ�����ھ������ڵ�����
     *
     * @param dateStr
     * @return
     */
    public static final String getDaysToNow(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "";
        } else {
            long seconds = (date.getTime() - System.currentTimeMillis()) / 1000;
            double d = seconds / 60 / 60 / 24;
            if (d > 0.5) {
                return "��" + ((int) d) + "����ֹ��";
            } else if (d >= 0) {
                return "��������ֹ��";
            } else {
                return "";
            }
        }

    }

    /**
     * ��������������ʱ��
     *
     * @param dateStr
     * @return �磺 9��6�� 12:20
     */
    public static final String toChinestDescTime(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "��ȡʱ��ʧ��";
        } else {
            try {
                return new SimpleDateFormat("MM��dd�� HH:mm").format(date);
            } catch (Exception e) {
                return "���޷���ȡʱ��";
            }
        }
    }

    /**
     * ��������������ʱ��
     *
     * @param dateStr
     * @return �磺 9��6��
     */
    public static final String toChinestTime(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "��ȡʱ��ʧ��";
        } else {
            try {
                return new SimpleDateFormat("MM��dd��").format(date);
            } catch (Exception e) {
                return "���޷���ȡʱ��";
            }
        }
    }

    /**
     * ��������������ʱ��
     *
     * @param dateStr
     * @return �磺 9��6�� 12:20
     */
    public static final String toChinestDescTime2(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "��ȡʱ��ʧ��";
        } else {
            try {
                return new SimpleDateFormat("yyy��MM��dd��").format(date);
            } catch (Exception e) {
                return "���޷���ȡʱ��";
            }
        }
    }

    /**
     * ����Ӣ��������ʱ��
     *
     * @param dateStr
     * @return �磺 09-06 12:30
     */
    public static final String toEnglishDescTime(String dateStr) {
        // TODO: 2017-12-18 �������
        Date date = parseDate(dateStr);
        if (date == null) {
            return "��ȡʱ��ʧ��";
        } else {
            try {
                return new SimpleDateFormat("YYYY-MM-dd HH:mm").format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * ����ʱ������
     * �� 09-06
     * /��/��
     */
    public static final String toEnglishSmollTime(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "��ȡʱ��ʧ��";
        } else {
            try {
                return new SimpleDateFormat("MM-dd").format(date);
            } catch (Exception e) {
                return "���޷���ȡʱ��";
            }
        }
    }

    /**
     * ���ط�������
     *
     * @param dateStr
     * @return �磺 ������2015-09-04
     */
    public static final String getPubDate(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "��ȡʱ��ʧ��";
        } else {
            try {
                return new SimpleDateFormat("������yyyy-MM-dd").format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * ������������
     *
     * @param fromDate
     * @param toDate
     * @return �磺 2015�� 9��5�� �� 9��15��
     */
    @SuppressWarnings("deprecation")
    public static final String getBetweenDate(String fromDate, String toDate) {
        Date from = parseDate(fromDate);
        Date to = parseDate(toDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("M��d��");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy�� M��d��");
        if (from.getYear() == to.getYear()) {
            return sdf2.format(from) + " �� " + sdf1.format(to);
        } else {
            return sdf2.format(from) + " �� " + sdf2.format(to);
        }
    }

    /**
     * ת��Ϊ����ʱ��
     *
     * @param dateStr
     * @return �磺 2015-11-25 13:20
     */
    public static final String parseDateTime(String dateStr) {
        try {
            Date date = parseDate(dateStr);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        } catch (Exception e) {
            return "δ֪";
        }
    }

    /**
     * ����ʱ��
     *
     * @param dateStr
     * @return �磺 14:30
     */
    public static final String getTime(String dateStr) {
        try {
            return new SimpleDateFormat("HH:mm").format(parseDate(dateStr));
        } catch (Exception e) {
            return "δ֪";
        }
    }

    /**
     * �����·�����
     *
     * @param dateStr
     * @return �磺 ����
     */
    @SuppressWarnings("deprecation")
    public static final String getMonthDesc(String dateStr) {
        String[] desc = new String[]{"һ��", "����", "����", "����", "����", "����", "����", "����", "����", "ʮ��", "ʮһ��", "ʮ����",};
        try {
            return desc[parseDate(dateStr).getMonth()];
        } catch (Exception e) {
            e.printStackTrace();
            return "δ֪";
        }
    }

    /**
     * ��������
     *
     * @param dateStr
     * @return �磺 11��21��
     */
    public static final String getMonthAndDay(String dateStr) {
        try {
            return new SimpleDateFormat("MM��dd��").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "δ֪";
        }
    }

    /**
     * ����ƽ����ʽ����
     *
     * @param dateStr
     * @return �磺 2015-09-15
     */
    public static final String getSimpleDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "δ֪";
        }
    }

    /**
     * ���ظ���ʱ���ַ����Ƿ�������֮���ʱ��
     *
     * @param dateStr
     * @return �����쳣�ɷ��� false
     */
    public static final boolean isAfterNow(String dateStr) {
        try {
            return parseDate(dateStr).after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * ���ظ���ʱ���ַ����Ƿ�������֮ǰ��ʱ��
     *
     * @param dateStr
     * @return �����쳣�ɷ��� false
     */
    public static final boolean isBeforeNow(String dateStr) {
        try {
            return parseDate(dateStr).before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * ��������ʱ���ַ����ļ������ <br />
     * ��Ҫ��֤ beginTime �� endTime ������ͬ
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static String parseChineseBetweenTime(String beginTime, String endTime) {
        if (beginTime.equals(endTime)) {
            return parseDateTime(beginTime);
        } else {
            return parseDateTime(beginTime) + "-" + getTime(endTime);
        }
    }

    public static final String parseLongDateTime(String longdate) {
        try {
            return new SimpleDateFormat(FORMAT).format(new Date(Long.parseLong(longdate)));
        } catch (Exception e) {
            return "δ֪";
        }
    }

    /**
     * ������ʽ��ʱ���ʽ����Ʒ��ͳһ��
     *
     * @param dateStr
     * @return �磺 2015-09-15
     */
    public static final String getTimeAnOtherData(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd  HH:mm").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "δ֪";
        }
    }

    /**
     * ������ʽ��ʱ���ʽ����Ʒ��ͳһ��
     *
     * @param dateStr
     * @return �磺 2015-09-15 15:15
     */
    public static final String getTimeYMDHMData(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd  HH:mm").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * ������ʽ��ʱ���ʽ����Ʒ��ͳһ��
     *
     * @param dateStr
     * @return �磺 2015-09-15
     */
    public static final String getTimeyMdData(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            Date date =sdf.parse(dateStr);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    } /**
     * ������ʽ��ʱ���ʽ����Ʒ��ͳһ��
     *
     * @param dateStr
     * @return �磺 2015-09-15
     */
    public static final String getTimeyMdData(String dateStr,String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date =sdf.parse(dateStr);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
