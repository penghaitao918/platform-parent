package com.fanfandou.platform.api.billing.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillingWalletDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BillingWalletDetailExample() {
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

        public Criteria andWalletDetailidIsNull() {
            addCriterion("wallet_detailId is null");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidIsNotNull() {
            addCriterion("wallet_detailId is not null");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidEqualTo(Long value) {
            addCriterion("wallet_detailId =", value, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidNotEqualTo(Long value) {
            addCriterion("wallet_detailId <>", value, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidGreaterThan(Long value) {
            addCriterion("wallet_detailId >", value, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidGreaterThanOrEqualTo(Long value) {
            addCriterion("wallet_detailId >=", value, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidLessThan(Long value) {
            addCriterion("wallet_detailId <", value, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidLessThanOrEqualTo(Long value) {
            addCriterion("wallet_detailId <=", value, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidIn(List<Long> values) {
            addCriterion("wallet_detailId in", values, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidNotIn(List<Long> values) {
            addCriterion("wallet_detailId not in", values, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidBetween(Long value1, Long value2) {
            addCriterion("wallet_detailId between", value1, value2, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletDetailidNotBetween(Long value1, Long value2) {
            addCriterion("wallet_detailId not between", value1, value2, "walletDetailid");
            return (Criteria) this;
        }

        public Criteria andWalletIdIsNull() {
            addCriterion("wallet_id is null");
            return (Criteria) this;
        }

        public Criteria andWalletIdIsNotNull() {
            addCriterion("wallet_id is not null");
            return (Criteria) this;
        }

        public Criteria andWalletIdEqualTo(Long value) {
            addCriterion("wallet_id =", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdNotEqualTo(Long value) {
            addCriterion("wallet_id <>", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdGreaterThan(Long value) {
            addCriterion("wallet_id >", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdGreaterThanOrEqualTo(Long value) {
            addCriterion("wallet_id >=", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdLessThan(Long value) {
            addCriterion("wallet_id <", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdLessThanOrEqualTo(Long value) {
            addCriterion("wallet_id <=", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdIn(List<Long> values) {
            addCriterion("wallet_id in", values, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdNotIn(List<Long> values) {
            addCriterion("wallet_id not in", values, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdBetween(Long value1, Long value2) {
            addCriterion("wallet_id between", value1, value2, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdNotBetween(Long value1, Long value2) {
            addCriterion("wallet_id not between", value1, value2, "walletId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdIsNull() {
            addCriterion("billing_order_id is null");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdIsNotNull() {
            addCriterion("billing_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdEqualTo(String value) {
            addCriterion("billing_order_id =", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdNotEqualTo(String value) {
            addCriterion("billing_order_id <>", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdGreaterThan(String value) {
            addCriterion("billing_order_id >", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("billing_order_id >=", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdLessThan(String value) {
            addCriterion("billing_order_id <", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdLessThanOrEqualTo(String value) {
            addCriterion("billing_order_id <=", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdLike(String value) {
            addCriterion("billing_order_id like", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdNotLike(String value) {
            addCriterion("billing_order_id not like", value, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdIn(List<String> values) {
            addCriterion("billing_order_id in", values, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdNotIn(List<String> values) {
            addCriterion("billing_order_id not in", values, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdBetween(String value1, String value2) {
            addCriterion("billing_order_id between", value1, value2, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andBillingOrderIdNotBetween(String value1, String value2) {
            addCriterion("billing_order_id not between", value1, value2, "billingOrderId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountIsNull() {
            addCriterion("currency_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountIsNotNull() {
            addCriterion("currency_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountEqualTo(Integer value) {
            addCriterion("currency_amount =", value, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountNotEqualTo(Integer value) {
            addCriterion("currency_amount <>", value, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountGreaterThan(Integer value) {
            addCriterion("currency_amount >", value, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("currency_amount >=", value, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountLessThan(Integer value) {
            addCriterion("currency_amount <", value, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("currency_amount <=", value, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountIn(List<Integer> values) {
            addCriterion("currency_amount in", values, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountNotIn(List<Integer> values) {
            addCriterion("currency_amount not in", values, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountBetween(Integer value1, Integer value2) {
            addCriterion("currency_amount between", value1, value2, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andCurrencyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("currency_amount not between", value1, value2, "currencyAmount");
            return (Criteria) this;
        }

        public Criteria andDetailDescIsNull() {
            addCriterion("detail_desc is null");
            return (Criteria) this;
        }

        public Criteria andDetailDescIsNotNull() {
            addCriterion("detail_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDetailDescEqualTo(String value) {
            addCriterion("detail_desc =", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescNotEqualTo(String value) {
            addCriterion("detail_desc <>", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescGreaterThan(String value) {
            addCriterion("detail_desc >", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescGreaterThanOrEqualTo(String value) {
            addCriterion("detail_desc >=", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescLessThan(String value) {
            addCriterion("detail_desc <", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescLessThanOrEqualTo(String value) {
            addCriterion("detail_desc <=", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescLike(String value) {
            addCriterion("detail_desc like", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescNotLike(String value) {
            addCriterion("detail_desc not like", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescIn(List<String> values) {
            addCriterion("detail_desc in", values, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescNotIn(List<String> values) {
            addCriterion("detail_desc not in", values, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescBetween(String value1, String value2) {
            addCriterion("detail_desc between", value1, value2, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailDescNotBetween(String value1, String value2) {
            addCriterion("detail_desc not between", value1, value2, "detailDesc");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIsNull() {
            addCriterion("detail_type is null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIsNotNull() {
            addCriterion("detail_type is not null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeEqualTo(Byte value) {
            addCriterion("detail_type =", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotEqualTo(Byte value) {
            addCriterion("detail_type <>", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeGreaterThan(Byte value) {
            addCriterion("detail_type >", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("detail_type >=", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLessThan(Byte value) {
            addCriterion("detail_type <", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLessThanOrEqualTo(Byte value) {
            addCriterion("detail_type <=", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIn(List<Byte> values) {
            addCriterion("detail_type in", values, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotIn(List<Byte> values) {
            addCriterion("detail_type not in", values, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeBetween(Byte value1, Byte value2) {
            addCriterion("detail_type between", value1, value2, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("detail_type not between", value1, value2, "detailType");
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