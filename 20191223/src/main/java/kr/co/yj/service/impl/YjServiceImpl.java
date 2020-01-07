package kr.co.yj.service.impl;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.yj.dao.YjDao;
import kr.co.yj.service.YjService;

@Service("service")
public class YjServiceImpl implements YjService {

	@Resource(name="dao")
	private YjDao yjDao;

	@Override
	public Map<String, Object> idchk(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.idchk(map);
	}

	@Override
	public Map<String, Object> passchk(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.passchk(map);
	}

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.list(map);
	}

	@Override
	public int save(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.save(map);
	}

	@Override
	public int historysave(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.historysave(map);
	}

	@Override
	public int seq() {
		// TODO Auto-generated method stub
		return yjDao.seq();
	}

	@Override
	public int seqqq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.seqqq(map);
	}

	@Override
	public int payment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.payment(map);
	}

	@Override
	public int repayment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.repayment(map);
	}

	@Override
	public int historypayment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.historypayment(map);
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.detail(map);
	}

	@Override
	public List<Map<String, Object>> historydetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.historydetail(map);
	}

	@Override
	public int reSave(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.reSave(map);
	}

	@Override
	public int back(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.back(map);
	}

	@Override
	public int historyback(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.historyback(map);
	}



	@Override
	public List<Map<String, Object>> proxy(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.proxy(map);
	}

	@Override
	public String proxy2(String proxyId) {
		// TODO Auto-generated method stub
		return yjDao.proxy2(proxyId);
	}

	@Override
	public Map<String, Object> proxy3(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.proxy3(map);
	}

	@Override
	public int proxy4(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.proxy4(map);
	}

	@Override
	public Map<String, Object> proxyone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.proxyone(map);
	}

	@Override
	public int sing(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yjDao.sing(map);
	}

}
