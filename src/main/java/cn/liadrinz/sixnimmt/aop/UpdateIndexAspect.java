package cn.liadrinz.sixnimmt.aop;

import cn.liadrinz.sixnimmt.data.protocol.Card;
import cn.liadrinz.sixnimmt.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
public class UpdateIndexAspect {
    @Pointcut("@annotation(UpdateIndex)")
    public void updateIndexAOP() {

    }

    @Around("@annotation(updateIndex)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, UpdateIndex updateIndex) {
        return null;
    }
}
