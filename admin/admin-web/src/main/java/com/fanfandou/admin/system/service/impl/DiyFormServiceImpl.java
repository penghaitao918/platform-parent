package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.system.dao.DiyFormMapper;
import com.fanfandou.admin.system.entity.DiyForm;
import com.fanfandou.admin.system.service.DiyFormService;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongliang on 2016/3/15.
 * Description service实现类
 */
@Service("customFormService")
public class DiyFormServiceImpl implements DiyFormService {
    @Autowired(required = true)
    private CacheService cacheService;
    @Autowired
    private DiyFormMapper diyFormMapper;

    /**
     * 更新数据字典缓存
     *
     * @throws Exception
     */
    public void updateDiyFormCache() throws Exception {
        List<DiyForm> diyForms = diyFormMapper.getAll();
        for (DiyForm diyForm : diyForms) {
            cacheService.hPut(PublicCachedKeyConstant.CUSTOMFORM_LIST, diyForm.getFormCode(), diyForm);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void delCustomList(String key) throws Exception {
        //更新缓存
        cacheService.hDel(PublicCachedKeyConstant.CUSTOMFORM_LIST, key);
        //更新数据库
        diyFormMapper.deleteById(key);
        //更新缓存
        updateDiyFormCache();
    }


    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void updateDiyForm(DiyForm diyForm) throws Exception {

       /* DiyForm */
        DiyForm data = cacheService.hGet(PublicCachedKeyConstant.CUSTOMFORM_LIST, diyForm.getFormCode(), DiyForm.class);
        if (data == null) {
            data = diyFormMapper.selById(diyForm.getFormCode());
            updateDiyFormCache();
        }

        if (data != null && data.getFormCode().equals(diyForm.getFormCode()) && data.getCreateTime().compareTo(diyForm.getCreateTime()) == 0) {
            //更新数据库
            diyFormMapper.update(diyForm);
            //更新数据字典
            cacheService.hPut(PublicCachedKeyConstant.CUSTOMFORM_LIST, diyForm.getFormCode(), diyForm);
        } else {
            throw new ServiceException(AdminException.ADMIN_WRONG_DIYFORMCODE);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void insertCustomForm(DiyForm diyForm) throws Exception {
        DiyForm data = diyFormMapper.selById(diyForm.getFormCode());
        DiyForm diyForms = cacheService.hGet(PublicCachedKeyConstant.CUSTOMFORM_LIST, diyForm.getFormCode(), DiyForm.class);

        if (data != null || diyForms != null) {
            updateDiyFormCache();
            throw new ServiceException(AdminException.ADMIN_WRONG_DIYFORMCODE);
        } else {
            diyForm.setCreateTime(new Date());
            //更新数据库
            diyFormMapper.insert(diyForm);
            //更新数据字典
            cacheService.hPut(PublicCachedKeyConstant.CUSTOMFORM_LIST, diyForm.getFormCode(), diyForm);
        }

    }

    @Override
    public List<DiyForm> getCustomForm() throws Exception {
        List<DiyForm> uniformList = cacheService.hGetValues(PublicCachedKeyConstant.CUSTOMFORM_LIST, DiyForm.class);

        //如果缓存中没有数据，查询数据库
        if (uniformList == null || uniformList.size() == 0  ) {
            updateDiyFormCache();
            return diyFormMapper.getAll();
        } else {
            return uniformList;
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public DiyForm getCustomFormByKey(String key) throws Exception {
        DiyForm diyForm = cacheService.hGet(PublicCachedKeyConstant.CUSTOMFORM_LIST, key, DiyForm.class);
        if (diyForm == null) {
            diyForm = diyFormMapper.selById(key);
            updateDiyFormCache();
            return diyForm;
        } else {
            return diyForm;
        }

    }


}
