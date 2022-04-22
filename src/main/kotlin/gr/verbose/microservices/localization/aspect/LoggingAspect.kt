package gr.verbose.microservices.localization.aspect

import com.fasterxml.jackson.databind.ObjectMapper
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.CodeSignature
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Aspect
@Component
@Order(2)
class LoggingAspect {
    private val logger = LoggerFactory.getLogger(LoggingAspect::class.java)

    @Pointcut(value = "execution(public * gr.verbose.microservices.localization.api.*.*(..))")
    private fun apiLog(){}

    @Before(value = "apiLog()")
    fun doBefore(joinPoint: JoinPoint) {
        val args = joinPoint.args
        val codeSignature = joinPoint.signature as CodeSignature
        val parameterNames = codeSignature.parameterNames
        val signatureName = codeSignature.name
        val parameters = HashMap<String, Any>(args.size).toMutableMap()
        for (i in args.indices) {
            if(args[i] !is BindingResult) {
                val value = if (args[i] != null) args[i] else ""
                parameters[parameterNames[i]] = value
            }
        }
        logger.info("$signatureName called $parameters")
    }

    @AfterReturning(returning = "ret", pointcut = "apiLog()")
    @Throws(Throwable::class)
    fun afterReturning(joinPoint: JoinPoint, ret: Any?) {
        val codeSignature = joinPoint.signature as CodeSignature
        val signatureName = codeSignature.name
        val returning = ret?.let { ObjectMapper().writeValueAsString(ret) }
        logger.info("$signatureName returns $returning")
    }
}