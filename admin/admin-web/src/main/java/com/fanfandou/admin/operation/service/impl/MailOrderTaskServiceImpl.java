package com.fanfandou.admin.operation.service.impl;

import com.fanfandou.admin.operation.dao.MailOrderTaskMapper;
import com.fanfandou.admin.operation.entity.MailOrderTask;
import com.fanfandou.admin.operation.service.MailOrderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service实现类
 */
@Service("mailOrderTaskService")
public class MailOrderTaskServiceImpl implements MailOrderTaskService {

    @Autowired
    private MailOrderTaskMapper mailOrderTaskMapper;

    /**
     * 查询所有.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<MailOrderTask> selectAll() {
        return this.mailOrderTaskMapper.selectAll();
    }

    /**
     * 添加.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addMailOrderTask(MailOrderTask mailOrderTask) {
        this.mailOrderTaskMapper.insert(mailOrderTask);
    }

    /**
     * 删除.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delMailOrderTask(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.mailOrderTaskMapper.delete(id);
        }
    }

    /**
     * 更新.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updMailOrderTask(MailOrderTask mailOrderTask) {
        this.mailOrderTaskMapper.update(mailOrderTask);
    }

    /**
     * 通过id查找.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public MailOrderTask selMailOrderTaskById(int id) {
        return this.mailOrderTaskMapper.selectById(id);
    }

    /**
     * 查询未发送.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<MailOrderTask> selectNotSend() {
        return mailOrderTaskMapper.selectNotSend();
    }

    /**
     * 查询错误原因.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public String selFailedReason(int mailOrderId) {
        return mailOrderTaskMapper.selFailedReason();
    }
}
