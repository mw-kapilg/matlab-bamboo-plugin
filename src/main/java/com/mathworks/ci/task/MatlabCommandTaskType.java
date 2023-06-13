// /**
//  * Copyright 2020-2022 The MathWorks, Inc.
//  * 
//  * Run MATLAB Command Task Invocation
//  */

// package com.mathworks.ci.task;

// import com.atlassian.bamboo.build.logger.BuildLogger;
// import com.atlassian.bamboo.process.ProcessService;
// import com.atlassian.bamboo.task.TaskContext;
// import com.atlassian.bamboo.task.TaskException;
// import com.atlassian.bamboo.task.TaskResult;
// import com.atlassian.bamboo.task.TaskResultBuilder;
// import com.atlassian.bamboo.task.TaskType;
// import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
// import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
// import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
// import com.atlassian.utils.process.ExternalProcess;
// import com.mathworks.ci.helper.MatlabCommandRunner;
// import com.mathworks.ci.helper.MatlabBuilderConstants;
// import org.jetbrains.annotations.NotNull;
// import org.jetbrains.annotations.Nullable;

// @Scanned
// public class MatlabCommandTaskType implements TaskType {
//     // @NotNull private String matlabExecutable;
//     // @Nullable private String matlabCommand;

//     @ComponentImport
//     private final ProcessService processService;

//     @ComponentImport
//     private final CapabilityContext capabilityContext;

//     private MatlabCommandRunner matlabCommandRunner;

//     // public MatlabCommandTaskType() {
//     //     this.matlabExecutable = null;
//     //     this.matlabCommand = null;
//     // }

//     public MatlabCommandTaskType(ProcessService processService, CapabilityContext capabilityContext, MatlabCommandRunner matlabCommandRunner) {
//         this.processService = processService;
//         this.capabilityContext = capabilityContext;
//         this.matlabCommandRunner = matlabCommandRunner;
//     }

//     public MatlabCommandTaskType(ProcessService processService, CapabilityContext capabilityContext) {
//         this(processService, capabilityContext, new MatlabCommandRunner(processService, capabilityContext));
//     }

//     @NotNull
//     @Override
//     public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
//         TaskResultBuilder taskResultBuilder = TaskResultBuilder.newBuilder(taskContext);
//         BuildLogger buildLogger = taskContext.getBuildLogger();

//         String matlabCommand = taskContext.getConfigurationMap().get(MatlabBuilderConstants.MATLAB_COMMAND_CFG_KEY);

//         try {
//             ExternalProcess process = matlabCommandRunner.run(matlabCommand, taskContext);
//             taskResultBuilder.checkReturnCode(process);
//         } catch (Exception e) {
//             buildLogger.addErrorLogEntry(e.getMessage());
//         } finally {
//             matlabCommandRunner.cleanup(buildLogger);
//         }
//         return taskResultBuilder.build();
//     }

//     // /**
//     //  * Sets label (<em>not a path</em>) of command to be executed. This label must be first
//     //  * defined in the GUI on the Administration/Executables page.
//     //  */
//     // public MatlabCommandTaskType matlabExecutable(@NotNull final String matlabExecutable) {
//     //     // checkNotEmpty("matlabExecutable", matlabExecutable);
//     //     this.matlabExecutable = matlabExecutable;
//     //     return this;
//     // }

//     // /**
//     //  * Sets MATLAB command to be passed when run command is executed.
//     //  */
//     // public MatlabCommandTaskType matlabCommand(@Nullable final String matlabCommand) {
//     //     this.matlabCommand = matlabCommand;
//     //     return this;
//     // }
// }
