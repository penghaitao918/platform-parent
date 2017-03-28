package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.api.system.entity.ResEnum;
import com.fanfandou.admin.api.system.entity.Resource;
import com.fanfandou.admin.api.system.service.ResService;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * author shengbo.
 * Description:展示游戏信息.
 */
@RestController
@RequestMapping("/system/res")
public class ResController {
    @Autowired
    private ResService resService;


    @Autowired
    private PermissionService permissionService;

    /**
     * 显示资源列表.
     *
     * @return ModelAndView
     */
    @RequestMapping("/resInit")
    public ModelAndView gameList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/ResourceList");
        return mav;
    }

    /**
     * 获取资源列表.
     *
     * @return ModelAndView
     */
    @RequestMapping("/list")
    public List<Resource> getList() {
        return this.resService.selectAll();
    }


    /**
     * 添加游戏.
     *
     * @param res 资源对象
     * @return ModelAndView 游戏集合
     */
    @RequestMapping("/insertGame")
    public ModelAndView insertGame(Resource res) {
        this.resService.addGameCode(res);
        return gameList();
    }

    /**
     * 添加渠道.
     *
     * @param res 资源对象
     * @return ModelAndView 渠道集合
     */
    @RequestMapping("/insertSite")
    public ModelAndView insertSite(Resource res) {
        this.resService.addSiteCode(res);
        return gameList();
    }

    /**
     * 删除游戏资源.
     *
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping("/delGame")
    public ModelAndView delGame(@RequestParam(value = "ID") int id) {
        this.resService.delGame(id);
        return gameList();
    }

    /**
     * 更新游戏资源.
     *
     * @param res 资源对象
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateGame", method = RequestMethod.POST)
    public ModelAndView updateGame(Resource res) {
        this.resService.updateGame(res);
        return gameList();
    }

    /**
     * 显示资源更新.
     *
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping("/editGame")
    public ModelAndView editGame(@RequestParam(value = "ID") int id) {
        ModelAndView mav = new ModelAndView("system/res/editGame");
        Resource res = this.resService.selectById(id);
        mav.addObject("res", res);
        return mav;
    }

    /**
     * 导入Excel.
     */
    /*@RequestMapping("/importExcel")
    public ModelAndView importExcel(@RequestParam("filename") MultipartFile file, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("/system/res/resList");
        String temp = request.getSession().getServletContext()
                .getRealPath(File.separator)
                + "temp";
        File tempFile = new File(temp);
        boolean isExists =tempFile.exists();
        if (!isExists) {
            tempFile.mkdirs();
        }
        DiskFileItemFactory dff = new DiskFileItemFactory();
        dff.setSizeThreshold(10 * 1024 * 1024);
        dff.setRepository(tempFile);

        if (file.isEmpty()) {
            return null;
        }

        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        System.out.println(name);
        System.out.println(size);
        InputStream in = file.getInputStream();
        List<Resource> resourceList = this.euService.importExcel(in,name);
        int count = resourceList.size();
        String AlertMessage;
        if (count != 0) {
            AlertMessage = "导入成功" + count + "条";
        } else {
            AlertMessage = "导入失败";
        }
        System.out.println(AlertMessage);

        request.getSession().setAttribute("msg", AlertMessage);
        mav.addObject("res", resourceList);
        System.out.println("这个modelView是：" + mav);
        return mav;
    }*/

    /**
     * 分页查询.
     *
     * @param resCode 资源code
     * @param page    page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<Resource> getPageList(String resCode, Page page) {
        return this.resService.getPageList(resCode, page);
    }

    /**
     * 批量删除.
     *
     * @param resIds id集合
     * @return 资源集合
     */
    @RequestMapping("/delResource/{resIds}")
    @ResponseBody
    public List<Resource> delPermission(@PathVariable(value = "resIds") String resIds) {
        String[] ids = resIds.split(",");
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            idList.add(id);
        }
        this.resService.delResList(idList);
        return this.resService.selectAll();
    }

    /**
     * 根据id查.
     *
     * @param id resId
     */
    @ResponseBody
    @RequestMapping(value = "/editRes/{id}")
    public Resource edit(@PathVariable(value = "id") int id) {
        return resService.selectById(id);
    }

    /**
     * 获取游戏资源.
     */
    @ResponseBody
    @RequestMapping("/gameMap")
    public Map getGameMap() {
        return permissionService.getGameMap();
    }

    /**
     * 获取渠道资源.
     */
    @ResponseBody
    @RequestMapping("/siteMap")
    public Map getSiteMap() {
        return permissionService.getSiteMap();
    }

    /**
     * 根据id修改状态
     *
     * @param ids   资源id
     * @param state 状态码
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/updateState")
    public void menuState(String ids, int state) throws ServiceException {
        this.resService.optAvailable(ids, state);
    }

    /**
     * 更新资源缓存
     *
     * @throws ServiceException
     */
    @RequestMapping(value = "/updateResCache")
    public void updateResCache() throws ServiceException {
        this.resService.updateGameCode(ResEnum.game);
        this.resService.updateSiteCode(ResEnum.site);
    }
}