package com.fanfandou.platform.api.game.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameToyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameToyExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andBindUidIsNull() {
            addCriterion("bind_uid is null");
            return (Criteria) this;
        }

        public Criteria andBindUidIsNotNull() {
            addCriterion("bind_uid is not null");
            return (Criteria) this;
        }

        public Criteria andBindUidEqualTo(Long value) {
            addCriterion("bind_uid =", value, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidNotEqualTo(Long value) {
            addCriterion("bind_uid <>", value, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidGreaterThan(Long value) {
            addCriterion("bind_uid >", value, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidGreaterThanOrEqualTo(Long value) {
            addCriterion("bind_uid >=", value, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidLessThan(Long value) {
            addCriterion("bind_uid <", value, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidLessThanOrEqualTo(Long value) {
            addCriterion("bind_uid <=", value, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidIn(List<Long> values) {
            addCriterion("bind_uid in", values, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidNotIn(List<Long> values) {
            addCriterion("bind_uid not in", values, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidBetween(Long value1, Long value2) {
            addCriterion("bind_uid between", value1, value2, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindUidNotBetween(Long value1, Long value2) {
            addCriterion("bind_uid not between", value1, value2, "bindUid");
            return (Criteria) this;
        }

        public Criteria andBindGameIsNull() {
            addCriterion("bind_game is null");
            return (Criteria) this;
        }

        public Criteria andBindGameIsNotNull() {
            addCriterion("bind_game is not null");
            return (Criteria) this;
        }

        public Criteria andBindGameEqualTo(Integer value) {
            addCriterion("bind_game =", value, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameNotEqualTo(Integer value) {
            addCriterion("bind_game <>", value, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameGreaterThan(Integer value) {
            addCriterion("bind_game >", value, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_game >=", value, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameLessThan(Integer value) {
            addCriterion("bind_game <", value, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameLessThanOrEqualTo(Integer value) {
            addCriterion("bind_game <=", value, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameIn(List<Integer> values) {
            addCriterion("bind_game in", values, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameNotIn(List<Integer> values) {
            addCriterion("bind_game not in", values, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameBetween(Integer value1, Integer value2) {
            addCriterion("bind_game between", value1, value2, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindGameNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_game not between", value1, value2, "bindGame");
            return (Criteria) this;
        }

        public Criteria andBindSiteIsNull() {
            addCriterion("bind_site is null");
            return (Criteria) this;
        }

        public Criteria andBindSiteIsNotNull() {
            addCriterion("bind_site is not null");
            return (Criteria) this;
        }

        public Criteria andBindSiteEqualTo(Integer value) {
            addCriterion("bind_site =", value, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteNotEqualTo(Integer value) {
            addCriterion("bind_site <>", value, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteGreaterThan(Integer value) {
            addCriterion("bind_site >", value, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_site >=", value, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteLessThan(Integer value) {
            addCriterion("bind_site <", value, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteLessThanOrEqualTo(Integer value) {
            addCriterion("bind_site <=", value, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteIn(List<Integer> values) {
            addCriterion("bind_site in", values, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteNotIn(List<Integer> values) {
            addCriterion("bind_site not in", values, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteBetween(Integer value1, Integer value2) {
            addCriterion("bind_site between", value1, value2, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindSiteNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_site not between", value1, value2, "bindSite");
            return (Criteria) this;
        }

        public Criteria andBindAreaIsNull() {
            addCriterion("bind_area is null");
            return (Criteria) this;
        }

        public Criteria andBindAreaIsNotNull() {
            addCriterion("bind_area is not null");
            return (Criteria) this;
        }

        public Criteria andBindAreaEqualTo(Integer value) {
            addCriterion("bind_area =", value, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaNotEqualTo(Integer value) {
            addCriterion("bind_area <>", value, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaGreaterThan(Integer value) {
            addCriterion("bind_area >", value, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind_area >=", value, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaLessThan(Integer value) {
            addCriterion("bind_area <", value, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaLessThanOrEqualTo(Integer value) {
            addCriterion("bind_area <=", value, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaIn(List<Integer> values) {
            addCriterion("bind_area in", values, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaNotIn(List<Integer> values) {
            addCriterion("bind_area not in", values, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaBetween(Integer value1, Integer value2) {
            addCriterion("bind_area between", value1, value2, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("bind_area not between", value1, value2, "bindArea");
            return (Criteria) this;
        }

        public Criteria andBindStatusIsNull() {
            addCriterion("bind_status is null");
            return (Criteria) this;
        }

        public Criteria andBindStatusIsNotNull() {
            addCriterion("bind_status is not null");
            return (Criteria) this;
        }

        public Criteria andBindStatusEqualTo(Byte value) {
            addCriterion("bind_status =", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotEqualTo(Byte value) {
            addCriterion("bind_status <>", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusGreaterThan(Byte value) {
            addCriterion("bind_status >", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("bind_status >=", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusLessThan(Byte value) {
            addCriterion("bind_status <", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusLessThanOrEqualTo(Byte value) {
            addCriterion("bind_status <=", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusIn(List<Byte> values) {
            addCriterion("bind_status in", values, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotIn(List<Byte> values) {
            addCriterion("bind_status not in", values, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusBetween(Byte value1, Byte value2) {
            addCriterion("bind_status between", value1, value2, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("bind_status not between", value1, value2, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNull() {
            addCriterion("bind_time is null");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNotNull() {
            addCriterion("bind_time is not null");
            return (Criteria) this;
        }

        public Criteria andBindTimeEqualTo(Date value) {
            addCriterion("bind_time =", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotEqualTo(Date value) {
            addCriterion("bind_time <>", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThan(Date value) {
            addCriterion("bind_time >", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bind_time >=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThan(Date value) {
            addCriterion("bind_time <", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThanOrEqualTo(Date value) {
            addCriterion("bind_time <=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeIn(List<Date> values) {
            addCriterion("bind_time in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotIn(List<Date> values) {
            addCriterion("bind_time not in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeBetween(Date value1, Date value2) {
            addCriterion("bind_time between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotBetween(Date value1, Date value2) {
            addCriterion("bind_time not between", value1, value2, "bindTime");
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