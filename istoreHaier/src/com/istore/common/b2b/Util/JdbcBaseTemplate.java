package com.istore.common.b2b.Util;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public abstract class JdbcBaseTemplate <T>{
	
	
	protected abstract Class<T> getEntityClass();
	
	
	protected JdbcTemplate jdbcTemplate;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/****
	 * 更新插入数据
	 * @param sql
	 * @param args
	 */
	public int update(String sql,Object[] args){
		return this.jdbcTemplate.update(sql, args);
		
	}
	

	/***
	 * 批量更新
	 * @param sqls
	 * @return
	 */
	public int[] batchUpdate(String[] sqls){
		return this.jdbcTemplate.batchUpdate(sqls);
	}
	
	
	/***
	 * 条件查询
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @return
	 */
	public  List<T> findByObject(String sql,Object[] args,RowMapper<T> rowMapper){
		return this.jdbcTemplate.query(sql, args, rowMapper);
	}
	
	
	/***
	 * 查询结果数量
	 * @param sql
	 * @param args
	 * @return
	 */
	public int findRows(String sql,Object[] args){
		 return this.jdbcTemplate.queryForInt(sql, args);
	}
	
	
	/***
	 * 查询单个对象
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @return
	 */
	public T get(String sql,Object[] args,RowMapper<T> rowMapper)throws EmptyResultDataAccessException{
		return jdbcTemplate.queryForObject(sql, args ,rowMapper);
		
	}
	
	
	/***
	 * 查询单个对象值
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<?, ?> findForMap(String sql,Object[] args) throws EmptyResultDataAccessException{
		return jdbcTemplate.queryForMap(sql, args);
		
	}
	
}
