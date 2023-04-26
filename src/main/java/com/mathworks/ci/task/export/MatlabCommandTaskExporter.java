/**
 * Copyright 2020-2022 The MathWorks, Inc.
 * 
 * Run MATLAB Command Task Invocation
 */

package com.mathworks.ci.task.export;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.bamboo.specs.builders.task.CommandTask;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.task.export.TaskValidationContext;
import com.atlassian.bamboo.task.TaskContainer;
import com.atlassian.bamboo.task.TaskDefinition;

import com.atlassian.bamboo.plugins.command.task.CommandConfig;

import com.atlassian.bamboo.task.TaskConfigConstants;

import com.atlassian.bamboo.util.Narrow;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.mathworks.ci.task.MatlabCommandTaskProperties;
 
public class MatlabCommandTaskExporter {
    private static final ValidationContext COMMAND_CONTEXT = ValidationContext.of("Command task");

    @Autowired
    private UIConfigSupport uiConfigSupport;


    @NotNull
    @Override
    public CommandTask toSpecsEntity(@NotNull final TaskDefinition taskDefinition) {
        final Map<String, String> configuration = taskDefinition.getConfiguration();
        return new CommandTask()
                .executable(configuration.get(CommandConfig.CFG_SCRIPT))
                .argument(configuration.getOrDefault(CommandConfig.CFG_ARGUMENT, null))
                .environmentVariables(configuration.getOrDefault(TaskConfigConstants.CFG_ENVIRONMENT_VARIABLES, null))
                .workingSubdirectory(configuration.getOrDefault(TaskConfigConstants.CFG_WORKING_SUBDIRECTORY, null));
    }

    @Override
    public Map<String, String> toTaskConfiguration(@NotNull TaskContainer taskContainer, final TaskProperties taskProperties) {
        final MatlabCommandTaskProperties commandTaskProperties = Narrow.downTo(taskProperties, MatlabCommandTaskProperties.class);
        Preconditions.checkState(commandTaskProperties != null, "Don't know how to import task properties of type: " + taskProperties.getClass().getName());

        final Map<String, String> cfg = new HashMap<>();
        cfg.put(CommandConfig.CFG_SCRIPT, commandTaskProperties.getExecutable());
        if (commandTaskProperties.getArgument() != null)
                cfg.put(CommandConfig.CFG_ARGUMENT, commandTaskProperties.getArgument());
        if (commandTaskProperties.getEnvironmentVariables() != null)
                cfg.put(CommandConfig.CFG_ENVIRONMENT_VARIABLES, commandTaskProperties.getEnvironmentVariables());
        if (commandTaskProperties.getWorkingSubdirectory() != null) 
                cfg.put(TaskConfigConstants.CFG_WORKING_SUBDIRECTORY, commandTaskProperties.getWorkingSubdirectory());
        return cfg;
    }

    @Override
    public List<ValidationProblem> validate(@NotNull TaskValidationContext taskValidationContext, @NotNull TaskProperties taskProperties) {
        final List<ValidationProblem> validationProblems = new ArrayList<>();
        final MatlabCommandTaskProperties commandTaskProperties = Narrow.downTo(taskProperties, MatlabCommandTaskProperties.class);
        if (commandTaskProperties != null) {
            final List<String> labels = uiConfigSupport.getExecutableLabels(CommandConfig.CAPABILITY_SHORT_KEY);
            final String label = commandTaskProperties.getExecutable();
            if (labels == null || !labels.contains(label)) {
                validationProblems.add(new ValidationProblem(
                        COMMAND_CONTEXT, "Can't find executable by label: '" + label + "'. Available values: " + labels));
            }
        }
        return validationProblems;
    }
}
