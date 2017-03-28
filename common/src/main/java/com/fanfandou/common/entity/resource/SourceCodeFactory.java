package com.fanfandou.common.entity.resource;

import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.service.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * Description: 资源配置，获取game、site等配置.
 * <p/>
 * Date: 2016-04-07 15:54.
 * author: Arvin.
 */
@Service
public class SourceCodeFactory {
    private int id;
    private String code;
    private static Map<Integer, GameCode> games = new HashMap<>();
    private static Map<Integer, SiteCode> sites = new HashMap<>();
    @Autowired
    private CacheService cacheService;
    private static SourceCodeFactory sourceCodeFactory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @PostConstruct
    private void init() {
        sourceCodeFactory = this;
        sourceCodeFactory.cacheService = this.cacheService;
    }

    /**
     * 初始化game map.
     */
    @SuppressWarnings("unchecked")
    public static void initGameMap() {
        games = (Map<Integer, GameCode>) sourceCodeFactory.cacheService
                .get(PublicCachedKeyConstant.SOURCE_GAME_CODE_MAP, Map.class);
    }

    /**
     * 初始化site map.
     */
    @SuppressWarnings("unchecked")
    public static void initSiteMap() {
        sites = (Map<Integer, SiteCode>) sourceCodeFactory.cacheService
                .get(PublicCachedKeyConstant.SOURCE_SITE_CODE_MAP, Map.class);
    }

    /**
     * 获取gameCode.
     *
     * @param gameId gameId
     * @return GameCode
     */
    public static GameCode getGameById(int gameId) {
        if (games.size() == 0) {
            initGameMap();
        }
        GameCode gameCode = games.get(gameId);
        /*if (gameCode != null) {
            for (Map.Entry<Integer, GameCode> gameCodeEntry : games.entrySet()) {
                GameCode gc = gameCodeEntry.getValue();
                if (gc.equals(gameCode)) {
                    continue;
                }
                if (gc.isParent(gameCode)) {
                    gameCode.getChildren().add(gc);
                    continue;
                }
                if (gameCode.isParent(gc)) {
                    gameCode.setParent(gc);
                }
            }

        }*/
        return gameCode;
    }

    /**
     * 通过Code获取GameCode.
     */
    public static GameCode gatGameByCode(String gameCode) {
        if (games.size() == 0) {
            initGameMap();
        }
        for (Map.Entry<Integer, GameCode> gameCodeEntry : games.entrySet()) {
            if (gameCodeEntry.getValue().getCode().equals(gameCode)) {
                return gameCodeEntry.getValue();
            }
        }
        return null;
    }

    /**
     * 获取siteCode.
     *
     * @param siteId siteId
     * @return SiteCode
     */
    public static SiteCode getSiteById(int siteId) {
        if (sites.size() == 0) {
            initSiteMap();
        }
        SiteCode siteCode = sites.get(siteId);
        if (siteCode != null) {
            for (Map.Entry<Integer, SiteCode> siteCodeEntry : sites.entrySet()) {
                SiteCode sc = siteCodeEntry.getValue();
                if (sc.equals(siteCode)) {
                    continue;
                }
                if (sc.isParent(siteCode)) {
                    siteCode.getChildren().add(sc);
                    continue;
                }
                if (siteCode.isParent(sc)) {
                    siteCode.getParents().add(sc);
                }
            }

        }
        return siteCode;
    }

    /**
     * 通过Code获取site.
     */
    public static SiteCode getSiteByCode(String siteCode) {
        if (sites.size() == 0) {
            initSiteMap();
        }
        for (Map.Entry<Integer, SiteCode> siteCodeEntry : sites.entrySet()) {
            if (siteCode.contains(siteCodeEntry.getValue().getCode())) {
                return siteCodeEntry.getValue();
            }
        }
        return null;
    }

    /**
     * 获取所有gameCode.
     */
    public static Map<Integer, GameCode> getGames() {
        if (games.size() == 0) {
            initGameMap();
        }
        return games;
    }

    /**
     * 获取所有site.
     */
    public static Map<Integer, SiteCode> getSites() {
        if (sites.size() == 0) {
            initSiteMap();
        }
        return sites;
    }
}
