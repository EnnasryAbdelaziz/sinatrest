package eai.devass.commun.appli.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ResMapEdit  {

	private List<Map<String, Object>> listRes = null;

	public ResMapEdit(List<Map<String, Object>> listRes) {
		this.listRes = listRes;
	}
	
	public boolean isEmpty() {
		if(listRes == null || listRes.isEmpty()) {
			return true;
			
		} else {
			return false;
		}
	}
	
	
	public Long getLong(String key, int index) {
		Map<String, Object> mapRes = listRes.get(index);
		Object value = mapRes.get(key);
		if(value instanceof Long) {
			return (Long) value;
		}
		if(value instanceof BigDecimal) {
			return ((BigDecimal) value).longValue();
		}
		
		return null;
		
	}
	
	public Double getDouble(String key, int index) {
		Map<String, Object> mapRes = listRes.get(index);
		Object value = mapRes.get(key);
		if(value instanceof Long) {
			return ((Long) value).doubleValue();
		}
		if(value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		}
		
		return null;
		
	}
	
	public Date getDate(String key, int index) {
		Map<String, Object> mapRes = listRes.get(index);
		Object value = mapRes.get(key);
		if(value instanceof Date) {
			return (Date) value;
		}
		
		return null;
		
	}
	
	public Integer getInt(String key, int index) {
		Map<String, Object> mapRes = listRes.get(index);
		Object value = mapRes.get(key);
		if(value instanceof Integer) {
			return (Integer) value;
		}
		if(value instanceof BigDecimal) {
			return ((BigDecimal) value).intValue();
		}
		
		return null;
		
	}
	
	public String getString(String key, int index) {
		Map<String, Object> mapRes = listRes.get(index);
		return String.valueOf(mapRes.get(key));
		
	}
	
	public int size() {
		if(listRes == null) {
			return 0;
			
		} else {
			return listRes.size();
		}
	}
	

	public List<Map<String, Object>> getListRes() {
		return listRes;
	}
	
	
	
	
	
	
	

}
