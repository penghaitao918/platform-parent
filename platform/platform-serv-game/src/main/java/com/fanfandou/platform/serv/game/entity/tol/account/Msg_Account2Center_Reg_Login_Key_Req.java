// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from proto

package com.fanfandou.platform.serv.game.entity.tol.account;

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
public final class Msg_Account2Center_Reg_Login_Key_Req implements Externalizable, Message<Msg_Account2Center_Reg_Login_Key_Req>, Schema<Msg_Account2Center_Reg_Login_Key_Req>
{

    public static Schema<Msg_Account2Center_Reg_Login_Key_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Account2Center_Reg_Login_Key_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Account2Center_Reg_Login_Key_Req DEFAULT_INSTANCE = new Msg_Account2Center_Reg_Login_Key_Req();

    
    private String mSerialNumber;
    private Integer mAccountId;
    private Integer mAntiAddiction;

    public Msg_Account2Center_Reg_Login_Key_Req()
    {

    }

    public Msg_Account2Center_Reg_Login_Key_Req(
        String mSerialNumber,
        Integer mAccountId,
        Integer mAntiAddiction
    )
    {
        this.mSerialNumber = mSerialNumber;
        this.mAccountId = mAccountId;
        this.mAntiAddiction = mAntiAddiction;
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

    // mAntiAddiction

    public Integer getMAntiAddiction()
    {
        return mAntiAddiction;
    }


    public void setMAntiAddiction(Integer mAntiAddiction)
    {
        this.mAntiAddiction = mAntiAddiction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Account2Center_Reg_Login_Key_Req that = (Msg_Account2Center_Reg_Login_Key_Req) obj;
        return
                Objects.equals(this.mSerialNumber, that.mSerialNumber) &&
                Objects.equals(this.mAccountId, that.mAccountId) &&
                Objects.equals(this.mAntiAddiction, that.mAntiAddiction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSerialNumber, mAccountId, mAntiAddiction);
    }

    @Override
    public String toString() {
        return "Msg_Account2Center_Reg_Login_Key_Req{" +
                    "mSerialNumber=" + mSerialNumber +
                    ", mAccountId=" + mAccountId +
                    ", mAntiAddiction=" + mAntiAddiction +
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

    public Schema<Msg_Account2Center_Reg_Login_Key_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Account2Center_Reg_Login_Key_Req newMessage()
    {
        return new Msg_Account2Center_Reg_Login_Key_Req();
    }

    public Class<Msg_Account2Center_Reg_Login_Key_Req> typeClass()
    {
        return Msg_Account2Center_Reg_Login_Key_Req.class;
    }

    public String messageName()
    {
        return Msg_Account2Center_Reg_Login_Key_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Account2Center_Reg_Login_Key_Req.class.getName();
    }

    public boolean isInitialized(Msg_Account2Center_Reg_Login_Key_Req message)
    {
        return 
            message.mSerialNumber != null 
            && message.mAccountId != null 
            && message.mAntiAddiction != null;
    }

    public void mergeFrom(Input input, Msg_Account2Center_Reg_Login_Key_Req message) throws IOException
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
                    message.mAntiAddiction = input.readUInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Account2Center_Reg_Login_Key_Req message) throws IOException
    {
        if(message.mSerialNumber == null)
            throw new UninitializedMessageException(message);
        output.writeString(1, message.mSerialNumber, false);

        if(message.mAccountId == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(2, message.mAccountId, false);

        if(message.mAntiAddiction == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(3, message.mAntiAddiction, false);
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
