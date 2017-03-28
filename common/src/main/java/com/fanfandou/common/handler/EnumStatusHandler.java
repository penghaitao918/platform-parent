package com.fanfandou.common.handler;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: Mybatis自定义转换类型 .
 *
 * @author Arvin.
 */
public class EnumStatusHandler<E extends Enum<E> & EnumStatus<E>> extends BaseTypeHandler<E> {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Integer, E> enumMap = new HashMap<Integer, E>();

    /**
     * @param type 正在使用的枚举类.
     */
    public EnumStatusHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        for (E e : enums) {
            enumMap.put(e.getId(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex,
                                    E enumStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(columnIndex, enumStatus.getId());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String colunnName) throws SQLException {
        int id = resultSet.getInt(colunnName);

        if (resultSet.wasNull()) {
            return null;
        } else {
            return getEnumStatus(id);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        int id = resultSet.getInt(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return getEnumStatus(id);
        }
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        int id = callableStatement.getInt(columnIndex);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return getEnumStatus(id);
        }
    }

    private E getEnumStatus(int id) {
        EnumStatus<E> enumStatus = enumMap.get(id);
        if (enumStatus == null) {
            return null;
        }
        return enumStatus.getById(id);
    }


}
