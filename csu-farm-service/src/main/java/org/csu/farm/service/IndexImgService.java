

package org.csu.farm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.farm.bean.model.IndexImg;

import java.util.List;


public interface IndexImgService extends IService<IndexImg> {

	void deleteIndexImgsByIds(Long[] ids);

    List<IndexImg> listIndexImgs();

    void removeIndexImgCache();
}
