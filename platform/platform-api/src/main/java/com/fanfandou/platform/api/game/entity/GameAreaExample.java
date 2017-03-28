package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.ActStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameAreaExample() {
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

        public Criteria andSiteIdsIsNull() {
            addCriterion("site_ids is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdsIsNotNull() {
            addCriterion("site_ids is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdsEqualTo(String value) {
            addCriterion("site_ids =", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsNotEqualTo(String value) {
            addCriterion("site_ids <>", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsGreaterThan(String value) {
            addCriterion("site_ids >", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsGreaterThanOrEqualTo(String value) {
            addCriterion("site_ids >=", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsLessThan(String value) {
            addCriterion("site_ids <", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsLessThanOrEqualTo(String value) {
            addCriterion("site_ids <=", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsLike(String value) {
            addCriterion("site_ids like", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsNotLike(String value) {
            addCriterion("site_ids not like", value, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsIn(List<String> values) {
            addCriterion("site_ids in", values, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsNotIn(List<String> values) {
            addCriterion("site_ids not in", values, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsBetween(String value1, String value2) {
            addCriterion("site_ids between", value1, value2, "siteIds");
            return (Criteria) this;
        }

        public Criteria andSiteIdsNotBetween(String value1, String value2) {
            addCriterion("site_ids not between", value1, value2, "siteIds");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaTagIsNull() {
            addCriterion("area_tag is null");
            return (Criteria) this;
        }

        public Criteria andAreaTagIsNotNull() {
            addCriterion("area_tag is not null");
            return (Criteria) this;
        }

        public Criteria andAreaTagEqualTo(Byte value) {
            addCriterion("area_tag =", value, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagNotEqualTo(Byte value) {
            addCriterion("area_tag <>", value, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagGreaterThan(Byte value) {
            addCriterion("area_tag >", value, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagGreaterThanOrEqualTo(Byte value) {
            addCriterion("area_tag >=", value, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagLessThan(Byte value) {
            addCriterion("area_tag <", value, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagLessThanOrEqualTo(Byte value) {
            addCriterion("area_tag <=", value, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagIn(List<Byte> values) {
            addCriterion("area_tag in", values, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagNotIn(List<Byte> values) {
            addCriterion("area_tag not in", values, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagBetween(Byte value1, Byte value2) {
            addCriterion("area_tag between", value1, value2, "areaTag");
            return (Criteria) this;
        }

        public Criteria andAreaTagNotBetween(Byte value1, Byte value2) {
            addCriterion("area_tag not between", value1, value2, "areaTag");
            return (Criteria) this;
        }

        public Criteria andLoadStatusIsNull() {
            addCriterion("load_status is null");
            return (Criteria) this;
        }

        public Criteria andLoadStatusIsNotNull() {
            addCriterion("load_status is not null");
            return (Criteria) this;
        }

        public Criteria andLoadStatusEqualTo(Byte value) {
            addCriterion("load_status =", value, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusNotEqualTo(Byte value) {
            addCriterion("load_status <>", value, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusGreaterThan(Byte value) {
            addCriterion("load_status >", value, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("load_status >=", value, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusLessThan(Byte value) {
            addCriterion("load_status <", value, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusLessThanOrEqualTo(Byte value) {
            addCriterion("load_status <=", value, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusIn(List<Byte> values) {
            addCriterion("load_status in", values, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusNotIn(List<Byte> values) {
            addCriterion("load_status not in", values, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusBetween(Byte value1, Byte value2) {
            addCriterion("load_status between", value1, value2, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andLoadStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("load_status not between", value1, value2, "loadStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusIsNull() {
            addCriterion("maintenance_status is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusIsNotNull() {
            addCriterion("maintenance_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusEqualTo(MaintenanceStatus value) {
            addCriterion("maintenance_status =", value, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusNotEqualTo(MaintenanceStatus value) {
            addCriterion("maintenance_status <>", value, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusGreaterThan(MaintenanceStatus value) {
            addCriterion("maintenance_status >", value, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("maintenance_status >=", value, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusLessThan(MaintenanceStatus value) {
            addCriterion("maintenance_status <", value, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusLessThanOrEqualTo(MaintenanceStatus value) {
            addCriterion("maintenance_status <=", value, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusIn(List<MaintenanceStatus> values) {
            addCriterion("maintenance_status in", values, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusNotIn(List<MaintenanceStatus> values) {
            addCriterion("maintenance_status not in", values, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusBetween(MaintenanceStatus value1, MaintenanceStatus value2) {
            addCriterion("maintenance_status between", value1, value2, "maintenanceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceStatusNotBetween(MaintenanceStatus value1, MaintenanceStatus value2) {
            addCriterion("maintenance_status not between", value1, value2, "maintenanceStatus");
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

        public Criteria andValidStatusEqualTo(ActStatus value) {
            addCriterion("valid_status =", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotEqualTo(ActStatus value) {
            addCriterion("valid_status <>", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThan(ActStatus value) {
            addCriterion("valid_status >", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThanOrEqualTo(ActStatus value) {
            addCriterion("valid_status >=", value.getId(), "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThan(ActStatus value) {
            addCriterion("valid_status <", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThanOrEqualTo(ActStatus value) {
            addCriterion("valid_status <=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusIn(List<ActStatus> values) {
            addCriterion("valid_status in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotIn(List<ActStatus> values) {
            addCriterion("valid_status not in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusBetween(ActStatus value1, ActStatus value2) {
            addCriterion("valid_status between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotBetween(ActStatus value1, ActStatus value2) {
            addCriterion("valid_status not between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeIsNull() {
            addCriterion("availible_time is null");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeIsNotNull() {
            addCriterion("availible_time is not null");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeEqualTo(Date value) {
            addCriterion("availible_time =", value, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeNotEqualTo(Date value) {
            addCriterion("availible_time <>", value, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeGreaterThan(Date value) {
            addCriterion("availible_time >", value, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("availible_time >=", value, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeLessThan(Date value) {
            addCriterion("availible_time <", value, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeLessThanOrEqualTo(Date value) {
            addCriterion("availible_time <=", value, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeIn(List<Date> values) {
            addCriterion("availible_time in", values, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeNotIn(List<Date> values) {
            addCriterion("availible_time not in", values, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeBetween(Date value1, Date value2) {
            addCriterion("availible_time between", value1, value2, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andAvailibleTimeNotBetween(Date value1, Date value2) {
            addCriterion("availible_time not between", value1, value2, "availibleTime");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinIsNull() {
            addCriterion("support_version_min is null");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinIsNotNull() {
            addCriterion("support_version_min is not null");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinEqualTo(String value) {
            addCriterion("support_version_min =", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinNotEqualTo(String value) {
            addCriterion("support_version_min <>", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinGreaterThan(String value) {
            addCriterion("support_version_min >", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinGreaterThanOrEqualTo(String value) {
            addCriterion("support_version_min >=", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinLessThan(String value) {
            addCriterion("support_version_min <", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinLessThanOrEqualTo(String value) {
            addCriterion("support_version_min <=", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinLike(String value) {
            addCriterion("support_version_min like", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinNotLike(String value) {
            addCriterion("support_version_min not like", value, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinIn(List<String> values) {
            addCriterion("support_version_min in", values, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinNotIn(List<String> values) {
            addCriterion("support_version_min not in", values, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinBetween(String value1, String value2) {
            addCriterion("support_version_min between", value1, value2, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMinNotBetween(String value1, String value2) {
            addCriterion("support_version_min not between", value1, value2, "supportVersionMin");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxIsNull() {
            addCriterion("support_version_max is null");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxIsNotNull() {
            addCriterion("support_version_max is not null");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxEqualTo(String value) {
            addCriterion("support_version_max =", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxNotEqualTo(String value) {
            addCriterion("support_version_max <>", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxGreaterThan(String value) {
            addCriterion("support_version_max >", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxGreaterThanOrEqualTo(String value) {
            addCriterion("support_version_max >=", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxLessThan(String value) {
            addCriterion("support_version_max <", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxLessThanOrEqualTo(String value) {
            addCriterion("support_version_max <=", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxLike(String value) {
            addCriterion("support_version_max like", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxNotLike(String value) {
            addCriterion("support_version_max not like", value, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxIn(List<String> values) {
            addCriterion("support_version_max in", values, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxNotIn(List<String> values) {
            addCriterion("support_version_max not in", values, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxBetween(String value1, String value2) {
            addCriterion("support_version_max between", value1, value2, "supportVersionMax");
            return (Criteria) this;
        }

        public Criteria andSupportVersionMaxNotBetween(String value1, String value2) {
            addCriterion("support_version_max not between", value1, value2, "supportVersionMax");
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

        public Criteria andAreaDescIsNull() {
            addCriterion("area_desc is null");
            return (Criteria) this;
        }

        public Criteria andAreaDescIsNotNull() {
            addCriterion("area_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAreaDescEqualTo(String value) {
            addCriterion("area_desc =", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotEqualTo(String value) {
            addCriterion("area_desc <>", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescGreaterThan(String value) {
            addCriterion("area_desc >", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescGreaterThanOrEqualTo(String value) {
            addCriterion("area_desc >=", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLessThan(String value) {
            addCriterion("area_desc <", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLessThanOrEqualTo(String value) {
            addCriterion("area_desc <=", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLike(String value) {
            addCriterion("area_desc like", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotLike(String value) {
            addCriterion("area_desc not like", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescIn(List<String> values) {
            addCriterion("area_desc in", values, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotIn(List<String> values) {
            addCriterion("area_desc not in", values, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescBetween(String value1, String value2) {
            addCriterion("area_desc between", value1, value2, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotBetween(String value1, String value2) {
            addCriterion("area_desc not between", value1, value2, "areaDesc");
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