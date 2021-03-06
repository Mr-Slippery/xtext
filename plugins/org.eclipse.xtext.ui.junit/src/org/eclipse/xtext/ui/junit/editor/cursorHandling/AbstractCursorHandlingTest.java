/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.junit.editor.cursorHandling;

import static org.eclipse.xtext.ui.junit.util.IResourcesSetupUtil.*;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.junit.editor.AbstractEditorTest;

import com.google.common.collect.Lists;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public abstract class AbstractCursorHandlingTest extends AbstractEditorTest {

	private List<IFile> files = Lists.newArrayList();
	
	protected abstract String getFileExtension();
	
	@Override
	protected void setUp() throws Exception {
		closeWelcomePage();
	}
	@Override
	protected void tearDown() throws Exception {
		files.clear();
		closeEditors();
	}

	protected XtextEditor openEditor(String content) throws Exception {
		int cursor = content.indexOf('|');
		String fileExtension = getFileExtension();
		IFile file = createFile("foo/myfile" + files.size() + "." + fileExtension, content.replace("|", ""));
		files.add(file);
		XtextEditor editor = openEditor(file);
		editor.getInternalSourceViewer().setSelectedRange(cursor, 0);
		editor.getInternalSourceViewer().getTextWidget().setFocus();
		return editor;
	}

	protected void assertState(String string, XtextEditor editor) {
		int cursor = string.indexOf('|');
		assertEquals(string.replace("|", ""), editor.getDocument().get());
		ITextSelection selection = (ITextSelection) editor.getSelectionProvider().getSelection();
		assertEquals("unexpected cursor position:",cursor, selection.getOffset());
	}
	
	protected void navigateLeft(XtextEditor editor) {
		IAction action = editor.getAction(ITextEditorActionDefinitionIds.WORD_PREVIOUS);
		action.run();
	}
	
	protected void navigateRight(XtextEditor editor) {
		IAction action = editor.getAction(ITextEditorActionDefinitionIds.WORD_NEXT);
		action.run();
	}
	
	protected void toLineStart(XtextEditor editor) {
		IAction action = editor.getAction(ITextEditorActionDefinitionIds.LINE_START);
		action.run();
	}
}
