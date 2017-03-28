package com.fanfandou.admin.server.system.serviceImpl;

import com.fanfandou.admin.api.system.entity.ResEnum;
import com.fanfandou.admin.api.system.entity.Resource;
import com.fanfandou.admin.api.system.service.ResService;
import com.fanfandou.admin.server.system.dao.ResourceMapper;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.constant.RedisMessageTopicConstant;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.RedisMessagePublisher;
import com.fanfandou.common.service.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author
 * Description: 资源列表Service实现.
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResService {

    @Autowired(required = true)
    private ResourceMapper resourceMapper;

    @Autowired(required = true)
    private CacheService cacheService;

    @Autowired(required = true)
    private RedisMessagePublisher publisher;

    public List<Resource> selectAll() {
        return this.resourceMapper.selectAll();
    }

    public Resource selectById(int id) {
        return this.resourceMapper.selectById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addGameCode(Resource res) {
        res.setResType(ResEnum.game);
        res.setCreateTime(new Date());
        this.resourceMapper.isInsert(res.getResCode(), res.getUrl(), res.getResType(), res.getCreateTime(), res.getAvailable());
        updateGameCode(res.getResType());
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSiteCode(Resource res) {
        res.setResType(ResEnum.site);
        res.setCreateTime(new Date());
        this.resourceMapper.isInsert(res.getResCode(), res.getUrl(), res.getResType(), res.getCreateTime(), res.getAvailable());
        updateSiteCode(res.getResType());
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delGame(int id) {
        this.resourceMapper.delGame(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delResList(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.resourceMapper.delGame(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updateGame(Resource res) {
        this.resourceMapper.updateGame(res);
        if (res.getResType().getCode().equals("game")) {
            updateGameCode(res.getResType());
        } else {
            updateSiteCode(res.getResType());
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updateGameCode(ResEnum resType) {
        Map<Integer, GameCode> gameMap = new HashMap<Integer, GameCode>();

        //查询所有game类型的resource
        List<Resource> resList = this.resourceMapper.getResByType(resType.getId());

        int gameId;
        String code;
        for (Resource r : resList) {
            GameCode gameCode = new GameCode();
            gameId = r.getId();
            code = r.getResCode();
            gameCode.setId(gameId);
            gameCode.setCode(code);
            gameMap.put(gameId, gameCode);
        }
        //将map放到redis
        cacheService.put(PublicCachedKeyConstant.SOURCE_GAME_CODE_MAP, gameMap);

        //调用redis发布消息
        publisher.sendMessage(RedisMessageTopicConstant.MSG_TOPIC_SOURCE_GAME, "Code信息发布成功");

    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updateSiteCode(ResEnum resType) {
        Map<Integer, SiteCode> SiteMap = new HashMap<Integer, SiteCode>();
        // 查询所有site类型的resource
        List<Resource> resList = this.resourceMapper.getResByType(resType.getId());

        int siteId;
        String site;

        for (Resource r : resList) {
            SiteCode siteCode = new SiteCode();
            siteId = r.getId();
            site = r.getResCode();
            siteCode.setId(siteId);
            siteCode.setCode(site);
            SiteMap.put(siteId, siteCode);
        }
        //将map放到redis
        cacheService.put(PublicCachedKeyConstant.SOURCE_SITE_CODE_MAP, SiteMap);
        //调用redis发布消息
        publisher.sendMessage(RedisMessageTopicConstant.MSG_TOPIC_SOURCE_SITE, "Site已成功发布");
    }

    @Override
    public PageResult<Resource> getPageList(String resCode, Page page) {
        if (page.getOrder() == null) {
            page.setOrder("id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }
        String str = '%' + resCode + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("resCode", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        List<Resource> list = this.resourceMapper.pageList(paramMap);
        int totalCount = this.resourceMapper.totalCount();
        page.setTotalCount(totalCount);
        PageResult<Resource> pageResult = new PageResult<Resource>();
        pageResult.setPage(page);
        pageResult.setRows(list);
        //updateSiteCode(ResEnum.site);
        //updateGameCode(ResEnum.game);
        return pageResult;
    }


    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Resource selByIdType(int resId, int resType) {
        Map map = new HashMap();
        map.put("resId", resId);
        map.put("resType", resType);
        return resourceMapper.selByIdAndType(map);
    }

    /**
     * 一键开启和关闭.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void optAvailable(String ResIds, int available) throws ServiceException {
        String[] resIds = ResIds.split(",");
        for (String resIdStr : resIds) {
            int resId = Integer.parseInt(resIdStr);
            Resource resource = this.selectById(resId);
            if (available == 0 || available == 1) {
                resource.setAvailable(available);
                this.updateGame(resource);
            }
        }
    }

    @Override
    public Map<Integer, GameCode> getGameMap() {
        Map<Integer, GameCode> gameCodeMaps = (Map<Integer, GameCode>) cacheService.get(PublicCachedKeyConstant.SOURCE_GAME_CODE_MAP, Map.class);
        //如果缓存中没有数据，查询数据库
        if (gameCodeMaps == null) {
            //查询所有game类型的resource
            List<Resource> resList = this.resourceMapper.getResByType(1);
            Map<Integer, GameCode> gameCodeMap = new HashMap<>();

            int gameId;
            String code;
            for (Resource resource : resList) {
                GameCode gameCode = new GameCode();
                gameId = resource.getId();
                code = resource.getResCode();
                gameCode.setId(gameId);
                gameCode.setCode(code);
                gameCodeMap.put(gameId, gameCode);
            }
            //更新缓存
            updateGameCode(ResEnum.game);
            return gameCodeMap;
        } else {
            return gameCodeMaps;
        }
    }

    @Override
    public Map<Integer, SiteCode> getSiteMap() {
        Map<Integer, SiteCode> siteCodeMaps = (Map<Integer, SiteCode>) cacheService.get(PublicCachedKeyConstant.SOURCE_SITE_CODE_MAP, Map.class);
        if (siteCodeMaps == null) {
            //查询所有game类型的resource
            List<Resource> resList = this.resourceMapper.getResByType(2);
            Map<Integer, SiteCode> siteCodeMap = new HashMap<>();

            int gameId;
            String code;
            for (Resource resource : resList) {
                SiteCode siteCode = new SiteCode();
                gameId = resource.getId();
                code = resource.getResCode();
                siteCode.setId(gameId);
                siteCode.setCode(code);
                siteCodeMap.put(gameId, siteCode);
            }
            //更新缓存
            updateGameCode(ResEnum.game);
            return siteCodeMap;
        } else {
            return siteCodeMaps;
        }

    }
}
