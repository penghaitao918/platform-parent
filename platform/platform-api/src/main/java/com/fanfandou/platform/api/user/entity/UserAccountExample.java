package com.fanfandou.platform.api.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAccountExample() {
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

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
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

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(Byte value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(Byte value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(Byte value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(Byte value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(Byte value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<Byte> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<Byte> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(Byte value1, Byte value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedIsNull() {
            addCriterion("encrypt_seed is null");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedIsNotNull() {
            addCriterion("encrypt_seed is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedEqualTo(String value) {
            addCriterion("encrypt_seed =", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedNotEqualTo(String value) {
            addCriterion("encrypt_seed <>", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedGreaterThan(String value) {
            addCriterion("encrypt_seed >", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedGreaterThanOrEqualTo(String value) {
            addCriterion("encrypt_seed >=", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedLessThan(String value) {
            addCriterion("encrypt_seed <", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedLessThanOrEqualTo(String value) {
            addCriterion("encrypt_seed <=", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedLike(String value) {
            addCriterion("encrypt_seed like", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedNotLike(String value) {
            addCriterion("encrypt_seed not like", value, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedIn(List<String> values) {
            addCriterion("encrypt_seed in", values, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedNotIn(List<String> values) {
            addCriterion("encrypt_seed not in", values, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedBetween(String value1, String value2) {
            addCriterion("encrypt_seed between", value1, value2, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andEncryptSeedNotBetween(String value1, String value2) {
            addCriterion("encrypt_seed not between", value1, value2, "encryptSeed");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNull() {
            addCriterion("account_status is null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNotNull() {
            addCriterion("account_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusEqualTo(Byte value) {
            addCriterion("account_status =", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotEqualTo(Byte value) {
            addCriterion("account_status <>", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThan(Byte value) {
            addCriterion("account_status >", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("account_status >=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThan(Byte value) {
            addCriterion("account_status <", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThanOrEqualTo(Byte value) {
            addCriterion("account_status <=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIn(List<Byte> values) {
            addCriterion("account_status in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotIn(List<Byte> values) {
            addCriterion("account_status not in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusBetween(Byte value1, Byte value2) {
            addCriterion("account_status between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("account_status not between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataIsNull() {
            addCriterion("status_extdata is null");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataIsNotNull() {
            addCriterion("status_extdata is not null");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataEqualTo(String value) {
            addCriterion("status_extdata =", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataNotEqualTo(String value) {
            addCriterion("status_extdata <>", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataGreaterThan(String value) {
            addCriterion("status_extdata >", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataGreaterThanOrEqualTo(String value) {
            addCriterion("status_extdata >=", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataLessThan(String value) {
            addCriterion("status_extdata <", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataLessThanOrEqualTo(String value) {
            addCriterion("status_extdata <=", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataLike(String value) {
            addCriterion("status_extdata like", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataNotLike(String value) {
            addCriterion("status_extdata not like", value, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataIn(List<String> values) {
            addCriterion("status_extdata in", values, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataNotIn(List<String> values) {
            addCriterion("status_extdata not in", values, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataBetween(String value1, String value2) {
            addCriterion("status_extdata between", value1, value2, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andStatusExtdataNotBetween(String value1, String value2) {
            addCriterion("status_extdata not between", value1, value2, "statusExtdata");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNull() {
            addCriterion("confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNotNull() {
            addCriterion("confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusEqualTo(Byte value) {
            addCriterion("confirm_status =", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotEqualTo(Byte value) {
            addCriterion("confirm_status <>", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThan(Byte value) {
            addCriterion("confirm_status >", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("confirm_status >=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThan(Byte value) {
            addCriterion("confirm_status <", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThanOrEqualTo(Byte value) {
            addCriterion("confirm_status <=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIn(List<Byte> values) {
            addCriterion("confirm_status in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotIn(List<Byte> values) {
            addCriterion("confirm_status not in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusBetween(Byte value1, Byte value2) {
            addCriterion("confirm_status between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("confirm_status not between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNull() {
            addCriterion("confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNotNull() {
            addCriterion("confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeEqualTo(Date value) {
            addCriterion("confirm_time =", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotEqualTo(Date value) {
            addCriterion("confirm_time <>", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThan(Date value) {
            addCriterion("confirm_time >", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_time >=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThan(Date value) {
            addCriterion("confirm_time <", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThanOrEqualTo(Date value) {
            addCriterion("confirm_time <=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIn(List<Date> values) {
            addCriterion("confirm_time in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotIn(List<Date> values) {
            addCriterion("confirm_time not in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeBetween(Date value1, Date value2) {
            addCriterion("confirm_time between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotBetween(Date value1, Date value2) {
            addCriterion("confirm_time not between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmIpIsNull() {
            addCriterion("confirm_ip is null");
            return (Criteria) this;
        }

        public Criteria andConfirmIpIsNotNull() {
            addCriterion("confirm_ip is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmIpEqualTo(String value) {
            addCriterion("confirm_ip =", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpNotEqualTo(String value) {
            addCriterion("confirm_ip <>", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpGreaterThan(String value) {
            addCriterion("confirm_ip >", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpGreaterThanOrEqualTo(String value) {
            addCriterion("confirm_ip >=", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpLessThan(String value) {
            addCriterion("confirm_ip <", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpLessThanOrEqualTo(String value) {
            addCriterion("confirm_ip <=", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpLike(String value) {
            addCriterion("confirm_ip like", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpNotLike(String value) {
            addCriterion("confirm_ip not like", value, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpIn(List<String> values) {
            addCriterion("confirm_ip in", values, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpNotIn(List<String> values) {
            addCriterion("confirm_ip not in", values, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpBetween(String value1, String value2) {
            addCriterion("confirm_ip between", value1, value2, "confirmIp");
            return (Criteria) this;
        }

        public Criteria andConfirmIpNotBetween(String value1, String value2) {
            addCriterion("confirm_ip not between", value1, value2, "confirmIp");
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

        public Criteria andCreateDeviceSerialIsNull() {
            addCriterion("create_device_serial is null");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialIsNotNull() {
            addCriterion("create_device_serial is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialEqualTo(String value) {
            addCriterion("create_device_serial =", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialNotEqualTo(String value) {
            addCriterion("create_device_serial <>", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialGreaterThan(String value) {
            addCriterion("create_device_serial >", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialGreaterThanOrEqualTo(String value) {
            addCriterion("create_device_serial >=", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialLessThan(String value) {
            addCriterion("create_device_serial <", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialLessThanOrEqualTo(String value) {
            addCriterion("create_device_serial <=", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialLike(String value) {
            addCriterion("create_device_serial like", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialNotLike(String value) {
            addCriterion("create_device_serial not like", value, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialIn(List<String> values) {
            addCriterion("create_device_serial in", values, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialNotIn(List<String> values) {
            addCriterion("create_device_serial not in", values, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialBetween(String value1, String value2) {
            addCriterion("create_device_serial between", value1, value2, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andCreateDeviceSerialNotBetween(String value1, String value2) {
            addCriterion("create_device_serial not between", value1, value2, "createDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpIsNull() {
            addCriterion("last_update_ip is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpIsNotNull() {
            addCriterion("last_update_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpEqualTo(String value) {
            addCriterion("last_update_ip =", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpNotEqualTo(String value) {
            addCriterion("last_update_ip <>", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpGreaterThan(String value) {
            addCriterion("last_update_ip >", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpGreaterThanOrEqualTo(String value) {
            addCriterion("last_update_ip >=", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpLessThan(String value) {
            addCriterion("last_update_ip <", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpLessThanOrEqualTo(String value) {
            addCriterion("last_update_ip <=", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpLike(String value) {
            addCriterion("last_update_ip like", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpNotLike(String value) {
            addCriterion("last_update_ip not like", value, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpIn(List<String> values) {
            addCriterion("last_update_ip in", values, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpNotIn(List<String> values) {
            addCriterion("last_update_ip not in", values, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpBetween(String value1, String value2) {
            addCriterion("last_update_ip between", value1, value2, "lastUpdateIp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIpNotBetween(String value1, String value2) {
            addCriterion("last_update_ip not between", value1, value2, "lastUpdateIp");
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

        public Criteria andLastLoginIpIsNull() {
            addCriterion("last_login_ip is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNotNull() {
            addCriterion("last_login_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpEqualTo(String value) {
            addCriterion("last_login_ip =", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotEqualTo(String value) {
            addCriterion("last_login_ip <>", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThan(String value) {
            addCriterion("last_login_ip >", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("last_login_ip >=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThan(String value) {
            addCriterion("last_login_ip <", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThanOrEqualTo(String value) {
            addCriterion("last_login_ip <=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLike(String value) {
            addCriterion("last_login_ip like", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotLike(String value) {
            addCriterion("last_login_ip not like", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIn(List<String> values) {
            addCriterion("last_login_ip in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotIn(List<String> values) {
            addCriterion("last_login_ip not in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpBetween(String value1, String value2) {
            addCriterion("last_login_ip between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotBetween(String value1, String value2) {
            addCriterion("last_login_ip not between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialIsNull() {
            addCriterion("last_login_device_serial is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialIsNotNull() {
            addCriterion("last_login_device_serial is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialEqualTo(String value) {
            addCriterion("last_login_device_serial =", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialNotEqualTo(String value) {
            addCriterion("last_login_device_serial <>", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialGreaterThan(String value) {
            addCriterion("last_login_device_serial >", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialGreaterThanOrEqualTo(String value) {
            addCriterion("last_login_device_serial >=", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialLessThan(String value) {
            addCriterion("last_login_device_serial <", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialLessThanOrEqualTo(String value) {
            addCriterion("last_login_device_serial <=", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialLike(String value) {
            addCriterion("last_login_device_serial like", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialNotLike(String value) {
            addCriterion("last_login_device_serial not like", value, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialIn(List<String> values) {
            addCriterion("last_login_device_serial in", values, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialNotIn(List<String> values) {
            addCriterion("last_login_device_serial not in", values, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialBetween(String value1, String value2) {
            addCriterion("last_login_device_serial between", value1, value2, "lastLoginDeviceSerial");
            return (Criteria) this;
        }

        public Criteria andLastLoginDeviceSerialNotBetween(String value1, String value2) {
            addCriterion("last_login_device_serial not between", value1, value2, "lastLoginDeviceSerial");
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