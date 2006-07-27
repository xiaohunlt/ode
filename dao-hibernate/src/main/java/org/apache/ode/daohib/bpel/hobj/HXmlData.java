/*
 * File:      $RCSfile$
 * Copyright: (C) 1999-2005 FiveSight Technologies Inc.
 *
 */
package org.apache.ode.daohib.bpel.hobj;

import org.apache.ode.daohib.hobj.HObject;
import org.apache.ode.daohib.hobj.HLargeData;

import java.util.Collection;
import java.util.HashSet;

/**
 * @hibernate.class
 *  table="BPEL_XML_DATA"
 */
public class HXmlData extends HObject{
  
	private boolean _simpleType;
  private HLargeData _data;
  private Collection<HVariableProperty> _properties = new HashSet<HVariableProperty>();
  private String _name;
  private HScope _scope;
  private HProcessInstance _instance;

  /** Constructor. */
	public HXmlData() {
		super();
	}

  /**
   * @hibernate.many-to-one column="LDATA_ID" cascade="delete"
   */
  public HLargeData getData() {
    return _data;
  }

  public void setData(HLargeData data) {
    _data = data;
  }

  /**
   * @hibernate.property
   *  column="NAME"
   *  type="string"
   *  length="255"
   *  not-null="true"
   */
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

  /**
   * @hibernate.bag
   *  lazy="true"
   *  inverse="true"
   *  cascade="delete"
   * @hibernate.collection-key
   *  column="XML_DATA_ID"
   * @hibernate.collection-one-to-many
   *  class="org.apache.ode.daohib.bpel.hobj.HVariableProperty"
   */
	public Collection<HVariableProperty> getProperties() {
		return _properties;
	}
  
	public void setProperties(Collection<HVariableProperty> properties) {
		_properties = properties;
	}

  /**
   * @hibernate.many-to-one
   *  column="SCOPE_ID"
   */
  public HScope getScope() {
    return _scope;
  }

  public void setScope(HScope scope) {
    _scope = scope;

    if(scope != null) {
    	setInstance(scope.getInstance());
    }
  }

  /**
   * @hibernate.many-to-one
   *  column="PIID"
   */
  public HProcessInstance getInstance() {
    return _instance;
  }

  public void setInstance(HProcessInstance instance) {
    _instance = instance;
  }

  /**
   * @hibernate.property
   *  column="IS_SIMPLE_TYPE"
   */
  public boolean isSimpleType() {
    return _simpleType;
  }

  public void setSimpleType(boolean simpleType) {
    _simpleType = simpleType;
  }

}
