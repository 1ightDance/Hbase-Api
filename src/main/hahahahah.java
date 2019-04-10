package hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
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
import org.apache.hadoop.yarn.webapp.example.HelloWorld.Hello;
/*
 * 
 * ��ʹ�������֮����ʹ��Breakconnection() �����Ͽ�����
 * 
 * 
 * 
 * 
 * */
public class HBaseBasicServiceImpl implements HBaseBasicService {
	  Configuration conf;   
	  Connection connection; 
	  HBaseAdmin hba ;
    public HBaseBasicServiceImpl() throws Exception {
    	 conf=HBaseConfiguration.create();
		   
			conf.set("hbase.zookeeper.quorum","47.107.174.124,47.107.166.42,119.23.249.129,120.77.223.42");  //�ڵ����
	        conf.set("hbase.zookeeper.property.clientPort","2181");
	        connection=ConnectionFactory.createConnection(conf);
	       
	       
	} 
  /* public Connection HBaseConnect() throws IOException {  //����Ĺ����Ǹ���������ֱ�ӷ���connection 
			Configuration conf=HBaseConfiguration.create();
		   // conf.set("hbase.master", "47.107.174.124:60000");
			conf.set("hbase.zookeeper.quorum"," 47.107.174.124,47.107.166.42,119.23.249.129,120.77.223.42");  //�ڵ����
	        conf.set("hbase.zookeeper.property.clientPort","2181");
	        connection=ConnectionFactory.createConnection(conf);
	      //  hba = new HBaseAdmin(conf);
	    
	        return connection;
		}*/
   
    public void Breakconnection() throws IOException {  //����ɲ�����Ҫ�Ͽ�����
   	// hba.close();
		 connection.close(); 
	}

	@Override
	public Result getdata(String tableName, String rowkey, String cf, String cloumn) throws Exception {
		 Table tName = connection.getTable(TableName.valueOf(tableName)); //ָ������
	     	Get get = new Get(Bytes.toBytes(rowkey)); //ָ���м�
	     	get.addColumn(cf.getBytes(), cloumn.getBytes());//ָ����
	     	System.out.println("hello in getdata");
	        Result result = tName.get(get);
		return result;
	}

	@Override
	public boolean putdata(String tableName, String rowkey, String cf, String cloumn, String value) throws Exception {
		//������
		 try{
			 Table tName = connection.getTable(TableName.valueOf(tableName));
		     org.apache.hadoop.hbase.client. Put put = new org.apache.hadoop.hbase.client. Put(Bytes.toBytes(rowkey));
	         put.add(cf.getBytes(),cloumn.getBytes(),value.getBytes());
	         tName.put(put);
	         return true;
	        }catch (Exception e) {
				return false;
			}
		
	}

	@Override
	public boolean delData(String tableName, String rowkey, String cf, String cloumn)throws Exception {
		//ɾ����
	    try {
	    	Table tName= connection.getTable(TableName.valueOf(tableName));
	 	    Delete delete =new Delete(rowkey.getBytes());
	 	    delete.deleteColumn(cf.getBytes(), cloumn.getBytes());
	 	    tName.delete(delete);
	    	return true;
	    }catch (Exception e) {
			return false;
		}
	
	}

	@Override
	public boolean delRow(String tableName, String rowkey)throws Exception {
		//ɾ��һ����
		try {
			 Table table= connection.getTable(TableName.valueOf(tableName));
			 Delete delete =new Delete(Bytes.toBytes(rowkey));
			  table.delete(delete);
			  return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delfamily(String tableName, String rowkey, String cf) throws Exception{
		//ɾ��ĳһ��
		try {
			   Table table= connection.getTable(TableName.valueOf(tableName));
			   Delete delete =new Delete(rowkey.getBytes());
			   delete.deleteFamily(cf.getBytes());
			   table.delete(delete);
			   return false;
		}catch (Exception e) {
			return true;
		}
		
	}

	@Override
	public ResultScanner scantable(String tableName) throws Exception {
		//ɨ�����ű�
		TableName tName = TableName.valueOf(tableName);
    	//if(hba.tableExists(tName)) 
    	Scan scan=new Scan();
        HTable scTable = (HTable) connection.getTable(tName);
        ResultScanner rs = ((HTable) scTable).getScanner(scan);
        
	    return rs;
	}

	@Override
	public ResultScanner scantable(String tableName, String startrowkey, String stoprowkey)throws Exception {
		//������ʼ�к�ֹͣ��ɨ��
		TableName tName = TableName.valueOf(tableName);
    	
        Scan scan=new Scan(startrowkey.getBytes(), stoprowkey.getBytes());
        HTable scTable = (HTable) connection.getTable(tName);
        ResultScanner rs = ((HTable) scTable).getScanner(scan);
		return rs;
	}

	@Override
	public ResultScanner scantable(String tableName, String startrowkey)throws Exception {
		//������ʼ�м�ɨ������β
		TableName tName = TableName.valueOf(tableName);
    	Scan scan=new Scan(startrowkey.getBytes());
        HTable scTable = (HTable) connection.getTable(tName);
        ResultScanner rs = ((HTable) scTable).getScanner(scan);
        return rs;
            
	}

	@Override
	public long count(String tableName)throws Exception {
		//ͳ������
		 Table table = connection.getTable(TableName.valueOf(tableName));
		   Scan scan =new Scan(); 
		   long rowCount = 0;
		   scan.setFilter(new FirstKeyOnlyFilter());
		   ResultScanner resultScanner = table.getScanner(scan);
		   for (Result result : resultScanner) {
			   rowCount += result.size();
		   	}
		   return rowCount;
	}

	@Override
	public ResultScanner FamilyFilter(String tableName, String cf) throws Exception{
		//���������
		Table table = connection.getTable(TableName.valueOf(tableName));
    	Scan scan =new Scan();
    	FamilyFilter filter1= new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(cf)));
    	scan.setFilter(filter1);
    	ResultScanner scanner = table.getScanner(scan);
	    return scanner;
	}

	@Override
	public ResultScanner QualifierFilter(String tableName, String column)throws Exception {
		//�й�����
		Table table = connection.getTable(TableName.valueOf(tableName));
    	Scan scan =new Scan();
    	QualifierFilter filter = new QualifierFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(column)));
    	scan.setFilter(filter);
    	ResultScanner scanner = table.getScanner(scan);
    	return scanner;
	}

	@Override
	public ResultScanner RowFilter(String tableName, String row) throws Exception{
		//�м�������
		Table table = connection.getTable(TableName.valueOf(tableName));
    	Scan scan =new Scan();
    	RowFilter filter = new RowFilter(CompareOp.LESS_OR_EQUAL,  new BinaryComparator(Bytes.toBytes(row)));
    	scan.setFilter(filter);
    	ResultScanner scanner = table.getScanner(scan);
    	return scanner;
	}

	@Override
	public ResultScanner PrefixFilter(String tableName, String reg) throws Exception{
		
		Table table = connection.getTable(TableName.valueOf(tableName));
    	Scan scan =new Scan();
    	PrefixFilter filter = new PrefixFilter(Bytes.toBytes(reg));
    	scan.setFilter(filter);
    	ResultScanner scanner = table.getScanner(scan);
		return null;
	}

	@Override
	public ResultScanner SkipFilter(String tableName, String cloumn)  throws Exception{
		Table table = connection.getTable(TableName.valueOf(tableName));		
		Scan scan = new Scan();		
		Filter filter = new SkipFilter(new ValueFilter(CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("102"))));
		//Filter filter = new SkipFilter(new DependentColumnFilter(Bytes.toBytes("course"), Bytes.toBytes("art"),false,CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("90"))));		
		//�ù�������Ҫ���������������ʹ��		s
		scan.setFilter(filter);	
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
		
	}

	@Override
	public ResultScanner TimestampsFilter(String tableName, List<Long> Timestamps) throws Exception {
		//ʱ���
		Table table = connection.getTable(TableName.valueOf(tableName));	
		Scan scan = new Scan();	
	    Filter filter = new TimestampsFilter(Timestamps);		
		scan.setFilter(filter);	
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
}

	@Override
	public ResultScanner TimestampsFilter(String tableName, Long Timestamps) throws Exception {
		Table table = connection.getTable(TableName.valueOf(tableName));	
		Scan scan = new Scan();	
			//ls�д��������Ҫ����ƥ���ʱ���		
		List<Long> ls = new ArrayList<Long>();		
		ls.add(Timestamps);
		//java���Ե����ͳ���Ĭ��Ϊint�ͣ�����long�ͳ������Ժ�ӡ�l����L��		
		Filter filter = new TimestampsFilter(ls);		
		scan.setFilter(filter);	
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
	}

	@Override
	public ResultScanner WhileMatchbyrowkeyFilter(String tableName, String rowkey) throws Exception {
		Table table = connection.getTable(TableName.valueOf(tableName));	
		Scan scan = new Scan();	
		Filter filter = new WhileMatchFilter(new RowFilter(CompareOp.LESS_OR_EQUAL,  new BinaryComparator(Bytes.toBytes(rowkey))));		
		scan.setFilter(filter);		
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
	}

	@Override
	public ResultScanner WhileMatchbycolumnFilter(String tableName, String column) throws Exception{
		Table table = connection.getTable(TableName.valueOf(tableName));	
		Scan scan = new Scan();	
		Filter filter = new WhileMatchFilter( new QualifierFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(column))));		
		scan.setFilter(filter);		
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
	}

	@Override
	public ResultScanner WhileMatchbyvalueFilter(String tableName, String value) throws Exception {

		Table table = connection.getTable(TableName.valueOf(tableName));	
		Scan scan = new Scan();	
		Filter filter = new WhileMatchFilter(new ValueFilter(CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes(value))));		
		scan.setFilter(filter);		
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
		
	}

	@Override
	public ResultScanner ValueFilter(String tableName, String value) throws Exception {
		//ֵ������
		Table table = connection.getTable(TableName.valueOf(tableName));
		   Scan scan =new Scan(); 
		   ValueFilter filter = new ValueFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(value)));
		   scan.setFilter(filter);
		   ResultScanner scanner = table.getScanner(scan);
		   return scanner;
	}
	
	

}
