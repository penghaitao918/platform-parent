package com.fanfandou.admin.api.operation.entity;

import java.util.ArrayList;
import java.util.List;

public class GamePatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GamePatchExample() {
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

        public Criteria andPatchNameIsNull() {
            addCriterion("patch_name is null");
            return (Criteria) this;
        }

        public Criteria andPatchNameIsNotNull() {
            addCriterion("patch_name is not null");
            return (Criteria) this;
        }

        public Criteria andPatchNameEqualTo(String value) {
            addCriterion("patch_name =", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameNotEqualTo(String value) {
            addCriterion("patch_name <>", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameGreaterThan(String value) {
            addCriterion("patch_name >", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("patch_name >=", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameLessThan(String value) {
            addCriterion("patch_name <", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameLessThanOrEqualTo(String value) {
            addCriterion("patch_name <=", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameLike(String value) {
            addCriterion("patch_name like", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameNotLike(String value) {
            addCriterion("patch_name not like", value, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameIn(List<String> values) {
            addCriterion("patch_name in", values, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameNotIn(List<String> values) {
            addCriterion("patch_name not in", values, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameBetween(String value1, String value2) {
            addCriterion("patch_name between", value1, value2, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchNameNotBetween(String value1, String value2) {
            addCriterion("patch_name not between", value1, value2, "patchName");
            return (Criteria) this;
        }

        public Criteria andPatchDescIsNull() {
            addCriterion("patch_desc is null");
            return (Criteria) this;
        }

        public Criteria andPatchDescIsNotNull() {
            addCriterion("patch_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPatchDescEqualTo(String value) {
            addCriterion("patch_desc =", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescNotEqualTo(String value) {
            addCriterion("patch_desc <>", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescGreaterThan(String value) {
            addCriterion("patch_desc >", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescGreaterThanOrEqualTo(String value) {
            addCriterion("patch_desc >=", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescLessThan(String value) {
            addCriterion("patch_desc <", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescLessThanOrEqualTo(String value) {
            addCriterion("patch_desc <=", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescLike(String value) {
            addCriterion("patch_desc like", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescNotLike(String value) {
            addCriterion("patch_desc not like", value, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescIn(List<String> values) {
            addCriterion("patch_desc in", values, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescNotIn(List<String> values) {
            addCriterion("patch_desc not in", values, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescBetween(String value1, String value2) {
            addCriterion("patch_desc between", value1, value2, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchDescNotBetween(String value1, String value2) {
            addCriterion("patch_desc not between", value1, value2, "patchDesc");
            return (Criteria) this;
        }

        public Criteria andPatchUrlIsNull() {
            addCriterion("patch_url is null");
            return (Criteria) this;
        }

        public Criteria andPatchUrlIsNotNull() {
            addCriterion("patch_url is not null");
            return (Criteria) this;
        }

        public Criteria andPatchUrlEqualTo(String value) {
            addCriterion("patch_url =", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlNotEqualTo(String value) {
            addCriterion("patch_url <>", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlGreaterThan(String value) {
            addCriterion("patch_url >", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlGreaterThanOrEqualTo(String value) {
            addCriterion("patch_url >=", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlLessThan(String value) {
            addCriterion("patch_url <", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlLessThanOrEqualTo(String value) {
            addCriterion("patch_url <=", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlLike(String value) {
            addCriterion("patch_url like", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlNotLike(String value) {
            addCriterion("patch_url not like", value, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlIn(List<String> values) {
            addCriterion("patch_url in", values, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlNotIn(List<String> values) {
            addCriterion("patch_url not in", values, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlBetween(String value1, String value2) {
            addCriterion("patch_url between", value1, value2, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchUrlNotBetween(String value1, String value2) {
            addCriterion("patch_url not between", value1, value2, "patchUrl");
            return (Criteria) this;
        }

        public Criteria andPatchSizeIsNull() {
            addCriterion("patch_size is null");
            return (Criteria) this;
        }

        public Criteria andPatchSizeIsNotNull() {
            addCriterion("patch_size is not null");
            return (Criteria) this;
        }

        public Criteria andPatchSizeEqualTo(String value) {
            addCriterion("patch_size =", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeNotEqualTo(String value) {
            addCriterion("patch_size <>", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeGreaterThan(String value) {
            addCriterion("patch_size >", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeGreaterThanOrEqualTo(String value) {
            addCriterion("patch_size >=", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeLessThan(String value) {
            addCriterion("patch_size <", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeLessThanOrEqualTo(String value) {
            addCriterion("patch_size <=", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeLike(String value) {
            addCriterion("patch_size like", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeNotLike(String value) {
            addCriterion("patch_size not like", value, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeIn(List<String> values) {
            addCriterion("patch_size in", values, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeNotIn(List<String> values) {
            addCriterion("patch_size not in", values, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeBetween(String value1, String value2) {
            addCriterion("patch_size between", value1, value2, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchSizeNotBetween(String value1, String value2) {
            addCriterion("patch_size not between", value1, value2, "patchSize");
            return (Criteria) this;
        }

        public Criteria andPatchVersionIsNull() {
            addCriterion("patch_version is null");
            return (Criteria) this;
        }

        public Criteria andPatchVersionIsNotNull() {
            addCriterion("patch_version is not null");
            return (Criteria) this;
        }

        public Criteria andPatchVersionEqualTo(int value) {
            addCriterion("patch_version =", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionNotEqualTo(int value) {
            addCriterion("patch_version <>", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionGreaterThan(int value) {
            addCriterion("patch_version >", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionGreaterThanOrEqualTo(int value) {
            addCriterion("patch_version >=", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionLessThan(int value) {
            addCriterion("patch_version <", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionLessThanOrEqualTo(int value) {
            addCriterion("patch_version <=", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionLike(int value) {
            addCriterion("patch_version like", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionNotLike(int value) {
            addCriterion("patch_version not like", value, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionIn(List<Integer> values) {
            addCriterion("patch_version in", values, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionNotIn(List<Integer> values) {
            addCriterion("patch_version not in", values, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionBetween(int value1, int value2) {
            addCriterion("patch_version between", value1, value2, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andPatchVersionNotBetween(int value1, int value2) {
            addCriterion("patch_version not between", value1, value2, "patchVersion");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(int value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(int value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(int value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(int value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(int value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(int value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<Byte> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<Integer> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(int value1, int value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(int value1, int value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusIsNull() {
            addCriterion("white_status is null");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusIsNotNull() {
            addCriterion("white_status is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusEqualTo(int value) {
            addCriterion("white_status =", value, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusNotEqualTo(int value) {
            addCriterion("white_status <>", value, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusGreaterThan(int value) {
            addCriterion("white_status >", value, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusGreaterThanOrEqualTo(int value) {
            addCriterion("white_status >=", value, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusLessThan(int value) {
            addCriterion("white_status <", value, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusLessThanOrEqualTo(Byte value) {
            addCriterion("white_status <=", value, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusIn(List<Integer> values) {
            addCriterion("white_status in", values, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusNotIn(List<Integer> values) {
            addCriterion("white_status not in", values, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusBetween(int value1, int value2) {
            addCriterion("white_status between", value1, value2, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteStatusNotBetween(int value1, int value2) {
            addCriterion("white_status not between", value1, value2, "whiteStatus");
            return (Criteria) this;
        }

        public Criteria andWhiteContentIsNull() {
            addCriterion("white_content is null");
            return (Criteria) this;
        }

        public Criteria andWhiteContentIsNotNull() {
            addCriterion("white_content is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteContentEqualTo(String value) {
            addCriterion("white_content =", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentNotEqualTo(String value) {
            addCriterion("white_content <>", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentGreaterThan(String value) {
            addCriterion("white_content >", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentGreaterThanOrEqualTo(String value) {
            addCriterion("white_content >=", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentLessThan(String value) {
            addCriterion("white_content <", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentLessThanOrEqualTo(String value) {
            addCriterion("white_content <=", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentLike(String value) {
            addCriterion("white_content like", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentNotLike(String value) {
            addCriterion("white_content not like", value, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentIn(List<String> values) {
            addCriterion("white_content in", values, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentNotIn(List<String> values) {
            addCriterion("white_content not in", values, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentBetween(String value1, String value2) {
            addCriterion("white_content between", value1, value2, "whiteContent");
            return (Criteria) this;
        }

        public Criteria andWhiteContentNotBetween(String value1, String value2) {
            addCriterion("white_content not between", value1, value2, "whiteContent");
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

        public Criteria andValidStatusEqualTo(int value) {
            addCriterion("valid_status =", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotEqualTo(int value) {
            addCriterion("valid_status <>", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThan(int value) {
            addCriterion("valid_status >", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThanOrEqualTo(int value) {
            addCriterion("valid_status >=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThan(int value) {
            addCriterion("valid_status <", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThanOrEqualTo(int value) {
            addCriterion("valid_status <=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusIn(List<Integer> values) {
            addCriterion("valid_status in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotIn(List<Integer> values) {
            addCriterion("valid_status not in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusBetween(int value1, int value2) {
            addCriterion("valid_status between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotBetween(int value1, int value2) {
            addCriterion("valid_status not between", value1, value2, "validStatus");
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