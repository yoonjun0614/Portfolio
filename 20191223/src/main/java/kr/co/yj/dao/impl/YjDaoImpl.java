package kr.co.yj.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.yj.dao.YjDao;


@Repository("dao")
public class YjDaoImpl implements YjDao {


	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Map<String, Object> idchk(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.idchk", map);
	}

	@Override
	public Map<String, Object> passchk(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.passchk", map);
	}

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("mapper.list", map);
	}

	@Override
	public int save(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.save", map);
	}

	@Override
	public int historysave(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.historysave", map);
	}

	@Override
	public int seq() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.seq");
	}

	@Override
	public int seqqq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.seqqq", map);
	}

	@Override
	public int payment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.payment", map);
	}

	@Override
	public int repayment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.repayment", map);
	}

	@Override
	public int historypayment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.historypayment", map);
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.detail", map);
	}

	@Override
	public List<Map<String, Object>> historydetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("mapper.historydetail", map);
	}

	@Override
	public int reSave(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("mapper.resave", map);
	}

	@Override
	public int back(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("mapper.back", map);
	}

	@Override
	public int historyback(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("mapper.historyback", map);
	}

	@Override
	public List<Map<String, Object>> proxy(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("mapper.proxylist", map);
	}

	@Override
	public String proxy2(String proxyId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.proxy2", proxyId);
	}

	@Override
	public Map<String, Object> proxy3(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.proxy3", map);
	}

	@Override
	public int proxy4(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.proxy4", map);
	}

	@Override
	public Map<String, Object> proxyone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("mapper.proxyone", map);
	}

	@Override
	public int sing(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("mapper.sing", map);
	}
	
}
