/*
 * Copyright 2015 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortmocks.mock.rest.model.project.service.message.input;

import com.fortmocks.core.basis.model.Input;
import com.fortmocks.core.basis.model.validation.NotNull;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
public class UpdateCurrentRestMockResponseSequenceIndexInput implements Input {

    @NotNull
    private Long restMethodId;
    @NotNull
    private Integer currentRestMockResponseSequenceIndex;

    public UpdateCurrentRestMockResponseSequenceIndexInput(Long restMethodId, Integer currentRestMockResponseSequenceIndex) {
        this.restMethodId = restMethodId;
        this.currentRestMockResponseSequenceIndex = currentRestMockResponseSequenceIndex;
    }

    public Long getRestMethodId() {
        return restMethodId;
    }

    public void setRestMethodId(Long restMethodId) {
        this.restMethodId = restMethodId;
    }

    public Integer getCurrentRestMockResponseSequenceIndex() {
        return currentRestMockResponseSequenceIndex;
    }

    public void setCurrentRestMockResponseSequenceIndex(Integer currentRestMockResponseSequenceIndex) {
        this.currentRestMockResponseSequenceIndex = currentRestMockResponseSequenceIndex;
    }
}
