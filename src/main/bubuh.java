package hbase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.text.TabExpander;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.record.compiler.JBoolean;
import org.apache.hadoop.util.GSet;
import org.mortbay.util.ajax.JSON;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.CellUtil;
import com.google.common.collect.Table.Cell;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.filter.WhileMatchFilter;
import org.apache.hadoop.hbase.filter.SkipFilter;
import org.apache.hadoop.hbase.filter.TimestampsFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.hadoop.hbase.client.Result;

public interface HBaseBasicService {
	 
	
	//1.���ݴ���ı��� �м� ������ ���� �������� 
	Result getdata(String tableName,String rowkey,String cf,String column) throws Exception; 
	//2.���ݴ���� ���� �м� ������ ���� �����ݲ������ݿ� �����Ƿ�ɹ�
	boolean putdata(String tableName,String rowkey,String cf,String column,String value)throws Exception;
	//3.���ݴ���� ���� �м� ������ ���� ɾ������ �����Ƿ�ɹ�
	boolean delData(String tableName,String rowkey,String cf,String column)throws Exception;
	//4.���ݴ���� ���� �м� ɾ��ĳһ������ �����Ƿ�ɹ�
	boolean delRow(String tableName,String rowkey)throws Exception;
	//5.���ݴ���ı��� �м�  ���� ɾ��ĳһ����
	boolean delfamily(String tableName,String rowkey,String cf)throws Exception;
    //6.���ݴ���ı��� ɨ�����ű�
	ResultScanner scantable(String tableName)throws Exception;
	//7.���ݴ���ı��� ��ʼ�м� ��ֹ�м� ����ɨ��
	ResultScanner scantable(String tableName ,String startrowkey ,String stoprowkey)throws Exception;
	//8.���ݴ���ı��� ��ʼ�м� ����ɨ��
	ResultScanner scantable(String tableName ,String startrowkey)throws Exception;
	 //9.ͳ������
	 long count(String tableName)throws Exception;
	 //10.���ݴ���ı��� ������ ���ҷ�����������
	 ResultScanner FamilyFilter(String tableName,String cf)throws Exception;
	 //11.���� ����ı���  ���� ���ҷ��������������� 
	 ResultScanner  QualifierFilter(String tableName ,String colmun)throws Exception;
	 //12.���ݴ���� ���� �м��� ���ط��ϵ��м� ���� ��������
	 ResultScanner RowFilter(String tableName,String row )throws Exception;
	 //13.ȡ��rowkey��ָ��prefix��ͷ�������� ���� ��Щ�е�ȫ������
	 ResultScanner PrefixFilter(String tableName, String reg)throws Exception;
	 //14.���������е�ÿ�����������ˣ�ֻҪ����һ�в��������������ж������˵������ط����е�ȫ������
	 ResultScanner SkipFilter(String tableName,String column)throws Exception;
	 //15.��ʱ������� ���ط����е�ȫ������
	 ResultScanner TimestampsFilter(String tableName,List<Long>Timestamps )throws Exception;
	 ResultScanner TimestampsFilter(String tableName,Long Timestamps )throws Exception;
	  //16.һ������һ�����Ϲ������������ݣ���ֹͣɨ��  ���ط����е�ȫ������
	      //������
	 ResultScanner WhileMatchbyrowkeyFilter(String tableName,String rowkey)throws Exception;
	      //������
	 ResultScanner  WhileMatchbycolumnFilter(String tableName,String column)throws Exception;
	      //����ֵ
	 ResultScanner  WhileMatchbyvalueFilter(String tableName ,String value)throws Exception;
	 //17.����valueֵѰ�� ȫ���е�value���ᱻ����
	 ResultScanner  ValueFilter(String tableName,String value) throws Exception;
	
}
