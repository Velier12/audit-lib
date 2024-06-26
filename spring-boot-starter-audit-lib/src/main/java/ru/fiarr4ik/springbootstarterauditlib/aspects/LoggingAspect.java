package ru.fiarr4ik.springbootstarterauditlib.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fiarr4ik.springbootstarterauditlib.annotations.AuditLog;
import ru.fiarr4ik.springbootstarterauditlib.config.LoggingConfig;
import ru.fiarr4ik.springbootstarterauditlib.enums.LogLevel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

    /**
     * Аспект для логгирования
     */
    @Aspect
    @Component
    public class LoggingAspect {
        private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

        @Autowired
        private LoggingConfig loggingConfig;

        @Pointcut("@annotation(ru.fiarr4ik.springbootstarterauditlib.annotations.AuditLog)")
        private void loggerAnnotation() {

        }

        @Around("loggerAnnotation()")
        public Object annotationLoggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            AuditLog auditLog = method.getAnnotation(AuditLog.class);

            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            LogLevel logLevel = auditLog.logLevel();

            log(logLevel, "Метод " + methodName + " вызвался с аргументами " + Arrays.toString(args));

            try {
                Object result = joinPoint.proceed();
                log(logLevel, "Метод " + methodName + " вернул: " + result);
                return result;
            } catch (Throwable throwable) {
                log(LogLevel.ERROR, "Метод " + methodName + " вызвал ошибку: " + throwable);
                throw throwable;
            }
        }

        /**
         * Метод для логгирования.
         * Настраивается в .properties
         * @param logLevel  уровень логгирования из перечисления {@link LogLevel}
         * @param message  сообщение в лог
         */
        private void log(LogLevel logLevel, String message) {
            if (loggingConfig.isConsoleEnabled()) {
                logToConsole(logLevel, message);
            }
            if (loggingConfig.isFileEnabled()) {
                logToFile(logLevel, message);
            }
        }

        /**
         * Метод для лога в консоль
         * @param logLevel  уровень логгирования из перечисления {@link LogLevel}
         * @param message  сообщение в лог
         */
        private void logToConsole(LogLevel logLevel, String message) {
            switch (logLevel) {
                case DEBUG:
                    LOGGER.debug("{} {}", logLevel, message);
                    break;
                case INFO:
                    LOGGER.info("{} {}", logLevel, message);
                    break;
                case WARNING:
                    LOGGER.warn("{} {}", logLevel, message);
                    break;
                case ERROR:
                    LOGGER.error("{} {}", logLevel, message);
                    break;
                case TRACE:
                    LOGGER.trace("{} {}", logLevel, message);
                    break;
                default:
                    break;
            }
        }

        /**
         * Лог в файл
         * @param logLevel  уровень логгирования из перечисления {@link LogLevel}
         * @param message  сообщение в лог
         */
        private void logToFile(LogLevel logLevel, String message) {
            String filePath = loggingConfig.getFilePath();
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
                writer.println(logLevel + " " + message + "\n");
            } catch (IOException e) {
                LOGGER.error("Ошибка при записи в файл логов: {}", e.getMessage());
            }
        }
    }
