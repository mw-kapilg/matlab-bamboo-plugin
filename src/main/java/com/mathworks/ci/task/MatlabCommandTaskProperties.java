/**
 * Copyright 2020-2022 The MathWorks, Inc.
 * 
 * Run MATLAB Command Task Invocation
 */

package com.mathworks.ci.task;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
// import org.springframework.data.annotation.*;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

// @Immutable
public final class MatlabCommandTaskProperties extends TaskProperties{
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties("com.mathworks.ci.matlab-bamboo-plugin:runMATLABCommand");
    private static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of("Command task");

    private final String command;
    @NotNull private final String executable;
    // @Nullable private final String argument;
    // @Nullable private final String environmentVariables;
    // @Nullable private final String workingSubdirectory;

    // for importing
    private MatlabCommandTaskProperties() {
        this.command = null;
        this.executable = null;
        // this.argument = null;
        // this.environmentVariables = null;
        // this.workingSubdirectory = null;
    }

    public MatlabCommandTaskProperties(@Nullable final String description,
                                 final boolean enabled,
                                 final String command,
                                 @NotNull final String executable
                                //  @Nullable final String argument,
                                //  @Nullable final String environmentVariables,
                                //  @Nullable final String workingSubdirectory
                                 ) throws PropertiesValidationException {
        // uncomment the following line
        // super(description, enabled);

        this.command = command;
        this.executable = executable;
        // this.argument = argument;
        // this.environmentVariables = environmentVariables;
        // this.workingSubdirectory = workingSubdirectory;

        validate();
    }

    @Override
    public void validate() throws PropertiesValidationException {
        super.validate();

        // checkThat(VALIDATION_CONTEXT, StringUtils.isNotBlank(executable), "Executable is not defined");
    }

    // Equals and hash code
    // ...

    // Getters
    // ...

    public String getCommand() {
        return this.command;
    }

    public String getExecutable() {
        return this.executable;
    }

    // public String getArgument() {
    //     return this.argument;
    // }

    // public String getEnvironmentVariables() {
    //     return this.environmentVariables;
    // }

    // public String getWorkingSubdirectory() {
    //     return this.workingSubdirectory;
    // }

    public AtlassianModuleProperties getAtlassianPlugin() {
        return this.ATLASSIAN_PLUGIN;
    }
}
