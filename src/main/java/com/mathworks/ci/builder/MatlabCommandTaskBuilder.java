package com.mathworks.ci.builder;

import com.atlassian.bamboo.specs.api.builders.task.Task;
import com.mathworks.ci.task.MatlabCommandTask;
import com.mathworks.ci.properties.MatlabCommandTaskProperties;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class MatlabCommandTaskBuilder extends Task <MatlabCommandTaskBuilder, MatlabCommandTaskProperties> {
    @NotNull private String matlabExecutable;
    @Nullable private String matlabCommand;

    /**
     * Sets label (<em>not a path</em>) of command to be executed. This label must be first
     * defined in the GUI on the Administration/Executables page.
     */
    public MatlabCommandTaskBuilder matlabExecutable(@NotNull final String matlabExecutable) {
        // checkNotEmpty("matlabExecutable", matlabExecutable);
        this.matlabExecutable = matlabExecutable;
        return this;
    }

    /**
     * Sets MATLAB command to be passed when run command is executed.
     */
    public MatlabCommandTaskBuilder matlabCommand(@Nullable final String matlabCommand) {
        this.matlabCommand = matlabCommand;
        return this;
    }

    @NotNull
    @Override
    protected MatlabCommandTaskProperties build() {
        return new MatlabCommandTaskProperties(
                description,
                taskEnabled,
                matlabExecutable,
                matlabCommand);
    }
}
