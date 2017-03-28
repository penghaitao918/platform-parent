package com.fanfandou.admin.operation.service.impl;

import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.api.system.entity.Resource;
import com.fanfandou.admin.api.system.service.ResService;
import com.fanfandou.admin.operation.entity.ItemType;
import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.service.InputExcelService;
import com.fanfandou.admin.operation.service.ItemTypeService;
import com.fanfandou.admin.operation.service.MailOrderService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.service.GameAreaService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/7/19.
 * Description 订单excel导入导出接口.
 */
@Service("inputExcelService")
public class InputExcelServiceImpl extends BaseLogger implements InputExcelService {


    @Autowired
    private MailOrderService mailOrderService;

    @Autowired
    private GameAreaService gameAreaService;

    @Autowired
    private ItemTypeService itemTypeService;

    @Autowired
    private ResService resService;

    private List<String> failedReasonList = new ArrayList<>();

    /**
     * 导入excel的实现方法 .
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<String> importOrderExcel(InputStream in, String filename, String applyReason) throws ServiceException {
        failedReasonList.clear();
        List<MailOrder> orderList = readOrderExcel(in, filename);

        for (int i = 0; i < orderList.size(); i++) {
            boolean flag = true;
            MailOrder mailOrder = orderList.get(i);
            //验证游戏id
            int gameId = mailOrder.getGameId();
            //1游戏 2渠道
            Resource resource = resService.selByIdType(gameId, 1);
            if (resource == null) {
                String str = "错误:" + mailOrder.getMailTitle() + "没有找到该游戏";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }
            //服务器id
            String areaIds = mailOrder.getAreaIds();
            int areaId = Integer.parseInt(areaIds);
            GameArea gameArea = gameAreaService.getGameAreaById(areaId);
            if (gameArea == null) {
                String str = "错误:" + mailOrder.getMailTitle() + "没有找到该服务器";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }
            //对象类型
            int sendByType = mailOrder.getSendByType();
            if (sendByType < 0 || sendByType > 3) {
                String str = "错误:" + mailOrder.getMailTitle() + "没有该对象类型";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }
            //邮件类型
            int mailType = mailOrder.getMailType();
            if (mailType < 0 || mailType > 5) {
                String str = "错误:" + mailOrder.getMailTitle() + "没有找到该邮件类型";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }
            //发送对象在审核那边判断

            //邮件类型 只能是个人邮件1
            mailOrder.setSendType(1);
            //申请原因
            mailOrder.setApplyReason(applyReason);

            String itemJson = mailOrder.getItemJson();
            List<GoodsItem> list = mailOrderService.getGoodsItemList(itemJson);
            List<GoodsItem> newList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                GoodsItem items = list.get(j);
                //物品id
                int itemCode = items.getItemId();
                int itemType1 = Integer.parseInt(items.getItemType().getValue());
                ItemType itemType = itemTypeService.selByCodeAndType(gameId, itemCode, itemType1);
                if (itemType == null) {
                    String str = "错误:" + mailOrder.getMailTitle() + "没有这种物品";
                    failedReasonList.add(str);
                    logger.debug(str);
                    flag = false;
                }
                if (!flag) {
                    break;
                }
                items.setItemName(itemType.getItemName());
                newList.add(items);

            }
            String goodsJson = mailOrderService.setGoodsItemJson(newList);
            mailOrder.setItemJson(goodsJson);
            if (flag) {
                System.out.println("正确:" + mailOrder.getMailTitle() + "添加成功");
                mailOrderService.addMailOrder(mailOrder);
            }
        }
        return failedReasonList;
    }

    /**
     * 读取excel内容的实现.
     *
     * @param is 输入流
     * @return 读取到的资源列表
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<MailOrder> readOrderExcel(InputStream is, String filename) throws ServiceException {
        Workbook wb;
        List<MailOrder> orders = new ArrayList<>();
        try {
            if (filename.endsWith(".xls")) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            //循环页
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    boolean flag = true;
                    int excelNum = rowNum + 1;
                    Row row = hssfSheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    MailOrder mailOrder = new MailOrder();
                    String[] itemCodes = null;
                    String[] itemTypes = null;
                    String[] itemValues = null;
                    //循环列
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        Cell cell = (row).getCell(i);
                        if (cell != null) {
                            String value = getCellValue(cell);

                            if (i == 0) {
                                if (value != null) {
                                    mailOrder.setGameId(Integer.parseInt(value));
                                }
                            } else if (i == 1) {
                                if (value != null) {
                                    mailOrder.setAreaIds(value);
                                }
                            } else if (i == 2) {
                                if (value != null) {
                                    mailOrder.setSendByType(Integer.parseInt(value));
                                }
                            } else if (i == 3) {
                                if (value != null) {
                                    mailOrder.setSendByValue(value);
                                }
                            } else if (i == 4) {
                                if (value != null) {
                                    mailOrder.setMailTitle(value);
                                }
                            } else if (i == 5) {
                                if (value != null) {
                                    mailOrder.setMailContent(value);
                                }
                            } else if (i == 6) {
                                if (value != null) {
                                    String itemCodeStr = value;
                                    itemCodes = itemCodeStr.split(",");
                                }
                            } else if (i == 7) {
                                if (value != null) {
                                    String itemTypeStr = value;
                                    itemTypes = itemTypeStr.split(",");
                                }
                            } else if (i == 8) {
                                if (value != null) {
                                    String itemValueStr = value;
                                    itemValues = itemValueStr.split(",");
                                }
                            } else if (i == 9) {
                                if (value != null) {
                                    mailOrder.setMailType(Integer.parseInt(value));
                                }
                            }
                        } else {
                            flag = false;
                            String str = "错误：第" + excelNum + "行数据中有空值";
                            failedReasonList.add(str);
                            logger.debug(str);
                            break;
                        }
                    }
                    if (flag) {

                        String json = null;
                        List<GoodsItem> items = new ArrayList<>();
                        if (itemCodes.length == itemTypes.length && itemCodes.length == itemValues.length
                                && itemTypes.length == itemValues.length && itemCodes.length != 0) {
                            for (int i = 0; i < itemCodes.length; i++) {
                                GoodsItem item = new GoodsItem();
                                int itemCode = Integer.parseInt(itemCodes[i]);
                             /*   int itemType = Integer.parseInt(itemTypes[i]);*/
                                int itemValue = Integer.parseInt(itemValues[i]);
                                item.setItemId(itemCode);
                                DicItem dicItem=new DicItem();
                                dicItem.setValue(itemTypes[i]);
                                item.setItemType(dicItem);
                                item.setValue(itemValue);
                                items.add(item);
                                json = mailOrderService.setGoodsItemJson(items);
                            }
                        } else {
                            flag = false;
                            String str = "错误：第" + excelNum + "行数据的物品信息不对";
                            failedReasonList.add(str);
                            logger.debug(str);
                        }
                        if (flag) {
                            mailOrder.setItemJson(json);
                            orders.add(mailOrder);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new ServiceException(AdminException.ADMIN_INPUT_EXCEL);
        }
        return orders;
    }

    /**
     * 导入excel的实现方法 .
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<String> importItemExcel(InputStream in, String filename) throws Exception {
        failedReasonList.clear();
        List<ItemType> itemList = readItemExcel(in, filename);

        for (int i = 0; i < itemList.size(); i++) {
            boolean flag = true;
            ItemType item = itemList.get(i);
            //验证游戏id
            int gameId = item.getGameId();
            //1游戏 2渠道
            Resource resource = resService.selByIdType(gameId, 1);
            if (resource == null) {
                String str = "错误:" + item.getItemName() + "没有找到该游戏";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }
            //物品code
            int itemCode = item.getItemCode();
            int itemType = item.getItemType();
            ItemType item1 = itemTypeService.selByCodeAndType(gameId, itemCode, itemType);
            if (item1 != null) {
                String str = "错误:" + item.getItemName() + "这个游戏下的该类型里已经有相同的code存在了";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }
            //物品类型
            if (itemType < 0 || itemType > 3) {
                String str = "错误:" + item.getItemName() + "没有该物品类型";
                failedReasonList.add(str);
                logger.debug(str);
                flag = false;
            }

            if (flag) {
                System.out.println("正确:" + item.getItemName() + "添加成功");
                itemTypeService.addItemType(item);
            }
        }
        return failedReasonList;
    }

    /**
     * 读取excel内容的实现.
     *
     * @param is 输入流
     * @return 读取到的资源列表
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<ItemType> readItemExcel(InputStream is, String filename) throws Exception {
        Workbook wb;
        if (filename.endsWith(".xls")) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        List<ItemType> items = new ArrayList<>();
        //循环页
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = wb.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行row 从第二行开始
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                boolean flag = true;
                int excelNum = rowNum + 1;
                Row row = hssfSheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                ItemType itemType = new ItemType();
                //循环列
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = (row).getCell(i);
                    if (cell != null) {
                        String value = getCellValue(cell);
                        if (i == 0) {
                            if (value != null) {
                                itemType.setGameId(Integer.parseInt(value));
                            }
                        } else if (i == 1) {
                            if (value != null) {
                                itemType.setItemCode(Integer.parseInt(value));
                            }
                        } else if (i == 2) {
                            if (value != null) {
                                itemType.setItemName(value);
                            }
                        } else if (i == 3) {
                            if (value != null) {
                                itemType.setItemType(Integer.parseInt(value));
                            }
                        } else if (i == 4) {
                            if (value != null) {
                                itemType.setItemExtend(value);
                            }
                        }
                    } else {
                        flag = false;
                        String str = "错误：第" + excelNum + "行数据中有空值";
                        failedReasonList.add(str);
                        logger.debug(str);
                        break;
                    }
                }
                if (flag) {
                    items.add(itemType);
                }
            }
        }
        return items;
    }

    /**
     * 类型转换方法.
     *
     * @param cell excel中的成员
     * @return 统一返回String类型
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public String getCellValue(Cell cell) {

        String value = null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                long dd = (long) cell.getNumericCellValue();
                value = dd + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 创建2003版本的Excel文件.
     */
    public void creat2003Excel() throws Exception {
        HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象
        HSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象
        //sheet.setColumnWidth(1, 8000);// 设置第二列的宽度为
        HSSFRow row = sheet.createRow(0);// 创建一个行对象
        //row.setHeightInPoints(23);// 设置行高23像素
        HSSFCellStyle style = workBook.createCellStyle();// 创建样式对象
        // 设置字体
        HSSFFont font = workBook.createFont();// 创建字体对象
        style.setFont(font);// 将字体加入到样式对象
        font.setFontHeightInPoints((short) 15);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
        font.setFontName("黑体");// 设置为黑体字
        // 设置对齐方式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        // 设置边框
        style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线
        style.setBorderBottom(HSSFCellStyle.BORDER_NONE);// 底部边框双线
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框
        for (int i = 0; i < 10; i++) {
            HSSFCell cell = row.createCell(i);
            sheet.setColumnWidth(i, 5000);// 设置列的宽度为
            if (i == 0) {
                cell.setCellValue("游戏id");
            } else if (i == 1) {
                cell.setCellValue("服务器id");
            } else if (i == 2) {
                cell.setCellValue("对象类型");
            } else if (i == 3) {
                cell.setCellValue("发送对象");
            } else if (i == 4) {
                cell.setCellValue("邮件标题");
            } else if (i == 5) {
                cell.setCellValue("邮件正文");
            } else if (i == 6) {
                cell.setCellValue("物品code");
            } else if (i == 7) {
                cell.setCellValue("物品类型");
            } else if (i == 8) {
                cell.setCellValue("物品数量");
            } else if (i == 9) {
                cell.setCellValue("邮件类型");
            }
            cell.setCellStyle(style);
        }
        //HSSFRow row1 = sheet.createRow(25);// 创建一个行对象
        //HSSFCell cell = row1.createCell(0);
        HSSFCellStyle style1 = workBook.createCellStyle();
        style1.setFont(font);
        //style1.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线
        style1.setTopBorderColor(HSSFColor.RED.index);// 设置为红色
        //cell.setCellValue("参数说明：");

        // 文件输出流
        FileOutputStream os = new FileOutputStream("style_2003.xls");
        workBook.write(os);// 将文档对象写入文件输出流
        os.close();// 关闭文件输出流
        System.out.println("创建成功 office 2003 excel");
    }
}
