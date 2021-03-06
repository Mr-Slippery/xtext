/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtend.lib.macro.declaration;

import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend.lib.macro.services.Tracability;

import com.google.common.annotations.Beta;

/**
 * Represents an annotation.
 * 
 * @author Sven Efftinge
 */
@Beta
public interface AnnotationReference extends Element {

	/**
	 * @return the annotation type
	 */
	AnnotationTypeDeclaration getAnnotationTypeDeclaration();

	/**
	 * Returns the value for the given property.
	 * For source annotation references where {@link Tracability#isSource(NamedElement)} returns <code>true</code>, 
	 * this method returns <code>null</code> if no value is specified on the annotation reference directly.
	 * 
	 * For generated or external annotation references where {@link Tracability#isSource(NamedElement)} returns <code>false</code>,
	 * the default value of the annotation property is returned.
	 * 
	 * @param property the name of the property
	 * @return the value set on this annotation reference, the default value of the property or <code>null</code>.
	 */
	Object getValue(String property);
	
	/**
	 * Returns the expression for the given annotation property.
	 * Returns <code>null</code> if no expression is set, or this annotation reference is an external element
	 * (i.e. {@link Tracability#isExternal(NamedElement)} returns <code>true</code>). 
	 * 
	 * @param property the name of the property
	 * @return the expression
	 */
	Expression getExpression(String property);

	
}