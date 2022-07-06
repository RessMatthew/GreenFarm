

package org.csu.farm.service;

import org.csu.farm.bean.app.dto.ProductItemDto;
import org.csu.farm.bean.model.UserAddr;

public interface TransportManagerService {

	Double calculateTransfee(ProductItemDto productItem, UserAddr userAddr);
}
