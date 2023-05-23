/**
 * Copyright 2020-2022 The MathWorks, Inc.
 * 
 * Run MATLAB Command Task Invocation
 */

 package com.mathworks.ci.export;

import com.atlassian.bamboo.task.export.TaskDefinitionExporter;
import com.atlassian.bamboo.task.TaskContainer;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.specs.api.builders.task.Task;
import com.atlassian.bamboo.task.export.TaskValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.util.Narrow;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.mathworks.ci.properties.MatlabCommandTaskProperties;
import com.mathworks.ci.helper.MatlabBuilderConstants;
import com.mathworks.ci.builder.MatlabCommandTaskBuilder;

public class MatlabCommandTaskExporter implements TaskDefinitionExporter {
    @NotNull
    @Override
    public Map<String, String> toTaskConfiguration(@NotNull final TaskContainer taskContainer, @NotNull final TaskProperties taskProperties) {

        final MatlabCommandTaskProperties matlabCommandTaskProperties = Narrow.downTo(taskProperties, MatlabCommandTaskProperties.class);
        if (matlabCommandTaskProperties != null) {
            final Map<String, String> cfg = new HashMap<>();
            cfg.put(MatlabBuilderConstants.MATLAB_CFG_KEY, matlabCommandTaskProperties.getMatlabExecutable());
            cfg.put(MatlabBuilderConstants.MATLAB_COMMAND_CFG_KEY, matlabCommandTaskProperties.getMatlabCommand());
            return cfg;
        }
        throw new IllegalStateException("Don't know how to import task properties of type: " + taskProperties.getClass().getName());
    }

    @NotNull
    @Override
    public MatlabCommandTaskBuilder toSpecsEntity(@NotNull final TaskDefinition taskDefinition) {
        final Map<String, String> configuration = taskDefinition.getConfiguration();

        // final MatlabCommandTask matlabCommandTask = new MatlabCommandTask();

        return new MatlabCommandTaskBuilder()
                .matlabExecutable(configuration.get(MatlabBuilderConstants.MATLAB_CFG_KEY))
                .matlabCommand(configuration.get(MatlabBuilderConstants.MATLAB_COMMAND_CFG_KEY));
    }

    @Override
    public List<ValidationProblem> validate(@NotNull TaskValidationContext taskValidationContext,
                                            @NotNull TaskProperties taskProperties) {
        final List<ValidationProblem> result = new ArrayList<>();
        // final FastlaneTaskProperties properties = Narrow.downTo(taskProperties, FastlaneTaskProperties.class);
        // if (properties != null) {
        //     final String executableLabel = properties.getExecutableLabel();
        //     if (StringUtils.isEmpty(executableLabel)) {
        //         result.add(new ValidationProblem(FASTLANE_CONTEXT, "Executable can't be empty"));
        //     }
        //     if (StringUtils.isEmpty(properties.getLane())) {
        //         result.add(new ValidationProblem(FASTLANE_CONTEXT, "Lane can't be empty"));
        //     } else {
        //         final String capabilityKey = String.format("%s.%s", FastlaneCapabilityDefaultsHelper.FASTLANE_CAPABILITY_PREFIX, properties.getExecutableLabel());
        //         if (StringUtils.isBlank(capabilityContext.getCapabilityValue(capabilityKey))) {
        //             result.add(new ValidationProblem(FASTLANE_CONTEXT, "Executable " + properties.getExecutableLabel() + " doesn't exist."));
        //         }
        //     }
        // }
        return result;
    }
}
