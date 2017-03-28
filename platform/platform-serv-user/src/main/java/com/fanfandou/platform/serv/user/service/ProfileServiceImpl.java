package com.fanfandou.platform.serv.user.service;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.user.entity.UserProfile;
import com.fanfandou.platform.api.user.service.ProfileService;
import com.fanfandou.platform.serv.user.dao.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 用户资料相关业务实现.
 * <p/>
 * Date: 2016-02-24 16:41.
 * author: Arvin.
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public void createProfile(UserProfile profile) throws ServiceException {
        userProfileMapper.insert(profile);
    }

    @Override
    public UserProfile getProfileByUid(long userId) {
        return userProfileMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void acticeProfile(UserProfile userProfile) throws ServiceException {
        userProfileMapper.updateByPrimaryKeySelective(userProfile);
    }
}
