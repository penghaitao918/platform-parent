import com.fanfandou.admin.api.operation.entity.DeviceType;
import com.fanfandou.admin.api.operation.entity.GameVersionCheck;
import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.admin.api.operation.service.NoticeService;
import com.fanfandou.admin.api.operation.service.PatchService;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/7/27.
 * Description test.
 */
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;



    @Test
    public void test(){
        int gameId = 1;
        int siteId = 2;
        int areaId = 21;
        String siteIds = "%,"+siteId+",%";
        System.out.println(siteIds);

//        List<Notice> list = noticeService.findByGameSiteAreaId(gameId,siteId,areaId);
//        System.out.println(list);
    }


}
