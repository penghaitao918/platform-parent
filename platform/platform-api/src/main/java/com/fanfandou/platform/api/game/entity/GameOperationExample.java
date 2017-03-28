package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameOperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameOperationExample() {
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

        public Criteria andOptIdIsNull() {
            addCriterion("opt_id is null");
            return (Criteria) this;
        }

        public Criteria andOptIdIsNotNull() {
            addCriterion("opt_id is not null");
            return (Criteria) this;
        }

        public Criteria andOptIdEqualTo(Long value) {
            addCriterion("opt_id =", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotEqualTo(Long value) {
            addCriterion("opt_id <>", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdGreaterThan(Long value) {
            addCriterion("opt_id >", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("opt_id >=", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdLessThan(Long value) {
            addCriterion("opt_id <", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdLessThanOrEqualTo(Long value) {
            addCriterion("opt_id <=", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdIn(List<Long> values) {
            addCriterion("opt_id in", values, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotIn(List<Long> values) {
            addCriterion("opt_id not in", values, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdBetween(Long value1, Long value2) {
            addCriterion("opt_id between", value1, value2, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotBetween(Long value1, Long value2) {
            addCriterion("opt_id not between", value1, value2, "optId");
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

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Long value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Long value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Long value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Long value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Long> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Long> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("opt_type is null");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("opt_type is not null");
            return (Criteria) this;
        }

        public Criteria andOptTypeEqualTo(OperationType value) {
            addCriterion("opt_type =", value.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotEqualTo(OperationType value) {
            addCriterion("opt_type <>", value.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThan(OperationType value) {
            addCriterion("opt_type >", value.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(OperationType value) {
            addCriterion("opt_type >=", value.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThan(OperationType value) {
            addCriterion("opt_type <", value.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(OperationType value) {
            addCriterion("opt_type <=", value.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIn(List<OperationType> values) {
            List<Integer> valueIds = new ArrayList<>();
            for (OperationType status : values) {
                valueIds.add(status.getId());
            }
            addCriterion("opt_type in", valueIds, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotIn(List<OperationType> values) {
            List<Integer> valueIds = new ArrayList<>();
            for (OperationType status : values) {
                valueIds.add(status.getId());
            }
            addCriterion("opt_type not in", valueIds, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeBetween(OperationType value1, OperationType value2) {
            addCriterion("opt_type between", value1.getId(), value2.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotBetween(OperationType value1, OperationType value2) {
            addCriterion("opt_type not between", value1.getId(), value2.getId(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptContentIsNull() {
            addCriterion("opt_content is null");
            return (Criteria) this;
        }

        public Criteria andOptContentIsNotNull() {
            addCriterion("opt_content is not null");
            return (Criteria) this;
        }

        public Criteria andOptContentEqualTo(String value) {
            addCriterion("opt_content =", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotEqualTo(String value) {
            addCriterion("opt_content <>", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentGreaterThan(String value) {
            addCriterion("opt_content >", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentGreaterThanOrEqualTo(String value) {
            addCriterion("opt_content >=", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentLessThan(String value) {
            addCriterion("opt_content <", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentLessThanOrEqualTo(String value) {
            addCriterion("opt_content <=", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentLike(String value) {
            addCriterion("opt_content like", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotLike(String value) {
            addCriterion("opt_content not like", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentIn(List<String> values) {
            addCriterion("opt_content in", values, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotIn(List<String> values) {
            addCriterion("opt_content not in", values, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentBetween(String value1, String value2) {
            addCriterion("opt_content between", value1, value2, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotBetween(String value1, String value2) {
            addCriterion("opt_content not between", value1, value2, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptDescIsNull() {
            addCriterion("opt_desc is null");
            return (Criteria) this;
        }

        public Criteria andOptDescIsNotNull() {
            addCriterion("opt_desc is not null");
            return (Criteria) this;
        }

        public Criteria andOptDescEqualTo(String value) {
            addCriterion("opt_desc =", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescNotEqualTo(String value) {
            addCriterion("opt_desc <>", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescGreaterThan(String value) {
            addCriterion("opt_desc >", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescGreaterThanOrEqualTo(String value) {
            addCriterion("opt_desc >=", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescLessThan(String value) {
            addCriterion("opt_desc <", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescLessThanOrEqualTo(String value) {
            addCriterion("opt_desc <=", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescLike(String value) {
            addCriterion("opt_desc like", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescNotLike(String value) {
            addCriterion("opt_desc not like", value, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescIn(List<String> values) {
            addCriterion("opt_desc in", values, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescNotIn(List<String> values) {
            addCriterion("opt_desc not in", values, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescBetween(String value1, String value2) {
            addCriterion("opt_desc between", value1, value2, "optDesc");
            return (Criteria) this;
        }

        public Criteria andOptDescNotBetween(String value1, String value2) {
            addCriterion("opt_desc not between", value1, value2, "optDesc");
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

        public Criteria andValidStatusEqualTo(ValidStatus value) {
            addCriterion("valid_status =", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotEqualTo(ValidStatus value) {
            addCriterion("valid_status <>", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThan(ValidStatus value) {
            addCriterion("valid_status >", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThanOrEqualTo(ValidStatus value) {
            addCriterion("valid_status >=", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThan(ValidStatus value) {
            addCriterion("valid_status <", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThanOrEqualTo(ValidStatus value) {
            addCriterion("valid_status <=", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusIn(List<ValidStatus> values) {
            List<Integer> valueIds = new ArrayList<>();
            for (ValidStatus status : values) {
                valueIds.add(status.getId());
            }
            addCriterion("valid_status in", valueIds, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotIn(List<ValidStatus> values) {
            List<Integer> valueIds = new ArrayList<>();
            for (ValidStatus status : values) {
                valueIds.add(status.getId());
            }
            addCriterion("valid_status not in", valueIds, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusBetween(ValidStatus value1, ValidStatus value2) {
            addCriterion("valid_status between", value1.getId(), value2.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotBetween(ValidStatus value1, ValidStatus value2) {
            addCriterion("valid_status not between", value1.getId(), value2.getId(), "validStatus");
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

        public Criteria andTryTimesIsNull() {
            addCriterion("try_times is null");
            return (Criteria) this;
        }

        public Criteria andTryTimesIsNotNull() {
            addCriterion("try_times is not null");
            return (Criteria) this;
        }

        public Criteria andTryTimesEqualTo(Integer value) {
            addCriterion("try_times =", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotEqualTo(Integer value) {
            addCriterion("try_times <>", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesGreaterThan(Integer value) {
            addCriterion("try_times >", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("try_times >=", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesLessThan(Integer value) {
            addCriterion("try_times <", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesLessThanOrEqualTo(Integer value) {
            addCriterion("try_times <=", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesIn(List<Integer> values) {
            addCriterion("try_times in", values, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotIn(List<Integer> values) {
            addCriterion("try_times not in", values, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesBetween(Integer value1, Integer value2) {
            addCriterion("try_times between", value1, value2, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("try_times not between", value1, value2, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeIsNull() {
            addCriterion("last_try_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeIsNotNull() {
            addCriterion("last_try_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeEqualTo(Date value) {
            addCriterion("last_try_time =", value, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeNotEqualTo(Date value) {
            addCriterion("last_try_time <>", value, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeGreaterThan(Date value) {
            addCriterion("last_try_time >", value, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_try_time >=", value, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeLessThan(Date value) {
            addCriterion("last_try_time <", value, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_try_time <=", value, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeIn(List<Date> values) {
            addCriterion("last_try_time in", values, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeNotIn(List<Date> values) {
            addCriterion("last_try_time not in", values, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeBetween(Date value1, Date value2) {
            addCriterion("last_try_time between", value1, value2, "lastTryTime");
            return (Criteria) this;
        }

        public Criteria andLastTryTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_try_time not between", value1, value2, "lastTryTime");
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

        public Criteria andDeliverStatusEqualTo(ActStatus value) {
            addCriterion("deliver_status =", value.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotEqualTo(ActStatus value) {
            addCriterion("deliver_status <>", value.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusGreaterThan(ActStatus value) {
            addCriterion("deliver_status >", value.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusGreaterThanOrEqualTo(ActStatus value) {
            addCriterion("deliver_status >=", value.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLessThan(ActStatus value) {
            addCriterion("deliver_status <", value.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLessThanOrEqualTo(ActStatus value) {
            addCriterion("deliver_status <=", value.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIn(List<ActStatus> values) {
            List<Integer> valueIds = new ArrayList<>();
            for (ActStatus status : values) {
                valueIds.add(status.getId());
            }
            addCriterion("deliver_status in", valueIds, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotIn(List<ActStatus> values) {
            List<Integer> valueIds = new ArrayList<>();
            for (ActStatus status : values) {
                valueIds.add(status.getId());
            }
            addCriterion("deliver_status not in", valueIds, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusBetween(ActStatus value1, ActStatus value2) {
            addCriterion("deliver_status between", value1.getId(), value2.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotBetween(ActStatus value1, ActStatus value2) {
            addCriterion("deliver_status not between", value1.getId(), value2.getId(), "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNull() {
            addCriterion("deliver_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNotNull() {
            addCriterion("deliver_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeEqualTo(Date value) {
            addCriterion("deliver_time =", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotEqualTo(Date value) {
            addCriterion("deliver_time <>", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThan(Date value) {
            addCriterion("deliver_time >", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver_time >=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThan(Date value) {
            addCriterion("deliver_time <", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThanOrEqualTo(Date value) {
            addCriterion("deliver_time <=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIn(List<Date> values) {
            addCriterion("deliver_time in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotIn(List<Date> values) {
            addCriterion("deliver_time not in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeBetween(Date value1, Date value2) {
            addCriterion("deliver_time between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotBetween(Date value1, Date value2) {
            addCriterion("deliver_time not between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameIsNull() {
            addCriterion("deliver_server_name is null");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameIsNotNull() {
            addCriterion("deliver_server_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameEqualTo(String value) {
            addCriterion("deliver_server_name =", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameNotEqualTo(String value) {
            addCriterion("deliver_server_name <>", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameGreaterThan(String value) {
            addCriterion("deliver_server_name >", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameGreaterThanOrEqualTo(String value) {
            addCriterion("deliver_server_name >=", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameLessThan(String value) {
            addCriterion("deliver_server_name <", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameLessThanOrEqualTo(String value) {
            addCriterion("deliver_server_name <=", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameLike(String value) {
            addCriterion("deliver_server_name like", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameNotLike(String value) {
            addCriterion("deliver_server_name not like", value, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameIn(List<String> values) {
            addCriterion("deliver_server_name in", values, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameNotIn(List<String> values) {
            addCriterion("deliver_server_name not in", values, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameBetween(String value1, String value2) {
            addCriterion("deliver_server_name between", value1, value2, "deliverServerName");
            return (Criteria) this;
        }

        public Criteria andDeliverServerNameNotBetween(String value1, String value2) {
            addCriterion("deliver_server_name not between", value1, value2, "deliverServerName");
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