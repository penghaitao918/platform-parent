package com.fanfandou.platform.serv.game.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.entity.GameToy;
import com.fanfandou.platform.api.game.entity.GameToyBatch;
import com.fanfandou.platform.api.game.entity.ToyBindStatus;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.GameToyService;
import com.fanfandou.platform.serv.game.ToyCodeCheck;
import com.fanfandou.platform.serv.game.dao.GameToyBatchMapper;
import com.fanfandou.platform.serv.game.dao.GameToyMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:玩具接口实现.
 * Date:2016/5/25
 */
@Service("gameToyService")
public class GameToyServiceImpl extends BaseLogger implements GameToyService {

    @Autowired
    private GameToyMapper gameToyMapper;

    @Autowired
    private GameToyBatchMapper gameToyBatchMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Override
    public void createBatch(String bachCode, int maxCode, Date proTime, int toyType, int gameId, int siteId) throws ServiceException {
        Date createTime = new Date();
        //先取到当前批次最大值
        if (maxCode < gameToyBatchMapper.getMaxCode()) {
            throw new GameException(GameException.GAME_TOY_BATCH_CODE_REPEAT,"批次号创建重复");
        }
        GameToyBatch gameToyBatch = new GameToyBatch();
        gameToyBatch.setBatchCode(bachCode);
        gameToyBatch.setMaxCode(maxCode);
        gameToyBatch.setActiveCode(0);
        gameToyBatch.setToyType(toyType);
        gameToyBatch.setCreateTime(proTime);
        gameToyBatch.setGameId(gameId);
        gameToyBatch.setSiteId(siteId);
        gameToyBatchMapper.insertSelective(gameToyBatch);
    }

    @Override
    public List<Integer> getActedToyType(long userId, int toyType) throws ServiceException {
        logger.info("getActedToyType ");
        return gameToyMapper.getActedToyType(userId);
    }

    @Override
    public void saveBatch(List<GameToy> toys) {

    }

    @Override
    public void bindToUser(long toyGuid, int toyType, long userId, int siteId, int gameId) throws ServiceException {
        logger.info("bindToUser ");
        //首先拿到当前类型所有的批次号
        List<GameToyBatch> typeBatches = gameToyBatchMapper.selectBatchByType(toyType);
        //根据批次号排序，获取可激活的批次
        GameToyBatch cuurentToyBatch = null;
        for (GameToyBatch gameToyBatch : typeBatches) {
            if (gameToyBatch.getActiveCode() < gameToyBatch.getMaxCode()) {
                cuurentToyBatch = gameToyBatch;
                break;
            }
        }

        if (cuurentToyBatch == null) {
            throw new GameException(GameException.GAME_TOYBATCH_ISNULL);
        }

        int currentMaxCode = gameToyBatchMapper.getMaxCode();
        if (!ToyCodeCheck.check(toyGuid, toyType, currentMaxCode)) {
            throw new GameException(GameException.GAME_TOY_CODE_INVALIED, "激活码无效");
        }
        if (userId == 0 || gameId == 0) {
            throw new GameException(GameException.GAME_LACK_BIND_TOY_PARAMS, "缺少绑定参数");
        }
        GameToy gt = gameToyMapper.checkBindCode(toyGuid);
        if (gt != null) {
            if (gt.getBindUid() == userId) {
                throw new GameException(GameException.GAME_TOY_CODE_BOUND_FOR_SELF, "玩具已经被本账号绑定");
            }
            throw new GameException(GameException.GAME_TOY_CHECK_OTHER_BOUND, "玩具已被绑定");
        }
        long seqValue = tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_GAME_TOY);
        long toyCode = 1;
        List<GameToy> gameToyList = gameToyMapper.getMaxToyCode();
        if (CollectionUtils.isEmpty(gameToyList)) {
            toyCode = 1;
        } else {
            GameToy gameToys = gameToyList.get(0);
            long currentCode = gameToyMapper.getMaxToyCode().get(0).getToyCode();
            toyCode = gameToys.getToyCode() + 1;
            if (currentCode >= toyCode) {
                toyCode = currentCode + 1;
            }
        }
        logger.info("bindToUser >>>>>>>> toyCode = " + toyCode);
        GameToy gameToy = new GameToy();
        gameToy.setBindGame(gameId);
        gameToy.setGameId(gameId);
        gameToy.setSiteId(siteId);
        gameToy.setCreateTime(new Date());
        gameToy.setBindSite(siteId);
        gameToy.setToyType(toyType);
        gameToy.setToyGuid(toyGuid);
        gameToy.setBindTime(new Date());
        gameToy.setBindUid(userId);
        gameToy.setBatchId(cuurentToyBatch.getBatchId());
        gameToy.setProdTime(new Date());

        gameToy.setBindStatus(ToyBindStatus.BOUND.getId());
        gameToy.setId(seqValue);

        //激活成功，激活数量+1
        cuurentToyBatch.setActiveCode(cuurentToyBatch.getActiveCode() + 1);
        gameToyBatchMapper.updateByPrimaryKeySelective(cuurentToyBatch);
        logger.info("bindToUser >>>>>>>>batch update success");
        gameToy.setToyCode(toyCode);
        gameToyMapper.insertSelective(gameToy);
        logger.info("bindToUser >>>>>>>>toycode insert success");


    }

    @Override
    public boolean verifyToy(long toyGuid, int toyType, long userId, int siteId, int gameId) throws ServiceException {
        //GameToy gameToyUid = gameToyMapper.selectByUser(userId);
        GameToy gameToyGuid = gameToyMapper.selectByToyGuid(toyGuid);

        int currentMaxCode = gameToyBatchMapper.getMaxCode();
        logger.debug("verifyToy >> currentMaxCode = " + currentMaxCode);
        if (!ToyCodeCheck.check(toyGuid, toyType, currentMaxCode)) {
            throw new GameException(GameException.GAME_TOY_GUID_ERROR, "激活码无效");
        }
        if (gameToyGuid != null) {
            //说明该激活码存在
            if (userId != gameToyGuid.getBindUid()) {
                throw new GameException(GameException.GAME_TOY_CHECK_OTHER_BOUND, "玩具已经被其他账号绑定");
            } else {
                throw new GameException(GameException.GAME_TOY_CODE_BOUND_FOR_SELF, "玩具已被本账号绑定");
            }
        } else {
            logger.info("verifyToy gameToyGuid is null");
            return true;
        }
    }

    @Override
    public GameToy getGameToy(long toyGuid) {
        return gameToyMapper.selectByToyGuid(toyGuid);
    }

    @Override
    public PageResult<GameToy> getToys(Page page, GameToy toy, Date from, Date to) {
        logger.debug("enter getToys");
        if (page.getOrder() == null) {
            page.setOrder("id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }
        int startNum = (page.getPageIndex() - 1) * page.getPageSize();

        logger.info("getToys >>>>>> getToys  from = " + from + ", to = " + to);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("gameId", toy.getGameId());
        paramMap.put("siteId", toy.getSiteId());
        paramMap.put("bindGame", toy.getBindGame());
        paramMap.put("bindSite", toy.getBindSite());
        paramMap.put("bindArea", toy.getBindArea());
        paramMap.put("bindStatus", toy.getBindStatus());
        paramMap.put("bindTime", toy.getBindTime());
        paramMap.put("creatTime", toy.getCreateTime());
        paramMap.put("toyType", toy.getToyType());
        paramMap.put("from", from);
        paramMap.put("to", to);
        paramMap.put("batchCode", toy.getBatchId());
        paramMap.put("toyGuid", toy.getToyGuid());
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", page.getPageSize());
        paramMap.put("order", page.getOrder());
        paramMap.put("sort", page.getSort());


        List<GameToy> gametoysList = gameToyMapper.getGameToyBySelective(paramMap);
        int totalCount = gameToyMapper.totalCount(paramMap);
        page.setTotalCount(totalCount);
        PageResult<GameToy> pageResult = new PageResult<GameToy>();
        pageResult.setPage(page);
        pageResult.setRows(gametoysList);
        return pageResult;
    }

    @Override
    public PageResult<GameToyBatch> getToyBatches(Page page, GameToyBatch gameToyBatch, Date from, Date to)
            throws ServiceException {
        if (page.getOrder() == null) {
            page.setOrder("batch_id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }

        logger.info("getToyBatches >>>>>> getToys  from = " + from + ", to = " + to);
        int startNum = (page.getPageIndex() - 1) * page.getPageSize();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("batchCode", gameToyBatch.getBatchCode());
        paramMap.put("toyType", gameToyBatch.getToyType());
        paramMap.put("gameId", gameToyBatch.getGameId());
        paramMap.put("siteId", gameToyBatch.getSiteId());
        paramMap.put("from", from);
        paramMap.put("to", to);
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", page.getPageSize());
        paramMap.put("order", page.getOrder());
        paramMap.put("sort", page.getSort());
        List<GameToyBatch> gameToyBatchList = gameToyBatchMapper.selectGameToyBatchForPage(paramMap);
        int totalCount = gameToyBatchMapper.totalCount(paramMap);
        page.setTotalCount(totalCount);
        PageResult<GameToyBatch> pageResult = new PageResult<GameToyBatch>();
        pageResult.setPage(page);
        pageResult.setRows(gameToyBatchList);
        return pageResult;
    }

    public PageResult<GameToyBatch> getToyBatchesTest(Page page, GameToyBatch gameToyBatch, Date from, Date to)
            throws ServiceException {
        if (page.getOrder() == null) {
            page.setOrder("batch_id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }

        int startNum = (page.getPageIndex() - 1) * page.getPageSize();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("batchCode", gameToyBatch.getBatchCode());
        paramMap.put("toyType", gameToyBatch.getToyType());
        paramMap.put("gameId", gameToyBatch.getGameId());
        paramMap.put("siteId", gameToyBatch.getSiteId());
        paramMap.put("from", from);
        paramMap.put("to", to);
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", page.getPageSize());
        paramMap.put("order", page.getOrder());
        paramMap.put("sort", page.getSort());
        page.setPageIndex(startNum);
        List<GameToyBatch> gameToyBatchList = gameToyBatchMapper.selectGameToyBatchForPageTest(gameToyBatch,page);
        int totalCount = gameToyBatchMapper.totalCount(paramMap);
        page.setTotalCount(totalCount);
        PageResult<GameToyBatch> pageResult = new PageResult<GameToyBatch>();
        pageResult.setPage(page);
        pageResult.setRows(gameToyBatchList);
        return pageResult;
    }


    @Override
    public void invalidCode(List<Long> ids) throws ServiceException {
        for (Long codeId : ids) {
            GameToy gameToy = new GameToy();
            gameToy.setId(codeId);
            gameToy.setBindStatus(ToyBindStatus.INVALID.getId());
            gameToyMapper.updateByPrimaryKeySelective(gameToy);
        }
    }
}
