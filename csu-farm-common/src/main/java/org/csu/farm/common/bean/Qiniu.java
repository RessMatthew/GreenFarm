

package org.csu.farm.common.bean;

import org.csu.farm.common.enums.QiniuZone;
import lombok.Data;

/**
 * 七牛云存储配置信息
 */
@Data
public class Qiniu {

	private String accessKey;

	private String secretKey;

	private String bucket;

	private String resourcesUrl;

	private QiniuZone zone;
}
