package com.hww.JComPZ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hww.dao.Dao;
import com.hww.model.BookType;

public class MapPz {
	private static Map<String, BookType> map = new HashMap();
	
	public static Map getMap(){
		List<BookType> list = Dao.selectBookCategory();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getTypeID(), list.get(i));
		}
		return map;
	}
	

}
