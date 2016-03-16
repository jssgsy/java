package com.univ.json;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;


/** 
 * @author: liuml
 * @date: 2015年7月24日 下午6:17:09 
 * @version: 1.0 
 * @description: 测试各种java对象转json对象的例子.
 * 注意导入包的版本问题。很容易发生版本冲突
 * 	json-lib-2.4-jdk15.jar
 * 	jakarta commons-lang 2.5		
 *	jakarta commons-beanutils 1.8.0
 *	jakarta commons-collections 3.2.1
 *	jakarta commons-logging 1.1.1
 *	ezmorph 1.0.6
 *补充：struts2的lib包下有配套的json包，因为struts2支持json。所以包也可以到struts下的lib文件下取。
 */

public class JSonTest {

	 /*一）java对象转json对象：
	  * 主要使用两个类：JSONArray和JSONObject。
	  * JSONArray：将java对象列表转换成json对象时使用，包括集合类，和字符串json数组；
	  * JSONObject：将java对象转换成json对象时使用，包括map,date,自定义类型；
     *  可以将Map理解成一个对象，里面的key/value对可以理解成对象的属性/属性值
     *  即{key1:value1,key2,value2......}
     * 
     * JSONObject是一个name:value集合，通过它的get(key)方法取得的是key后对应的value部分(字符串)
     *         通过它的getJSONObject(key)可以取到一个JSONObject，--> 转换成map,
     *         通过它的getJSONArray(key) 可以取到一个JSONArray 
     */

	//Date类型转JSON
	@Test
	public void testDateToJSON(){
		Date d = new Date();		
        System.out.println(JSONObject.fromObject(d));
        /*{"date":24,"day":5,"hours":19,"minutes":25,"month":6,"seconds":31,"time":1437737131984,
        "timezoneOffset":-480,"year":115}*/
        
        //下面将teacher中的Date类型的属性以年月日的格式转换json对象，便于前台使用
		Teacher teacher = new Teacher();
		teacher.setDate1(new Date());		
//		teacher.setDate2(new Date());
		teacher.setName("teacher");
		
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
        	/*
        	 * key: 要解析成json对象的java对象(这里是teacher)的类型为Date的属性
            *  value: 要解析成json对象的java对象(这里是teacher)的类型为Date的属性值
            *  return:要解析成json对象的java对象(这里是teacher)的类型为Date的属性处理后的值(如进行格式化)
            */
			public Object processObjectValue(String key, Object value,
					JsonConfig jsonConfig) {	
				if (value ==null) {//Date类型的属性值有可能为null
					return null;
				}
				Date date = (Date) value;
				return DateFormat.getDateInstance().format(date);		
			}
			
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				// 这里要解析的Teacher对象中没有需要特殊处理的数组属性，因此这里直接返回null
				return null;
			}
		});
        System.out.println(JSONObject.fromObject(teacher, jsonConfig));
        
	}
	
	 //一般数组转换成JSON,数组用JSONArray
    @Test
    public void testArrayToJSON(){
        boolean[] boolArray = new boolean[]{true,false,true};  
        JSONArray jsonArray = JSONArray.fromObject( boolArray );  
        System.out.println( jsonArray );  
        // prints [true,false,true]  
    }    
    
    //Collection对象转换成JSON，Collection用JSONArray
    @Test
    public void testListToJSON(){
        List<String> list = new ArrayList<String>();  
        list.add( "first" );  
        list.add( "second" );  
        JSONArray jsonArray = JSONArray.fromObject( list );  
        System.out.println( jsonArray.toString() );  
        //["first","second"]  
    }
    
    
    //字符串 	json数组 	转换成json， 根据情况是用JSONArray或JSONObject
    @Test
    public void testJsonStrToJSON(){
        JSONArray jsonArray = JSONArray.fromObject( "['json','is','easy']" );  
        System.out.println( jsonArray );  
        //["json","is","easy"]  
    }
    
    //普通类型的 	json对象		 转换成对象
    @Test
    public void testJSONToObject() throws Exception{
        String json = "{name=\"json\",bool:true,int:1,double:2.2,array:[1,2]}";  
        JSONObject jsonObject = JSONObject.fromObject( json ); 
        System.out.println(jsonObject);
        //{"name":"json","bool":true,"int":1,"double":2.2,"array":[1,2]}
        
        Object bean = JSONObject.toBean( jsonObject );         
        System.out.println(PropertyUtils.getProperty(bean, "name"));
        System.out.println(PropertyUtils.getProperty(bean, "bool"));
        System.out.println(PropertyUtils.getProperty(bean, "int"));
        System.out.println(PropertyUtils.getProperty(bean, "double"));
        System.out.println(PropertyUtils.getProperty(bean, "array"));
        /*
        json
        true
        1
        2.2
        [1, 2]
        */           
    }
    
    //Map转换成json， 是用jsonObject
    @Test
    public void testMapToJSON(){
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put( "name", "json" );  
        map.put( "bool", Boolean.TRUE );  
        map.put( "int", new Integer(1) );  
        map.put( "arr", new String[]{"a","b"} );                    
        JSONObject jsonObject = JSONObject.fromObject( map );  
        System.out.println( jsonObject );  
        //{"arr":["a","b"],"int":1,"name":"json","bool":true}
    }
    
    //复合类型bean转成json
    @Test
    public void testBeanToJSON(){
        MyBean bean = new MyBean();
        bean.setId("001");
        bean.setName("银行卡");       
        
        List<String> cardNum = new ArrayList<String>();
        cardNum.add("农行");
        cardNum.add("工行");
        cardNum.add("建行");       
        
        bean.setCardNum(cardNum);
        
        JSONObject jsonObject = JSONObject.fromObject(bean);
        System.out.println(jsonObject);
        //{"cardNum":["农行","工行","建行"],"id":"001","name":"银行卡"}        
    }    
	
	/*
	 * 二）json对象转java对象
	 * 注意：json对象是{}括起来的键值对，因此要转换成的java对象也必须是键值对的形式，如自定义类型和map类型。
	 * 
	 */
    @Test
    public void testJsonObjectTojavaObject(){    
    	/**
    	 * 补充jsonObject的两个方法，getJSONArray(key)（知道key对应的值是数组时可以使用）和jsonObject.get(key)
    	 */
    	String str = "{\"name\":\"lml\",\"age\":24;\"man\":true,\"arr\":[\"aaa\",\"bbb\",\"ccc\"]}";
    	JSONObject jsonObject = JSONObject.fromObject(str);
    	System.out.println(jsonObject);
    	//取得str中arr属性的值["aaa","bbb","ccc"]
    	JSONArray array = jsonObject.getJSONArray("arr");
    	System.out.println(array);
    	//取得str中name属性的值
    	System.out.println(jsonObject.get("name"));
    	
    	//json对象转java bean
    	/*String student = "{\"name\":\"lml\",\"age\":24}";
    	JSONObject jsonObject = JSONObject.fromObject(student);
    	Json2Bean j2b = (Json2Bean) JSONObject.toBean(jsonObject, Json2Bean.class);
    	System.out.println(j2b.getName()+"   "+j2b.getAge());
    	
    	//json对象转map
    	Map map = (Map) JSONObject.toBean(jsonObject, HashMap.class);    	
    	Set keys = map.keySet();
    	Iterator it = keys.iterator();
    	while(it.hasNext()){
    		System.out.println(map.get(it.next()));
    	}*/
    	
    	
    	
    }
	
  //把JSON字符串数组转换为JAVA对象数组
    @Test
    public void test1(){
    	String jsonStr = "[\"aaa\",1,true]";
    	JSONArray jsonArray = JSONArray.fromObject(jsonStr);
    	List list = (List) JSONArray.toCollection(jsonArray);
    	for(int i=0;i<list.size();i++){
    		System.out.println(list.get(i));
    	}
    }
    
	/**
	 * 过滤掉值为null的属性
	 * 注意，如果String类型的属性为null，则在转换成json时，转换后的值将是""而不是null
	 */
	@Test
	public void excludeNull(){
	
		Entity entity = new Entity();
		entity.setAge(16);
		entity.setName("entity");
		System.out.println(JSONObject.fromObject(entity));
		//过滤前：{"age":16,"name":"entity","sex":"","teacher":null}
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {//也可以利用此方法只转换需要转换的属性
			/**
			 * 注意：虽然值为null的String类型被转成json后的值为""而不是null，但转换前其值仍然为null，因此这里会被过滤掉;
			 * source:欲被转成json的java对象(这里是entity)；
			 * name:欲被转成json的java对象的属性；
			 * value:欲被转成json的java对象的属性；
			 * return：true if the property will be filtered out, false otherwise
			 */
			public boolean apply(Object source, String name, Object value) {
				return value == null;//如果属性的值为null,则返回true，即被过滤掉
			}
		});
		System.out.println(JSONObject.fromObject(entity,jsonConfig));
		//过滤后：{"age":16,"name":"entity"}
	}
  
	
	@Test
	public void staticToJson(){
		Entity entity = new Entity();
		entity.setAge(16);
		entity.setName("enaaatity");
		JSONObject jsonObject = JSONObject.fromObject(entity);
		jsonObject.put("address", Entity.getAddress());
		System.out.println(jsonObject);
	}
	
	/*
	 * 测试jsonConfig同时有多种需求时,是自己想象的结果
	 * 注意：如果即设置了过滤空值，又注册了时间处理器，现在假设date2为null，实验结果为date2被空值过滤处理而没有被时间处理器处理，
	 * 并且与registerJsonValueProcessor和setJsonPropertyFilter的书写顺序无关，
	 * 可以认为属性转换过程大致为：任何一个属性会先进入setJsonPropertyFilter
	 * 中，如果被过滤掉了就接着转换下一个属性，如果没有被过滤则然后看此属性的类型是不是Date类型，是则进入registerJsonValueProcessor中。
	 * 当然，如果还设置了过滤某些属性，则这些属性既不会进入到setJsonPropertyFilter中也不会进入到registerJsonValueProcessor中。
	 * 可以认为json转换很智能
	 */
	@Test
	public void integrateTest(){
		Integrate integrate = new Integrate();
		integrate.setAddress("address");
		integrate.setSingle(false);
		integrate.setDate1(new Date());
		JsonConfig jsonConfig = new JsonConfig();
		
		
		//过滤掉指定字段
		jsonConfig.setExcludes(new String[]{"age"});
		
		//格式化日期
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
	        	
				public Object processObjectValue(String key, Object value,
						JsonConfig jsonConfig) {	
					System.out.println("格式化日期:  "+key+":  " + value);
					if (value ==null) {//Date类型的属性值有可能为null
						return null;
					}
					Date date = (Date) value;
					return DateFormat.getDateInstance().format(date);		
				}
				
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					// 这里要解析的Teacher对象中没有需要特殊处理的数组属性，因此这里直接返回null
					return null;
				}
			});
		
		//过滤掉空值
			jsonConfig.setJsonPropertyFilter(new PropertyFilter() {			
				public boolean apply(Object source, String name, Object value) {
					System.out.println("过滤掉空值:  "+name+":  " + value);
					return value == null;//如果属性的值为null,则返回true，即被过滤掉
				}
			});
			
		JSONObject jsonObject = JSONObject.fromObject(integrate,jsonConfig);
		System.out.println(jsonObject);
		
		
	}
	
	
	
	
	
	
	
	
	
	
}



