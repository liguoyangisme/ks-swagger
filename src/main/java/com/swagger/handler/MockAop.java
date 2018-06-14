package com.swagger.handler;

import com.swagger.annotation.MockList;
import com.swagger.annotation.MockMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.util.*;

/**
 * @Mock方法拦截，生成模拟数据
 * @author liguoyang
 * @create 2018-06-14 上午10:35
 **/
@Aspect
@Component
@Slf4j
public class MockAop {

    /**
     * @Mock 拦截点
     */
    @Pointcut("@annotation(com.swagger.annotation.Mock) or @annotation(com.swagger.annotation.MockList)")
    public void mock() {}

    /**
     * @MockList 拦截点
     */
    @Pointcut("@annotation(com.swagger.annotation.MockList)")
    public void mockList() {}

    /**
     * @MockMap 拦截点
     */
    @Pointcut("@annotation(com.swagger.annotation.MockMap)")
    public void mockMap() {}

    /**
     * @Mock 方法拦截，生成模拟数据
     * @param joinPoint 拦截点
     * @return 返回值
     */
    @Around("mock()")
    public Object mockAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //从切面中获取当前方法
            Method method = signature.getMethod();
            //返回类型
            Class cls = method.getReturnType();
            //生成模拟参数值
            return genParamValue(cls);
        }catch (Exception e){
            log.error("生成Mock数据时，发生异常！", e);
        }
        return joinPoint.proceed();
    }

    /**
     * @MockList 方法拦截，生成模拟数据
     * @param joinPoint 拦截点
     * @return 返回值
     */
    @Around("mockList()")
    public Object mockListAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //从切面中获取当前方法
            Method method = signature.getMethod();
            //返回类型
            Class cls = method.getReturnType();
            //注解（为了提取泛型类型）
            MockList mockList = method.getAnnotation(MockList.class);
            //生成模拟参数值
            return genParamValue(cls, mockList.type());
        }catch (Exception e){
            log.error("生成Mock数据时，发生异常！", e);
        }
        return joinPoint.proceed();
    }

    /**
     * @MockMap 方法拦截，生成模拟数据
     * @param joinPoint 拦截点
     * @return 返回值
     */
    @Around("mockMap()")
    public Object mockMapAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //从切面中获取当前方法
            Method method = signature.getMethod();
            //返回类型
            Class cls = method.getReturnType();
            //注解（为了提取泛型类型）
            MockMap mockList = method.getAnnotation(MockMap.class);
            //生成模拟参数值
            return genParamValue(cls, mockList.keyType(), mockList.valueType());
        }catch (Exception e){
            log.error("生成Mock数据时，发生异常！", e);
        }
        return joinPoint.proceed();
    }


    /**
     * 生成返回值
     * @param cls 返回值类型
     * @param genericType 泛型类型
     * @return 返回值
     */
    @SneakyThrows
    private Object genParamValue(Class cls, Class ... genericType){
        //String
        if(String.class.isAssignableFrom(cls)){
            return MockValue.mockString();

        //short
        }else if(Short.class.isAssignableFrom(cls) || cls == short.class){
            return Short.parseShort(MockValue.mockInt()+"");

        //int
        }else if(Integer.class.isAssignableFrom(cls) || cls == int.class){
            return MockValue.mockInt();

        //long
        }else if(Long.class.isAssignableFrom(cls) || cls == long.class){
            return Long.parseLong(MockValue.mockInt()+"");

        //double
        }else if(Double.class.isAssignableFrom(cls) || cls == double.class){
            return Double.parseDouble(MockValue.mockFloat()+"");

        //float
        }else if(Float.class.isAssignableFrom(cls) || cls == float.class){
            return MockValue.mockFloat();

        //date
        }else if(Date.class.isAssignableFrom(cls)){
            return new Date();

        //List
        }else if(List.class.isAssignableFrom(cls) && genericType.length==1) {
            List list = new ArrayList<>();
            //默认生成3条数据
            for (int i = 0; i < 3; i++) {
                list.add(genParamValue(genericType[0]));
            }
            return list;

        //Set
        }else if(Set.class.isAssignableFrom(cls) && genericType.length==1) {
            Set set = new HashSet();
            //默认生成3条数据
            for (int i = 0; i < 3; i++) {
                set.add(genParamValue(genericType[0]));
            }
            return set;

        //Array
        }else if(cls.isArray()){
            //数组类型
            Class arrayType = cls.getComponentType();
            Object list = Array.newInstance(arrayType,3);
            //默认生成3条数据
            for (int i = 0; i < 3; i++) {
                Array.set(list,i,genParamValue(arrayType));
            }
            return list;

        //Map
        }else if(Map.class.isAssignableFrom(cls) && genericType.length==2){
            Map map = new HashMap();
            //默认生成3条数据
            for (int i = 0; i < 3; i++) {
                map.put(genParamValue(genericType[0]), genParamValue(genericType[1]));
            }
            return map;


        //对象
        }else if (Object.class.isAssignableFrom(cls)){
            //生成类对象
            Object obj = cls.newInstance();
            //调用所有Set方法，生成模拟内容
            Arrays.stream(cls.getDeclaredFields()).filter(f -> getSetMethod(f, cls)!=null).forEach(f -> {
                //提取参数类型
                Class setParamClass = f.getType();
                try {
                    //生成参数值，并写入对象
                    getSetMethod(f, cls).invoke(obj, genParamValue(setParamClass, getGenericType(f)));
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            });

            return obj;
        }else {
            log.info("该类型不支持，返回默认字符串 [{}]",cls.getName());
            return "unsupported type ["+cls.getName()+"]";
        }
    }

    /**
     * 读取泛型类型
     * @param field 字段定义
     * @return 泛型类型
     */
    private Class[] getGenericType(Field field){
        if(List.class.isAssignableFrom(field.getType()) || Set.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType())) {
            // 如果是List类型，得到其Generic的类型
            Type genericType = field.getGenericType();
            // 如果是泛型参数的类型
            if (genericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericType;
                //得到泛型里的class类型对象
                return Arrays.stream(pt.getActualTypeArguments()).map(t -> (Class<?>)t).toArray(Class[]::new);
            }
            throw new RuntimeException("无法读取泛型");
        }
        return new Class[0];
    }

    /**
     * 检查字段是否拥有set方法
     * @param field 字段
     * @param cls 类定义
     * @return 是否拥有set方法
     */
    private Method getSetMethod(Field field, Class cls){
        String setMethodName = "set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
        val method =  Arrays.stream(cls.getMethods()).filter(m -> m.getName().equals(setMethodName)).findFirst();
        return method.get();
    }
}
