package com.fanfandou.platform.api.activity.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromoteAwardPackageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PromoteAwardPackageExample() {
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

        public Criteria andPackageNameIsNull() {
            addCriterion("package_name is null");
            return (Criteria) this;
        }

        public Criteria andPackageNameIsNotNull() {
            addCriterion("package_name is not null");
            return (Criteria) this;
        }

        public Criteria andPackageNameEqualTo(String value) {
            addCriterion("package_name =", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotEqualTo(String value) {
            addCriterion("package_name <>", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameGreaterThan(String value) {
            addCriterion("package_name >", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameGreaterThanOrEqualTo(String value) {
            addCriterion("package_name >=", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLessThan(String value) {
            addCriterion("package_name <", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLessThanOrEqualTo(String value) {
            addCriterion("package_name <=", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLike(String value) {
            addCriterion("package_name like", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotLike(String value) {
            addCriterion("package_name not like", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameIn(List<String> values) {
            addCriterion("package_name in", values, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotIn(List<String> values) {
            addCriterion("package_name not in", values, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameBetween(String value1, String value2) {
            addCriterion("package_name between", value1, value2, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotBetween(String value1, String value2) {
            addCriterion("package_name not between", value1, value2, "packageName");
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

        public Criteria andItemsPackageIsNull() {
            addCriterion("items_package is null");
            return (Criteria) this;
        }

        public Criteria andItemsPackageIsNotNull() {
            addCriterion("items_package is not null");
            return (Criteria) this;
        }

        public Criteria andItemsPackageEqualTo(String value) {
            addCriterion("items_package =", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageNotEqualTo(String value) {
            addCriterion("items_package <>", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageGreaterThan(String value) {
            addCriterion("items_package >", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageGreaterThanOrEqualTo(String value) {
            addCriterion("items_package >=", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageLessThan(String value) {
            addCriterion("items_package <", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageLessThanOrEqualTo(String value) {
            addCriterion("items_package <=", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageLike(String value) {
            addCriterion("items_package like", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageNotLike(String value) {
            addCriterion("items_package not like", value, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageIn(List<String> values) {
            addCriterion("items_package in", values, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageNotIn(List<String> values) {
            addCriterion("items_package not in", values, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageBetween(String value1, String value2) {
            addCriterion("items_package between", value1, value2, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andItemsPackageNotBetween(String value1, String value2) {
            addCriterion("items_package not between", value1, value2, "itemsPackage");
            return (Criteria) this;
        }

        public Criteria andPackageGreetIsNull() {
            addCriterion("package_greet is null");
            return (Criteria) this;
        }

        public Criteria andPackageGreetIsNotNull() {
            addCriterion("package_greet is not null");
            return (Criteria) this;
        }

        public Criteria andPackageGreetEqualTo(String value) {
            addCriterion("package_greet =", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetNotEqualTo(String value) {
            addCriterion("package_greet <>", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetGreaterThan(String value) {
            addCriterion("package_greet >", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetGreaterThanOrEqualTo(String value) {
            addCriterion("package_greet >=", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetLessThan(String value) {
            addCriterion("package_greet <", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetLessThanOrEqualTo(String value) {
            addCriterion("package_greet <=", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetLike(String value) {
            addCriterion("package_greet like", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetNotLike(String value) {
            addCriterion("package_greet not like", value, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetIn(List<String> values) {
            addCriterion("package_greet in", values, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetNotIn(List<String> values) {
            addCriterion("package_greet not in", values, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetBetween(String value1, String value2) {
            addCriterion("package_greet between", value1, value2, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageGreetNotBetween(String value1, String value2) {
            addCriterion("package_greet not between", value1, value2, "packageGreet");
            return (Criteria) this;
        }

        public Criteria andPackageDescIsNull() {
            addCriterion("package_desc is null");
            return (Criteria) this;
        }

        public Criteria andPackageDescIsNotNull() {
            addCriterion("package_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPackageDescEqualTo(String value) {
            addCriterion("package_desc =", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescNotEqualTo(String value) {
            addCriterion("package_desc <>", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescGreaterThan(String value) {
            addCriterion("package_desc >", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescGreaterThanOrEqualTo(String value) {
            addCriterion("package_desc >=", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescLessThan(String value) {
            addCriterion("package_desc <", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescLessThanOrEqualTo(String value) {
            addCriterion("package_desc <=", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescLike(String value) {
            addCriterion("package_desc like", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescNotLike(String value) {
            addCriterion("package_desc not like", value, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescIn(List<String> values) {
            addCriterion("package_desc in", values, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescNotIn(List<String> values) {
            addCriterion("package_desc not in", values, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescBetween(String value1, String value2) {
            addCriterion("package_desc between", value1, value2, "packageDesc");
            return (Criteria) this;
        }

        public Criteria andPackageDescNotBetween(String value1, String value2) {
            addCriterion("package_desc not between", value1, value2, "packageDesc");
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

        public Criteria andCreateAdminUserIdEqualTo(String value) {
            addCriterion("create_admin_user_id =", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotEqualTo(String value) {
            addCriterion("create_admin_user_id <>", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdGreaterThan(String value) {
            addCriterion("create_admin_user_id >", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_admin_user_id >=", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdLessThan(String value) {
            addCriterion("create_admin_user_id <", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_admin_user_id <=", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdLike(String value) {
            addCriterion("create_admin_user_id like", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotLike(String value) {
            addCriterion("create_admin_user_id not like", value, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdIn(List<String> values) {
            addCriterion("create_admin_user_id in", values, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotIn(List<String> values) {
            addCriterion("create_admin_user_id not in", values, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdBetween(String value1, String value2) {
            addCriterion("create_admin_user_id between", value1, value2, "createAdminUserId");
            return (Criteria) this;
        }

        public Criteria andCreateAdminUserIdNotBetween(String value1, String value2) {
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