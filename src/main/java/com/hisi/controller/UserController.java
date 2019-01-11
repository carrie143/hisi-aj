package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.hisi.common.file.FtpUtils;
import com.hisi.common.util.DateUtil;
import com.hisi.common.util.DateUtil.Pattern;
import com.hisi.common.util.ExcelImportUtils;
import com.hisi.common.util.NameUtil;
import com.hisi.common.util.PasswordUtil;
import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiUser;
import com.hisi.model.HisiUservo;
import com.hisi.service.DepartmentService;
import com.hisi.service.UserService;
import com.hisi.service.UserVoService;
/*import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;*/
import com.alibaba.druid.util.StringUtils;

@RestController
@Api(value = "HisiUser", description = "员工管理")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserVoService userVoService;
	@Autowired
	private PasswordUtil passwordUtil;
	@Value("${ftp.ip}")
	private String ftpHostname;//192.168.1.219
	@Value("${ftp.port}")
	private Integer ftpPort;//21
	@Value("${ftp.account}")
	private String ftpUsername;//administrator
	@Value("${ftp.password}")
	private String ftpPassword;//123.com
	@Value("${ftp.basePath}")//D:\\FTP
	private String ftpBasePath;
	@Value("${ftp.pic}")
	private String winFtp;
	@Value("${addUserPath}")
	private String addUserPath;
	//, headers = "Content-Type=application/json" 
	//@RequestBody Map<String,String> map
	@ApiOperation(value = "新增员工", httpMethod = "POST", response = YfslResult.class, notes = "新增员工")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public YfslResult addUser(HttpServletRequest req) throws Exception {
		
		MultipartHttpServletRequest request= (MultipartHttpServletRequest) req;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		String groupName=request.getParameter("groupName");
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String telPhone=request.getParameter("telPhone");
		String roleName=request.getParameter("roleName");
		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
		HisiUser hisiUser1 = new HisiUser();
		HisiUser hisiUser2 = new HisiUser();
		hisiUser1 = userService.findUserByUserId(userId);
		hisiUser2 = userService.findUserByTelPhone(telPhone);
		if (hisiUser1 != null&&hisiUser2 == null) {
			return YfslResult.fail("该员工id已存在");
		}
		if (hisiUser2 != null&&hisiUser1 == null) {
			return YfslResult.fail("该员工电话已存在");
		}
		if (hisiUser2 != null&&hisiUser1 != null) {
			return YfslResult.fail("该员工id和电话已存在");
		}
		byte[] imageByte=null;
		if(file==null){
			imageByte=null;
		}
		String imageUrl=Base64.getEncoder().encodeToString((file).getBytes());
		if (imageUrl == null) {
			imageByte=null;
		}
		byte[] b =Base64.getDecoder().decode(imageUrl);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}
		//String imgFilePath = "E:\\add";
		String imgFilePath =addUserPath;
		String fileName = getExtension() + ".png";
		//String path = imgFilePath + "\\" + fileName;
		String path = imgFilePath + "/" + fileName;
		OutputStream out = new FileOutputStream(path);
		out.write(b);
		out.flush();
		out.close();
		
		String filePath="user";
		FtpUtils ftp=new FtpUtils(ftpHostname,ftpPort,ftpUsername,ftpPassword,ftpBasePath);
		String nameEnd = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		if(nameEnd.equals("blob")){
			nameEnd="png";
		}
		String fileName1=NameUtil.getExtension()+"."+nameEnd;
		filePath=filePath+"/"+DateUtil.getSysTime(Pattern.PATTERN_4);
		boolean flag=false;
		//上传到服务器
		flag=ftp.uploadFile(filePath,fileName1,file.getInputStream());
		if(!flag){
			return YfslResult.fail("失败");
		}//  "/images/hisi_aj/"+   user/2018/05/09/1525856298712.jpg
		String returnPath=filePath+"/"+fileName1;	
		String departmentName = (String) session.getAttribute("departmentName");
		HisiUser hisiUser = new HisiUser();
		imageByte = userService.generateByte(path); 
		if(imageByte==null){
			return YfslResult.fail("图片不包含人脸，请重新上传");
		}
	    hisiUser.setSavepath(returnPath);
		hisiUser.setUserid(userId);
		hisiUser.setUsername(userName);
		hisiUser.setTelphone(telPhone);
		hisiUser.setGroupName(groupName);
		hisiUser.setRoleName(roleName);
		hisiUser.setDepartmentName(departmentName);
		hisiUser.setIamge(imageByte);
		hisiUser.setStaus(0);// 0:正常，1：被屏蔽，默认为正常状态
		Object generateScret = passwordUtil.generateScret(userId, "123456");
		String password=generateScret.toString();
		hisiUser.setPassword(password);
		hisiUser.setPassword1("123456");
		
		int n = userService.addUser(hisiUser);
		if (n > 0) {
			return YfslResult.ok("新增成功");
		}
		return YfslResult.fail("新增失败");
	}
	private String getExtension() {
		try {
			// 线程睡会<br>
			Thread.sleep(1);
			// 生成日期实体<br>
			Calendar calendar = Calendar.getInstance();
			String extension = calendar.getTime().getTime() + "";
			return extension;// 专网及EZVP公网<br>
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ApiOperation(value = "上传用户底片", httpMethod = "POST", response = YfslResult.class, notes = "上传用户底片")
	@PostMapping(value = "/upLoadUserPhoto", headers = "Content-Type=application/json")
	public YfslResult upLoadUserPhoto(HttpServletRequest request) throws Exception{
		String filePath="user"; 
		FtpUtils ftp=new FtpUtils(ftpHostname,ftpPort,ftpUsername,ftpPassword,ftpBasePath);
		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
		String nameEnd = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		String fileName=NameUtil.getExtension()+"."+nameEnd;
		filePath=filePath+"/"+DateUtil.getSysTime(Pattern.PATTERN_4);
		boolean flag=false;
		flag=ftp.uploadFile(filePath,fileName,file.getInputStream());
		if(!flag){
			return YfslResult.fail("失败");
		}
		return YfslResult.ok();
	}
	
	//, headers = "Content-Type=application/json"
	@ApiOperation(value = "编辑员工", httpMethod = "POST", response = YfslResult.class, notes = "编辑员工")
	@PostMapping(value = "/editUser")
	public YfslResult editUser(HttpServletRequest req) throws Exception {
		MultipartHttpServletRequest request= (MultipartHttpServletRequest) req;
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String telPhone=request.getParameter("telPhone");
		String groupName=request.getParameter("groupName");
		String roleName=request.getParameter("roleName");
		String imgUrl = request.getParameter("imgUrl");//原始图片路径
		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");//重新上传的图片
		//userid修改后password也被修改
		Object generateScret = passwordUtil.generateScret(userId, "123456");
		String password=generateScret.toString();
		List<HisiUser> users=userService.findAllUserId();
		for(int i=0;i<users.size();i++){
			//跟其他员工的userId重复
			if(userId.equals(users.get(i).getUserid())&&id!=users.get(i).getId()){
				return YfslResult.fail("员工id重复");
			}
		}
		if(imgUrl!=null){//图片没有修改,则只修改其他信息
			int n=userService.editPart(id,userId,userName,telPhone,groupName,password,roleName);
			if(n>0){
				return YfslResult.ok();
			}
		}
		//BASE64Encoder encoder=new BASE64Encoder();
		byte[] imageByte=null;
		if(file==null){
			imageByte=null;
		}
		//base64字符串
		//String imageUrl=encoder.encode((file).getBytes());
		String imageUrl=Base64.getEncoder().encodeToString((file).getBytes());

		if (imageUrl == null) {
			//return YfslResult.fail("图片为空");
			imageByte=null;
		}
		/*BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(imageUrl);*/
		byte[] b =Base64.getDecoder().decode(imageUrl);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}
		String imgFilePath = "E:\\add";
		String fileName = getExtension() + ".png";
		String path = imgFilePath + "\\" + fileName;
		OutputStream out = new FileOutputStream(path);
		out.write(b);
		out.flush();
		out.close();
		//上传图片
		String filePath="user"; 
		FtpUtils ftp=new FtpUtils(ftpHostname,ftpPort,ftpUsername,ftpPassword,ftpBasePath);
		String nameEnd = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		if(nameEnd.equals("blob")){
			nameEnd="png";
		}
		String fileName1=NameUtil.getExtension()+"."+nameEnd;
		filePath=filePath+"/"+DateUtil.getSysTime(Pattern.PATTERN_4);
		boolean flag=false;
		//上传到服务器
		flag=ftp.uploadFile(filePath,fileName1,file.getInputStream());
		if(!flag){
			return YfslResult.fail("失败");
		}
		String savePath=filePath+"/"+fileName1;
		//String departmentName="部门1";
		imageByte = userService.generateByte(path);
		if(imageByte==null){
			return YfslResult.fail("图片不包含人脸，请重新上传");
		}
		int i=userService.editUser(id,userId,userName,telPhone,groupName,imageByte,savePath,password,roleName);
		if(i>0){
			return YfslResult.ok("编辑成功");
		}
		return YfslResult.fail("编辑失败");
	}
	@ApiOperation(value = "修改密码即个人设置", httpMethod = "POST", response = YfslResult.class, notes = "修改密码")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public YfslResult changePassword(
			@ApiParam(required = true, name = "oldPassword", value = "旧密码") @RequestParam String oldPassword,
			@ApiParam(required = true, name = "newPassword", value = "新密码") @RequestParam String newPassword,
			@ApiParam(required = true, name = "newPassword1", value = "新密码1") @RequestParam String newPassword1,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 数据库存的密码（加密）
		System.out.println("new  sessionId:"+session.getId());
		Object password = session.getAttribute("password");
		Object userId1 = session.getAttribute("userId");
		String oldPassword1 = password.toString();
		String userId = userId1.toString();
		Object scret = passwordUtil.generateScret(userId, oldPassword);
		oldPassword = scret.toString();
		if (!oldPassword.equals(oldPassword1)) {
			return YfslResult.fail("旧密码不正确");
		}
		if (!newPassword.equals(newPassword1)) {
			return YfslResult.fail("两次密码输入不一致");
		}
		Object generateScret = passwordUtil.generateScret(userId, newPassword);
		String newSecretPassword = generateScret.toString();
		int i = userService.updatePassword(userId,newPassword, newSecretPassword);
		if (i > 0) {
			session.setAttribute("password", newSecretPassword);
			return YfslResult.ok("修改成功");
		}
		return YfslResult.fail("修改失败");
	}

	@ApiOperation(value = "下载模板", httpMethod = "GET", notes = "下载模板")
	@RequestMapping(value = "/downLoad", method = RequestMethod.GET)
	public void downLoad(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String[] headers = { "人员id", "姓名", "电话"};// 导出的Excel头部，根据自己项目修改
		List<HisiUservo> dataset=userVoService.selectAll();
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 18);
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			HisiUservo t = (HisiUservo) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;

					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						textValue = sdf.format(date);
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}

					HSSFRichTextString richString = new HSSFRichTextString(
							textValue);
					HSSFFont font3 = workbook.createFont();
					font3.setColor(HSSFColor.BLUE.index);// 定义Excel数据颜色
					richString.applyFont(font3);
					cell.setCellValue(richString);

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;filename=User.xls");// 默认Excel名称
		response.flushBuffer();
		workbook.write(response.getOutputStream());
	}
	@ApiOperation(value = "导入模板", httpMethod = "POST", notes = "导入模板")//, headers = "Content-Type=multipart/form-data"
	@RequestMapping(value = "/upLoad", method = RequestMethod.POST)
	//@ApiParam(name="file",value="文件",required=true)@RequestParam MultipartFile file,
	public YfslResult upLoad(HttpServletRequest request) throws Exception {
		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		System.out.println("upLoad:"+session.getId());
		Object departmentId1 = session.getAttribute("departmentId");
		int departmentId=(int)departmentId1;
		String name=departmentService.getName(departmentId);
		if(file==null){
			return YfslResult.fail("文件不能为空");
		}
		String fileName = file.getOriginalFilename();
		if(!ExcelImportUtils.validateExcel(fileName)){
			return YfslResult.fail("文件必须是excel格式");
		}
		long size = file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			return YfslResult.fail("文件不能为空");
		}
		return userService.batchImport(fileName,file,name);
	}
	@ApiOperation(value = "删除员工", httpMethod = "GET", response = YfslResult.class, notes = "删除员工")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public YfslResult deleteUser(
			@ApiParam(name = "id", value = "主键id", required = true) @RequestParam Integer id) {
		int i=userService.deleteUser(id);
		if(i>0){
			return YfslResult.ok("删除成功");
		}
		return YfslResult.fail("删除失败");
	}
	@ApiOperation(value = "屏蔽员工", httpMethod = "GET", response = YfslResult.class, notes = "屏蔽员工")
	@RequestMapping(value = "/disabledUser", method = RequestMethod.GET)
	public YfslResult disabledUser(@ApiParam(name = "id", value = "主键id", required = true) @RequestParam Integer id){
		int i=userService.disabledUser(id);
		if(i>0){
			return YfslResult.ok("屏蔽成功");
		}
		return YfslResult.fail("屏蔽失败");
	}
	@ApiOperation(value = "解屏蔽员工", httpMethod = "GET", response = YfslResult.class, notes = "解屏蔽员工")
	@RequestMapping(value = "/undisabledUser", method = RequestMethod.GET)
	public YfslResult undisabledUser(@ApiParam(name = "id", value = "主键id", required = true) @RequestParam Integer id){
		int i=userService.undisabledUser(id);
		if(i>0){
			return YfslResult.ok("解屏蔽成功");
		}
		return YfslResult.fail("解屏蔽失败");
	}
	@ApiOperation(value = "重置密码", httpMethod = "GET", response = YfslResult.class, notes = "重置密码")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public YfslResult resetPassword(@ApiParam(name = "userId", value = "员工id", required = true) @RequestParam String userId){
		int i=userService.resetPassword(userId);
		if(i>0){
			return YfslResult.ok("重置成功");
		}
		return YfslResult.fail("重置失败");
	}
	@ApiOperation(value = "根据多项条件筛选人员",httpMethod="GET",response=YfslResult.class,notes = "根据多项条件筛选人员")
	@RequestMapping(value="/findByConditions",method=RequestMethod.GET)
	public YfslResult findByConditions(@RequestParam(required = true) @ApiParam("页数，首页为1") int pageNum,
			@RequestParam(required = true) @ApiParam("每页数据条数") int pageSize,
			@RequestParam(required = true) @ApiParam("多条件") String conditions,
			@RequestParam(required = true) @ApiParam("分组名") String groupName,
			@RequestParam(required = true) @ApiParam("角色名") String roleName) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Object departmentId1 = session.getAttribute("departmentId");
		if(!departmentId1.equals(null)){
			//可能没有setAttribute
		int departmentId=(int) departmentId1;
		List<HisiUser> hisiUsers=userService.findByConditions(conditions,pageNum,pageSize,groupName,roleName,departmentId);
		for(int i=0;i<hisiUsers.size();i++){
			String savepath = hisiUsers.get(i).getSavepath();
			if(savepath!=null){
				savepath=winFtp+savepath;
				hisiUsers.get(i).setSavepath(savepath);
			}
		}
		return YfslResult.ok(new PageInfo<HisiUser>(hisiUsers));
		}
		return YfslResult.fail("获取失败");
	}
	@ApiOperation(value = "获取人员列表",httpMethod="GET",response=YfslResult.class,notes = "获取人员列表")
	@RequestMapping(value="/findAllUsers",method=RequestMethod.GET)
	public YfslResult findAllUsers(@RequestParam(required = true) @ApiParam("页数，首页为1") int pageNum,
			@RequestParam(required = true) @ApiParam("每页数据条数") int pageSize) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Object departmentId1 = session.getAttribute("departmentId");
		if(!departmentId1.equals(null)){
			//可能没有setAttribute
		int departmentId=(int) departmentId1;
		List<HisiUser> hisiUsers=userService.findAllUsers(pageNum,pageSize,departmentId);
		for(int i=0;i<hisiUsers.size();i++){
			String savepath = hisiUsers.get(i).getSavepath();
			if(savepath!=null){
				savepath=winFtp+savepath;
				hisiUsers.get(i).setSavepath(savepath);
			}
		}
		return YfslResult.ok(new PageInfo<HisiUser>(hisiUsers));
		}
		return YfslResult.fail("获取失败");
	}		
}
