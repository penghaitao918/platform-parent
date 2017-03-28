package com.fanfandou.platform.serv.activity.service;


import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.common.util.GenCodeUtil;
import com.fanfandou.platform.api.activity.entity.PromoteAwardPackage;
import com.fanfandou.platform.api.activity.entity.PromoteCode;
import com.fanfandou.platform.api.activity.entity.PromoteCodeBatch;
import com.fanfandou.platform.api.activity.entity.PromoteCodeExample;
import com.fanfandou.platform.api.activity.exception.ActivityException;
import com.fanfandou.platform.api.activity.service.PromoteCodeBatchService;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.serv.activity.dao.PromoteAwardPackageMapper;
import com.fanfandou.platform.serv.activity.dao.PromoteCodeBatchMapper;
import com.fanfandou.platform.serv.activity.dao.PromoteCodeMapper;
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
 * Created by zhongliang.
 * Descreption:礼包批次管理.
 * Date:2016/3/13
 */
@Service("promoteCodeBatchService")
public class PromoteCodeBatchServiceImpl extends BaseLogger implements PromoteCodeBatchService {

    @Autowired
    private PromoteCodeBatchMapper promoteCodeBatchMapper;

    @Autowired
    private PromoteAwardPackageMapper promoteAwardPackageMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private PromoteCodeMapper promoteCodeMapper;

    @Autowired
    private CacheService cacheService;

    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    @Override
    public void createCodeBatch(PromoteCodeBatch promoteCodeBatch) throws ServiceException {
        promoteCodeBatch.setCreateDate(new Date());
        promoteCodeBatch.setUsedAmount(0);
        this.promoteCodeBatchMapper.insert(promoteCodeBatch);
    }

    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    @Override
    public void updateCodeBatch(PromoteCodeBatch promoteCodeBatch) throws ServiceException {
        this.promoteCodeBatchMapper.updateByPrimaryKey(promoteCodeBatch);
    }

    @Override
    public PageResult getCodeBatch(Integer gameId, String batchName, Page page, String from, String to) throws Exception {

        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (gameId == -1) {
            gameId = null;
        }
        if (batchName == null || batchName.equals("")) {
            batchName = "%%";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = null;
        Date toDate = null;
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

        String packageNameStr = '%' + batchName + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();

        Map paramMap = new HashMap();
        paramMap.put("batchName", packageNameStr);
        paramMap.put("gameId", gameId);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        paramMap.put("from", fromDate);
        paramMap.put("to", toDate);
        List<PromoteCodeBatch> promoteCodeBatchList = this.promoteCodeBatchMapper.pageList(paramMap);

        Map map = new HashMap();
        map.put("batchName", packageNameStr);
        map.put("gameId", gameId);
        map.put("from", fromDate);
        map.put("to", toDate);
        int totalCount = this.promoteCodeBatchMapper.totalCount(map);

        page.setTotalCount(totalCount);
        PageResult<PromoteCodeBatch> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(promoteCodeBatchList);
        return pageResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PromoteCodeBatch getCodeBatchById(Integer id) throws ServiceException {
        return this.promoteCodeBatchMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteCodeBatchById(List<Integer> idList) throws ServiceException {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.promoteCodeBatchMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<String> generatePromoteCode(PromoteCodeBatch promoteCodeBatch) throws ServiceException {
        //获取礼包码个数
        int codeNum = promoteCodeBatch.getAmount();
        List<String> codes = new ArrayList<>();
        Date date = new Date();
        if (codeNum > 0) {
            for (int i = 0; i < codeNum; i++) {
                long seqValue = tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_ACTIVITY_PROMOTE_CODE);
                String code = GenCodeUtil.generateCode(String.format("%07d", i));
                PromoteCode promoteCode = new PromoteCode();
                promoteCode.setCodeId(seqValue);
                promoteCode.setBatchId(promoteCodeBatch.getBatchId());
                promoteCode.setCreateDate(date);
                promoteCode.setDeliverStatus(ActStatus.UNACT);
                promoteCode.setValidStatus(ValidStatus.VALID);
                promoteCode.setPromoteCode(code);
                promoteCode.setDrawSiteId(promoteCodeBatch.getSiteId());
                promoteCode.setDrawStatus(ActStatus.UNACT);
                promoteCode.setDrawGameId(promoteCodeBatch.getGameId());
                promoteCodeMapper.insertSelective(promoteCode);
                //cacheService.hPut(PublicCachedKeyConstant.ACTIVITY_PROMOTE_CODE, code, promoteCode);
                codes.add(code);
            }
        }
        return codes;
    }

    @Override
    public String checkPromoteCode(int gameId, int siteId, int roleId, String code) throws ServiceException {
        /*PromoteCode promoteCode = cacheService.hGet(PublicCachedKeyConstant.ACTIVITY_PROMOTE_CODE, code,
                PromoteCode.class);*/
        PromoteCodeExample promoteCodeExample = new PromoteCodeExample();
        promoteCodeExample.createCriteria().andPromoteCodeEqualTo(code);
        PromoteCode promoteCode = promoteCodeMapper.selectByExample(promoteCodeExample).get(0);
        //PromoteCode promoteCode = promoteCodeMapper.selectByPrimaryKey()
        //礼包码校验
        if (promoteCode == null) {
            throw new ActivityException(ActivityException.GAME_ACITIVITT_PROMOTE_CODE_UNEXIST, "礼包码不存在");
        }

        if (gameId != promoteCode.getDrawGameId()) {
            throw new ActivityException(ActivityException.GAME_ACITIVITT_PROMOTE_CODE_NOTMATCH, "游戏不匹配");
        }

        if (siteId != 0 && promoteCode.getDrawSiteId() != siteId) {
            throw new ActivityException(ActivityException.GAME_ACITIVITT_PROMOTE_CODE_NOTMATCH, "渠道不匹配");
        }
        long nowDate = new Date().getTime();
        //判断礼包码类型
        //根据激活码获取批次信息
        PromoteCodeBatch codeBatch = promoteCodeBatchMapper.selectByPrimaryKey(promoteCode.getBatchId());
        //再根据批次获取到包裹信息
        PromoteAwardPackage awardPackage = promoteAwardPackageMapper.selectByPrimaryKey(codeBatch.getPackageId());
        String tips = null;

        switch (awardPackage.getPromoteCategory().getId()) {

            case 1:
                //激活码，只需判定是否已使用就可以了。
                if (promoteCode.getDrawStatus().equals(ActStatus.ACTED) || !promoteCode.getValidStatus().equals(ValidStatus.VALID)) {
                    throw new ActivityException(ActivityException.GAME_ACTIVITY_PROMOTE_CODE_DULPLICATE, "激活码已被使用或者无效");
                }
                long promoteEndDate = codeBatch.getAvailableEndDate().getTime();
                if (nowDate > promoteEndDate) {
                    throw new ActivityException(ActivityException.GAME_ACTIVITY_PROMOTE_CODE_OUTOFDATE, "激活码已过期");
                }
                promoteCode.setDeliverDate(new Date());
                promoteCode.setDrawRoleId(roleId + "");
                promoteCode.setDrawStatus(ActStatus.ACTED);
                promoteCode.setValidStatus(ValidStatus.INVALID);
                cacheService.hPut(PublicCachedKeyConstant.ACTIVITY_PROMOTE_CODE, code, promoteCode);
                tips = codeBatch.getBatchDesc();
                break;
            default:
                break;
        }

        return tips;
    }

    @Override
    public List getPromoteCodeList() throws Exception {
        return cacheService.getList(PublicCachedKeyConstant.ACTIVITY_PROMOTE_CODE, List.class);
    }
}
