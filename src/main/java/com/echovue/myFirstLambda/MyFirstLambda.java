package com.echovue.myFirstLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyFirstLambda implements RequestStreamHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handleRequest(final InputStream inputStream, final OutputStream outputStream,
                              final Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");

        DataObject data = mapper.readValue(inputStream, DataObject.class);
        data.calculateResults();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(mapper.writeValueAsString(data));
        writer.close();
    }
}


