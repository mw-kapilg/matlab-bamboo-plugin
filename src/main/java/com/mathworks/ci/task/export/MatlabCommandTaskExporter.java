/**
 * Copyright 2020-2022 The MathWorks, Inc.
 * 
 * Run MATLAB Command Task Invocation
 */

package com.mathworks.ci.task.export;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.bamboo.specs.api.builders.AtlassianModule;
import com.atlassian.bamboo.specs.api.builders.task.AnyTask;
import com.atlassian.bamboo.specs.api.builders.task.Task;
// import com.atlassian.bamboo.specs.builders.task.CommandTask;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.task.export.TaskValidationContext;
import com.atlassian.bamboo.task.TaskContainer;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.task.export.TaskDefinitionExporter;

// import com.atlassian.bamboo.plugins.command.task.CommandConfig;

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
import com.mathworks.ci.helper.MatlabBuilderConstants;
 
public class MatlabCommandTaskExporter implements TaskDefinitionExporter {
    private static final ValidationContext COMMAND_CONTEXT = ValidationContext.of("Command task");

    // private final CapabilityContext capabilityContext;

    @Autowired
    private UIConfigSupport uiConfigSupport;

    public MatlabCommandTaskExporter(
        // CapabilityContext capabilityContext
        ) {
        // this.capabilityContext = capabilityContext;
    }

    @NotNull
    @Override
    public Task toSpecsEntity(@NotNull final TaskDefinition taskDefinition) {
        // final Map<String, String> configuration = taskDefinition.getConfiguration();

        return new AnyTask(new AtlassianModule(taskDefinition.getPluginKey())).configuration(taskDefinition.getConfiguration());


        // return new MatlabCommandTask()
        //         .executable(configuration.get(MatlabBuilderConstants.MATLAB_CFG_KEY))
        //         .command(configuration.get(MatlabBuilderConstants.MATLAB_COMMAND_CFG_KEY))
        //         // .argument(configuration.getOrDefault(CommandConfig.CFG_ARGUMENT, null))
        //         // .environmentVariables(configuration.getOrDefault(TaskConfigConstants.CFG_ENVIRONMENT_VARIABLES, null))
        //         // .workingSubdirectory(configuration.getOrDefault(TaskConfigConstants.CFG_WORKING_SUBDIRECTORY, null))
        //         ;
    }

    @Override
    public Map<String, String> toTaskConfiguration(@NotNull TaskContainer taskContainer, final TaskProperties taskProperties) {
        final MatlabCommandTaskProperties matlabCommandTaskProperties = Narrow.downTo(taskProperties, MatlabCommandTaskProperties.class);
        // Preconditions.checkState(matlabCommandTaskProperties != null, "Don't know how to import task properties of type: " + taskProperties.getClass().getName());

        final Map<String, String> cfg = new HashMap<>();
        cfg.put(MatlabBuilderConstants.MATLAB_CFG_KEY, matlabCommandTaskProperties.getExecutable());
        cfg.put(MatlabBuilderConstants.MATLAB_COMMAND_CFG_KEY, matlabCommandTaskProperties.getCommand());
        // cfg.put(CommandConfig.CFG_SCRIPT, matlabCommandTaskProperties.getExecutable());
        // if (matlabCommandTaskProperties.getArgument() != null)
        //         cfg.put(CommandConfig.CFG_ARGUMENT, matlabCommandTaskProperties.getArgument());
        // if (matlabCommandTaskProperties.getEnvironmentVariables() != null)
        //         cfg.put(CommandConfig.CFG_ENVIRONMENT_VARIABLES, matlabCommandTaskProperties.getEnvironmentVariables());
        // if (matlabCommandTaskProperties.getWorkingSubdirectory() != null) 
        //         cfg.put(TaskConfigConstants.CFG_WORKING_SUBDIRECTORY, matlabCommandTaskProperties.getWorkingSubdirectory());
        return cfg;
    }

    @Override
    public List<ValidationProblem> validate(@NotNull TaskValidationContext taskValidationContext, @NotNull TaskProperties taskProperties) {
        final List<ValidationProblem> validationProblems = new ArrayList<>();
        final MatlabCommandTaskProperties matlabCommandTaskProperties = Narrow.downTo(taskProperties, MatlabCommandTaskProperties.class);
        // if (matlabCommandTaskProperties != null) {
        //     final List<String> labels = uiConfigSupport.getExecutableLabels(CommandConfig.CAPABILITY_SHORT_KEY);
        //     final String label = matlabCommandTaskProperties.getExecutable();
        //     if (labels == null || !labels.contains(label)) {
        //         validationProblems.add(new ValidationProblem(
        //                 COMMAND_CONTEXT, "Can't find executable by label: '" + label + "'. Available values: " + labels));
        //     }
        // }
        return validationProblems;
    }
}
