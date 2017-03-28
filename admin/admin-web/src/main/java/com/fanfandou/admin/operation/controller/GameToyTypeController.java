package com.fanfandou.admin.operation.controller;


import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.operation.entity.ItemType;
import com.fanfandou.admin.operation.service.ItemTypeService;
import com.fanfandou.admin.system.service.SysLogService;
import com.fanfandou.admin.system.service.UserService;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * author zhongliang.
 * Description:物品类型操作.
 */
@RestController
@RequestMapping(value = "/operation/gameToyType")
public class GameToyTypeController {

    @Autowired
    private ItemTypeService itemTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 分页对象
     *
     * @param name   物品名称
     * @param gameId 游戏id
     * @param page   page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<ItemType> getPageList(String name, int gameId, Page page) {
        return this.itemTypeService.getPageList(name, gameId, page);
    }

    /**
     * 添加
     *
     * @param itemType 物品类型对象
     */
    @RequestMapping("/addItemType")
    public String addUser(ItemType itemType) {
        try {
            this.itemTypeService.addItemType(itemType);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.itemType" + e.getId();
        }
        return null;

    }

    /**
     * 修改
     *
     * @param itemType 物品类型对象
     */
    @RequestMapping(value = "/updateItemType")
    public String editUser(ItemType itemType) {
        try {
            this.itemTypeService.updItemType(itemType);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.itemType" + e.getId();
        }
        return null;
    }

    /**
     * 根据id查询物品
     *
     * @param id id
     * @return 物品
     */
    @ResponseBody
    @RequestMapping("/selItemTypeById")
    public ItemType selItemTypeById(int id) {
        return this.itemTypeService.selItemTypeById(id);
    }

    /**
     * 根据类型查询物品集合
     *
     * @param type 类型
     * @return 物品集合
     */
    @ResponseBody
    @RequestMapping("/selItemTypeByType")
    public List<ItemType> selItemTypeByType(int type) {
        return this.itemTypeService.selByType(type);
    }

    /**
     * 删除
     *
     * @param itemTypeIds 物品类型id
     */
    @RequestMapping("/delItemType")
    @ResponseBody
    public void delItemType(String itemTypeIds) {
        String[] ids = itemTypeIds.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            idList.add(id);
        }
        this.itemTypeService.delItemType(idList);
    }

    /**
     * 跳转到分类页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/gameToyTypeInit")
    public ModelAndView toListMenu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/GameToyType");
        return mav;
    }


    /**
     * 根据id修改状态.
     *
     * @param ids   id集合
     * @param state 状态
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/updateState")
    public void itemState(String ids, int state) throws ServiceException {
        itemTypeService.optAvailable(ids, state);
    }

    /**
     * 分页查询(根据道具名称，道具类型查询)
     *
     * @param itemTypeName 物品名称
     * @param gameId       游戏id
     * @param itemType     物品类型
     * @param itemCode     物品code
     * @param page         page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/pageListByType")
    public PageResult<ItemType> getPageListByType(String itemTypeName, int gameId, int itemType, String itemCode, Page page) throws ServiceException {
        if (itemCode.equals("") || itemCode.equals(null)) {
            itemCode = "0";
        }
        PageResult<ItemType> itemTypes = this.itemTypeService.twoFind(itemTypeName, gameId, itemType, Integer.parseInt(itemCode), page);
        if (page.getPageIndex() == 1) {
            List<ItemType> itemTypeList = JSON.parseArray(userHabitQuery(itemType), ItemType.class);
            if (itemTypeList != null) {
                for (int i = itemTypeList.size() - 1; i >= 0; i--) {
                    itemTypes.getRows().add(0, itemTypeList.get(i));
                }
            }
        }
        return itemTypes;
    }

    /**
     * 更新用户习惯
     *
     * @return 用户集合
     */
    @ResponseBody
    @RequestMapping(value = "/habitEdit", method = RequestMethod.POST)
    public void userHabitEdit(int itemId, int itemType) throws ServiceException {
        String itemString = userHabitQuery(itemType);
        List<ItemType> itemTypes = JSON.parseArray(itemString, ItemType.class);
        if (itemTypes != null) {
            int index = -1;
            for (int i = 0; i < itemTypes.size(); i++) {
                if (itemId == itemTypes.get(i).getId()) {
                    index = i;
                }
            }

            if (index != -1) {
                itemTypes.add(0, itemTypes.get(index));
                itemTypes.remove(index + 1);
            } else {
                itemTypes.add(0, itemTypeService.selItemTypeById(itemId));
                if (itemTypes.size() > 3) {
                    itemTypes.remove(itemTypes.size() - 1);
                }

            }

            itemString = JSON.toJSONString(itemTypes);
        } else {
            itemTypes = new ArrayList<>();
            itemTypes.add(0, itemTypeService.selItemTypeById(itemId));
            if (itemTypes.size() > 3) {
                itemTypes.remove(itemTypes.size() - 1);
            }
            itemString = JSON.toJSONString(itemTypes);
        }


        userService.userHabitAdd(PublicCachedKeyConstant.USER_HABIT_ITEM + sysLogService.getUserId() + itemType, itemString);
    }

    /**
     * 获取用户习惯
     *
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/habitQuery", method = RequestMethod.POST)
    public String userHabitQuery(int itemType) throws ServiceException {
        return userService.userHabitQuery(PublicCachedKeyConstant.USER_HABIT_ITEM + sysLogService.getUserId() + itemType);
    }

    @ResponseBody
    @RequestMapping("/selItemTypeByAll")
    public List<ItemType> selItemTypeByAll() {
        return this.itemTypeService.selectAll();
    }

    /**
     * @param request  请求
     * @param response 响应
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/download")
    public ModelAndView download(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

//		String storeName = "Spring3.xAPI_zh.chm";
        String storeName = "Item.xls";
        String contentType = "application/octet-stream";
        ArticleController.download(request, response, storeName, contentType);
        return null;
    }

    /**
     * @param file    文件
     * @param request 请求
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

        //获取文件 存储位置
        String realPath = request.getSession().getServletContext()
                .getRealPath("/resource/Uploads");

        File pathFile = new File(realPath);

        if (!pathFile.exists()) {
            //文件夹不存 创建文件
            pathFile.mkdirs();
        }
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件名称：" + file.getOriginalFilename());
        System.out.println("文件大小:" + file.getSize());
        System.out.println(".................................................");
        //将文件copy上传到服务器
        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        //FileUtils.copy


        //获取modelandview对象
        ModelAndView view = new ModelAndView();
        view.setViewName("/operation/UploadWrong");
        view.addObject("returnList", itemTypeService.importExcel(realPath + "/" + file.getOriginalFilename(), file.getOriginalFilename()));
        return view;
    }

}
