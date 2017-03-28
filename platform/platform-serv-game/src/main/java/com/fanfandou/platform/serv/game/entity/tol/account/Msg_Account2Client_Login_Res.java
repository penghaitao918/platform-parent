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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Generated("java_bean")
public final class Msg_Account2Client_Login_Res implements Externalizable, Message<Msg_Account2Client_Login_Res>, Schema<Msg_Account2Client_Login_Res>
{

    public static Schema<Msg_Account2Client_Login_Res> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Account2Client_Login_Res getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Account2Client_Login_Res DEFAULT_INSTANCE = new Msg_Account2Client_Login_Res();

    
    private Integer mAccountId;
    private Integer mKey;
    private List<Integer> mToyType;
    private Integer mRes;

    public Msg_Account2Client_Login_Res()
    {

    }

    public Msg_Account2Client_Login_Res(
        Integer mRes
    )
    {
        this.mRes = mRes;
    }

    // getters and setters

    // mAccountId

    public Integer getMAccountId()
    {
        return mAccountId;
    }


    public void setMAccountId(Integer mAccountId)
    {
        this.mAccountId = mAccountId;
    }

    // mKey

    public Integer getMKey()
    {
        return mKey;
    }


    public void setMKey(Integer mKey)
    {
        this.mKey = mKey;
    }

    // mToyType

    public List<Integer> getMToyTypeList()
    {
        return mToyType;
    }


    public void setMToyTypeList(List<Integer> mToyType)
    {
        this.mToyType = mToyType;
    }

    // mRes

    public Integer getMRes()
    {
        return mRes;
    }


    public void setMRes(Integer mRes)
    {
        this.mRes = mRes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Account2Client_Login_Res that = (Msg_Account2Client_Login_Res) obj;
        return
                Objects.equals(this.mAccountId, that.mAccountId) &&
                Objects.equals(this.mKey, that.mKey) &&
                Objects.equals(this.mToyType, that.mToyType) &&
                Objects.equals(this.mRes, that.mRes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAccountId, mKey, mToyType, mRes);
    }

    @Override
    public String toString() {
        return "Msg_Account2Client_Login_Res{" +
                    "mAccountId=" + mAccountId +
                    ", mKey=" + mKey +
                    ", mToyType=" + mToyType +
                    ", mRes=" + mRes +
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

    public Schema<Msg_Account2Client_Login_Res> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Account2Client_Login_Res newMessage()
    {
        return new Msg_Account2Client_Login_Res();
    }

    public Class<Msg_Account2Client_Login_Res> typeClass()
    {
        return Msg_Account2Client_Login_Res.class;
    }

    public String messageName()
    {
        return Msg_Account2Client_Login_Res.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Account2Client_Login_Res.class.getName();
    }

    public boolean isInitialized(Msg_Account2Client_Login_Res message)
    {
        return 
            message.mRes != null;
    }

    public void mergeFrom(Input input, Msg_Account2Client_Login_Res message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.mAccountId = input.readUInt32();
                    break;
                case 2:
                    message.mKey = input.readUInt32();
                    break;
                case 3:
                    if(message.mToyType == null)
                        message.mToyType = new ArrayList<Integer>();
                    message.mToyType.add(input.readUInt32());break;
                case 4:
                    message.mRes = input.readUInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Account2Client_Login_Res message) throws IOException
    {
        if(message.mAccountId != null)
            output.writeUInt32(1, message.mAccountId, false);

        if(message.mKey != null)
            output.writeUInt32(2, message.mKey, false);

        if(message.mToyType != null)
        {
            for(Integer mToyType : message.mToyType)
            {
                if(mToyType != null)
                    output.writeUInt32(3, mToyType, true);
            }
        }

        if(message.mRes == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(4, message.mRes, false);
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
