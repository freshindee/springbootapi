package com.fitscorp.j2eemobileapi.restservices.restservices.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    // 400

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.valueOf(apiError.getStatus()), request);
    }

    protected ResponseEntity<Object> handleAlreadyExists(final UserExistsException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, "Email address already exists!");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }
    
    // 401

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<Object> handleBadCredentials(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), null, "Wrong credentials, authentication failed!");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    // 403

    @ExceptionHandler({ UsernameNotFoundException.class })
    public ResponseEntity<Object> handleUserNotFound(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.FORBIDDEN.value(), null, "Access Denied");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }
    
    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.valueOf(apiError.getStatus()), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final String error = ex.getRequestPartName() + " part is missing";
        
        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final String error = ex.getParameterName() + " parameter is missing";
        
        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), null, errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    // 404

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), null, error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED.value(), null, builder.toString());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    // 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        System.out.println(ex.getLocalizedMessage());
        final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), null, builder.substring(0, builder.length() - 2));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

    // 500

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        if (!ex.getMessage().contains("Image not found")) {
        	logger.error("error", ex);
        	System.out.println(ex.getLocalizedMessage());
        }
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Internal server error");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
    }

}