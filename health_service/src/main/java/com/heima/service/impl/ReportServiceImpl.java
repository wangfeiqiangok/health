package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.heima.dao.MemberDao;
import com.heima.dao.OrderDao;
import com.heima.dao.PackageDao;
import com.heima.dao.ReportDao;
import com.heima.service.ReportService;

import com.heima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDao reportDao;

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PackageDao packageDao;

    @Override
    public Map<String, Object> getBusinessReport() {
        //获取当前日期
        String today = DateUtils.parseDate2String(DateUtils.getToday(),"yyyy-MM-dd");
        String reportDate = today;

        //获取本周一的日期
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday(),"yyyy-MM-dd");
        //获取本周日的日期
        String thisWeekSunday = DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek(),"yyyy-MM-dd");

        //获取本月第一天
        String firstDayOfThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDayOfThisMonth(),"yyyy-MM-dd");

        //获取本月最后一天
        String lastDayOfThisMonth = DateUtils.parseDate2String(DateUtils.getLastDayOfThisMonth(),"yyyy-MM-dd");

        //今日新增会员数
        Integer todayNewMember=memberDao.findMember(today);

        //总会员数
        Integer totalMember=memberDao.findMemberTotalCount();

        //本周新增会员数
        Integer thisWeekNewMember=memberDao.findMemberCountAfterDate(thisWeekMonday);

        //本月新增会员数
        Integer thisMonthNewMember=memberDao.findMemberCountAfterDate(firstDayOfThisMonth);

        //今日预约数
        Integer todayOrderNumber = orderDao.findOrderCountByDate(today);

        //本周预约数
        Integer thisWeekOrderNumber = orderDao.findOrderCountBetweenDate(thisWeekMonday,thisWeekSunday);

        //本月预约数
        Integer thisMonthOrderNumber = orderDao.findOrderCountBetweenDate(firstDayOfThisMonth, lastDayOfThisMonth);


        //今日到诊数
        Integer todayVisitsNumber = orderDao.findVisitsCountByDate(today);

        //本周到诊数
        Integer thisWeekVisitsNumber = orderDao.findVisitsCountAfterDate(thisWeekMonday);

        //本月到诊数
        Integer thisMonthVisitsNumber = orderDao.findVisitsCountAfterDate(firstDayOfThisMonth);

        // ===============热门套餐 取前4个套餐=========================
        List<Map<String,Object>> hotPackage = packageDao.findHotPackage();


        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("reportDate",reportDate);
        resultMap.put("todayNewMember",todayNewMember);
        resultMap.put("totalMember",totalMember);
        resultMap.put("thisWeekNewMember",thisWeekNewMember);
        resultMap.put("thisMonthNewMember",thisMonthNewMember);
        resultMap.put("todayOrderNumber",todayOrderNumber);
        resultMap.put("todayVisitsNumber",todayVisitsNumber);
        resultMap.put("thisWeekOrderNumber",thisWeekOrderNumber);
        resultMap.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        resultMap.put("thisMonthOrderNumber",thisMonthOrderNumber);
        resultMap.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        resultMap.put("hotPackage",hotPackage);

        return resultMap;
    }
}
