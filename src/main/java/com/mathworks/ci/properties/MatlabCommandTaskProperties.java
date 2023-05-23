/**
 * Copyright 2020-2022 The MathWorks, Inc.
 * 
 * Run MATLAB Command Task Invocation
 */

package com.mathworks.ci.properties;

import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.mathworks.ci.builder.MatlabCommandTaskBuilder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
// import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

// @Immutable
@Builder(MatlabCommandTaskBuilder.class)
public class MatlabCommandTaskProperties extends TaskProperties {
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties("matlab-bamboo-plugin:runMATLABCommand");
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
        // super(description, enabled, new ArrayList<RequirementProperties>());
        super();

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

    @Override
    public boolean equals(Object o){
        return (this == o);
    }

    @Override
    public int hashCode(){
        return this.hashCode();
    }
}
