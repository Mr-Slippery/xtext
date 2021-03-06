/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.grammaranalysis.impl;

import org.eclipse.xtext.AbstractElement;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class DefaultNFATransition extends AbstractNFATransition<DefaultNFAState, DefaultNFATransition> {

	public DefaultNFATransition(DefaultNFAState source, DefaultNFAState target, boolean ruleCall,
			AbstractElement loopCenter) {
		super(source, target, ruleCall, loopCenter);
	}
}