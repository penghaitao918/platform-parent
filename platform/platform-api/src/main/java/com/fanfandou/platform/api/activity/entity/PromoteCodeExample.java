package com.fanfandou.platform.api.activity.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromoteCodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PromoteCodeExample() {
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

        public Criteria andCodeIdIsNull() {
            addCriterion("code_id is null");
            return (Criteria) this;
        }

        public Criteria andCodeIdIsNotNull() {
            addCriterion("code_id is not null");
            return (Criteria) this;
        }

        public Criteria andCodeIdEqualTo(Long value) {
            addCriterion("code_id =", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotEqualTo(Long value) {
            addCriterion("code_id <>", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThan(Long value) {
            addCriterion("code_id >", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("code_id >=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThan(Long value) {
            addCriterion("code_id <", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThanOrEqualTo(Long value) {
            addCriterion("code_id <=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdIn(List<Long> values) {
            addCriterion("code_id in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotIn(List<Long> values) {
            addCriterion("code_id not in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdBetween(Long value1, Long value2) {
            addCriterion("code_id between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotBetween(Long value1, Long value2) {
            addCriterion("code_id not between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeIsNull() {
            addCriterion("promote_code is null");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeIsNotNull() {
            addCriterion("promote_code is not null");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeEqualTo(String value) {
            addCriterion("promote_code =", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeNotEqualTo(String value) {
            addCriterion("promote_code <>", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeGreaterThan(String value) {
            addCriterion("promote_code >", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("promote_code >=", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeLessThan(String value) {
            addCriterion("promote_code <", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeLessThanOrEqualTo(String value) {
            addCriterion("promote_code <=", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeLike(String value) {
            addCriterion("promote_code like", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeNotLike(String value) {
            addCriterion("promote_code not like", value, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeIn(List<String> values) {
            addCriterion("promote_code in", values, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeNotIn(List<String> values) {
            addCriterion("promote_code not in", values, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeBetween(String value1, String value2) {
            addCriterion("promote_code between", value1, value2, "promoteCode");
            return (Criteria) this;
        }

        public Criteria andPromoteCodeNotBetween(String value1, String value2) {
            addCriterion("promote_code not between", value1, value2, "promoteCode");
            return (Criteria) this;
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

        public Criteria andDrawSiteIdIsNull() {
            addCriterion("draw_site_id is null");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdIsNotNull() {
            addCriterion("draw_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdEqualTo(Integer value) {
            addCriterion("draw_site_id =", value, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdNotEqualTo(Integer value) {
            addCriterion("draw_site_id <>", value, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdGreaterThan(Integer value) {
            addCriterion("draw_site_id >", value, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("draw_site_id >=", value, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdLessThan(Integer value) {
            addCriterion("draw_site_id <", value, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("draw_site_id <=", value, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdIn(List<Integer> values) {
            addCriterion("draw_site_id in", values, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdNotIn(List<Integer> values) {
            addCriterion("draw_site_id not in", values, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("draw_site_id between", value1, value2, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("draw_site_id not between", value1, value2, "drawSiteId");
            return (Criteria) this;
        }

        public Criteria andDrawChannelIsNull() {
            addCriterion("draw_channel is null");
            return (Criteria) this;
        }

        public Criteria andDrawChannelIsNotNull() {
            addCriterion("draw_channel is not null");
            return (Criteria) this;
        }

        public Criteria andDrawChannelEqualTo(String value) {
            addCriterion("draw_channel =", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelNotEqualTo(String value) {
            addCriterion("draw_channel <>", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelGreaterThan(String value) {
            addCriterion("draw_channel >", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelGreaterThanOrEqualTo(String value) {
            addCriterion("draw_channel >=", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelLessThan(String value) {
            addCriterion("draw_channel <", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelLessThanOrEqualTo(String value) {
            addCriterion("draw_channel <=", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelLike(String value) {
            addCriterion("draw_channel like", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelNotLike(String value) {
            addCriterion("draw_channel not like", value, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelIn(List<String> values) {
            addCriterion("draw_channel in", values, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelNotIn(List<String> values) {
            addCriterion("draw_channel not in", values, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelBetween(String value1, String value2) {
            addCriterion("draw_channel between", value1, value2, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawChannelNotBetween(String value1, String value2) {
            addCriterion("draw_channel not between", value1, value2, "drawChannel");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdIsNull() {
            addCriterion("draw_game_id is null");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdIsNotNull() {
            addCriterion("draw_game_id is not null");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdEqualTo(Integer value) {
            addCriterion("draw_game_id =", value, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdNotEqualTo(Integer value) {
            addCriterion("draw_game_id <>", value, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdGreaterThan(Integer value) {
            addCriterion("draw_game_id >", value, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("draw_game_id >=", value, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdLessThan(Integer value) {
            addCriterion("draw_game_id <", value, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdLessThanOrEqualTo(Integer value) {
            addCriterion("draw_game_id <=", value, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdIn(List<Integer> values) {
            addCriterion("draw_game_id in", values, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdNotIn(List<Integer> values) {
            addCriterion("draw_game_id not in", values, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdBetween(Integer value1, Integer value2) {
            addCriterion("draw_game_id between", value1, value2, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameIdNotBetween(Integer value1, Integer value2) {
            addCriterion("draw_game_id not between", value1, value2, "drawGameId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdIsNull() {
            addCriterion("draw_game_area_id is null");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdIsNotNull() {
            addCriterion("draw_game_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdEqualTo(Integer value) {
            addCriterion("draw_game_area_id =", value, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdNotEqualTo(Integer value) {
            addCriterion("draw_game_area_id <>", value, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdGreaterThan(Integer value) {
            addCriterion("draw_game_area_id >", value, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("draw_game_area_id >=", value, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdLessThan(Integer value) {
            addCriterion("draw_game_area_id <", value, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("draw_game_area_id <=", value, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdIn(List<Integer> values) {
            addCriterion("draw_game_area_id in", values, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdNotIn(List<Integer> values) {
            addCriterion("draw_game_area_id not in", values, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("draw_game_area_id between", value1, value2, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawGameAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("draw_game_area_id not between", value1, value2, "drawGameAreaId");
            return (Criteria) this;
        }

        public Criteria andDrawStatusIsNull() {
            addCriterion("draw_status is null");
            return (Criteria) this;
        }

        public Criteria andDrawStatusIsNotNull() {
            addCriterion("draw_status is not null");
            return (Criteria) this;
        }

        public Criteria andDrawStatusEqualTo(Byte value) {
            addCriterion("draw_status =", value, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusNotEqualTo(Byte value) {
            addCriterion("draw_status <>", value, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusGreaterThan(Byte value) {
            addCriterion("draw_status >", value, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("draw_status >=", value, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusLessThan(Byte value) {
            addCriterion("draw_status <", value, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusLessThanOrEqualTo(Byte value) {
            addCriterion("draw_status <=", value, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusIn(List<Byte> values) {
            addCriterion("draw_status in", values, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusNotIn(List<Byte> values) {
            addCriterion("draw_status not in", values, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusBetween(Byte value1, Byte value2) {
            addCriterion("draw_status between", value1, value2, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("draw_status not between", value1, value2, "drawStatus");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdIsNull() {
            addCriterion("draw_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdIsNotNull() {
            addCriterion("draw_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdEqualTo(Integer value) {
            addCriterion("draw_user_id =", value, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdNotEqualTo(Integer value) {
            addCriterion("draw_user_id <>", value, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdGreaterThan(Integer value) {
            addCriterion("draw_user_id >", value, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("draw_user_id >=", value, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdLessThan(Integer value) {
            addCriterion("draw_user_id <", value, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("draw_user_id <=", value, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdIn(List<Integer> values) {
            addCriterion("draw_user_id in", values, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdNotIn(List<Integer> values) {
            addCriterion("draw_user_id not in", values, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdBetween(Integer value1, Integer value2) {
            addCriterion("draw_user_id between", value1, value2, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("draw_user_id not between", value1, value2, "drawUserId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdIsNull() {
            addCriterion("draw_role_id is null");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdIsNotNull() {
            addCriterion("draw_role_id is not null");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdEqualTo(String value) {
            addCriterion("draw_role_id =", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdNotEqualTo(String value) {
            addCriterion("draw_role_id <>", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdGreaterThan(String value) {
            addCriterion("draw_role_id >", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("draw_role_id >=", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdLessThan(String value) {
            addCriterion("draw_role_id <", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdLessThanOrEqualTo(String value) {
            addCriterion("draw_role_id <=", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdLike(String value) {
            addCriterion("draw_role_id like", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdNotLike(String value) {
            addCriterion("draw_role_id not like", value, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdIn(List<String> values) {
            addCriterion("draw_role_id in", values, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdNotIn(List<String> values) {
            addCriterion("draw_role_id not in", values, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdBetween(String value1, String value2) {
            addCriterion("draw_role_id between", value1, value2, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawRoleIdNotBetween(String value1, String value2) {
            addCriterion("draw_role_id not between", value1, value2, "drawRoleId");
            return (Criteria) this;
        }

        public Criteria andDrawDateIsNull() {
            addCriterion("draw_date is null");
            return (Criteria) this;
        }

        public Criteria andDrawDateIsNotNull() {
            addCriterion("draw_date is not null");
            return (Criteria) this;
        }

        public Criteria andDrawDateEqualTo(Date value) {
            addCriterion("draw_date =", value, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateNotEqualTo(Date value) {
            addCriterion("draw_date <>", value, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateGreaterThan(Date value) {
            addCriterion("draw_date >", value, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateGreaterThanOrEqualTo(Date value) {
            addCriterion("draw_date >=", value, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateLessThan(Date value) {
            addCriterion("draw_date <", value, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateLessThanOrEqualTo(Date value) {
            addCriterion("draw_date <=", value, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateIn(List<Date> values) {
            addCriterion("draw_date in", values, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateNotIn(List<Date> values) {
            addCriterion("draw_date not in", values, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateBetween(Date value1, Date value2) {
            addCriterion("draw_date between", value1, value2, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawDateNotBetween(Date value1, Date value2) {
            addCriterion("draw_date not between", value1, value2, "drawDate");
            return (Criteria) this;
        }

        public Criteria andDrawIpIsNull() {
            addCriterion("draw_ip is null");
            return (Criteria) this;
        }

        public Criteria andDrawIpIsNotNull() {
            addCriterion("draw_ip is not null");
            return (Criteria) this;
        }

        public Criteria andDrawIpEqualTo(String value) {
            addCriterion("draw_ip =", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpNotEqualTo(String value) {
            addCriterion("draw_ip <>", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpGreaterThan(String value) {
            addCriterion("draw_ip >", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpGreaterThanOrEqualTo(String value) {
            addCriterion("draw_ip >=", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpLessThan(String value) {
            addCriterion("draw_ip <", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpLessThanOrEqualTo(String value) {
            addCriterion("draw_ip <=", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpLike(String value) {
            addCriterion("draw_ip like", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpNotLike(String value) {
            addCriterion("draw_ip not like", value, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpIn(List<String> values) {
            addCriterion("draw_ip in", values, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpNotIn(List<String> values) {
            addCriterion("draw_ip not in", values, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpBetween(String value1, String value2) {
            addCriterion("draw_ip between", value1, value2, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDrawIpNotBetween(String value1, String value2) {
            addCriterion("draw_ip not between", value1, value2, "drawIp");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIsNull() {
            addCriterion("deliver_status is null");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIsNotNull() {
            addCriterion("deliver_status is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusEqualTo(Byte value) {
            addCriterion("deliver_status =", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotEqualTo(Byte value) {
            addCriterion("deliver_status <>", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusGreaterThan(Byte value) {
            addCriterion("deliver_status >", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("deliver_status >=", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLessThan(Byte value) {
            addCriterion("deliver_status <", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLessThanOrEqualTo(Byte value) {
            addCriterion("deliver_status <=", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIn(List<Byte> values) {
            addCriterion("deliver_status in", values, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotIn(List<Byte> values) {
            addCriterion("deliver_status not in", values, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusBetween(Byte value1, Byte value2) {
            addCriterion("deliver_status between", value1, value2, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("deliver_status not between", value1, value2, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNull() {
            addCriterion("deliver_date is null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNotNull() {
            addCriterion("deliver_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateEqualTo(Date value) {
            addCriterion("deliver_date =", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotEqualTo(Date value) {
            addCriterion("deliver_date <>", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThan(Date value) {
            addCriterion("deliver_date >", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver_date >=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThan(Date value) {
            addCriterion("deliver_date <", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThanOrEqualTo(Date value) {
            addCriterion("deliver_date <=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIn(List<Date> values) {
            addCriterion("deliver_date in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotIn(List<Date> values) {
            addCriterion("deliver_date not in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateBetween(Date value1, Date value2) {
            addCriterion("deliver_date between", value1, value2, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotBetween(Date value1, Date value2) {
            addCriterion("deliver_date not between", value1, value2, "deliverDate");
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