package com.fanfandou.common.sequence;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class SequenceValue {
    //
    private String sequenceName = null;

    private long curValue = 1;
    private long maxValue = 0;

    //
    public SequenceValue(String name) {
        sequenceName = name.toUpperCase();
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setCurValue(long curValue) {
        this.curValue = curValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public boolean hasNext() {
        return curValue <= maxValue;
    }

    public long getNextValue() {
        return curValue++;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SequenceValue)) {
            return false;
        }

        return sequenceName.equalsIgnoreCase(((SequenceValue) obj).getSequenceName());
    }

    @Override
    public int hashCode() {
        return sequenceName.hashCode();
    }
}
