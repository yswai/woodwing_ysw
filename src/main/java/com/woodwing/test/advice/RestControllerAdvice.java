package com.woodwing.test.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class RestControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Problem onException(MethodArgumentNotValidException e) {
		String logId = UUID.randomUUID().toString();
		log.error("[{}] Caught exception while handling a request. Stacktrace: {}", logId, ExceptionUtils.getStackTrace(e));
		String msg = getExceptionMessage(e);
		VndErrors vndErrors = new VndErrors(logId, msg, Link.of("/"));
		return Problem.create()
						.withTitle(HttpStatus.BAD_REQUEST.getReasonPhrase())
						.withDetail(msg)
						.withProperties(vndErrors)
						.withStatus(HttpStatus.BAD_REQUEST);
	}

	private String getExceptionMessage(Exception e) {
		return StringUtils.hasText(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
	}

}