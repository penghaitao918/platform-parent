package com.fanfandou.platform.serv.activity.service;


import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.PromoteAwardPackage;
import com.fanfandou.platform.api.activity.service.PromoteAwardPackageService;
import com.fanfandou.platform.serv.activity.dao.PromoteAwardPackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongliang.
 * Descreption:礼包管理.
 * Date:2016/3/13
 */
@Service("promoteAwardPackageService")
public class PromoteAwardPackageServiceImpl extends BaseLogger implements PromoteAwardPackageService {

    @Autowired
    private PromoteAwardPackageMapper promoteAwardPackageMapper;



    @Override
    public void createAwardPackage(PromoteAwardPackage promoteAwardPackage) throws ServiceException {
        promoteAwardPackage.setCreateDate(new Date());
        this.promoteAwardPackageMapper.insert(promoteAwardPackage);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void updateAwardPackage(PromoteAwardPackage promoteAwardPackage) throws ServiceException {
        this.promoteAwardPackageMapper.updateByPrimaryKey(promoteAwardPackage);
    }

    @Override
    public PageResult getAwardPackage(Integer gameId, String packageName, Page page) throws ServiceException {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (gameId == -1) {
            gameId = null;
        }
        if (packageName == null || packageName.equals("")) {
            packageName = "%%";
        }

        String packageNameStr = '%' + packageName + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();

        Map paramMap = new HashMap();
        paramMap.put("packageName", packageNameStr);
        paramMap.put("gameId", gameId);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        List<PromoteAwardPackage> promoteAwardPackageList = this.promoteAwardPackageMapper.pageList(paramMap);

        Map map = new HashMap();
        map.put("packageName", packageNameStr);
        map.put("gameIds", gameId);
        int totalCount = this.promoteAwardPackageMapper.totalCount(map);

        page.setTotalCount(totalCount);
        PageResult<PromoteAwardPackage> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(promoteAwardPackageList);
        return pageResult;
    }

    @Override
    public PromoteAwardPackage getAwardPackageById(Integer id) throws ServiceException {
        return this.promoteAwardPackageMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void deleteAwardPackageById(List<Integer> idList) throws ServiceException {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.promoteAwardPackageMapper.deleteByPrimaryKey(id);
        }
    }



    @Override
    public List<PromoteAwardPackage> getList(Integer gameId) throws ServiceException {
        if (gameId == -1) {
            gameId = null;
        }
        Map paramMap = new HashMap();
        paramMap.put("gameId", gameId);
        List<PromoteAwardPackage> promoteAwardPackageList = this.promoteAwardPackageMapper.getList(paramMap);
        return promoteAwardPackageList;
    }
}
