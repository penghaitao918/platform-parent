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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Generated("java_bean")
public final class Msg_Gm2Logic_Notice_Req implements Externalizable, Message<Msg_Gm2Logic_Notice_Req>, Schema<Msg_Gm2Logic_Notice_Req>
{

    public static Schema<Msg_Gm2Logic_Notice_Req> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Msg_Gm2Logic_Notice_Req getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Msg_Gm2Logic_Notice_Req DEFAULT_INSTANCE = new Msg_Gm2Logic_Notice_Req();

    
    private String mSerialNumber;
    private List<GmNotice> mNotice = new ArrayList<>();


    public Msg_Gm2Logic_Notice_Req()
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

    // mNotice

    public List<GmNotice> getMNoticeList()
    {
        return mNotice;
    }


    public void setMNoticeList(List<GmNotice> mNotice)
    {
        this.mNotice = mNotice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Msg_Gm2Logic_Notice_Req that = (Msg_Gm2Logic_Notice_Req) obj;
        return
                Objects.equals(this.mSerialNumber, that.mSerialNumber) &&
                Objects.equals(this.mNotice, that.mNotice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mSerialNumber, mNotice);
    }

    @Override
    public String toString() {
        return "Msg_Gm2Logic_Notice_Req{" +
                    "mSerialNumber=" + mSerialNumber +
                    ", mNotice=" + mNotice +
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

    public Schema<Msg_Gm2Logic_Notice_Req> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Msg_Gm2Logic_Notice_Req newMessage()
    {
        return new Msg_Gm2Logic_Notice_Req();
    }

    public Class<Msg_Gm2Logic_Notice_Req> typeClass()
    {
        return Msg_Gm2Logic_Notice_Req.class;
    }

    public String messageName()
    {
        return Msg_Gm2Logic_Notice_Req.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Msg_Gm2Logic_Notice_Req.class.getName();
    }

    public boolean isInitialized(Msg_Gm2Logic_Notice_Req message)
    {
        return true;
    }

    public void mergeFrom(Input input, Msg_Gm2Logic_Notice_Req message) throws IOException
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
                    if(message.mNotice == null)
                        message.mNotice = new ArrayList<GmNotice>();
                    message.mNotice.add(input.mergeObject(null, GmNotice.getSchema()));
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Msg_Gm2Logic_Notice_Req message) throws IOException
    {
        if(message.mSerialNumber != null)
            output.writeString(1, message.mSerialNumber, false);

        if(message.mNotice != null)
        {
            for(GmNotice mNotice : message.mNotice)
            {
                if(mNotice != null)
                    output.writeObject(2, mNotice, GmNotice.getSchema(), true);
            }
        }

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
