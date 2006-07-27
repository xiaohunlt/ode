/*
 * File:      $RCSfile$
 * Copyright: (C) 1999-2005 FiveSight Technologies Inc.
 *
 */
package org.apache.ode.bpel.compiler;

import org.apache.ode.bom.api.*;
import org.apache.ode.bom.wsdl.WSDLFactory4BPEL;
import org.apache.ode.bom.wsdl.WSDLFactoryBPEL20;
import org.apache.ode.bpel.elang.xpath10.compiler.XPath10ExpressionCompilerBPEL20;
import org.apache.ode.bpel.elang.xpath20.compiler.XPath20ExpressionCompilerBPEL20;

/**
 * OASIS BPEL V2.0 Compiler
 */
public class BpelCompiler20 extends BpelCompiler {
  public static final String OASIS_EXPLANG_XPATH_1_0 = "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0";

  public static final String OASIS_EXPLANG_XPATH_2_0 = "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath2.0";

  public BpelCompiler20() {
    super((WSDLFactory4BPEL) WSDLFactoryBPEL20.newInstance());

    registerActivityCompiler(EmptyActivity.class, new EmptyGenerator());
    registerActivityCompiler(CompensateActivity.class, new CompensateGenerator());
    registerActivityCompiler(FlowActivity.class, new FlowGenerator());
    registerActivityCompiler(SequenceActivity.class, new SequenceGenerator());
    registerActivityCompiler(AssignActivity.class, new AssignGenerator());
    registerActivityCompiler(ThrowActivity.class, new ThrowGenerator());
    registerActivityCompiler(WhileActivity.class, new WhileGenerator());
    registerActivityCompiler(SwitchActivity.class, new SwitchGenerator());
    registerActivityCompiler(PickActivity.class, new PickGenerator());
    registerActivityCompiler(ReplyActivity.class, new ReplyGenerator());
    registerActivityCompiler(ReceiveActivity.class, new ReceiveGenerator());
    registerActivityCompiler(InvokeActivity.class, new InvokeGenerator());
    registerActivityCompiler(WaitActivity.class, new WaitGenerator());
    registerActivityCompiler(TerminateActivity.class, new TerminateGenerator());
    registerActivityCompiler(RethrowActivity.class, new RethrowGenerator());
    registerActivityCompiler(ForEachActivity.class, new ForEachGenerator());

    registerExpressionLanguage(OASIS_EXPLANG_XPATH_1_0, new XPath10ExpressionCompilerBPEL20());
    registerExpressionLanguage(OASIS_EXPLANG_XPATH_2_0, new XPath20ExpressionCompilerBPEL20());
  }

  protected String getBpwsNamespace() {
    return Constants.NS_WSBPEL_2004_03;
  }

  protected String getDefaultExpressionLanguage() {
    return OASIS_EXPLANG_XPATH_1_0;
  }
}
