

package org.csu.farm.service;

import java.io.IOException;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.AttachFile;

/**
 *
 * Created by lgh on 2018/07/27.
 */
public interface AttachFileService extends IService<AttachFile> {

	String uploadFile(byte[] bytes,String originalName) throws IOException;
	
	void deleteFile(String fileName);
}
