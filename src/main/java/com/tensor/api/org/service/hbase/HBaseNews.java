package com.tensor.api.org.service.hbase;

import org.mortbay.util.ajax.JSON;
import com.google.gson.JsonObject;
import  com.tensor.api.org.enpity.New;
import  com.tensor.api.org.enpity.ResultData;

public interface HBaseNews {
	
	//1.���ݴ���Ĳ��������Ų������ݿ� �����Ƿ�ɹ�
	 ResultData<Boolean>Putnews(New news);
	 //2.��ȡȫ������
	 ResultData<JsonObject>GetallNews();
	 //3.��ȡȫ������ �����м�-����
	 ResultData<JsonObject>GetallText();
	 //4.��ȡȫ������ ���� �м�-����
	 ResultData<JsonObject>GetallAuthor();
	 //5.��ȡȫ������ ���� �м�-����
	 ResultData<JsonObject>GetallTitle();
	 //6.�����м���ȡ���� ���ض�Ӧ����
	 ResultData<JsonObject>Getnewstbyrowkey(String tablename,String rowkey);
	 //7.���ݱ����ȡ����  ���ض�Ӧ����
	 ResultData<JsonObject>Getnewsbytitle(String tablename ,String newtitle);
	 //8.�������߶�����    ���ض�Ӧ����
	 ResultData<JsonObject>Getnewsnyauthor(String tablename,String author);
	 //9.���ݷ��������
	 ResultData<JsonObject>Getnewsbytype(String tablename,String newType);	
}
