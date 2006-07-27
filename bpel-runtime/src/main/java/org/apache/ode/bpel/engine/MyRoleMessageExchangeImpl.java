package org.apache.ode.bpel.engine;


import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.apache.ode.bpel.dao.MessageExchangeDAO;
import org.apache.ode.bpel.iapi.BpelEngineException;
import org.apache.ode.bpel.iapi.EndpointReference;
import org.apache.ode.bpel.iapi.Message;
import org.apache.ode.bpel.iapi.MessageExchange;

import org.apache.ode.bpel.iapi.MyRoleMessageExchange;


class MyRoleMessageExchangeImpl extends MessageExchangeImpl 
  implements MyRoleMessageExchange {
  
  private static final Log __log = LogFactory.getLog(MyRoleMessageExchangeImpl.class);
  
  public MyRoleMessageExchangeImpl(
      BpelEngineImpl engine, 
      MessageExchangeDAO mexdao) {
    super(engine, mexdao);
  }


  public CorrelationStatus getCorrelationStatus() {
    return CorrelationStatus.valueOf(getDAO().getCorrelationStatus());
  }

  
  void setCorrelationStatus(CorrelationStatus status) {
    getDAO().setCorrelationStatus(status.toString());
  }

  public void invoke(Message request) {
    if (request == null) {
      String errmsg = "Must pass non-null message to invoke()!";
      __log.fatal(errmsg);
      throw new NullPointerException(errmsg);
    }
    
    _dao.setRequest(((MessageImpl)request)._dao);
    _dao.setStatus(MessageExchange.Status.REQUEST.toString());
    
    BpelProcess target = _engine.route(getDAO().getCallee(), _epr, request);
    

    if (__log.isDebugEnabled())
      __log.debug("invoke() EPR= " + _epr + " ==> " + target);


    if (target == null) {
      if (__log.isWarnEnabled()) 
        __log.warn(__msgs.msgUnknownEPR("" + _epr));
      
      setCorrelationStatus(MyRoleMessageExchange.CorrelationStatus.UKNOWN_ENDPOINT);
      setFailure(MessageExchange.FailureType.UNKNOWN_ENDPOINT, null,null);
    } else {
      target.invokeProcess(getDAO().getCallee(), _epr, this);
    }
    
  }

  public void complete() {
  }

  public QName getServiceName() {
    return getDAO().getCallee();
  }

  public void setClientId(String clientKey) {
    getDAO().setCorrelationId(clientKey);
  }


  public String getClientId() {
    return getDAO().getCorrelationId();
  }
  
  public String toString() {
    try {
      return "{MyRoleMex#" + getMessageExchangeId()
      + " [Client " + getClientId() + "] calling " + getServiceName() + "." + getOperationName() + "(...)}";
    } catch (Throwable t) {
      return "{MyRoleMex#???}";
    }
  }

}
