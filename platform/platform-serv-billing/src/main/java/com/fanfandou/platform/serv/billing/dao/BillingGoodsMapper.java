package com.fanfandou.platform.serv.billing.dao;

import java.util.List;
import java.util.Map;

import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.BillingGoodsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingGoodsMapper {
    int countByExample(BillingGoodsExample example);

    int deleteByPrimaryKey(Integer goodsId);

    int deleteByGoodsCode(String goodsCode);

    int insert(BillingGoods record);

    int insertSelective(BillingGoods record);

    String getAreasByCode(String goodsCode);

    BillingGoods getGoodsByCode(@Param("gameId")int gameId, @Param("goodsId") int goodsId);

    List<BillingGoods> selectByExample(BillingGoodsExample example);

    List<BillingGoods> getGoodsBySelective(Map map);

    List<BillingGoods> getGoodsByGameId(@Param("gameId") int gameId);

    int totalCount(Map map);

    BillingGoods selectByPrimaryKey(Integer goodsId);

    BillingGoods selectByGoodsCode(String goodsCode);

    int updateByExampleSelective(@Param("record") BillingGoods record, @Param("example") BillingGoodsExample example);

    int updateByExample(@Param("record") BillingGoods record, @Param("example") BillingGoodsExample example);

    int updateByPrimaryKeySelective(BillingGoods record);

    int updateByPrimaryKey(BillingGoods record);
}