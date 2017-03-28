package com.fanfandou.platform.api.game.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AreaGroupExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andAreaGroupCodeIsNull() {
            addCriterion("area_group_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeIsNotNull() {
            addCriterion("area_group_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeEqualTo(String value) {
            addCriterion("area_group_code =", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeNotEqualTo(String value) {
            addCriterion("area_group_code <>", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeGreaterThan(String value) {
            addCriterion("area_group_code >", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_group_code >=", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeLessThan(String value) {
            addCriterion("area_group_code <", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("area_group_code <=", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeLike(String value) {
            addCriterion("area_group_code like", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeNotLike(String value) {
            addCriterion("area_group_code not like", value, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeIn(List<String> values) {
            addCriterion("area_group_code in", values, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeNotIn(List<String> values) {
            addCriterion("area_group_code not in", values, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeBetween(String value1, String value2) {
            addCriterion("area_group_code between", value1, value2, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupCodeNotBetween(String value1, String value2) {
            addCriterion("area_group_code not between", value1, value2, "areaGroupCode");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameIsNull() {
            addCriterion("area_group_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameIsNotNull() {
            addCriterion("area_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameEqualTo(String value) {
            addCriterion("area_group_name =", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameNotEqualTo(String value) {
            addCriterion("area_group_name <>", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameGreaterThan(String value) {
            addCriterion("area_group_name >", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_group_name >=", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameLessThan(String value) {
            addCriterion("area_group_name <", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameLessThanOrEqualTo(String value) {
            addCriterion("area_group_name <=", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameLike(String value) {
            addCriterion("area_group_name like", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameNotLike(String value) {
            addCriterion("area_group_name not like", value, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameIn(List<String> values) {
            addCriterion("area_group_name in", values, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameNotIn(List<String> values) {
            addCriterion("area_group_name not in", values, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameBetween(String value1, String value2) {
            addCriterion("area_group_name between", value1, value2, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupNameNotBetween(String value1, String value2) {
            addCriterion("area_group_name not between", value1, value2, "areaGroupName");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescIsNull() {
            addCriterion("area_group_desc is null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescIsNotNull() {
            addCriterion("area_group_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescEqualTo(String value) {
            addCriterion("area_group_desc =", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescNotEqualTo(String value) {
            addCriterion("area_group_desc <>", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescGreaterThan(String value) {
            addCriterion("area_group_desc >", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescGreaterThanOrEqualTo(String value) {
            addCriterion("area_group_desc >=", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescLessThan(String value) {
            addCriterion("area_group_desc <", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescLessThanOrEqualTo(String value) {
            addCriterion("area_group_desc <=", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescLike(String value) {
            addCriterion("area_group_desc like", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescNotLike(String value) {
            addCriterion("area_group_desc not like", value, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescIn(List<String> values) {
            addCriterion("area_group_desc in", values, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescNotIn(List<String> values) {
            addCriterion("area_group_desc not in", values, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescBetween(String value1, String value2) {
            addCriterion("area_group_desc between", value1, value2, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andAreaGroupDescNotBetween(String value1, String value2) {
            addCriterion("area_group_desc not between", value1, value2, "areaGroupDesc");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderIsNull() {
            addCriterion("display_order is null");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderIsNotNull() {
            addCriterion("display_order is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderEqualTo(Integer value) {
            addCriterion("display_order =", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderNotEqualTo(Integer value) {
            addCriterion("display_order <>", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderGreaterThan(Integer value) {
            addCriterion("display_order >", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("display_order >=", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderLessThan(Integer value) {
            addCriterion("display_order <", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderLessThanOrEqualTo(Integer value) {
            addCriterion("display_order <=", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderIn(List<Integer> values) {
            addCriterion("display_order in", values, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderNotIn(List<Integer> values) {
            addCriterion("display_order not in", values, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderBetween(Integer value1, Integer value2) {
            addCriterion("display_order between", value1, value2, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("display_order not between", value1, value2, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrIsNull() {
            addCriterion("client_enter_addr is null");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrIsNotNull() {
            addCriterion("client_enter_addr is not null");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrEqualTo(String value) {
            addCriterion("client_enter_addr =", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrNotEqualTo(String value) {
            addCriterion("client_enter_addr <>", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrGreaterThan(String value) {
            addCriterion("client_enter_addr >", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrGreaterThanOrEqualTo(String value) {
            addCriterion("client_enter_addr >=", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrLessThan(String value) {
            addCriterion("client_enter_addr <", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrLessThanOrEqualTo(String value) {
            addCriterion("client_enter_addr <=", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrLike(String value) {
            addCriterion("client_enter_addr like", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrNotLike(String value) {
            addCriterion("client_enter_addr not like", value, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrIn(List<String> values) {
            addCriterion("client_enter_addr in", values, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrNotIn(List<String> values) {
            addCriterion("client_enter_addr not in", values, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrBetween(String value1, String value2) {
            addCriterion("client_enter_addr between", value1, value2, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andClientEnterAddrNotBetween(String value1, String value2) {
            addCriterion("client_enter_addr not between", value1, value2, "clientEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrIsNull() {
            addCriterion("server_enter_addr is null");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrIsNotNull() {
            addCriterion("server_enter_addr is not null");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrEqualTo(String value) {
            addCriterion("server_enter_addr =", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrNotEqualTo(String value) {
            addCriterion("server_enter_addr <>", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrGreaterThan(String value) {
            addCriterion("server_enter_addr >", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrGreaterThanOrEqualTo(String value) {
            addCriterion("server_enter_addr >=", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrLessThan(String value) {
            addCriterion("server_enter_addr <", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrLessThanOrEqualTo(String value) {
            addCriterion("server_enter_addr <=", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrLike(String value) {
            addCriterion("server_enter_addr like", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrNotLike(String value) {
            addCriterion("server_enter_addr not like", value, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrIn(List<String> values) {
            addCriterion("server_enter_addr in", values, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrNotIn(List<String> values) {
            addCriterion("server_enter_addr not in", values, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrBetween(String value1, String value2) {
            addCriterion("server_enter_addr between", value1, value2, "serverEnterAddr");
            return (Criteria) this;
        }

        public Criteria andServerEnterAddrNotBetween(String value1, String value2) {
            addCriterion("server_enter_addr not between", value1, value2, "serverEnterAddr");
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