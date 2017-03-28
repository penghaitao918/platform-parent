package com.fanfandou.platform.api.user.service;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.user.entity.UserProfile;

/**
 * Description: 用户资料相关业务.
 * <p/>
 * Date: 2016-02-23 17:41.
 * author: Arvin.
 */
public interface ProfileService {

    void createProfile(UserProfile profile) throws ServiceException;

    UserProfile getProfileByUid(long userId);

    void acticeProfile(UserProfile userProfile) throws ServiceException;

}
