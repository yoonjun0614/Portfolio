package kr.co.yj.service;

import java.util.List;
import java.util.Map;

public interface YjService {

	Map<String, Object> idchk(Map<String, Object> map);

	Map<String, Object> passchk(Map<String, Object> map);

	List<Map<String, Object>> list(Map<String, Object> map);

	int save(Map<String, Object> map);

	int historysave(Map<String, Object> map);

	int seq();

	int seqqq(Map<String, Object> map);

	int payment(Map<String, Object> map);

	int repayment(Map<String, Object> map);

	int historypayment(Map<String, Object> map);

	Map<String, Object> detail(Map<String, Object> map);

	List<Map<String, Object>> historydetail(Map<String, Object> map);

	int reSave(Map<String, Object> map);

	int back(Map<String, Object> map);

	int historyback(Map<String, Object> map);

	List<Map<String, Object>> proxy(Map<String, Object> map);

	String proxy2(String proxyId);

	Map<String, Object> proxy3(Map<String, Object> map);

	int proxy4(Map<String, Object> map);

	Map<String, Object> proxyone(Map<String, Object> map);

	int sing(Map<String, Object> map);



}
