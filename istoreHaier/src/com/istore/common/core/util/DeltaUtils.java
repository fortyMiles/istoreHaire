package com.istore.common.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.mapper.CatalogMapper;

/**
 * @ClassName: DeltaDaoImpl
 * @Description: 增量索引
 * @author jiangtao
 * 
 */
/**
 * 增量索引
 */
@Service
@Transactional
public class DeltaUtils {

	@Autowired
	CatalogMapper mapper;

	/**
	 * deltaBuild(获取某个目录下的属性,关联表catgroupattrrel)
	 * 
	 * @param @param id
	 * @param @param model
	 * @return void 返回类型
	 */
	public void deltaBuild(Long id, String model) {

		if ("catentry".equals(model)) {

			String count_sql = "select count(1) from ti_delta_catentry where catentry_id=#{id} and mastercatalog_id=10001";
			Integer count = mapper.countDelta(id, count_sql);
			if (count == 0) {
				String insert_sql = "INSERT INTO TI_DELTA_CATENTRY(MASTERCATALOG_ID,CATENTRY_ID,ACTION) VALUES(10001,#{id},'F')";
				mapper.insertDelta(id, insert_sql);
			}
		}
		if ("catgroup".equals(model)) {
			String count_sql = "select count(1) from ti_delta_catgroup where catgroup_id=#{id} and mastercatalog_id=10001";
			Integer count = mapper.countDelta(id, count_sql);
			if (count == 0) {
				String insert_sql = "insert into TI_DELTA_CATGROUP(MASTERCATALOG_ID,CATGROUP_ID,ACTION) values(10001,#{id},'F')";
				mapper.insertDelta(id, insert_sql);
			}
		}

	}
}
