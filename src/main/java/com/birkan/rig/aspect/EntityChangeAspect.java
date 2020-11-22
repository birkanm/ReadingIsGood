package com.birkan.rig.aspect;

import com.birkan.rig.common.OrderDto;
import com.birkan.rig.common.OrderRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@AllArgsConstructor
public class EntityChangeAspect {

    private final Logger logger;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @After("execution(* com.birkan.rig.service.OrderService.saveNewOrder(..))")
    public void loggingAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        OrderRequest request = (OrderRequest) args[0];

        String dateString = formatter.format(new Date());

        request.getOrderedBooks().forEach(k -> logger
            .info("Stock count changed ({} decrease) for Book: {} to Customer: {}", k.getQuantity(),
                k.getBookDto().getPkid(),
                dateString, request.getCustomerId()));
    }

    @AfterReturning(pointcut = "execution(* com.birkan.rig.service.OrderService.saveNewOrder(..))", returning = "retVal")
    public void logAfterReturningGetEmployee(Object retVal) {
        OrderDto orderDto = (OrderDto) retVal;
        logger.info("Order created with orderID: {} by Customer: {} on {}", orderDto.getOrderId(),
            orderDto.getCustomer().getPkid(), formatter.format(new Date()));
    }

}
