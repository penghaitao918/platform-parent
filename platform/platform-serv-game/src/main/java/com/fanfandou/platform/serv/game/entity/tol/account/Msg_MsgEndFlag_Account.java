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
public final class Msg_MsgEndFlag_Account implements Externalizable, Message<Msg_MsgEndFlag_Account>, Schema<Msg_MsgEndFlag_Account>
{

    public static Schema<Msg_MsgEndFlag_Account> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_MsgEndFlag_Account getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_MsgEndFlag_Account DEFAULT_INSTANCE = new Msg_MsgEndFlag_Account();

    

    public Msg_MsgEndFlag_Account()
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
        final Msg_MsgEndFlag_Account that = (Msg_MsgEndFlag_Account) obj;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Msg_MsgEndFlag_Account{" +
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

    public Schema<Msg_MsgEndFlag_Account> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_MsgEndFlag_Account newMessage()
    {
        return new Msg_MsgEndFlag_Account();
    }

    public Class<Msg_MsgEndFlag_Account> typeClass()
    {
        return Msg_MsgEndFlag_Account.class;
    }

    public String messageName()
    {
        return Msg_MsgEndFlag_Account.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_MsgEndFlag_Account.class.getName();
    }

    public boolean isInitialized(Msg_MsgEndFlag_Account message)
    {
        return true;
    }

    public void mergeFrom(Input input, Msg_MsgEndFlag_Account message) throws IOException
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


    public void writeTo(Output output, Msg_MsgEndFlag_Account message) throws IOException
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
