package com.smallren.aspect;


import com.smallren.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Description: 切换数据源
 * @Author: renbaojia
 * @CreateDate: 2019/8/8 11:35
 * @UpdateUser:
 * @UpdateDate: 2019/8/8 11:35
 * @UpdateRemark:
 */
@Aspect

@Order(-10)//保证该AOP在@Transactional之前执行

@Component

public class DynamicDataSourceAspect {


    /**
     * 写入主库的规则
     */
    @Pointcut("@annotation(com.smallren.annotation.Master) " +
            "|| execution(* com.smallren.service..*.insert*(..)) " +
            "|| execution(* com.smallren.service..*.add*(..)) " +
            "|| execution(* com.smallren.service..*.update*(..)) " +
            "|| execution(* com.smallren.service..*.delete*(..)) " +
            "|| execution(* com.smallren.service..*.remove*(..))")
    public void writePointcut() {

    }

    /**
     * 读取从库的规则
     */
    @Pointcut("@annotation(com.smallren.annotation.Slave) " +
            "|| (execution(* com.smallren.service..*.select*(..)) " +
            "|| execution(* com.smallren.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


}



