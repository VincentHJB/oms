package com.feifang.oms.utils;


import com.feifang.oms.Setting;
import com.feifang.oms.dao.entity.CommonAttachImg;
import com.feifang.oms.dao.rep.CommonAttachImgRep;
import com.feifang.oms.model.FileUploadType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class FileUploadUtil {
	
	@Autowired
    private Setting setting;

	@Autowired
	private CommonAttachImgRep commonAttachImgRep;
	
	private static String USER_AVATAR = "USER_AVATAR";
	
	
	public  String uploadFileByReq(HttpServletRequest req, Integer orderId,
								   Integer uid, Integer type, String uploadType){
		String inDBPath = "";
		String finalDBPath = "";
		//创建一个多部分解析器
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());
		//判断req是否有上传文件
		if(commonsMultipartResolver.isMultipart(req)){
			//转成多部分的request
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			//取得request的所有名
			List<MultipartFile> file = multipartRequest.getFiles(uploadType);
			for(int i = 0;i<file.size();i++){  
                MultipartFile tempFile = file.get(i);
                //保存文件  
               String in = saveFile(tempFile,orderId,uid,type);
               inDBPath += in; 
            }  
		}
		if(StringUtils.isNotBlank(inDBPath) && StringUtils.endsWith(inDBPath, ","))
			finalDBPath = StringUtils.chop(inDBPath);
		return finalDBPath;
		}
	
	public String saveFile(MultipartFile file, Integer orderId, Integer uid, Integer type) {
		if(StringUtils.isBlank(file.getOriginalFilename()))
			return "";
		
		String path = "";
		String virPath = "";
		
		String postfix = this.getPoxtfix(file.getOriginalFilename());
		String fileName = MD5Util.md5(file.getOriginalFilename() + file.getSize()) + "." + postfix;

		//真实路径
		if(type == FileUploadType.HOTEL_ROOM.getValue()){
			path = setting.User_Upload_Avtar_Base_Dir + "/" + uid + "/" + orderId + "/"+  DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		}else if(type == FileUploadType.HOTEL_BANNER.getValue()){
			path = setting.User_Upload_Avtar_Base_Dir + "/order_img/" + uid ;
		}

		//虚拟路径
		if(type == FileUploadType.HOTEL_ROOM.getValue()){
			virPath= setting.User_Upload_Avtar_Base_Virt + "/" + uid + "/" + orderId + "/"+ DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/"+ fileName;
		}else if(type == FileUploadType.HOTEL_BANNER.getValue()){
			virPath= setting.User_Upload_Avtar_Base_Virt + "/order_img/" + uid + "/"+ fileName;
		}
        
        String realPath = path + "/" + fileName;
        String url = setting.User_Upload_Avtar_Domain + virPath;
        
        CommonAttachImg attachImg = new CommonAttachImg();

        attachImg.setDomain(setting.User_Upload_Avtar_Domain);
        attachImg.setFileName(fileName);
        attachImg.setModule(USER_AVATAR);
        attachImg.setRealPath(realPath);
        attachImg.setVirtualPath(virPath);
        attachImg.setPostfix(postfix);
        attachImg.setOrderId(orderId);
        attachImg.setUrl(url);
        attachImg.setSize(file.getSize());
		commonAttachImgRep.save(attachImg);
        
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	File dirPath = new File(path);
                if(!dirPath .exists()  && !dirPath .isDirectory()){
                    dirPath.mkdirs();
                }
                try {
                	System.out.println("开始上传");
                    BufferedOutputStream out =
                            new BufferedOutputStream(
                                    new FileOutputStream(new File(path, fileName)));
                    out.write(file.getBytes());
                    System.out.println("文件上传完");
                    out.flush();
                    out.close();
                    return url+",";  
                }catch (IOException e){
                	e.printStackTrace();
                }
                // 转存文件  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return "";  
    }  
	
	 private String getPoxtfix(String allName){
	        String[] postfixs = allName.split("\\.");
	        if(postfixs.length <= 1){
	            return "png";
	        }else{
	            return postfixs[postfixs.length - 1];
	        }
	    }

}
