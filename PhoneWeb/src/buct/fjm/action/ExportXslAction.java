package buct.fjm.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.TPkgDetor;
import buct.fjm.model.Tpkg;
import buct.fjm.service.TPkgService;
import buct.fjm.util.ConfigRead;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("exportXsl")
@Scope("prototype")
public class ExportXslAction extends ActionSupport {
	private String macAdd;// 传过来的mac地址
	private InputStream excelFile;
	private TPkgService tPkgService;

	public TPkgService gettPkgService() {
		return tPkgService;
	}

	@Resource
	public void settPkgService(TPkgService tPkgService) {
		this.tPkgService = tPkgService;
	}

	public String getMacAdd() {
		return macAdd;
	}

	public void setMacAdd(String macAdd) {
		this.macAdd = macAdd;
	}

	// ++++++++++++++++++++++++++
	/*
	 * 数据库 查询后绑定到对象后 将对象赋值给list 然后转成对象进行列操作
	 */
	public void ExcelFile() {
		Workbook workbook = null;
		// 从数据库获取AttendBean的list
		// List<AttendBean> list = mgr.Attend(mgrName);
		List<TPkgDetor> list = new ArrayList();
		list = getPkgListByMac();
		workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("流量包详情表");// 创建页脚

		// 创建报表的头部标题
		Row row = sheet.createRow(0);
		String headString = "流量监测报表";// 头部显示的标题
		int colSum = 7;// 该报表的列数
		// 设置第一行
		Cell cell = row.createCell(0);
		row.setHeight((short) 400);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(headString));

		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0,
				(short) colSum));
		//CellRangeAddress四个参数分别代表，起始行、结束行、起始列、结束列。
		CellStyle cellStyle = workbook.createCellStyle();

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		// 设置第二行，第二行写检测的开始时间以及检测的结束时间
		row = sheet.createRow(1);
		row.setHeight((short) 300);

		cell = row.createCell(0);

		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		String examineStartTime = "startTime";// 检测开始时间
		String examineEndTime = "endTime";// 检测结束时间
		try{
			//读取配置文件中开始时间到结束时间
//			String confPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
//			confPath=confPath.substring(0, confPath.lastIndexOf("buct"));
			String confPath="/usr/local/raywork/conf/";
			String inputTConfigPath=confPath+"InputTConfig.conf";
			
			Map<String,String> inputTConfigMap=ConfigRead.read(inputTConfigPath);
			examineStartTime=inputTConfigMap.get("StartTime");
			examineEndTime=inputTConfigMap.get("EndTime");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		cell.setCellValue(new HSSFRichTextString("检测时间 ：" + examineStartTime
				+ " 至 " + examineEndTime));

		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 0,
				(short) colSum));

		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		// 创建数据表格的表头
		row = sheet.createRow(2);
		row.createCell(0).setCellValue("源Mac地址");
		row.createCell(1).setCellValue("目标IP");
		row.createCell(2).setCellValue("端口");
		row.createCell(3).setCellValue("协议");
		row.createCell(4).setCellValue("流量方向");
		row.createCell(5).setCellValue("总流量(/字节)");
		row.createCell(6).setCellValue("最后访问时间");

		cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		// 往数据表中添加数据
		for (int i = 1; i <= list.size(); i++) {
			TPkgDetor att = list.get(i - 1);
			row = sheet.createRow(i + 2);// 由于加了表标题，这里往下挪
			row.createCell(0).setCellValue(att.getTnativeMac());
			row.createCell(1).setCellValue(att.getTremoteIp());
			row.createCell(2).setCellValue(att.getTremotePort());
			// 6-http,17-udp
			int protocolNum = att.getTprotocolType();
			if (protocolNum == 6) {
				row.createCell(3).setCellValue("HTTP");
			} else {
				row.createCell(3).setCellValue("UDP");
			}
			if (att.getTflowDirection()) {
				// 1是下行流量
				row.createCell(4).setCellValue("下行");
			} else {
				row.createCell(4).setCellValue("上行");
			}
			row.createCell(5).setCellValue(att.getTflowAmount());
			row.createCell(6).setCellValue(att.getTlastVisitTime().toString());
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			workbook.write(baos);

		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] aa = baos.toByteArray();
		excelFile = new ByteArrayInputStream(aa, 0, aa.length);
		try {
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String execute() throws Exception {

		System.out.println("exportXsl start!");
		try {
			ExcelFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public List<TPkgDetor> getPkgListByMac() {
		String tnativeMac = macAdd;// 将传进来的mac地址赋值
		String hql = "from Tpkg where tnativeMac=\'" + tnativeMac + "\'";

		List<Tpkg> all = tPkgService.getListBySql(hql);

		List<TPkgDetor> pList = new ArrayList();
		for (Tpkg tpkg : all) {
			pList.add(new TPkgDetor(tpkg, 0));
		}
		return pList;
	}

}
