package com.fanfandou.platform.api.activity.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromoteCodeBatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PromoteCodeBatchExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Integer value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Integer value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Integer value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Integer> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Integer> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNull() {
            addCriterion("site_id is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("site_id is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Integer value) {
            addCriterion("site_id =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Integer value) {
            addCriterion("site_id <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Integer value) {
            addCriterion("site_id >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_id >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Integer value) {
            addCriterion("site_id <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("site_id <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Integer> values) {
            addCriterion("site_id in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Integer> values) {
            addCriterion("site_id not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("site_id between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("site_id not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNull() {
            addCriterion("game_id is null");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNotNull() {
            addCriterion("game_id is not null");
            return (Criteria) this;
        }

        public Criteria andGameIdEqualTo(Integer value) {
            addCriterion("game_id =", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotEqualTo(Integer value) {
            addCriterion("game_id <>", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThan(Integer value) {
            addCriterion("game_id >", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_id >=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThan(Integer value) {
            addCriterion("game_id <", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThanOrEqualTo(Integer value) {
            addCriterion("game_id <=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdIn(List<Integer> values) {
            addCriterion("game_id in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotIn(List<Integer> values) {
            addCriterion("game_id not in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdBetween(Integer value1, Integer value2) {
            addCriterion("game_id between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotBetween(Integer value1, Integer value2) {
            addCriterion("game_id not between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdIsNull() {
            addCriterion("game_area_id is null");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdIsNotNull() {
            addCriterion("game_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdEqualTo(Integer value) {
            addCriterion("game_area_id =", value, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdNotEqualTo(Integer value) {
            addCriterion("game_area_id <>", value, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdGreaterThan(Integer value) {
            addCriterion("game_area_id >", value, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_area_id >=", value, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdLessThan(Integer value) {
            addCriterion("game_area_id <", value, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("game_area_id <=", value, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdIn(List<Integer> values) {
            addCriterion("game_area_id in", values, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdNotIn(List<Integer> values) {
            addCriterion("game_area_id not in", values, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("game_area_id between", value1, value2, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andGameAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("game_area_id not between", value1, value2, "gameAreaId");
            return (Criteria) this;
        }

        public Criteria andBatchNameIsNull() {
            addCriterion("batch_name is null");
            return (Criteria) this;
        }

        public Criteria andBatchNameIsNotNull() {
            addCriterion("batch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNameEqualTo(String value) {
            addCriterion("batch_name =", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotEqualTo(String value) {
            addCriterion("batch_name <>", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameGreaterThan(String value) {
            addCriterion("batch_name >", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("batch_name >=", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLessThan(String value) {
            addCriterion("batch_name <", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLessThanOrEqualTo(String value) {
            addCriterion("batch_name <=", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLike(String value) {
            addCriterion("batch_name like", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotLike(String value) {
            addCriterion("batch_name not like", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameIn(List<String> values) {
            addCriterion("batch_name in", values, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotIn(List<String> values) {
            addCriterion("batch_name not in", values, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameBetween(String value1, String value2) {
            addCriterion("batch_name between", value1, value2, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotBetween(String value1, String value2) {
            addCriterion("batch_name not between", value1, value2, "batchName");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryIsNull() {
            addCriterion("promote_category is null");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryIsNotNull() {
            addCriterion("promote_category is not null");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryEqualTo(Integer value) {
            addCriterion("promote_category =", value, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryNotEqualTo(Integer value) {
            addCriterion("promote_category <>", value, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryGreaterThan(Integer value) {
            addCriterion("promote_category >", value, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("promote_category >=", value, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryLessThan(Integer value) {
            addCriterion("promote_category <", value, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("promote_category <=", value, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryIn(List<Integer> values) {
            addCriterion("promote_category in", values, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryNotIn(List<Integer> values) {
            addCriterion("promote_category not in", values, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryBetween(Integer value1, Integer value2) {
            addCriterion("promote_category between", value1, value2, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andPromoteCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("promote_category not between", value1, value2, "promoteCategory");
            return (Criteria) this;
        }

        public Criteria andAwardGreetIsNull() {
            addCriterion("award_greet is null");
            return (Criteria) this;
        }

        public Criteria andAwardGreetIsNotNull() {
            addCriterion("award_greet is not null");
            return (Criteria) this;
        }

        public Criteria andAwardGreetEqualTo(String value) {
            addCriterion("award_greet =", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetNotEqualTo(String value) {
            addCriterion("award_greet <>", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetGreaterThan(String value) {
            addCriterion("award_greet >", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetGreaterThanOrEqualTo(String value) {
            addCriterion("award_greet >=", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetLessThan(String value) {
            addCriterion("award_greet <", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetLessThanOrEqualTo(String value) {
            addCriterion("award_greet <=", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetLike(String value) {
            addCriterion("award_greet like", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetNotLike(String value) {
            addCriterion("award_greet not like", value, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetIn(List<String> values) {
            addCriterion("award_greet in", values, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetNotIn(List<String> values) {
            addCriterion("award_greet not in", values, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetBetween(String value1, String value2) {
            addCriterion("award_greet between", value1, value2, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andAwardGreetNotBetween(String value1, String value2) {
            addCriterion("award_greet not between", value1, value2, "awardGreet");
            return (Criteria) this;
        }

        public Criteria andPackageIdIsNull() {
            addCriterion("package_id is null");
            return (Criteria) this;
        }

        public Criteria andPackageIdIsNotNull() {
            addCriterion("package_id is not null");
            return (Criteria) this;
        }

        public Criteria andPackageIdEqualTo(Integer value) {
            addCriterion("package_id =", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotEqualTo(Integer value) {
            addCriterion("package_id <>", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdGreaterThan(Integer value) {
            addCriterion("package_id >", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("package_id >=", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdLessThan(Integer value) {
            addCriterion("package_id <", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdLessThanOrEqualTo(Integer value) {
            addCriterion("package_id <=", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdIn(List<Integer> values) {
            addCriterion("package_id in", values, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotIn(List<Integer> values) {
            addCriterion("package_id not in", values, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdBetween(Integer value1, Integer value2) {
            addCriterion("package_id between", value1, value2, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("package_id not between", value1, value2, "packageId");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeIsNull() {
            addCriterion("promote_batch_type is null");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeIsNotNull() {
            addCriterion("promote_batch_type is not null");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeEqualTo(Integer value) {
            addCriterion("promote_batch_type =", value, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeNotEqualTo(Integer value) {
            addCriterion("promote_batch_type <>", value, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeGreaterThan(Integer value) {
            addCriterion("promote_batch_type >", value, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("promote_batch_type >=", value, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeLessThan(Integer value) {
            addCriterion("promote_batch_type <", value, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeLessThanOrEqualTo(Integer value) {
            addCriterion("promote_batch_type <=", value, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeIn(List<Integer> values) {
            addCriterion("promote_batch_type in", values, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeNotIn(List<Integer> values) {
            addCriterion("promote_batch_type not in", values, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeBetween(Integer value1, Integer value2) {
            addCriterion("promote_batch_type between", value1, value2, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andPromoteBatchTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("promote_batch_type not between", value1, value2, "promoteBatchType");
            return (Criteria) this;
        }

        public Criteria andAwardRoleIsNull() {
            addCriterion("award_role is null");
            return (Criteria) this;
        }

        public Criteria andAwardRoleIsNotNull() {
            addCriterion("award_role is not null");
            return (Criteria) this;
        }

        public Criteria andAwardRoleEqualTo(String value) {
            addCriterion("award_role =", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleNotEqualTo(String value) {
            addCriterion("award_role <>", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleGreaterThan(String value) {
            addCriterion("award_role >", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleGreaterThanOrEqualTo(String value) {
            addCriterion("award_role >=", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleLessThan(String value) {
            addCriterion("award_role <", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleLessThanOrEqualTo(String value) {
            addCriterion("award_role <=", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleLike(String value) {
            addCriterion("award_role like", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleNotLike(String value) {
            addCriterion("award_role not like", value, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleIn(List<String> values) {
            addCriterion("award_role in", values, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleNotIn(List<String> values) {
            addCriterion("award_role not in", values, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleBetween(String value1, String value2) {
            addCriterion("award_role between", value1, value2, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAwardRoleNotBetween(String value1, String value2) {
            addCriterion("award_role not between", value1, value2, "awardRole");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountIsNull() {
            addCriterion("used_amount is null");
            return (Criteria) this;
        }

        public Criteria andUsedAmountIsNotNull() {
            addCriterion("used_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUsedAmountEqualTo(Integer value) {
            addCriterion("used_amount =", value, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountNotEqualTo(Integer value) {
            addCriterion("used_amount <>", value, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountGreaterThan(Integer value) {
            addCriterion("used_amount >", value, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("used_amount >=", value, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountLessThan(Integer value) {
            addCriterion("used_amount <", value, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountLessThanOrEqualTo(Integer value) {
            addCriterion("used_amount <=", value, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountIn(List<Integer> values) {
            addCriterion("used_amount in", values, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountNotIn(List<Integer> values) {
            addCriterion("used_amount not in", values, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountBetween(Integer value1, Integer value2) {
            addCriterion("used_amount between", value1, value2, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsedAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("used_amount not between", value1, value2, "usedAmount");
            return (Criteria) this;
        }

        public Criteria andUsingRuleIsNull() {
            addCriterion("using_rule is null");
            return (Criteria) this;
        }

        public Criteria andUsingRuleIsNotNull() {
            addCriterion("using_rule is not null");
            return (Criteria) this;
        }

        public Criteria andUsingRuleEqualTo(String value) {
            addCriterion("using_rule =", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleNotEqualTo(String value) {
            addCriterion("using_rule <>", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleGreaterThan(String value) {
            addCriterion("using_rule >", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleGreaterThanOrEqualTo(String value) {
            addCriterion("using_rule >=", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleLessThan(String value) {
            addCriterion("using_rule <", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleLessThanOrEqualTo(String value) {
            addCriterion("using_rule <=", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleLike(String value) {
            addCriterion("using_rule like", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleNotLike(String value) {
            addCriterion("using_rule not like", value, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleIn(List<String> values) {
            addCriterion("using_rule in", values, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleNotIn(List<String> values) {
            addCriterion("using_rule not in", values, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleBetween(String value1, String value2) {
            addCriterion("using_rule between", value1, value2, "usingRule");
            return (Criteria) this;
        }

        public Criteria andUsingRuleNotBetween(String value1, String value2) {
            addCriterion("using_rule not between", value1, value2, "usingRule");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateIsNull() {
            addCriterion("available_start_date is null");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateIsNotNull() {
            addCriterion("available_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateEqualTo(Date value) {
            addCriterion("available_start_date =", value, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateNotEqualTo(Date value) {
            addCriterion("available_start_date <>", value, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateGreaterThan(Date value) {
            addCriterion("available_start_date >", value, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("available_start_date >=", value, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateLessThan(Date value) {
            addCriterion("available_start_date <", value, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateLessThanOrEqualTo(Date value) {
            addCriterion("available_start_date <=", value, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateIn(List<Date> values) {
            addCriterion("available_start_date in", values, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateNotIn(List<Date> values) {
            addCriterion("available_start_date not in", values, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateBetween(Date value1, Date value2) {
            addCriterion("available_start_date between", value1, value2, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableStartDateNotBetween(Date value1, Date value2) {
            addCriterion("available_start_date not between", value1, value2, "availableStartDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateIsNull() {
            addCriterion("available_end_date is null");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateIsNotNull() {
            addCriterion("available_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateEqualTo(Date value) {
            addCriterion("available_end_date =", value, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateNotEqualTo(Date value) {
            addCriterion("available_end_date <>", value, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateGreaterThan(Date value) {
            addCriterion("available_end_date >", value, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("available_end_date >=", value, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateLessThan(Date value) {
            addCriterion("available_end_date <", value, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateLessThanOrEqualTo(Date value) {
            addCriterion("available_end_date <=", value, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateIn(List<Date> values) {
            addCriterion("available_end_date in", values, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateNotIn(List<Date> values) {
            addCriterion("available_end_date not in", values, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateBetween(Date value1, Date value2) {
            addCriterion("available_end_date between", value1, value2, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andAvailableEndDateNotBetween(Date value1, Date value2) {
            addCriterion("available_end_date not between", value1, value2, "availableEndDate");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusIsNull() {
            addCriterion("generate_status is null");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusIsNotNull() {
            addCriterion("generate_status is not null");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusEqualTo(Byte value) {
            addCriterion("generate_status =", value, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusNotEqualTo(Byte value) {
            addCriterion("generate_status <>", value, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusGreaterThan(Byte value) {
            addCriterion("generate_status >", value, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("generate_status >=", value, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusLessThan(Byte value) {
            addCriterion("generate_status <", value, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusLessThanOrEqualTo(Byte value) {
            addCriterion("generate_status <=", value, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusIn(List<Byte> values) {
            addCriterion("generate_status in", values, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusNotIn(List<Byte> values) {
            addCriterion("generate_status not in", values, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusBetween(Byte value1, Byte value2) {
            addCriterion("generate_status between", value1, value2, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andGenerateStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("generate_status not between", value1, value2, "generateStatus");
            return (Criteria) this;
        }

        public Criteria andBatchDescIsNull() {
            addCriterion("batch_desc is null");
            return (Criteria) this;
        }

        public Criteria andBatchDescIsNotNull() {
            addCriterion("batch_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDescEqualTo(String value) {
            addCriterion("batch_desc =", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotEqualTo(String value) {
            addCriterion("batch_desc <>", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescGreaterThan(String value) {
            addCriterion("batch_desc >", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescGreaterThanOrEqualTo(String value) {
            addCriterion("batch_desc >=", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescLessThan(String value) {
            addCriterion("batch_desc <", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescLessThanOrEqualTo(String value) {
            addCriterion("batch_desc <=", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescLike(String value) {
            addCriterion("batch_desc like", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotLike(String value) {
            addCriterion("batch_desc not like", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescIn(List<String> values) {
            addCriterion("batch_desc in", values, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotIn(List<String> values) {
            addCriterion("batch_desc not in", values, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescBetween(String value1, String value2) {
            addCriterion("batch_desc between", value1, value2, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotBetween(String value1, String value2) {
            addCriterion("batch_desc not between", value1, value2, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andValidStatusIsNull() {
            addCriterion("valid_status is null");
            return (Criteria) this;
        }

        public Criteria andValidStatusIsNotNull() {
            addCriterion("valid_status is not null");
            return (Criteria) this;
        }

        public Criteria andValidStatusEqualTo(Byte value) {
            addCriterion("valid_status =", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotEqualTo(Byte value) {
            addCriterion("valid_status <>", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThan(Byte value) {
            addCriterion("valid_status >", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("valid_status >=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThan(Byte value) {
            addCriterion("valid_status <", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThanOrEqualTo(Byte value) {
            addCriterion("valid_status <=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusIn(List<Byte> values) {
            addCriterion("valid_status in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotIn(List<Byte> values) {
            addCriterion("valid_status not in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusBetween(Byte value1, Byte value2) {
            addCriterion("valid_status between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("valid_status not between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdIsNull() {
            addCriterion("create_admin_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdIsNotNull() {
            addCriterion("create_admin_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdEqualTo(Integer value) {
            addCriterion("create_admin_user_id =", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotEqualTo(Integer value) {
            addCriterion("create_admin_user_id <>", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdGreaterThan(Integer value) {
            addCriterion("create_admin_user_id >", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_admin_user_id >=", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdLessThan(Integer value) {
            addCriterion("create_admin_user_id <", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_admin_user_id <=", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdIn(List<Integer> values) {
            addCriterion("create_admin_user_id in", values, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotIn(List<Integer> values) {
            addCriterion("create_admin_user_id not in", values, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_admin_user_id between", value1, value2, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_admin_user_id not between", value1, value2, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateIpIsNull() {
            addCriterion("create_ip is null");
            return (Criteria) this;
        }

        public Criteria andCreateIpIsNotNull() {
            addCriterion("create_ip is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIpEqualTo(String value) {
            addCriterion("create_ip =", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpNotEqualTo(String value) {
            addCriterion("create_ip <>", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpGreaterThan(String value) {
            addCriterion("create_ip >", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpGreaterThanOrEqualTo(String value) {
            addCriterion("create_ip >=", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpLessThan(String value) {
            addCriterion("create_ip <", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpLessThanOrEqualTo(String value) {
            addCriterion("create_ip <=", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpLike(String value) {
            addCriterion("create_ip like", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpNotLike(String value) {
            addCriterion("create_ip not like", value, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpIn(List<String> values) {
            addCriterion("create_ip in", values, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpNotIn(List<String> values) {
            addCriterion("create_ip not in", values, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpBetween(String value1, String value2) {
            addCriterion("create_ip between", value1, value2, "createIp");
            return (Criteria) this;
        }

        public Criteria andCreateIpNotBetween(String value1, String value2) {
            addCriterion("create_ip not between", value1, value2, "createIp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}