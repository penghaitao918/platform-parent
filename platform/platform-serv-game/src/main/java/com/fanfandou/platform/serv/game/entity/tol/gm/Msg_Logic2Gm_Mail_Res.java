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
public final class Msg_Logic2Gm_Mail_Res implements Externalizable, Message<Msg_Logic2Gm_Mail_Res>, Schema<Msg_Logic2Gm_Mail_Res>
{

    public static Schema<Msg_Logic2Gm_Mail_Res> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Logic2Gm_Mail_Res getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Logic2Gm_Mail_Res DEFAULT_INSTANCE = new Msg_Logic2Gm_Mail_Res();

    
    private String mSerialNumber;
    private Integer mRes;

    public Msg_Logic2Gm_Mail_Res()
    {

    }

    public Msg_Logic2Gm_Mail_Res(
        String mSerialNumber,
        Integer mRes
    )
    {
        this.mSerialNumber = mSerialNumber;
        this.mRes = mRes;
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
        final Msg_Logic2Gm_Mail_Res that = (Msg_Logic2Gm_Mail_Res) obj;
        return
                Objects.equals(this.mSerialNumber, that.mSerialNumber) &&
                Objects.equals(this.mRes, that.mRes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSerialNumber, mRes);
    }

    @Override
    public String toString() {
        return "Msg_Logic2Gm_Mail_Res{" +
                    "mSerialNumber=" + mSerialNumber +
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

    public Schema<Msg_Logic2Gm_Mail_Res> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Logic2Gm_Mail_Res newMessage()
    {
        return new Msg_Logic2Gm_Mail_Res();
    }

    public Class<Msg_Logic2Gm_Mail_Res> typeClass()
    {
        return Msg_Logic2Gm_Mail_Res.class;
    }

    public String messageName()
    {
        return Msg_Logic2Gm_Mail_Res.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Logic2Gm_Mail_Res.class.getName();
    }

    public boolean isInitialized(Msg_Logic2Gm_Mail_Res message)
    {
        return 
            message.mSerialNumber != null 
            && message.mRes != null;
    }

    public void mergeFrom(Input input, Msg_Logic2Gm_Mail_Res message) throws IOException
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
                    message.mRes = input.readUInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Logic2Gm_Mail_Res message) throws IOException
    {
        if(message.mSerialNumber == null)
            throw new UninitializedMessageException(message);
        output.writeString(1, message.mSerialNumber, false);

        if(message.mRes == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(2, message.mRes, false);
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
