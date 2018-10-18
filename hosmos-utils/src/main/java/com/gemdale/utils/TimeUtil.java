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
     * 获取现在年月日日期
     * yyyy年MM月dd日
     *
     * @return
     */
    public static final String getDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获取现在年月日日期
     * "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static final String getDateTimeNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 返回现在的时间字符串 <br/>
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
     * 时间比较大小 true == 大于 或者相等
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
     * 将字符串时间转换为Date
     *
     * @return 若转换失败，返回null
     */
    public static final Date parseDate(String dateStr) {
        try {
            return getFormat().parse(dateStr);
        } catch (ParseException e) {
            //LogUtil.e("转换时间失败,dateStr: " + dateStr);
            return null;
        }
    }

    /**
     * 返回指定日期距离现在的时间长度描述
     *
     * @param dateStr
     * @return
     */
    public static final String descToNow(String dateStr) {
        Date endDate = parseDate(dateStr);
        if (endDate == null) {
            return "刚刚";
        }
        long seconds = (System.currentTimeMillis() - endDate.getTime()) / 1000;
        if (seconds < 0) {
            return "刚刚";
        }
        int s = (int) (seconds % 60);
        seconds /= 60; // 总时间转化为分
        int m = (int) (seconds % 60);
        seconds /= 60; // 总时间转化为时
        int h = (int) (seconds % 24);
        seconds /= 24; // 总时间转化为日
        int d = (int) (seconds % 30);
        seconds /= 30; // 总时间转化为月
        int mo = (int) (seconds % 12);
        int y = (int) (seconds /= 12); // 总时间转化为年
        if (y > 0) {
            return y + "年前";
        } else if (mo > 0) {
            return mo + "月前";
        } else if (d > 0) {
            return d + "天前";
        } else if (h > 0) {
            return h + "小时前";
        } else if (m > 0) {
            return m + "分前";
        } else if (s > 30) {
            return "半分钟前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 返回指定日期，当距离小于等于某些天的距离时候返回距离现在时间长度描述，当大于某些天返回的现在的时间
     *
     * @param dateStr //时间
     * @param data    //某些临界天数
     * @return
     */
    public static final String descToNowAfterOthers(String dateStr, int data) {
        String s;
        Date endDate = parseDate(dateStr);
        long seconds = (System.currentTimeMillis() - endDate.getTime()) / 1000;
        seconds /= 60; // 总时间转化为分
        seconds /= 60; // 总时间转化为时
        seconds /= 24; // 总时间转化为日
        int d = (int) (seconds % 30);
        seconds /= 30; // 总时间转化为月
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
     * 返回指定日期距离现在的天数
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
                return "（" + ((int) d) + "天后截止）";
            } else if (d >= 0) {
                return "（半天后截止）";
            } else {
                return "";
            }
        }
    }

    /**
     * 返回中文描述的时间
     *
     * @param dateStr
     * @return 如： 9月6日 12:20
     */
    public static final String toChinestDescTime(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "获取时间失败";
        } else {
            try {
                return new SimpleDateFormat("MM月dd日 HH:mm").format(date);
            } catch (Exception e) {
                return "暂无法获取时间";
            }
        }
    }

    /**
     * 返回中文描述的时间
     *
     * @param dateStr
     * @return 如： 9月6日
     */
    public static final String toChinestTime(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "获取时间失败";
        } else {
            try {
                return new SimpleDateFormat("MM月dd日").format(date);
            } catch (Exception e) {
                return "暂无法获取时间";
            }
        }
    }

    /**
     * 返回中文描述的时间
     *
     * @param dateStr
     * @return 如： 9月6日 12:20
     */
    public static final String toChinestDescTime2(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "获取时间失败";
        } else {
            try {
                return new SimpleDateFormat("yyy年MM月dd日").format(date);
            } catch (Exception e) {
                return "暂无法获取时间";
            }
        }
    }

    /**
     * 返回英文描述的时间
     *
     * @param dateStr
     * @return 如： 09-06 12:30
     */
    public static final String toEnglishDescTime(String dateStr) {
        // TODO: 2017-12-18 这句会出错
        Date date = parseDate(dateStr);
        if (date == null) {
            return "获取时间失败";
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
     * 返回时间类型
     * 如 09-06
     * /月/日
     */
    public static final String toEnglishSmollTime(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "获取时间失败";
        } else {
            try {
                return new SimpleDateFormat("MM-dd").format(date);
            } catch (Exception e) {
                return "暂无法获取时间";
            }
        }
    }

    /**
     * 返回发布日期
     *
     * @param dateStr
     * @return 如： 发布于2015-09-04
     */
    public static final String getPubDate(String dateStr) {
        Date date = parseDate(dateStr);
        if (date == null) {
            return "获取时间失败";
        } else {
            try {
                return new SimpleDateFormat("发布于yyyy-MM-dd").format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * 返回区间日期
     *
     * @param fromDate
     * @param toDate
     * @return 如： 2015年 9月5日 至 9月15日
     */
    @SuppressWarnings("deprecation")
    public static final String getBetweenDate(String fromDate, String toDate) {
        Date from = parseDate(fromDate);
        Date to = parseDate(toDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("M月d日");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年 M月d日");
        if (from.getYear() == to.getYear()) {
            return sdf2.format(from) + " 至 " + sdf1.format(to);
        } else {
            return sdf2.format(from) + " 至 " + sdf2.format(to);
        }
    }

    /**
     * 转换为日期时间
     *
     * @param dateStr
     * @return 如： 2015-11-25 13:20
     */
    public static final String parseDateTime(String dateStr) {
        try {
            Date date = parseDate(dateStr);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 返回时间
     *
     * @param dateStr
     * @return 如： 14:30
     */
    public static final String getTime(String dateStr) {
        try {
            return new SimpleDateFormat("HH:mm").format(parseDate(dateStr));
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 返回月份描述
     *
     * @param dateStr
     * @return 如： 八月
     */
    @SuppressWarnings("deprecation")
    public static final String getMonthDesc(String dateStr) {
        String[] desc = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月",};
        try {
            return desc[parseDate(dateStr).getMonth()];
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 返回月日
     *
     * @param dateStr
     * @return 如： 11月21日
     */
    public static final String getMonthAndDay(String dateStr) {
        try {
            return new SimpleDateFormat("MM月dd日").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 返回平常格式日期
     *
     * @param dateStr
     * @return 如： 2015-09-15
     */
    public static final String getSimpleDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 返回给出时间字符串是否是现在之后的时间
     *
     * @param dateStr
     * @return 出现异常由返回 false
     */
    public static final boolean isAfterNow(String dateStr) {
        try {
            return parseDate(dateStr).after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回给出时间字符串是否是现在之前的时间
     *
     * @param dateStr
     * @return 出现异常由返回 false
     */
    public static final boolean isBeforeNow(String dateStr) {
        try {
            return parseDate(dateStr).before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回两个时间字符串的间隔描述 <br />
     * 需要保证 beginTime 与 endTime 日期相同
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
            return "未知";
        }
    }

    /**
     * 返回新式的时间格式（设计风格不统一）
     *
     * @param dateStr
     * @return 如： 2015-09-15
     */
    public static final String getTimeAnOtherData(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd  HH:mm").format(parseDate(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 返回新式的时间格式（设计风格不统一）
     *
     * @param dateStr
     * @return 如： 2015-09-15 15:15
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
     * 返回新式的时间格式（设计风格不统一）
     *
     * @param dateStr
     * @return 如： 2015-09-15
     */
    public static final String getTimeyMdData(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            Date date = sdf.parse(dateStr);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 返回新式的时间格式（设计风格不统一）
     *
     * @param dateStr
     * @return 如： 2015-09-15
     */
    public static final String getTimeyMdData(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateStr);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}