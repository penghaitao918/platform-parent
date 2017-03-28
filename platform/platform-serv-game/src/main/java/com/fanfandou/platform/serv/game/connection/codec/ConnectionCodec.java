package com.fanfandou.platform.serv.game.connection.codec;

/**
 * Description: 编码/协议 适配器.
 * <p/>
 * Date: 2016-05-05 15:23.
 * author: Arvin.
 */
public interface ConnectionCodec<T> {

    byte[] encode(T obj);

    T decode(byte[] datas);
}
