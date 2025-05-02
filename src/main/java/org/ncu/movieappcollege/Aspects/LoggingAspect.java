package org.ncu.movieappcollege.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* org.ncu.movieappcollege.Repository.MovieRepository.save(org.ncu.movieappcollege.Model.Movie))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🚀 [Before] Method called: " + joinPoint.getSignature().getName());
    }

    @After("execution(* org.ncu.movieappcollege.Repository.MovieRepository.getMovieById(org.ncu.movieappcollege.Model.Movie))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("✅ [After] Completed: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* org.ncu.movieappcollege.Repository.MovieRepository.findById(org.ncu.movieappcollege.Model.Movie))",
            returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("🎉 [AfterReturning] Category fetched: " + result);
    }

    @AfterThrowing(pointcut = "execution(* org.ncu.movieappcollege.Repository.MovieRepository(org.ncu.movieappcollege.Model.Movie))",
            throwing = "ex")
    public void logException(Throwable ex) {
        System.out.println("❌ [AfterThrowing] Exception caught: " + ex.getMessage());
    }

    @Around("execution(* org.ncu.movieappcollege.Controller.MovieController.*(..))")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println("⏱ [Around] Execution time of " + joinPoint.getSignature().getName() + ": " + duration + " ms");
        return result;
    }

}
