package com.fanfandou.common;

import com.fanfandou.common.entity.ValidStatus;
import org.junit.Test;

/**
 * Description: 枚举测试.
 * <p/>
 * Date: 2016-03-21 17:16.
 * author: Arvin.
 */
public class EnumTest {

    @Test
    public void testEnumEquals(){
        //true
        System.out.println(ValidStatus.INVALID==ValidStatus.valueOf(2));
        //true
        System.out.println(ValidStatus.INVALID.equals(ValidStatus.valueOf(2)));
    }
}
