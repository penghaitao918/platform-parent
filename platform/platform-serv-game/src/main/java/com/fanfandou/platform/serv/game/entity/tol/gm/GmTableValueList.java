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
public final class GmTableValueList implements Externalizable, Message<GmTableValueList>, Schema<GmTableValueList>
{

    public static Schema<GmTableValueList> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static GmTableValueList getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final GmTableValueList DEFAULT_INSTANCE = new GmTableValueList();

    
    private Integer mRowId;
    private List<GmTableValue> mColumns;

    public GmTableValueList()
    {

    }

    // getters and setters

    // mRowId

    public Integer getMRowId()
    {
        return mRowId;
    }


    public void setMRowId(Integer mRowId)
    {
        this.mRowId = mRowId;
    }

    // mColumns

    public List<GmTableValue> getMColumnsList()
    {
        return mColumns;
    }


    public void setMColumnsList(List<GmTableValue> mColumns)
    {
        this.mColumns = mColumns;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final GmTableValueList that = (GmTableValueList) obj;
        return
                Objects.equals(this.mRowId, that.mRowId) &&
                Objects.equals(this.mColumns, that.mColumns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mRowId, mColumns);
    }

    @Override
    public String toString() {
        return "GmTableValueList{" +
                    "mRowId=" + mRowId +
                    ", mColumns=" + mColumns +
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

    public Schema<GmTableValueList> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public GmTableValueList newMessage()
    {
        return new GmTableValueList();
    }

    public Class<GmTableValueList> typeClass()
    {
        return GmTableValueList.class;
    }

    public String messageName()
    {
        return GmTableValueList.class.getSimpleName();
    }

    public String messageFullName()
    {
        return GmTableValueList.class.getName();
    }

    public boolean isInitialized(GmTableValueList message)
    {
        return true;
    }

    public void mergeFrom(Input input, GmTableValueList message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.mRowId = input.readUInt32();
                    break;
                case 2:
                    if(message.mColumns == null)
                        message.mColumns = new ArrayList<GmTableValue>();
                    message.mColumns.add(input.mergeObject(null, GmTableValue.getSchema()));
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, GmTableValueList message) throws IOException
    {
        if(message.mRowId != null)
            output.writeUInt32(1, message.mRowId, false);

        if(message.mColumns != null)
        {
            for(GmTableValue mColumns : message.mColumns)
            {
                if(mColumns != null)
                    output.writeObject(2, mColumns, GmTableValue.getSchema(), true);
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
