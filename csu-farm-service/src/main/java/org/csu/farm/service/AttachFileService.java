

package org.csu.farm.service;

import java.io.IOException;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.AttachFile;


public interface AttachFileService extends IService<AttachFile> {

	String uploadFile(byte[] bytes,String originalName) throws IOException;
	
	void deleteFile(String fileName);
}
