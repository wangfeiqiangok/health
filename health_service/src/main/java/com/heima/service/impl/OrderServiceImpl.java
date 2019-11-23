package com.heima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.heima.common.MessageConstant;
import com.heima.dao.MemberDao;
import com.heima.dao.OrderDao;
import com.heima.dao.OrderSettingDao;
import com.heima.exception.HealthException;
import com.heima.pojo.Member;
import com.heima.pojo.Order;
import com.heima.pojo.OrderSetting;
import com.heima.service.OrderService;
import com.heima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    @Transactional
    public int addOrder(Map<String, String> orderInfo) throws HealthException {
        String orderDate = orderInfo.get("orderDate");
        OrderSetting orderSetting=orderSettingDao.findByOrderDate(orderDate);
        //通过日期查询是否预约,如果返回值为空,不能预约,报错提示
        if (null==orderSetting){
            throw new HealthException(MessageConstant.SELECTED_DATE_CANNOT_ORDER);

        }
        //如果可预约数大于预约数,报错
        if (orderSetting.getReservations()>=orderSetting.getNumber()){

            throw new HealthException(MessageConstant.ORDER_FULL);
        }
        //判断是否是会员
        Member member=memberDao.findByTelephone(orderInfo.get("telephone"));
      if (null==member){
          member=new Member();
          member.setRegTime(new Date());
          member.setPhoneNumber(orderInfo.get("telephone"));
          member.setIdCard(orderInfo.get("idCard"));
          member.setName(orderInfo.get("name"));
          member.setSex(orderInfo.get("sex"));
          memberDao.add(member);
      }
      //取出会员编号
        Integer memberId = member.getId();
        Order order = new Order();
        //添加到order类中
        order.setMemberId(memberId);

        try {
            order.setOrderDate(DateUtils.parseString2Date(orderDate));

        } catch (Exception e) {
            e.printStackTrace();
            throw new HealthException(MessageConstant.ORDER_FAIL);
        }
        //判断是否预约过,没预约就添加
        List<Order> orders=orderDao.findByCondition(order);
        if (null!=orders&&orders.size()>0){
            throw new HealthException(MessageConstant.HAS_ORDERED);

        }
        order.setPackageId(Integer.valueOf(orderInfo.get("packageId")));

        order.setOrderStatus("未到诊");

        order.setOrderType(orderInfo.get("orderType"));

        orderDao.add(order);

        //预约成功增加
        orderSettingDao.editReservationsByOrderDate(1,orderDate);

        return order.getId();



    }

    @Override
    public Map<String, Object> findById(int id) {
        return orderDao.findById(id);
    }
}
