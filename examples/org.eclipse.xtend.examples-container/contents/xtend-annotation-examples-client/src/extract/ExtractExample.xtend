/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package extract

@Extract
class ExtractExample {
	
	/**
	 * This method is extracted to an interface
	 */
	override void myPublicMethod() {
	}
	
	/**
	 * This method is not extracted
	 */
	private def void myPrivateMethod() {
		
	}
}