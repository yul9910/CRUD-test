package com.example.firstproject.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
@Slf4j
public class DebuggingAspect {

    //대상 메소드 선택 : api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut() {}
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) {
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        // 입력값 로깅하기
        for (Object obj : args) {
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }

    // 실행 시점 설정 : cut()에 지정된 대상 호출 성공 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, //cut()의 대상 메소드 정도로 이해
                                   Object returnObj) { // 리턴값
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        // 반환값 로깅
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}