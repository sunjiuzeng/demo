package com.demo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvUtil {
	
	public static void main(String[] args) throws IOException {
		String file = "D:/ljq.csv";
		
		List<Map<String, String>> list = readCSVFile(new File(file));
		
		for (Map<String, String> map : list) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				
				System.out.print(entry.getKey() + ":" + entry.getValue()+",");
			}
			
			System.out.println();
		}
	}

	/**
	 * 导入
	 *
	 * @param file
	 *            csv文件(路径+文件)
	 * @return
	 */
	public static List<String> importCsv(File file) {
		List<String> dataList = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dataList;
	}

	/**
	 * 导出
	 *
	 * @param file
	 *            csv文件(路径+文件名)，csv文件不存在会自动创建
	 * @param dataList
	 *            数据
	 * @return
	 */
	public static boolean exportCsv(File file, List<String> dataList) {
		boolean isSucess = false;

		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSucess;
	}

	public static List<Map<String, String>> readCSVFile(File file) throws IOException {
		
		InputStream inputStream = new FileInputStream(file);
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String rec = null;// 一行
		String str;// 一个单元格
		
		List<Map<String, String>> csvData = new ArrayList<Map<String, String>>();
		boolean flag = true;
		List<String> titles = new ArrayList<String>();
		Map<String, String> map = null;

		try {
			// 读取一行
			while ((rec = bufferedReader.readLine()) != null) {
				Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,|(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*");
				Matcher mCells = pCells.matcher(rec);
				
				List<String> cells = new ArrayList<String>();// 每行记录一个list
				
				// 读取每个单元格
				while (mCells.find()) {
					str = mCells.group();
					str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
					str = str.replaceAll("(?sm)(\"(\"))", "$2");
					
					if("".equals(str) || str == null){
						continue;
					}
					
					str = str.replaceAll("^\"|\"$", "");
					
					cells.add(str);
				}
				
				if(flag){
					titles.addAll(cells);
					flag = false;
				}else{
					map = new HashMap<String, String>();
					
					for (int i = 0; i < cells.size(); i++) {
						map.put(titles.get(i), cells.get(i));
					}
					
					csvData.add(map);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			
		}
		return csvData;
	}

}
