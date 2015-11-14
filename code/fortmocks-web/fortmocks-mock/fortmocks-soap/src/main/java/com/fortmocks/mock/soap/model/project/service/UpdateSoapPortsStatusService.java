package com.fortmocks.mock.soap.model.project.service;

import com.fortmocks.core.model.Service;
import com.fortmocks.core.model.Result;
import com.fortmocks.core.model.Task;
import com.fortmocks.mock.soap.model.project.domain.SoapOperation;
import com.fortmocks.mock.soap.model.project.service.message.input.UpdateSoapPortsStatusInput;
import com.fortmocks.mock.soap.model.project.service.message.output.UpdateSoapPortsStatusOutput;

import java.util.List;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
@org.springframework.stereotype.Service
public class UpdateSoapPortsStatusService extends AbstractSoapProjectProcessor implements Service<UpdateSoapPortsStatusInput, UpdateSoapPortsStatusOutput> {

    /**
     * The process message is responsible for processing an incoming task and generate
     * a response based on the incoming task input
     * @param task The task that will be processed by the service
     * @return A result based on the processed incoming task
     * @see Task
     * @see Result
     */
    @Override
    public Result<UpdateSoapPortsStatusOutput> process(final Task<UpdateSoapPortsStatusInput> task) {
        final UpdateSoapPortsStatusInput input = task.getInput();
        final List<SoapOperation> soapOperations = findSoapOperationType(input.getSoapProjectId(), input.getSoapPortId());
        for(SoapOperation soapOperation : soapOperations){
            soapOperation.setSoapOperationStatus(input.getSoapOperationStatus());
        }
        save(input.getSoapProjectId());
        return createResult(new UpdateSoapPortsStatusOutput());
    }
}