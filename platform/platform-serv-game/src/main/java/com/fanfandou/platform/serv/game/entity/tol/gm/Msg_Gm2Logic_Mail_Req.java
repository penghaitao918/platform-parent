// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from proto

package com.fanfandou.platform.serv.game.entity.tol.gm;

import io.protostuff.GraphIOUtil;
import io.protostuff.Input;
import io.protostuff.Message;
import io.protostuff.Output;
import io.protostuff.Schema;
import io.protostuff.UninitializedMessageException;

import javax.annotation.Generated;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Generated("java_bean")
public final class Msg_Gm2Logic_Mail_Req implements Externalizable, Message<Msg_Gm2Logic_Mail_Req>, Schema<Msg_Gm2Logic_Mail_Req>
{

    public static Schema<Msg_Gm2Logic_Mail_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Gm2Logic_Mail_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Gm2Logic_Mail_Req DEFAULT_INSTANCE = new Msg_Gm2Logic_Mail_Req();

    
    private String mSerialNumber;
    private Integer mAccountId;
    private Integer mServerId;
    private String mTitle;
    private String mContents;
    private List<Integer> mItemIds;
    private List<Integer> mItemCounts;
    private Integer mHoleSecond;

    public Msg_Gm2Logic_Mail_Req()
    {

    }

    public Msg_Gm2Logic_Mail_Req(
        String mSerialNumber,
        Integer mAccountId,
        Integer mServerId,
        String mTitle,
        String mContents,
        Integer mHoleSecond
    )
    {
        this.mSerialNumber = mSerialNumber;
        this.mAccountId = mAccountId;
        this.mServerId = mServerId;
        this.mTitle = mTitle;
        this.mContents = mContents;
        this.mHoleSecond = mHoleSecond;
    }

    // getters and setters

    // mSerialNumber

    public String getMSerialNumber()
    {
        return mSerialNumber;
    }


    public void setMSerialNumber(String mSerialNumber)
    {
        this.mSerialNumber = mSerialNumber;
    }

    // mAccountId

    public Integer getMAccountId()
    {
        return mAccountId;
    }


    public void setMAccountId(Integer mAccountId)
    {
        this.mAccountId = mAccountId;
    }

    // mServerId

    public Integer getMServerId()
    {
        return mServerId;
    }


    public void setMServerId(Integer mServerId)
    {
        this.mServerId = mServerId;
    }

    // mTitle

    public String getMTitle()
    {
        return mTitle;
    }


    public void setMTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }

    // mContents

    public String getMContents()
    {
        return mContents;
    }


    public void setMContents(String mContents)
    {
        this.mContents = mContents;
    }

    // mItemIds

    public List<Integer> getMItemIdsList()
    {
        return mItemIds;
    }


    public void setMItemIdsList(List<Integer> mItemIds)
    {
        this.mItemIds = mItemIds;
    }

    // mItemCounts

    public List<Integer> getMItemCountsList()
    {
        return mItemCounts;
    }


    public void setMItemCountsList(List<Integer> mItemCounts)
    {
        this.mItemCounts = mItemCounts;
    }

    // mHoleSecond

    public Integer getMHoleSecond()
    {
        return mHoleSecond;
    }


    public void setMHoleSecond(Integer mHoleSecond)
    {
        this.mHoleSecond = mHoleSecond;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Gm2Logic_Mail_Req that = (Msg_Gm2Logic_Mail_Req) obj;
        return
                Objects.equals(this.mSerialNumber, that.mSerialNumber) &&
                Objects.equals(this.mAccountId, that.mAccountId) &&
                Objects.equals(this.mServerId, that.mServerId) &&
                Objects.equals(this.mTitle, that.mTitle) &&
                Objects.equals(this.mContents, that.mContents) &&
                Objects.equals(this.mItemIds, that.mItemIds) &&
                Objects.equals(this.mItemCounts, that.mItemCounts) &&
                Objects.equals(this.mHoleSecond, that.mHoleSecond);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSerialNumber, mAccountId, mServerId, mTitle, mContents, mItemIds, mItemCounts, mHoleSecond);
    }

    @Override
    public String toString() {
        return "Msg_Gm2Logic_Mail_Req{" +
                    "mSerialNumber=" + mSerialNumber +
                    ", mAccountId=" + mAccountId +
                    ", mServerId=" + mServerId +
                    ", mTitle=" + mTitle +
                    ", mContents=" + mContents +
                    ", mItemIds=" + mItemIds +
                    ", mItemCounts=" + mItemCounts +
                    ", mHoleSecond=" + mHoleSecond +
                '}';
    }
    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<Msg_Gm2Logic_Mail_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Gm2Logic_Mail_Req newMessage()
    {
        return new Msg_Gm2Logic_Mail_Req();
    }

    public Class<Msg_Gm2Logic_Mail_Req> typeClass()
    {
        return Msg_Gm2Logic_Mail_Req.class;
    }

    public String messageName()
    {
        return Msg_Gm2Logic_Mail_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Gm2Logic_Mail_Req.class.getName();
    }

    public boolean isInitialized(Msg_Gm2Logic_Mail_Req message)
    {
        return 
            message.mSerialNumber != null 
            && message.mAccountId != null 
            && message.mServerId != null 
            && message.mTitle != null 
            && message.mContents != null 
            && message.mHoleSecond != null;
    }

    public void mergeFrom(Input input, Msg_Gm2Logic_Mail_Req message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.mSerialNumber = input.readString();
                    break;
                case 2:
                    message.mAccountId = input.readUInt32();
                    break;
                case 3:
                    message.mServerId = input.readUInt32();
                    break;
                case 4:
                    message.mTitle = input.readString();
                    break;
                case 5:
                    message.mContents = input.readString();
                    break;
                case 6:
                    if(message.mItemIds == null)
                        message.mItemIds = new ArrayList<Integer>();
                    message.mItemIds.add(input.readUInt32());break;
                case 7:
                    if(message.mItemCounts == null)
                        message.mItemCounts = new ArrayList<Integer>();
                    message.mItemCounts.add(input.readUInt32());break;
                case 8:
                    message.mHoleSecond = input.readUInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Gm2Logic_Mail_Req message) throws IOException
    {
        if(message.mSerialNumber == null)
            throw new UninitializedMessageException(message);
        output.writeString(1, message.mSerialNumber, false);

        if(message.mAccountId == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(2, message.mAccountId, false);

        if(message.mServerId == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(3, message.mServerId, false);

        if(message.mTitle == null)
            throw new UninitializedMessageException(message);
        output.writeString(4, message.mTitle, false);

        if(message.mContents == null)
            throw new UninitializedMessageException(message);
        output.writeString(5, message.mContents, false);

        if(message.mItemIds != null)
        {
            for(Integer mItemIds : message.mItemIds)
            {
                if(mItemIds != null)
                    output.writeUInt32(6, mItemIds, true);
            }
        }

        if(message.mItemCounts != null)
        {
            for(Integer mItemCounts : message.mItemCounts)
            {
                if(mItemCounts != null)
                    output.writeUInt32(7, mItemCounts, true);
            }
        }

        if(message.mHoleSecond == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(8, message.mHoleSecond, false);
    }

    public String getFieldName(int number)
    {
        return Integer.toString(number);
    }

    public int getFieldNumber(String name)
    {
        return Integer.parseInt(name);
    }
    

}
