package com.platform.serv.game.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.activity.service.PromoteCodeBatchService;
import com.fanfandou.platform.api.game.entity.AreaGroup;
import com.fanfandou.platform.api.game.entity.EnterAddress;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.entity.GameAreaExample;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.entity.GameToy;
import com.fanfandou.platform.api.game.entity.GameToyBatch;
import com.fanfandou.platform.api.game.entity.MaintenanceStatus;
import com.fanfandou.platform.api.game.entity.ToyBindStatus;
import com.fanfandou.platform.api.game.entity.ToyTest;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.api.game.service.GameToyService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.serv.game.dao.GameAreaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:平台游戏通讯接口测试类.
 * Date:2016/5/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class GameTest extends BaseLogger {

    @Autowired
    private GameToyService gameToyService;

    @Autowired
    private GameAreaService gameAreaService;

    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private GameAreaMapper gameAreaMapper;

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Test
    public void createCode() throws ServiceException {
        //gameToyService.createBatch(200,201,1,9,"10023",new Date());
        gameToyService.createBatch("第N批次", 60, new Date(), 1005, 1, 9);
    }

    @Test
    public void getTestTime() {
        System.out.println(new Date().getTime());
    }

    @Test
    public void bindToUser() throws ServiceException {
        gameToyService.bindToUser(768635197, 1013, 10011, 9, 1);
    }

    @Test
    public void verifyToy() {
        try {
            boolean flag = gameToyService.verifyToy(849091497, 1884, 10010, 9, 1);
            logger.debug("GameTest>verifyToy falg = " + flag);
        } catch (ServiceException e){
            logger.debug("errror : " + e.getId());
        }
    }

    @Test
    public void testboolean() {
        System.out.println("cadc222ae6d2901a1f439eb34ab95195".length());
        //System.out.println("testboolean >>> " + a);
    }

    @Test
    public void getBindUId() {
        GameToy uid = gameToyService.getGameToy(15711);
        if (uid != null) {
            logger.info("getBindUId >>> " + uid.getBindUid());
        } else {
            logger.info("is null");
        }
    }

    @Test
    public void getToys() throws Exception {
        Page curPage = new Page();
        curPage.setPageIndex(1);//第几页
        curPage.setPageSize(3);//多少条数据
        curPage.setSort("desc");//排序升降

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from = sdf.parse("1898-06-14 20:00:13");
        Date to = sdf.parse("2016-09-15 20:49:03");

        GameToy gameToy = new GameToy();
        gameToy.setBindStatus(ToyBindStatus.BOUND.getId());
        gameToy.setSiteId(9);
        curPage.setOrder("id");//根据什么排序
        PageResult pageResult = gameToyService.getToys(curPage, gameToy, from, to);
        logger.debug("GameTest>getToys>pageSize = " + pageResult.getRows().size());
        logger.debug("GameTest>getToys>getTotalCount = " + pageResult.getPage().getTotalCount());
    }

    @Test
    public void getToyTypes() throws ServiceException {
        List<Integer> typeList = gameToyService.getActedToyType(19010, 1011);
        if (typeList == null) {
            logger.error("typlist is null");
        } else {
            logger.info("getToyTypes >>>>> typeList = " + typeList.size());
        }

    }

    @Test
    public void testListNull() {
        List<Integer>  list1 = null;
        ToyTest toyTest = new ToyTest();
        toyTest.getListTest().addAll(list1);
    }

    @Test
    public void getToyBatches() throws Exception {
        Page curPage = new Page();
        curPage.setPageIndex(1);//第几页
        curPage.setPageSize(3);//多少条数据
        curPage.setSort("desc");//排序升降
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from = sdf.parse("1898-06-14 20:00:13");
        Date to = sdf.parse("2016-09-15 20:49:03");

        GameToyBatch gameToyBatch = new GameToyBatch();
        PageResult pageResult = gameToyService.getToyBatchesTest(curPage, gameToyBatch, from, to);
        logger.debug("GameTest>getToyBatches>pageSize = " + pageResult.getRows().size());
        logger.debug("GameTest>getToyBatches>getTotalCount = " + pageResult.getPage().getTotalCount());
    }

    @Test
    public void testActed() {
        logger.debug("testActed >>> " + ActStatus.valueOf("acted").getId());
    }


    //****************************************************Area******************************************************
    @Test
    public void createArea() throws ServiceException {
        GameArea gameArea = new GameArea();
        gameArea.setAreaCode("41");
        gameArea.setAreaDesc("41区");
        gameArea.setAreaName("41区");
        gameArea.setAreaTag(41);
        gameArea.setAvailableTime(new Date());
        //gameArea.setClientEnterAddr("127.0.0.1");
        gameArea.setCreateTime(new Date());
        gameArea.setDisplayOrder(1);
        gameArea.setGameId(1);
        gameArea.setId(40);
        gameArea.setLoadStatus(2);
        gameArea.setMaintenanceStatus(MaintenanceStatus.NORMAL);
        //gameArea.setServerEnterAddr("127.0.0.1");
        gameArea.setSiteIds("1,2,3,9");
        gameArea.setSupportVersionMax("1000");
        gameArea.setSupportVersionMin("2");
        gameArea.setValidStatus(ActStatus.ACTED);
        //gameArea.setAreaGroupId(2);

        EnterAddress server = new EnterAddress();
        server.setIpAddress("168.192.1.125");
        server.setPort(8021);


        EnterAddress client = new EnterAddress();
        client.setIpAddress("159.125.1.598");
        client.setPort(1258);

        List<EnterAddress> areaClientList = new ArrayList<>();
        areaClientList.add(client);
        areaClientList.add(server);

        AreaGroup areaGroup = new AreaGroup();
        areaGroup.setId(1);

/*
        gameArea.setClientEnterAddrList(areaClientList);
        gameArea.setServerEnterAddObj(server);
*/

        gameArea.setAreaGroup(areaGroup);

        gameAreaService.createGameArea(gameArea);
    }

    @Test
    public void updateArea() throws ServiceException {
        GameArea gameArea = new GameArea();
        gameArea.setId(1);
        gameArea.setAreaName("一区改2");       // GameAreaInfos gameAreaInfos = new GameAreaInfos();
        //gameAreaInfos.setGameArea(gameArea);
        //gameAreaService.updateGameAreaById(gameAreaInfos);
    }

    @Test
    public void deleteArea() throws ServiceException {
        gameAreaService.deleteGameAreaById(1);
    }

    @Test
    public void AddressJson() {

    }

    @Test
    public void tezt() {
        String a = null;
        String b = a + "abc";
        System.out.println(b);
    }

    @Test
    public void AddressJsonTest() throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaById(361);
        EnterAddress centerServerAddr = gameArea.getAreaGroup().getServerEnterAddrObj();
        logger.debug("AddressJsonTest " + centerServerAddr.getIpAddress());
    }

    @Test
    public void getGameAreas() throws ServiceException {
        List<GameArea> gameAreas = gameAreaService.getAreasByGameId(1);
        for (GameArea gameArea : gameAreas) {
            logger.debug("----------------------------------------------------------------");
            logger.debug("getGameAreas>>> areaGroup : " + gameArea.toString());
        }
        logger.debug("getGameAreas>>>" + gameAreas.size());
    }

    @Test
    public void getGameAreasPage() throws ServiceException {
        Page curPage = new Page();
        curPage.setPageIndex(1);//第几页
        curPage.setPageSize(3);//多少条数据
        curPage.setSort("desc");//排序升降
        curPage.setOrder("id");//根据什么排序

        PageResult pageResult = gameAreaService.getGameAreasForPage(null, null, curPage);
        List<GameArea> gameAreas = (List<GameArea>) pageResult.getRows();
        for (GameArea gameArea : gameAreas) {
            logger.debug("getGameAreas>>> " + gameArea.getAreaName());
        }

    }

    @Test
    public void gameAreaGroupByGameId() throws ServiceException {
        List<AreaGroup> areaGroups = gameAreaService.getAreaGroupByGameId(1);
        System.out.println(areaGroups.size());
    }

    @Test
    public void gameAreaGroupById() throws ServiceException {
        AreaGroup areaGroups = gameAreaService.getAreaGroupById(2);
    }

    @Test
    public void getGameAreaByPrimryId() throws ServiceException {

    }


    @Test
    public void getGameAreaGroupPage() throws ServiceException {
        Page curPage = new Page();
        curPage.setPageIndex(1);//第几页
        curPage.setPageSize(3);//多少条数据
        curPage.setSort("desc");//排序升降
        PageResult areaInfoses = gameAreaService.getAreaGroupPage(1, curPage);
        System.out.println("getGameAreaGroupPage >＞＞＞＞＞＞＞＞＞＞＞" + ((AreaGroup) areaInfoses.getRows().get(0)).getAreaGroupName());
    }

    @Test
    public void getAreaInfosFromCached() throws ServiceException {

    }

    @Test
    public void addGroupArea() throws ServiceException {
        AreaGroup areaGroup = new AreaGroup();
        areaGroup.setAreaGroupCode("100");
        areaGroup.setAreaGroupCode("测试大区");
        areaGroup.setAreaGroupName("测试大区");
        EnterAddress enterAddress = new EnterAddress();
        enterAddress.setIpAddress("120.5.5.154");
        enterAddress.setPort(1005);
        List<EnterAddress> enterAddressList = new ArrayList<>();
        enterAddressList.add(enterAddress);
        areaGroup.setClientEnterAddrList(enterAddressList);
        areaGroup.setServerEnterAddrObj(enterAddress);
        areaGroup.setCreateTime(new Date());
        areaGroup.setDisplayOrder(1);
        areaGroup.setGameId(1);
        gameAreaService.createAreaGroup(areaGroup);

    }

    @Test
    public void getRole() {
        GameRole gameRole = gameRoleService.getRoleByUserId(GameCode.getById(1), 381, 11);
        logger.info("getRole>>>" + gameRole.getRoleName());
    }

    @Test
    public void getTimestanps() {
        String afterDate = "2016-10-21 10:12:00";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = sdf.parse(afterDate);
            System.out.println("timeStanps = " + time.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetAreaInfos() throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(1,1 + "");
        System.out.println(gameArea.toString());
    }

    @Test
    public void debutGameArea() {
        GameAreaExample gameAreaExample = new GameAreaExample();
        gameAreaExample.createCriteria().andGameIdEqualTo(1)
                .andAreaCodeEqualTo("1").andValidStatusEqualTo(ActStatus.ACTED);
        List<GameArea> gameAreas = gameAreaMapper.selectByExample(gameAreaExample);
        if (gameAreas.size() == 0) {
            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        }
        System.out.println("gameAreas = " + gameAreas.get(0).getAreaName());
    }

    @Test
    public void testGmSend() throws ServiceException {
        Map<Integer, Object> hash = new HashMap<>();
        hash.put(512,3543);
        hash.put(513,11);
        //operationDispatchService.sendGmCommand(GameCode.getById(1), 1, 305,new DicItem(),hash);
    }

    public void testCreateCode() throws ServiceException {

    }
}
