package com.fanfandou.admin.operation.service.impl;

import com.fanfandou.admin.operation.dao.ApproveMapper;
import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.admin.operation.service.ApproveService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service实现类
 */
@Service("approveService")
public class ApproveServiceImpl implements ApproveService {
    @Autowired
    private ApproveMapper approveMapper;

    /**
     * 根据邮件状态，审批状态查询订单数
     * @param sendStatus 邮件状态
     * @param approvalStatus 审批状态
     * @return 数量统计
     */
    @Override
    public int selectCountByMid(Integer sendStatus, Integer approvalStatus) {
        Map paramMap = new HashMap();

        paramMap.put("sendStatus", sendStatus);
        paramMap.put("approvalStatus", approvalStatus);
        return this.approveMapper.selectCountByMid(paramMap);
    }

    /**
     * 查询所有.
     */
    public List<Approve> selectAll() {
        return this.approveMapper.selectAll();
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<Approve> getMailOrderPageList(String mailTitleStr, Page page, String from, String to,
                                                    int typeCode, Integer sendType, Integer mailType,
                                                    Integer sendStatus, Integer approvalStatus,
                                                    Integer gameId) throws Exception {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("a.id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (mailTitleStr == null || mailTitleStr.equals("")) {
            mailTitleStr = "%%";
        }

        if (gameId == null) {
            gameId = -1;
        }
        if (gameId == 0) {
            gameId = -1;
        }

        if (sendType == null) {
            sendType = 0;
        }
        if (mailType == null) {
            mailType = 0;
        }
        if (sendStatus == null) {
            sendStatus = 0;
        }
        if (approvalStatus == null) {
            approvalStatus = 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate;
        Date toDate;
        if (from == null) {
            from = "1900-01-01 00:00:00";
            fromDate = sdf.parse(from);
        } else {
            fromDate = sdf.parse(from);
        }

        if (to == null) {
            toDate = new Date();
        } else {
            toDate = sdf.parse(to);
        }
        String mailTitle = '%' + mailTitleStr + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();

        Map paramMap = new HashMap();
        paramMap.put("mailTitle", mailTitle);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        paramMap.put("from", fromDate);
        paramMap.put("to", toDate);
        paramMap.put("gameId", gameId);
        paramMap.put("mailType", mailType);
        paramMap.put("sendStatus", sendStatus);
        paramMap.put("approvalStatus", approvalStatus);
        paramMap.put("sendType", sendType);

        List<Approve> pageList = new ArrayList<>();
        if (typeCode == 1) {
            pageList = this.approveMapper.orderPageList(paramMap);
        }
        if (typeCode == 2) {
            pageList = this.approveMapper.approvePageList(paramMap);
        }

        Map map = new HashMap();
        map.put("mailTitle", mailTitle);
        map.put("from", fromDate);
        map.put("to", toDate);
        map.put("gameId", gameId);
        map.put("mailType", mailType);
        map.put("sendStatus", sendStatus);
        map.put("approvalStatus", approvalStatus);
        map.put("sendType", sendType);

        int totalCount = 0;

        if (typeCode == 1) {
            totalCount = this.approveMapper.orderCount(map);
        }
        if (typeCode == 2) {
            totalCount = this.approveMapper.approveCount(map);
        }
        page.setTotalCount(totalCount);
        PageResult<Approve> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(pageList);
        return pageResult;
    }

    /**
     * 添加.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addApprove(Approve approve) {
        this.approveMapper.insert(approve);
    }

    /**
     * 删除.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delApprove(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.approveMapper.delete(id);
        }
    }

    /**
     * 删除ByOrderId.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delByOrderId(int orderId) {

        this.approveMapper.deleteByOrderId(orderId);

    }

    /**
     * 更新.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updApprove(Approve approve) {
        this.approveMapper.update(approve);
    }

    /**
     * 更新ByOrderId.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updByOrderId(Approve approve) {
        this.approveMapper.updateByOrderId(approve);
    }

    /**
     * 通过id查找.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Approve selApproveById(int id) {
        return this.approveMapper.selectById(id);
    }

    /**
     * 通过OrderId查找.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Approve selByOrderId(int id) {
        return this.approveMapper.selectByOrderId(id);
    }


}
