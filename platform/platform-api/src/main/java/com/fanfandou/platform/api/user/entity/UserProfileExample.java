package com.fanfandou.platform.api.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserProfileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserProfileExample() {
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

        public Criteria andProfileDomainIsNull() {
            addCriterion("profile_domain is null");
            return (Criteria) this;
        }

        public Criteria andProfileDomainIsNotNull() {
            addCriterion("profile_domain is not null");
            return (Criteria) this;
        }

        public Criteria andProfileDomainEqualTo(Byte value) {
            addCriterion("profile_domain =", value, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainNotEqualTo(Byte value) {
            addCriterion("profile_domain <>", value, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainGreaterThan(Byte value) {
            addCriterion("profile_domain >", value, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainGreaterThanOrEqualTo(Byte value) {
            addCriterion("profile_domain >=", value, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainLessThan(Byte value) {
            addCriterion("profile_domain <", value, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainLessThanOrEqualTo(Byte value) {
            addCriterion("profile_domain <=", value, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainIn(List<Byte> values) {
            addCriterion("profile_domain in", values, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainNotIn(List<Byte> values) {
            addCriterion("profile_domain not in", values, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainBetween(Byte value1, Byte value2) {
            addCriterion("profile_domain between", value1, value2, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andProfileDomainNotBetween(Byte value1, Byte value2) {
            addCriterion("profile_domain not between", value1, value2, "profileDomain");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idcard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idcard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idcard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idcard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idcard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idcard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idcard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idcard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idcard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idcard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idcard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idcard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idcard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andHeadIconIsNull() {
            addCriterion("head_icon is null");
            return (Criteria) this;
        }

        public Criteria andHeadIconIsNotNull() {
            addCriterion("head_icon is not null");
            return (Criteria) this;
        }

        public Criteria andHeadIconEqualTo(String value) {
            addCriterion("head_icon =", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconNotEqualTo(String value) {
            addCriterion("head_icon <>", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconGreaterThan(String value) {
            addCriterion("head_icon >", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconGreaterThanOrEqualTo(String value) {
            addCriterion("head_icon >=", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconLessThan(String value) {
            addCriterion("head_icon <", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconLessThanOrEqualTo(String value) {
            addCriterion("head_icon <=", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconLike(String value) {
            addCriterion("head_icon like", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconNotLike(String value) {
            addCriterion("head_icon not like", value, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconIn(List<String> values) {
            addCriterion("head_icon in", values, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconNotIn(List<String> values) {
            addCriterion("head_icon not in", values, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconBetween(String value1, String value2) {
            addCriterion("head_icon between", value1, value2, "headIcon");
            return (Criteria) this;
        }

        public Criteria andHeadIconNotBetween(String value1, String value2) {
            addCriterion("head_icon not between", value1, value2, "headIcon");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Byte value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Byte value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Byte value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Byte value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Byte value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Byte value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Byte> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Byte> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Byte value1, Byte value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Byte value1, Byte value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andProfileStatusIsNull() {
            addCriterion("profile_status is null");
            return (Criteria) this;
        }

        public Criteria andProfileStatusIsNotNull() {
            addCriterion("profile_status is not null");
            return (Criteria) this;
        }

        public Criteria andProfileStatusEqualTo(Byte value) {
            addCriterion("profile_status =", value, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusNotEqualTo(Byte value) {
            addCriterion("profile_status <>", value, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusGreaterThan(Byte value) {
            addCriterion("profile_status >", value, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("profile_status >=", value, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusLessThan(Byte value) {
            addCriterion("profile_status <", value, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusLessThanOrEqualTo(Byte value) {
            addCriterion("profile_status <=", value, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusIn(List<Byte> values) {
            addCriterion("profile_status in", values, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusNotIn(List<Byte> values) {
            addCriterion("profile_status not in", values, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusBetween(Byte value1, Byte value2) {
            addCriterion("profile_status between", value1, value2, "profileStatus");
            return (Criteria) this;
        }

        public Criteria andProfileStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("profile_status not between", value1, value2, "profileStatus");
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

        public Criteria andProfileDescIsNull() {
            addCriterion("profile_desc is null");
            return (Criteria) this;
        }

        public Criteria andProfileDescIsNotNull() {
            addCriterion("profile_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProfileDescEqualTo(String value) {
            addCriterion("profile_desc =", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescNotEqualTo(String value) {
            addCriterion("profile_desc <>", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescGreaterThan(String value) {
            addCriterion("profile_desc >", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescGreaterThanOrEqualTo(String value) {
            addCriterion("profile_desc >=", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescLessThan(String value) {
            addCriterion("profile_desc <", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescLessThanOrEqualTo(String value) {
            addCriterion("profile_desc <=", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescLike(String value) {
            addCriterion("profile_desc like", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescNotLike(String value) {
            addCriterion("profile_desc not like", value, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescIn(List<String> values) {
            addCriterion("profile_desc in", values, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescNotIn(List<String> values) {
            addCriterion("profile_desc not in", values, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescBetween(String value1, String value2) {
            addCriterion("profile_desc between", value1, value2, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andProfileDescNotBetween(String value1, String value2) {
            addCriterion("profile_desc not between", value1, value2, "profileDesc");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Byte value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Byte value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Byte value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Byte value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Byte value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Byte> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Byte> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Byte value1, Byte value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNull() {
            addCriterion("audit_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNotNull() {
            addCriterion("audit_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdEqualTo(Long value) {
            addCriterion("audit_user_id =", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotEqualTo(Long value) {
            addCriterion("audit_user_id <>", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThan(Long value) {
            addCriterion("audit_user_id >", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("audit_user_id >=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThan(Long value) {
            addCriterion("audit_user_id <", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThanOrEqualTo(Long value) {
            addCriterion("audit_user_id <=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIn(List<Long> values) {
            addCriterion("audit_user_id in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotIn(List<Long> values) {
            addCriterion("audit_user_id not in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdBetween(Long value1, Long value2) {
            addCriterion("audit_user_id between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotBetween(Long value1, Long value2) {
            addCriterion("audit_user_id not between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditIpIsNull() {
            addCriterion("audit_ip is null");
            return (Criteria) this;
        }

        public Criteria andAuditIpIsNotNull() {
            addCriterion("audit_ip is not null");
            return (Criteria) this;
        }

        public Criteria andAuditIpEqualTo(String value) {
            addCriterion("audit_ip =", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpNotEqualTo(String value) {
            addCriterion("audit_ip <>", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpGreaterThan(String value) {
            addCriterion("audit_ip >", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpGreaterThanOrEqualTo(String value) {
            addCriterion("audit_ip >=", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpLessThan(String value) {
            addCriterion("audit_ip <", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpLessThanOrEqualTo(String value) {
            addCriterion("audit_ip <=", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpLike(String value) {
            addCriterion("audit_ip like", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpNotLike(String value) {
            addCriterion("audit_ip not like", value, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpIn(List<String> values) {
            addCriterion("audit_ip in", values, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpNotIn(List<String> values) {
            addCriterion("audit_ip not in", values, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpBetween(String value1, String value2) {
            addCriterion("audit_ip between", value1, value2, "auditIp");
            return (Criteria) this;
        }

        public Criteria andAuditIpNotBetween(String value1, String value2) {
            addCriterion("audit_ip not between", value1, value2, "auditIp");
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