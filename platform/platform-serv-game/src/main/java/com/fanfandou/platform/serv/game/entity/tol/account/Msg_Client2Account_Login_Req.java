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
public final class Msg_Client2Account_Login_Req implements Externalizable, Message<Msg_Client2Account_Login_Req>, Schema<Msg_Client2Account_Login_Req>
{

    public static Schema<Msg_Client2Account_Login_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Client2Account_Login_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Client2Account_Login_Req DEFAULT_INSTANCE = new Msg_Client2Account_Login_Req();

    
    private String mAccountName;
    private String mAccountKey;

    public Msg_Client2Account_Login_Req()
    {

    }

    public Msg_Client2Account_Login_Req(
        String mAccountName,
        String mAccountKey
    )
    {
        this.mAccountName = mAccountName;
        this.mAccountKey = mAccountKey;
    }

    // getters and setters

    // mAccountName

    public String getMAccountName()
    {
        return mAccountName;
    }


    public void setMAccountName(String mAccountName)
    {
        this.mAccountName = mAccountName;
    }

    // mAccountKey

    public String getMAccountKey()
    {
        return mAccountKey;
    }


    public void setMAccountKey(String mAccountKey)
    {
        this.mAccountKey = mAccountKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Client2Account_Login_Req that = (Msg_Client2Account_Login_Req) obj;
        return
                Objects.equals(this.mAccountName, that.mAccountName) &&
                Objects.equals(this.mAccountKey, that.mAccountKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAccountName, mAccountKey);
    }

    @Override
    public String toString() {
        return "Msg_Client2Account_Login_Req{" +
                    "mAccountName=" + mAccountName +
                    ", mAccountKey=" + mAccountKey +
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

    public Schema<Msg_Client2Account_Login_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Client2Account_Login_Req newMessage()
    {
        return new Msg_Client2Account_Login_Req();
    }

    public Class<Msg_Client2Account_Login_Req> typeClass()
    {
        return Msg_Client2Account_Login_Req.class;
    }

    public String messageName()
    {
        return Msg_Client2Account_Login_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Client2Account_Login_Req.class.getName();
    }

    public boolean isInitialized(Msg_Client2Account_Login_Req message)
    {
        return 
            message.mAccountName != null 
            && message.mAccountKey != null;
    }

    public void mergeFrom(Input input, Msg_Client2Account_Login_Req message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.mAccountName = input.readString();
                    break;
                case 2:
                    message.mAccountKey = input.readString();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Client2Account_Login_Req message) throws IOException
    {
        if(message.mAccountName == null)
            throw new UninitializedMessageException(message);
        output.writeString(1, message.mAccountName, false);

        if(message.mAccountKey == null)
            throw new UninitializedMessageException(message);
        output.writeString(2, message.mAccountKey, false);
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
