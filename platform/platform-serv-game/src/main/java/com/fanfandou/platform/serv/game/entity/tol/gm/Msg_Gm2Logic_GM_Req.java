// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from proto

package com.fanfandou.platform.serv.game.entity.tol.gm;

import io.protostuff.GraphIOUtil;
import io.protostuff.Input;
import io.protostuff.Message;
import io.protostuff.Output;
import io.protostuff.Schema;

import javax.annotation.Generated;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;


@Generated("java_bean")
public final class Msg_Gm2Logic_GM_Req implements Externalizable, Message<Msg_Gm2Logic_GM_Req>, Schema<Msg_Gm2Logic_GM_Req>
{

    public static Schema<Msg_Gm2Logic_GM_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Gm2Logic_GM_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Gm2Logic_GM_Req DEFAULT_INSTANCE = new Msg_Gm2Logic_GM_Req();

    
    private String mSerialNumber;
    private Integer mType;
    private Integer mAccountId;
    private Integer mServerId;
    private GmKeyValueList mParamList;

    public Msg_Gm2Logic_GM_Req()
    {

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

    // mType

    public Integer getMType()
    {
        return mType;
    }


    public void setMType(Integer mType)
    {
        this.mType = mType;
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

    // mParamList

    public GmKeyValueList getMParamList()
    {
        return mParamList;
    }


    public void setMParamList(GmKeyValueList mParamList)
    {
        this.mParamList = mParamList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Gm2Logic_GM_Req that = (Msg_Gm2Logic_GM_Req) obj;
        return
                Objects.equals(this.mSerialNumber, that.mSerialNumber) &&
                Objects.equals(this.mType, that.mType) &&
                Objects.equals(this.mAccountId, that.mAccountId) &&
                Objects.equals(this.mServerId, that.mServerId) &&
                Objects.equals(this.mParamList, that.mParamList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSerialNumber, mType, mAccountId, mServerId, mParamList);
    }

    @Override
    public String toString() {
        return "Msg_Gm2Logic_GM_Req{" +
                    "mSerialNumber=" + mSerialNumber +
                    ", mType=" + mType +
                    ", mAccountId=" + mAccountId +
                    ", mServerId=" + mServerId +
                    ", mParamList=" + mParamList +
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

    public Schema<Msg_Gm2Logic_GM_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Gm2Logic_GM_Req newMessage()
    {
        return new Msg_Gm2Logic_GM_Req();
    }

    public Class<Msg_Gm2Logic_GM_Req> typeClass()
    {
        return Msg_Gm2Logic_GM_Req.class;
    }

    public String messageName()
    {
        return Msg_Gm2Logic_GM_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Gm2Logic_GM_Req.class.getName();
    }

    public boolean isInitialized(Msg_Gm2Logic_GM_Req message)
    {
        return true;
    }

    public void mergeFrom(Input input, Msg_Gm2Logic_GM_Req message) throws IOException
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
                    message.mType = input.readUInt32();
                    break;
                case 3:
                    message.mAccountId = input.readUInt32();
                    break;
                case 4:
                    message.mServerId = input.readUInt32();
                    break;
                case 5:
                    message.mParamList = input.mergeObject(message.mParamList, GmKeyValueList.getSchema());
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Gm2Logic_GM_Req message) throws IOException
    {
        if(message.mSerialNumber != null)
            output.writeString(1, message.mSerialNumber, false);

        if(message.mType != null)
            output.writeUInt32(2, message.mType, false);

        if(message.mAccountId != null)
            output.writeUInt32(3, message.mAccountId, false);

        if(message.mServerId != null)
            output.writeUInt32(4, message.mServerId, false);

        if(message.mParamList != null)
             output.writeObject(5, message.mParamList, GmKeyValueList.getSchema(), false);

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
