package com.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.models.Egm;

public interface IExcelGateway {

	public HashMap<String,LinkedList<Egm>>getEgmMap(String filePath,String sheetName) throws Exception ;
}
