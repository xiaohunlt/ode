/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ode.bpel.obj;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Compiled representation of an expression language dependency.
 */
public class OExpressionLanguage extends OBase  implements Serializable{
	public static final long serialVersionUID = -1L;
	private static final String EXPRESSIONLANGUAGEURI = "expressionLanguageUri";
	private static final String PROPERTIES = "properties";

	@JsonCreator
	public OExpressionLanguage(){}
	public OExpressionLanguage(OProcess owner, Map<String, String> properties) {
		super(owner);
		setProperties(new HashMap<String, String>());
		if (properties != null)
			getProperties().putAll(properties);
	}

	public boolean equals(Object obj) {
		if (obj instanceof OExpressionLanguage)
			return getExpressionLanguageUri()
					.equals(getExpressionLanguageUri());
		else
			return super.equals(obj);
	}

	@JsonIgnore
	public String getExpressionLanguageUri() {
		Object o = fieldContainer.get(EXPRESSIONLANGUAGEURI);
		return o == null ? null : (String)o;
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	public Map<String, String> getProperties() {
		Object o = fieldContainer.get(PROPERTIES);
		return o == null ? null : (Map<String, String>)o;
	}

	public int hashCode() {
		return getExpressionLanguageUri().hashCode();
	}

	public void setExpressionLanguageUri(String expressionLanguageUri) {
		fieldContainer.put(EXPRESSIONLANGUAGEURI, expressionLanguageUri);
	}

	public void setProperties(Map<String, String> properties) {
		fieldContainer.put(PROPERTIES, properties);
	}
}
