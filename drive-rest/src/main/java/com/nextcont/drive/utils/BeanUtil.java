package com.nextcont.drive.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/23
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtil {


    public static <T> List<T> toBeans(List<Document> documents, Class<T> clazz){
        List<T> list = new ArrayList<T>();
        try {
            for (int i = 0; null != documents && i < documents.size(); i++) {
                list.add(toBean(documents.get(i), clazz));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /*
     * 将Bson 转化为对象
     *
     * @param:Bson文档
     *
     * @param:类pojo
     *
     * @param:返回对象
     */
    public static <T> T toBean(Document document, Class<T> clazz){

        T obj = null;
        try {
            obj = clazz.newInstance();// 声明一个对象
            Field[] fields = clazz.getDeclaredFields();// 获取所有属性
            Method[] methods = clazz.getMethods();// 获取所有的方法
        /*
         * 查找所有的属性，并通过属性名和数据库字段名通过相等映射
         */
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if(fieldName.equals("permissions"))
                    System.out.println();
                Column column = fields[i].getAnnotation(Column.class);
                Object bson = null;
                if (null != column && null != column.name())
                    bson = document.get(column.name());
                else if ("_id".equals(fieldName))
                    bson = document.get("_id");
                else
                    bson = document.get(fieldName);
                if (null == bson) {
                    continue;
                } else if (bson instanceof Document) {// 如果字段是文档了递归调用
                    bson = toBean((Document) bson, fields[i].getType());
                } else if (bson instanceof MongoCollection) {// 如果字段是文档集了调用colTOList方法
                    bson = colToList(bson, fields[i]);
                } else if (bson instanceof List) {// 如果字段是文档集了调用colTOList方法
                    bson = colToObjectList(bson, fields[i]);
            }
                for (int j = 0; j < methods.length; j++) {// 为对象赋值
                    String metdName = methods[j].getName();
                    if (equalFieldAndSet(fieldName, metdName)) {
                        methods[j].invoke(obj, bson);
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    public static List<Document> toBsons(List<Object> objs)
            throws IllegalArgumentException, SecurityException,
            IllegalAccessException, InvocationTargetException,
            NoSuchFieldException {
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; null != objs && i < objs.size(); i++) {
            documents.add(toBson(objs.get(i)));
        }
        return documents;
    }

    /*
     * 将对象转化为Bson文档
     *
     * @param:对象
     *
     * @param:类型
     *
     * @return:文档
     */
    public static Document toBson(Object obj){
        if (null == obj) {
            return null;
        }
        Document document = new Document();

        try {
            Class<? extends Object> clazz = obj.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; null != fields && i < fields.length; i++) {
                Column column = fields[i].getAnnotation(Column.class);// 获取列注解内容
                NotColumn notColumn = fields[i].getAnnotation(NotColumn.class);// 获取否列
                String key = null;// 对应的文档键值
                if (null != column && null != column.name()) {// 存在列映射取值
                    key = column.name();
                } else if (null != notColumn) {// 不是列的情况
                    continue;
                }
                else {
                    key = fields[i].getName();// 默认情况通过属性名映射
//                    if ("id".equals(key)) {// 替换id为_id
//                        key = "_id";
//                    }
                }
                String fieldName = fields[i].getName();

            /*
             * 获取对象属性值并映射到Document中
             */
                for (int j = 0; null != methods && j < methods.length; j++) {
                    String methdName = methods[j].getName();
                    if (null != fieldName && equalFieldAndGet(fieldName, methdName)) {
                        Object val = methods[j].invoke(obj);// 得到值
                        if (null == val) {
                            continue;
                        }
                        if (isJavaClass(methods[j].getReturnType())) {
                            if (methods[j].getReturnType().getName().equals("java.util.List")) {// 列表处理
                                @SuppressWarnings("unchecked")
                                List<Object> list = (List<Object>) val;
                                List<Document> documents = new ArrayList<Document>();

                                boolean StringFlag = false;
                                for (Object obj1 : list) {
                                    if(obj1 instanceof String) {
                                        StringFlag = true;
                                        break;
                                    }
                                    documents.add(toBson(obj1));
                                }
                                if(StringFlag)
                                    document.append(key, val);
                                else
                                    document.append(key, documents);

                            } else {// 其它对象处理，基本类型
                                document.append(key, val);
                            }
                        } else {// 自定义类型
                            document.append(key, toBson(val));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return document;
    }

    /*
     * 是否是自定义类型】
     *
     * false:是自定义
     */
    private static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

    /*
     * 将文档集转化为列表
     *
     * @param:文档集
     *
     * @param:属性类型
     *
     * @return:返回列表
     */
    private static List<Object> colToList(Object bson, Field field)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();// 获取列表的类型
        List<Object> objs = new ArrayList<Object>();
//        List<Document> list = (ArrayList<Document>)bson;
//        list.forEach(t->toBean(t, (Class) pt.getActualTypeArguments()[0]));

        @SuppressWarnings("unchecked")
        MongoCollection<Document> cols = (MongoCollection<Document>) bson;
        MongoCursor<Document> cursor = cols.find().iterator();
        while (cursor.hasNext()) {
            Document child = cursor.next();
            @SuppressWarnings("rawtypes")
            Class clz = (Class) pt.getActualTypeArguments()[0];// 获取元素类型
            @SuppressWarnings("unchecked")
            Object obj = toBean(child, clz);
            System.out.println(child);
            objs.add(obj);
        }
        return objs;
    }


    private static List<Object> colToObjectList(Object bson, Field field)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();// 获取列表的类型
        Class clz = (Class) pt.getActualTypeArguments()[0];// 获取元素类型
        if(!clz.getTypeName().equals("java.lang.String")) {
            List<Object> objs = new ArrayList<Object>();

            @SuppressWarnings("unchecked")
            List<Document> list = (List<Document>) bson;
            list.forEach(t -> {
                Object obj = toBean(t, clz);
                objs.add(obj);
            });
            return objs;
        }else{
            return (List)bson;
        }
    }
    /*
     * 比较setter方法和属性相等
     */
    private static boolean equalFieldAndSet(String field, String methodName) {
        return methodName.toLowerCase().matches("set" + field.toLowerCase());
    }

    /*
     * 比较getter方法和属性相等
     */
    private static boolean equalFieldAndGet(String field, String methodName) {
        String lowerCaseMethodName = methodName.toLowerCase();
        String lowerCaseFileIdName = field.toLowerCase();
        return lowerCaseMethodName.matches("get" + lowerCaseFileIdName)||lowerCaseMethodName.matches("is"+lowerCaseFileIdName);
    }
}
