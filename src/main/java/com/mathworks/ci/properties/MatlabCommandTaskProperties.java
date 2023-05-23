/**
 * Copyright 2020-2022 The MathWorks, Inc.
 * 
 * Run MATLAB Command Task Invocation
 */

package com.mathworks.ci.properties;

import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
// import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

// @Immutable
public class MatlabCommandTaskProperties extends TaskProperties {
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties("com.mathworks.ci:matlab-bamboo-plugin");
    private static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of("Matlab Command task");

    @NotNull private final String matlabExecutable;
    @Nullable private final String matlabCommand;

    // for importing
    private MatlabCommandTaskProperties() {
        this.matlabExecutable = null;
        this.matlabCommand = null;
    }

    public MatlabCommandTaskProperties(@Nullable final String description,
                                 final boolean enabled,
                                 @NotNull final String matlabExecutable,
                                 @Nullable final String matlabCommand) throws PropertiesValidationException {
        super(description, enabled);

        this.matlabExecutable = matlabExecutable;
        this.matlabCommand = matlabCommand;

        validate();
    }

    @Override
    public void validate() throws PropertiesValidationException {
        super.validate();

        // checkThat(VALIDATION_CONTEXT, StringUtils.isNotBlank(matlabExecutable), "Executable is not defined");
    }

    public String getMatlabExecutable() {
        return this.matlabExecutable;
    }

    public String getMatlabCommand() {
        return this.matlabCommand;
    }

    public AtlassianModuleProperties getAtlassianPlugin() {
        return this.ATLASSIAN_PLUGIN;
    }
}
