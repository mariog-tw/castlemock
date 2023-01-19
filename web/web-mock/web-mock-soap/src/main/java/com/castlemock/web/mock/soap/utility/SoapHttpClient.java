package com.castlemock.web.mock.soap.utility;

import com.castlemock.model.core.http.ContentEncoding;
import com.castlemock.model.core.http.HttpHeader;
import com.castlemock.model.mock.soap.domain.SoapOperation;
import com.castlemock.model.mock.soap.domain.SoapRequest;
import com.castlemock.model.mock.soap.domain.SoapResponse;
import com.castlemock.web.core.utility.CharsetUtility;
import com.castlemock.web.core.utility.HttpMessageSupport;
import com.castlemock.web.mock.soap.controller.mock.AbstractSoapServiceController;
import com.castlemock.web.mock.soap.model.SoapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Optional;

public class SoapHttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoapHttpClient.class);
    private static final String FORWARDED_RESPONSE_NAME = "Forwarded response";

    public Optional<SoapResponse> getSoapResponse(final SoapRequest request,
                                                  final String soapProjectId,
                                                  final String soapPortId,
                                                  final SoapOperation soapOperation,
                                                  final HttpServletRequest httpServletRequest) {
        HttpURLConnection connection = null;
        try {
            connection = HttpMessageSupport.establishConnection(
                    soapOperation.getForwardedEndpoint(),
                    request.getHttpMethod(),
                    request.getBody(),
                    request.getHttpHeaders());

            final Integer responseCode = connection.getResponseCode();
            final List<ContentEncoding> encodings = HttpMessageSupport.extractContentEncoding(connection);
            final List<HttpHeader> responseHttpHeaders = HttpMessageSupport.extractHttpHeaders(connection);
            final String characterEncoding = CharsetUtility.parseHttpHeaders(responseHttpHeaders);
            final String responseBody = HttpMessageSupport.extractHttpBody(connection, encodings, characterEncoding);
            final SoapResponse response = SoapResponse.builder()
                    .mockResponseName(FORWARDED_RESPONSE_NAME)
                    .body(responseBody)
                    .httpHeaders(responseHttpHeaders)
                    .httpStatusCode(responseCode)
                    .contentEncodings(encodings)
                    .build();
            return Optional.of(response);
        } catch (IOException exception){
            LOGGER.error("Unable to forward request", exception);
            return Optional.empty();
        } finally {
            if(connection != null){
                connection.disconnect();
            }
        }
    }
}
