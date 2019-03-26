package com.tensor.api.org.service.hbase;

import com.google.gson.JsonObject;
import org.apache.hadoop.hbase.client.Result;

public interface HBaseBasicService {
	 
	
	//1.���ݴ���ı��� �м� ������ ���� �������� 
	Result getdata(String tableName,String rowkey,String cf,String cloumn); 
	//2.���ݴ���� ���� �м� ������ ���� �����ݲ������ݿ� �����Ƿ�ɹ�
	boolean putdata(String tableName,String rowkey,String cf,String cloumn,String value);
	//3.���ݴ���� ���� �м� ������ ���� ɾ������ �����Ƿ�ɹ�
	boolean delData(String tableName,String rowkey,String cf,String cloumn);
	//4.���ݴ���� ���� �м� ɾ��ĳһ������ �����Ƿ�ɹ�
	boolean delRow(String tableName,String rowkey);
	//5.���ݴ���ı��� �м�  ���� ɾ��ĳһ����
	boolean delfamily(String tableName,String rowkey,String cf);
                //6.���ݴ���ı��� ɨ�����ű�
	 Result scantable(String tableName);
	//7.���ݴ���ı��� ��ʼ�м� ��ֹ�м� ����ɨ��
	 Result scantable(String tableName ,String startrowkey ,String stoprowkey);
	//8.���ݴ���ı��� ��ʼ�м� ����ɨ��
	 Result scantable(String tableName ,String startrowkey);
	 //9.ͳ������
	 long count();
	 //10.���ݴ���ı��� ������ ���ҷ����������� ���� �м�-����-����-����
	 Result FamilyFilter(String tableName,String cf);
	 //11.���� ����ı���  ���� ���ҷ��������������� �����м�-����-����-����
	 Result  QualifierFilter(String tableName ,String clomun);
	 //12.���ݴ���� ���� �м��� ���ط��ϵ��м� ���� ��������
	 Result RowFilter(String tableName,String row );
	 //13.ȡ��rowkey��ָ��prefix��ͷ�������� ���� ��Щ�е�ȫ������
	 Result PrefixFilter(String tableName, String reg);
	 //14.���������е�ÿ�����������ˣ�ֻҪ����һ�в��������������ж������˵������ط����е�ȫ������
	 Result SkipFilter(String tableName,String cloumn);
	 //15.��ʱ������� ���ط����е�ȫ������
	  Result TimestampsFilter(String tableName,List<Long>Timestamps );
	  Result TimestampsFilter(String tableName,Long Timestamps );
	  //16.һ������һ�����Ϲ������������ݣ���ֹͣɨ��  ���ط����е�ȫ������
	      //������
	       Result WhileMatchbyrowkeyFilter(String tableName,String rowkey);
	      //������
	       Result  WhileMatchbycolumnFilter(String tableName,String column);
	      //����ֵ
	      Result  WhileMatchbyvalueFilter(String tableName ,String value);
	  
	
}
