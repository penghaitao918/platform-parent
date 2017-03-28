package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.ValidStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameRoleExample() {
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

        public Criteria andRoleHeadIconIsNull() {
            addCriterion("role_head_icon is null");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconIsNotNull() {
            addCriterion("role_head_icon is not null");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconEqualTo(String value) {
            addCriterion("role_head_icon =", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconNotEqualTo(String value) {
            addCriterion("role_head_icon <>", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconGreaterThan(String value) {
            addCriterion("role_head_icon >", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconGreaterThanOrEqualTo(String value) {
            addCriterion("role_head_icon >=", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconLessThan(String value) {
            addCriterion("role_head_icon <", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconLessThanOrEqualTo(String value) {
            addCriterion("role_head_icon <=", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconLike(String value) {
            addCriterion("role_head_icon like", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconNotLike(String value) {
            addCriterion("role_head_icon not like", value, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconIn(List<String> values) {
            addCriterion("role_head_icon in", values, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconNotIn(List<String> values) {
            addCriterion("role_head_icon not in", values, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconBetween(String value1, String value2) {
            addCriterion("role_head_icon between", value1, value2, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleHeadIconNotBetween(String value1, String value2) {
            addCriterion("role_head_icon not between", value1, value2, "roleHeadIcon");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIsNull() {
            addCriterion("role_level is null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIsNotNull() {
            addCriterion("role_level is not null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelEqualTo(Integer value) {
            addCriterion("role_level =", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotEqualTo(Integer value) {
            addCriterion("role_level <>", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelGreaterThan(Integer value) {
            addCriterion("role_level >", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_level >=", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelLessThan(Integer value) {
            addCriterion("role_level <", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelLessThanOrEqualTo(Integer value) {
            addCriterion("role_level <=", value, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIn(List<Integer> values) {
            addCriterion("role_level in", values, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotIn(List<Integer> values) {
            addCriterion("role_level not in", values, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelBetween(Integer value1, Integer value2) {
            addCriterion("role_level between", value1, value2, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("role_level not between", value1, value2, "roleLevel");
            return (Criteria) this;
        }

        public Criteria andRoleDescIsNull() {
            addCriterion("role_desc is null");
            return (Criteria) this;
        }

        public Criteria andRoleDescIsNotNull() {
            addCriterion("role_desc is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDescEqualTo(String value) {
            addCriterion("role_desc =", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotEqualTo(String value) {
            addCriterion("role_desc <>", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescGreaterThan(String value) {
            addCriterion("role_desc >", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescGreaterThanOrEqualTo(String value) {
            addCriterion("role_desc >=", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescLessThan(String value) {
            addCriterion("role_desc <", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescLessThanOrEqualTo(String value) {
            addCriterion("role_desc <=", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescLike(String value) {
            addCriterion("role_desc like", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotLike(String value) {
            addCriterion("role_desc not like", value, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescIn(List<String> values) {
            addCriterion("role_desc in", values, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotIn(List<String> values) {
            addCriterion("role_desc not in", values, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescBetween(String value1, String value2) {
            addCriterion("role_desc between", value1, value2, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andRoleDescNotBetween(String value1, String value2) {
            addCriterion("role_desc not between", value1, value2, "roleDesc");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNull() {
            addCriterion("last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Date value) {
            addCriterion("last_login_time =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Date value) {
            addCriterion("last_login_time <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Date value) {
            addCriterion("last_login_time >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_time >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Date value) {
            addCriterion("last_login_time <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_login_time <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Date> values) {
            addCriterion("last_login_time in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Date> values) {
            addCriterion("last_login_time not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Date value1, Date value2) {
            addCriterion("last_login_time between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_login_time not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeIsNull() {
            addCriterion("last_logout_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeIsNotNull() {
            addCriterion("last_logout_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeEqualTo(Date value) {
            addCriterion("last_logout_time =", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeNotEqualTo(Date value) {
            addCriterion("last_logout_time <>", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeGreaterThan(Date value) {
            addCriterion("last_logout_time >", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_logout_time >=", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeLessThan(Date value) {
            addCriterion("last_logout_time <", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_logout_time <=", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeIn(List<Date> values) {
            addCriterion("last_logout_time in", values, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeNotIn(List<Date> values) {
            addCriterion("last_logout_time not in", values, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeBetween(Date value1, Date value2) {
            addCriterion("last_logout_time between", value1, value2, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_logout_time not between", value1, value2, "lastLogoutTime");
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

        public Criteria andValidStatusIsNull() {
            addCriterion("valid_status is null");
            return (Criteria) this;
        }

        public Criteria andValidStatusIsNotNull() {
            addCriterion("valid_status is not null");
            return (Criteria) this;
        }

        public Criteria andValidStatusEqualTo(ValidStatus value) {
            addCriterion("valid_status =", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotEqualTo(ValidStatus value) {
            addCriterion("valid_status <>", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThan(ValidStatus value) {
            addCriterion("valid_status >", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThanOrEqualTo(ValidStatus value) {
            addCriterion("valid_status >=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThan(ValidStatus value) {
            addCriterion("valid_status <", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThanOrEqualTo(ValidStatus value) {
            addCriterion("valid_status <=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusIn(List<ValidStatus> values) {
            addCriterion("valid_status in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotIn(List<ValidStatus> values) {
            addCriterion("valid_status not in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusBetween(ValidStatus value1, ValidStatus value2) {
            addCriterion("valid_status between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotBetween(ValidStatus value1, ValidStatus value2) {
            addCriterion("valid_status not between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountIsNull() {
            addCriterion("total_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountIsNotNull() {
            addCriterion("total_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountEqualTo(Long value) {
            addCriterion("total_pay_amount =", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountNotEqualTo(Long value) {
            addCriterion("total_pay_amount <>", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountGreaterThan(Long value) {
            addCriterion("total_pay_amount >", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_pay_amount >=", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountLessThan(Long value) {
            addCriterion("total_pay_amount <", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountLessThanOrEqualTo(Long value) {
            addCriterion("total_pay_amount <=", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountIn(List<Long> values) {
            addCriterion("total_pay_amount in", values, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountNotIn(List<Long> values) {
            addCriterion("total_pay_amount not in", values, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountBetween(Long value1, Long value2) {
            addCriterion("total_pay_amount between", value1, value2, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountNotBetween(Long value1, Long value2) {
            addCriterion("total_pay_amount not between", value1, value2, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesIsNull() {
            addCriterion("total_pay_times is null");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesIsNotNull() {
            addCriterion("total_pay_times is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesEqualTo(Integer value) {
            addCriterion("total_pay_times =", value, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesNotEqualTo(Integer value) {
            addCriterion("total_pay_times <>", value, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesGreaterThan(Integer value) {
            addCriterion("total_pay_times >", value, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_pay_times >=", value, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesLessThan(Integer value) {
            addCriterion("total_pay_times <", value, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesLessThanOrEqualTo(Integer value) {
            addCriterion("total_pay_times <=", value, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesIn(List<Integer> values) {
            addCriterion("total_pay_times in", values, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesNotIn(List<Integer> values) {
            addCriterion("total_pay_times not in", values, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesBetween(Integer value1, Integer value2) {
            addCriterion("total_pay_times between", value1, value2, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalPayTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("total_pay_times not between", value1, value2, "totalPayTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesIsNull() {
            addCriterion("total_login_times is null");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesIsNotNull() {
            addCriterion("total_login_times is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesEqualTo(Integer value) {
            addCriterion("total_login_times =", value, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesNotEqualTo(Integer value) {
            addCriterion("total_login_times <>", value, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesGreaterThan(Integer value) {
            addCriterion("total_login_times >", value, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_login_times >=", value, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesLessThan(Integer value) {
            addCriterion("total_login_times <", value, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesLessThanOrEqualTo(Integer value) {
            addCriterion("total_login_times <=", value, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesIn(List<Integer> values) {
            addCriterion("total_login_times in", values, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesNotIn(List<Integer> values) {
            addCriterion("total_login_times not in", values, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesBetween(Integer value1, Integer value2) {
            addCriterion("total_login_times between", value1, value2, "totalLoginTimes");
            return (Criteria) this;
        }

        public Criteria andTotalLoginTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("total_login_times not between", value1, value2, "totalLoginTimes");
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