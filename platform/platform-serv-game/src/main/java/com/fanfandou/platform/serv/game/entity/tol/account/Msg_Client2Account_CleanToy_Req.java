// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from proto

package com.fanfandou.platform.serv.game.entity.tol.account;

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


@Generated("java_bean")
public final class Msg_Client2Account_CleanToy_Req implements Externalizable, Message<Msg_Client2Account_CleanToy_Req>, Schema<Msg_Client2Account_CleanToy_Req>
{

    public static Schema<Msg_Client2Account_CleanToy_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Client2Account_CleanToy_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Client2Account_CleanToy_Req DEFAULT_INSTANCE = new Msg_Client2Account_CleanToy_Req();

    

    public Msg_Client2Account_CleanToy_Req()
    {

    }

    // getters and setters

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Client2Account_CleanToy_Req that = (Msg_Client2Account_CleanToy_Req) obj;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Msg_Client2Account_CleanToy_Req{" +
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

    public Schema<Msg_Client2Account_CleanToy_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Client2Account_CleanToy_Req newMessage()
    {
        return new Msg_Client2Account_CleanToy_Req();
    }

    public Class<Msg_Client2Account_CleanToy_Req> typeClass()
    {
        return Msg_Client2Account_CleanToy_Req.class;
    }

    public String messageName()
    {
        return Msg_Client2Account_CleanToy_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Client2Account_CleanToy_Req.class.getName();
    }

    public boolean isInitialized(Msg_Client2Account_CleanToy_Req message)
    {
        return true;
    }

    public void mergeFrom(Input input, Msg_Client2Account_CleanToy_Req message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Client2Account_CleanToy_Req message) throws IOException
    {
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
