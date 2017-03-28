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
import java.util.Objects;

@Generated("java_bean")
public final class Msg_Gm2Logic_Shop_Req implements Externalizable, Message<Msg_Gm2Logic_Shop_Req>, Schema<Msg_Gm2Logic_Shop_Req>
{

    public static Schema<Msg_Gm2Logic_Shop_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Gm2Logic_Shop_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Gm2Logic_Shop_Req DEFAULT_INSTANCE = new Msg_Gm2Logic_Shop_Req();

    
    private String mSerialNumber;
    private Integer mAccountId;
    private Integer mServerId;
    private Integer mRmb;
    private Integer mGoodsId;
    private Integer mType;

    public Msg_Gm2Logic_Shop_Req()
    {

    }

    public Msg_Gm2Logic_Shop_Req(
        String mSerialNumber,
        Integer mAccountId,
        Integer mServerId,
        Integer mRmb,
        Integer mGoodsId,
        Integer mType
    )
    {
        this.mSerialNumber = mSerialNumber;
        this.mAccountId = mAccountId;
        this.mServerId = mServerId;
        this.mRmb = mRmb;
        this.mGoodsId = mGoodsId;
        this.mType = mType;
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

    // mRmb

    public Integer getMRmb()
    {
        return mRmb;
    }


    public void setMRmb(Integer mRmb)
    {
        this.mRmb = mRmb;
    }

    // mGoodsId

    public Integer getMGoodsId()
    {
        return mGoodsId;
    }


    public void setMGoodsId(Integer mGoodsId)
    {
        this.mGoodsId = mGoodsId;
    }

    // mType

    public Integer getMType()
    {
        return mType;
    }


    public void setMType(Integer mType)
    {
        this.mType = mType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Gm2Logic_Shop_Req that = (Msg_Gm2Logic_Shop_Req) obj;
        return
                Objects.equals(this.mSerialNumber, that.mSerialNumber) &&
                Objects.equals(this.mAccountId, that.mAccountId) &&
                Objects.equals(this.mServerId, that.mServerId) &&
                Objects.equals(this.mRmb, that.mRmb) &&
                Objects.equals(this.mGoodsId, that.mGoodsId) &&
                Objects.equals(this.mType, that.mType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSerialNumber, mAccountId, mServerId, mRmb, mGoodsId, mType);
    }

    @Override
    public String toString() {
        return "Msg_Gm2Logic_Shop_Req{" +
                    "mSerialNumber=" + mSerialNumber +
                    ", mAccountId=" + mAccountId +
                    ", mServerId=" + mServerId +
                    ", mRmb=" + mRmb +
                    ", mGoodsId=" + mGoodsId +
                    ", mType=" + mType +
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

    public Schema<Msg_Gm2Logic_Shop_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Gm2Logic_Shop_Req newMessage()
    {
        return new Msg_Gm2Logic_Shop_Req();
    }

    public Class<Msg_Gm2Logic_Shop_Req> typeClass()
    {
        return Msg_Gm2Logic_Shop_Req.class;
    }

    public String messageName()
    {
        return Msg_Gm2Logic_Shop_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Gm2Logic_Shop_Req.class.getName();
    }

    public boolean isInitialized(Msg_Gm2Logic_Shop_Req message)
    {
        return 
            message.mSerialNumber != null 
            && message.mAccountId != null 
            && message.mServerId != null 
            && message.mRmb != null 
            && message.mGoodsId != null 
            && message.mType != null;
    }

    public void mergeFrom(Input input, Msg_Gm2Logic_Shop_Req message) throws IOException
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
                    message.mRmb = input.readUInt32();
                    break;
                case 5:
                    message.mGoodsId = input.readUInt32();
                    break;
                case 6:
                    message.mType = input.readUInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Gm2Logic_Shop_Req message) throws IOException
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

        if(message.mRmb == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(4, message.mRmb, false);

        if(message.mGoodsId == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(5, message.mGoodsId, false);

        if(message.mType == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(6, message.mType, false);
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