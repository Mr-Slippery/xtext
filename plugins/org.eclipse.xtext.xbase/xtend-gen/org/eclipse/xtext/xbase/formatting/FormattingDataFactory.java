package org.eclipse.xtext.xbase.formatting;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.preferences.PreferenceKey;
import org.eclipse.xtext.xbase.formatting.BlankLineKey;
import org.eclipse.xtext.xbase.formatting.CommentInfo;
import org.eclipse.xtext.xbase.formatting.FormattableDocument;
import org.eclipse.xtext.xbase.formatting.FormattingData;
import org.eclipse.xtext.xbase.formatting.FormattingDataInit;
import org.eclipse.xtext.xbase.formatting.FormattingPreferenceValues;
import org.eclipse.xtext.xbase.formatting.HiddenLeafAccess;
import org.eclipse.xtext.xbase.formatting.HiddenLeafs;
import org.eclipse.xtext.xbase.formatting.LeafInfo;
import org.eclipse.xtext.xbase.formatting.NewLineData;
import org.eclipse.xtext.xbase.formatting.NewLineKey;
import org.eclipse.xtext.xbase.formatting.NewLineOrPreserveKey;
import org.eclipse.xtext.xbase.formatting.WhitespaceData;
import org.eclipse.xtext.xbase.formatting.WhitespaceInfo;
import org.eclipse.xtext.xbase.formatting.WhitespaceKey;
import org.eclipse.xtext.xbase.formatting.XbaseFormatterPreferenceKeys;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class FormattingDataFactory {
  @Inject
  @Extension
  private HiddenLeafAccess _hiddenLeafAccess;
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> newFormattingData(final HiddenLeafs leafs, final Procedure1<? super FormattingDataInit> init) {
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _xblockexpression = null;
    {
      FormattingDataInit _formattingDataInit = new FormattingDataInit();
      final FormattingDataInit data = _formattingDataInit;
      init.apply(data);
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData = this.newFormattingData(leafs, data.key, data);
      _xblockexpression = (_newFormattingData);
    }
    return _xblockexpression;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData(final HiddenLeafs leafs, final Void key, final FormattingDataInit it) {
    final Function1<FormattableDocument,Iterable<FormattingData>> _function = new Function1<FormattableDocument,Iterable<FormattingData>>() {
      public Iterable<FormattingData> apply(final FormattableDocument doc) {
        Integer _elvis = null;
        if (it.newLines != null) {
          _elvis = it.newLines;
        } else {
          _elvis = ObjectExtensions.<Integer>operator_elvis(it.newLines, Integer.valueOf(0));
        }
        final int newLines2 = (_elvis).intValue();
        boolean _or = false;
        boolean _and = false;
        boolean _equals = Objects.equal(it.space, null);
        if (!_equals) {
          _and = false;
        } else {
          boolean _equals_1 = Objects.equal(it.newLines, null);
          _and = (_equals && _equals_1);
        }
        if (_and) {
          _or = true;
        } else {
          boolean _and_1 = false;
          int _newLinesInComments = leafs.getNewLinesInComments();
          boolean _equals_2 = (_newLinesInComments == 0);
          if (!_equals_2) {
            _and_1 = false;
          } else {
            boolean _or_1 = false;
            boolean _equals_3 = (newLines2 == 0);
            if (_equals_3) {
              _or_1 = true;
            } else {
              boolean _equals_4 = Objects.equal(it.space, "");
              _or_1 = (_equals_3 || _equals_4);
            }
            _and_1 = (_equals_2 && _or_1);
          }
          _or = (_and || _and_1);
        }
        if (_or) {
          boolean _isDebugConflicts = doc.isDebugConflicts();
          return FormattingDataFactory.this.newWhitespaceData(leafs, it.space, it.increaseIndentationChange, it.decreaseIndentationChange, _isDebugConflicts);
        } else {
          boolean _isDebugConflicts_1 = doc.isDebugConflicts();
          return FormattingDataFactory.this.newNewLineData(leafs, newLines2, newLines2, it.increaseIndentationChange, it.decreaseIndentationChange, _isDebugConflicts_1);
        }
      }
    };
    return _function;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData(final HiddenLeafs leafs, final BlankLineKey key, final FormattingDataInit it) {
    final Function1<FormattableDocument,Iterable<FormattingData>> _function = new Function1<FormattableDocument,Iterable<FormattingData>>() {
      public Iterable<FormattingData> apply(final FormattableDocument doc) {
        Iterable<FormattingData> _xblockexpression = null;
        {
          FormattingPreferenceValues _cfg = doc.getCfg();
          final int blankline = _cfg.get(key);
          FormattingPreferenceValues _cfg_1 = doc.getCfg();
          final int preserve = _cfg_1.get(XbaseFormatterPreferenceKeys.preserveBlankLines);
          final int min = (blankline + 1);
          int _plus = (preserve + 1);
          final int max = Math.max(_plus, min);
          boolean _isDebugConflicts = doc.isDebugConflicts();
          Iterable<FormattingData> _newNewLineData = FormattingDataFactory.this.newNewLineData(leafs, min, max, it.increaseIndentationChange, it.decreaseIndentationChange, _isDebugConflicts);
          _xblockexpression = (_newNewLineData);
        }
        return _xblockexpression;
      }
    };
    return _function;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData(final HiddenLeafs leafs, final NewLineOrPreserveKey key, final FormattingDataInit it) {
    final Function1<FormattableDocument,Iterable<FormattingData>> _function = new Function1<FormattableDocument,Iterable<FormattingData>>() {
      public Iterable<FormattingData> apply(final FormattableDocument doc) {
        Iterable<FormattingData> _xblockexpression = null;
        {
          FormattingPreferenceValues _cfg = doc.getCfg();
          final boolean newLine = _cfg.get(key);
          FormattingPreferenceValues _cfg_1 = doc.getCfg();
          final boolean preserve = _cfg_1.get(XbaseFormatterPreferenceKeys.preserveNewLines);
          int _xifexpression = (int) 0;
          if (newLine) {
            _xifexpression = 1;
          } else {
            _xifexpression = 0;
          }
          int _xifexpression_1 = (int) 0;
          boolean _or = false;
          if (preserve) {
            _or = true;
          } else {
            _or = (preserve || newLine);
          }
          if (_or) {
            _xifexpression_1 = 1;
          } else {
            _xifexpression_1 = 0;
          }
          boolean _isDebugConflicts = doc.isDebugConflicts();
          Iterable<FormattingData> _newNewLineData = FormattingDataFactory.this.newNewLineData(leafs, _xifexpression, _xifexpression_1, it.increaseIndentationChange, it.decreaseIndentationChange, _isDebugConflicts);
          _xblockexpression = (_newNewLineData);
        }
        return _xblockexpression;
      }
    };
    return _function;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData(final HiddenLeafs leafs, final NewLineKey key, final FormattingDataInit it) {
    final Function1<FormattableDocument,Iterable<FormattingData>> _function = new Function1<FormattableDocument,Iterable<FormattingData>>() {
      public Iterable<FormattingData> apply(final FormattableDocument doc) {
        Iterable<FormattingData> _xblockexpression = null;
        {
          FormattingPreferenceValues _cfg = doc.getCfg();
          final boolean newLine = _cfg.get(key);
          int _xifexpression = (int) 0;
          if (newLine) {
            _xifexpression = 1;
          } else {
            _xifexpression = 0;
          }
          final int minmax = _xifexpression;
          boolean _isDebugConflicts = doc.isDebugConflicts();
          Iterable<FormattingData> _newNewLineData = FormattingDataFactory.this.newNewLineData(leafs, minmax, minmax, it.increaseIndentationChange, it.decreaseIndentationChange, _isDebugConflicts);
          _xblockexpression = (_newNewLineData);
        }
        return _xblockexpression;
      }
    };
    return _function;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData(final HiddenLeafs leafs, final PreferenceKey key, final FormattingDataInit it) {
    Class<? extends PreferenceKey> _class = key.getClass();
    String _plus = ("Unknown configuration key kind: " + _class);
    RuntimeException _runtimeException = new RuntimeException(_plus);
    throw _runtimeException;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData(final HiddenLeafs leafs, final WhitespaceKey key, final FormattingDataInit it) {
    final Function1<FormattableDocument,Iterable<FormattingData>> _function = new Function1<FormattableDocument,Iterable<FormattingData>>() {
      public Iterable<FormattingData> apply(final FormattableDocument doc) {
        Iterable<FormattingData> _xblockexpression = null;
        {
          FormattingPreferenceValues _cfg = doc.getCfg();
          final boolean space = _cfg.get(key);
          String _xifexpression = null;
          if (space) {
            _xifexpression = " ";
          } else {
            _xifexpression = "";
          }
          boolean _isDebugConflicts = doc.isDebugConflicts();
          Iterable<FormattingData> _newWhitespaceData = FormattingDataFactory.this.newWhitespaceData(leafs, _xifexpression, it.increaseIndentationChange, it.decreaseIndentationChange, _isDebugConflicts);
          _xblockexpression = (_newWhitespaceData);
        }
        return _xblockexpression;
      }
    };
    return _function;
  }
  
  protected Iterable<FormattingData> newWhitespaceData(final HiddenLeafs leafs, final String space, final int increaseIndentationChange, final int decreaseIndentationChange, final boolean trace) {
    ArrayList<FormattingData> _xblockexpression = null;
    {
      final ArrayList<FormattingData> result = CollectionLiterals.<FormattingData>newArrayList();
      boolean isFirst = true;
      List<LeafInfo> _leafs = leafs.getLeafs();
      for (final LeafInfo leaf : _leafs) {
        boolean _matched = false;
        if (!_matched) {
          if (leaf instanceof WhitespaceInfo) {
            _matched=true;
            int _offset = ((WhitespaceInfo)leaf).getOffset();
            int _length = ((WhitespaceInfo)leaf).getLength();
            int _xifexpression = (int) 0;
            if (isFirst) {
              _xifexpression = increaseIndentationChange;
            } else {
              _xifexpression = 0;
            }
            int _xifexpression_1 = (int) 0;
            if (isFirst) {
              _xifexpression_1 = decreaseIndentationChange;
            } else {
              _xifexpression_1 = 0;
            }
            RuntimeException _xifexpression_2 = null;
            if (trace) {
              RuntimeException _runtimeException = new RuntimeException();
              _xifexpression_2 = _runtimeException;
            }
            WhitespaceData _whitespaceData = new WhitespaceData(_offset, _length, _xifexpression, _xifexpression_1, _xifexpression_2, space);
            result.add(_whitespaceData);
            isFirst = false;
          }
        }
        if (!_matched) {
          if (leaf instanceof CommentInfo) {
            _matched=true;
          }
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  protected Iterable<FormattingData> newNewLineData(final HiddenLeafs leafs, final int minNewLines, final int maxNewLines, final int increaseIndentationChange, final int decreaseIndentationChange, final boolean trace) {
    ArrayList<FormattingData> _xblockexpression = null;
    {
      final ArrayList<FormattingData> result = CollectionLiterals.<FormattingData>newArrayList();
      boolean applied = false;
      List<LeafInfo> _leafs = leafs.getLeafs();
      for (final LeafInfo leaf : _leafs) {
        boolean _matched = false;
        if (!_matched) {
          if (leaf instanceof WhitespaceInfo) {
            _matched=true;
            int _minus = (-1);
            int _multiply = (decreaseIndentationChange * _minus);
            final boolean equalIndentationChange = (increaseIndentationChange == _multiply);
            boolean _and = false;
            CommentInfo _trailingComment = ((WhitespaceInfo)leaf).trailingComment();
            boolean _isTrailing = false;
            if (_trailingComment!=null) {
              _isTrailing=_trailingComment.isTrailing();
            }
            if (!_isTrailing) {
              _and = false;
            } else {
              CommentInfo _trailingComment_1 = ((WhitespaceInfo)leaf).trailingComment();
              boolean _isMultiline = false;
              if (_trailingComment_1!=null) {
                _isMultiline=_trailingComment_1.isMultiline();
              }
              boolean _not = (!_isMultiline);
              _and = (_isTrailing && _not);
            }
            if (_and) {
              String _xifexpression = null;
              int _offset = ((WhitespaceInfo)leaf).getOffset();
              boolean _equals = (_offset == 0);
              if (_equals) {
                _xifexpression = "";
              } else {
                String _xifexpression_1 = null;
                boolean _equals_1 = (maxNewLines == 0);
                if (_equals_1) {
                  _xifexpression_1 = null;
                } else {
                  _xifexpression_1 = " ";
                }
                _xifexpression = _xifexpression_1;
              }
              final String space = _xifexpression;
              int _offset_1 = ((WhitespaceInfo)leaf).getOffset();
              int _length = ((WhitespaceInfo)leaf).getLength();
              RuntimeException _xifexpression_2 = null;
              if (trace) {
                RuntimeException _runtimeException = new RuntimeException();
                _xifexpression_2 = _runtimeException;
              }
              WhitespaceData _whitespaceData = new WhitespaceData(_offset_1, _length, 0, 0, _xifexpression_2, space);
              result.add(_whitespaceData);
            } else {
              boolean _not_1 = (!applied);
              if (_not_1) {
                int _newLines = leafs.getNewLines();
                int _max = Math.max(_newLines, minNewLines);
                int newLines = Math.min(_max, maxNewLines);
                boolean _and_1 = false;
                boolean _and_2 = false;
                boolean _lessThan = (newLines < 1);
                if (!_lessThan) {
                  _and_2 = false;
                } else {
                  int _offset_2 = ((WhitespaceInfo)leaf).getOffset();
                  boolean _greaterThan = (_offset_2 > 0);
                  _and_2 = (_lessThan && _greaterThan);
                }
                if (!_and_2) {
                  _and_1 = false;
                } else {
                  boolean _or = false;
                  CommentInfo _leadingComment = ((WhitespaceInfo)leaf).leadingComment();
                  boolean _isMultiline_1 = false;
                  if (_leadingComment!=null) {
                    _isMultiline_1=_leadingComment.isMultiline();
                  }
                  if (_isMultiline_1) {
                    _or = true;
                  } else {
                    CommentInfo _trailingComment_2 = ((WhitespaceInfo)leaf).trailingComment();
                    boolean _isMultiline_2 = false;
                    if (_trailingComment_2!=null) {
                      _isMultiline_2=_trailingComment_2.isMultiline();
                    }
                    _or = (_isMultiline_1 || _isMultiline_2);
                  }
                  _and_1 = (_and_2 && _or);
                }
                if (_and_1) {
                  newLines = 1;
                }
                CommentInfo _leadingComment_1 = ((WhitespaceInfo)leaf).leadingComment();
                boolean _endsWithNewLine = false;
                if (_leadingComment_1!=null) {
                  _endsWithNewLine=_leadingComment_1.endsWithNewLine();
                }
                if (_endsWithNewLine) {
                  int _minus_1 = (newLines - 1);
                  newLines = _minus_1;
                }
                boolean _and_3 = false;
                CommentInfo _leadingComment_2 = ((WhitespaceInfo)leaf).leadingComment();
                boolean _endsWithNewLine_1 = false;
                if (_leadingComment_2!=null) {
                  _endsWithNewLine_1=_leadingComment_2.endsWithNewLine();
                }
                boolean _not_2 = (!_endsWithNewLine_1);
                if (!_not_2) {
                  _and_3 = false;
                } else {
                  boolean _equals_2 = (newLines == 0);
                  _and_3 = (_not_2 && _equals_2);
                }
                if (_and_3) {
                  int _offset_3 = ((WhitespaceInfo)leaf).getOffset();
                  int _length_1 = ((WhitespaceInfo)leaf).getLength();
                  RuntimeException _xifexpression_3 = null;
                  if (trace) {
                    RuntimeException _runtimeException_1 = new RuntimeException();
                    _xifexpression_3 = _runtimeException_1;
                  }
                  String _xifexpression_4 = null;
                  int _offset_4 = ((WhitespaceInfo)leaf).getOffset();
                  boolean _equals_3 = (_offset_4 == 0);
                  if (_equals_3) {
                    _xifexpression_4 = "";
                  } else {
                    String _xifexpression_5 = null;
                    boolean _containsComment = leafs.containsComment();
                    if (_containsComment) {
                      _xifexpression_5 = null;
                    } else {
                      _xifexpression_5 = " ";
                    }
                    _xifexpression_4 = _xifexpression_5;
                  }
                  WhitespaceData _whitespaceData_1 = new WhitespaceData(_offset_3, _length_1, increaseIndentationChange, decreaseIndentationChange, _xifexpression_3, _xifexpression_4);
                  result.add(_whitespaceData_1);
                } else {
                  boolean _and_4 = false;
                  if (!equalIndentationChange) {
                    _and_4 = false;
                  } else {
                    List<LeafInfo> _leafs_1 = leafs.getLeafs();
                    LeafInfo _last = IterableExtensions.<LeafInfo>last(_leafs_1);
                    boolean _notEquals = (!Objects.equal(_last, leaf));
                    _and_4 = (equalIndentationChange && _notEquals);
                  }
                  if (_and_4) {
                    int _offset_5 = ((WhitespaceInfo)leaf).getOffset();
                    int _length_2 = ((WhitespaceInfo)leaf).getLength();
                    RuntimeException _xifexpression_6 = null;
                    if (trace) {
                      RuntimeException _runtimeException_2 = new RuntimeException();
                      _xifexpression_6 = _runtimeException_2;
                    }
                    NewLineData _newLineData = new NewLineData(_offset_5, _length_2, increaseIndentationChange, decreaseIndentationChange, _xifexpression_6, Integer.valueOf(newLines));
                    result.add(_newLineData);
                  } else {
                    int _offset_6 = ((WhitespaceInfo)leaf).getOffset();
                    int _length_3 = ((WhitespaceInfo)leaf).getLength();
                    int _xifexpression_7 = (int) 0;
                    if (equalIndentationChange) {
                      _xifexpression_7 = 0;
                    } else {
                      _xifexpression_7 = increaseIndentationChange;
                    }
                    int _xifexpression_8 = (int) 0;
                    if (equalIndentationChange) {
                      _xifexpression_8 = 0;
                    } else {
                      _xifexpression_8 = decreaseIndentationChange;
                    }
                    RuntimeException _xifexpression_9 = null;
                    if (trace) {
                      RuntimeException _runtimeException_3 = new RuntimeException();
                      _xifexpression_9 = _runtimeException_3;
                    }
                    NewLineData _newLineData_1 = new NewLineData(_offset_6, _length_3, _xifexpression_7, _xifexpression_8, _xifexpression_9, Integer.valueOf(newLines));
                    result.add(_newLineData_1);
                  }
                }
                applied = true;
              } else {
                int newLines_1 = 1;
                CommentInfo _leadingComment_3 = ((WhitespaceInfo)leaf).leadingComment();
                boolean _endsWithNewLine_2 = false;
                if (_leadingComment_3!=null) {
                  _endsWithNewLine_2=_leadingComment_3.endsWithNewLine();
                }
                if (_endsWithNewLine_2) {
                  int _minus_2 = (newLines_1 - 1);
                  newLines_1 = _minus_2;
                }
                boolean _and_5 = false;
                if (!equalIndentationChange) {
                  _and_5 = false;
                } else {
                  List<LeafInfo> _leafs_2 = leafs.getLeafs();
                  LeafInfo _last_1 = IterableExtensions.<LeafInfo>last(_leafs_2);
                  boolean _notEquals_1 = (!Objects.equal(_last_1, leaf));
                  _and_5 = (equalIndentationChange && _notEquals_1);
                }
                if (_and_5) {
                  int _offset_7 = ((WhitespaceInfo)leaf).getOffset();
                  int _length_4 = ((WhitespaceInfo)leaf).getLength();
                  RuntimeException _xifexpression_10 = null;
                  if (trace) {
                    RuntimeException _runtimeException_4 = new RuntimeException();
                    _xifexpression_10 = _runtimeException_4;
                  }
                  NewLineData _newLineData_2 = new NewLineData(_offset_7, _length_4, increaseIndentationChange, decreaseIndentationChange, _xifexpression_10, Integer.valueOf(newLines_1));
                  result.add(_newLineData_2);
                } else {
                  int _offset_8 = ((WhitespaceInfo)leaf).getOffset();
                  int _length_5 = ((WhitespaceInfo)leaf).getLength();
                  RuntimeException _xifexpression_11 = null;
                  if (trace) {
                    RuntimeException _runtimeException_5 = new RuntimeException();
                    _xifexpression_11 = _runtimeException_5;
                  }
                  NewLineData _newLineData_3 = new NewLineData(_offset_8, _length_5, 0, 0, _xifexpression_11, Integer.valueOf(newLines_1));
                  result.add(_newLineData_3);
                }
              }
            }
          }
        }
        if (!_matched) {
          if (leaf instanceof CommentInfo) {
            _matched=true;
          }
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public Function1<? super FormattableDocument,? extends Iterable<FormattingData>> append(final INode node, final Procedure1<? super FormattingDataInit> init) {
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _xifexpression = null;
    boolean _notEquals = (!Objects.equal(node, null));
    if (_notEquals) {
      HiddenLeafs _hiddenLeafsAfter = this._hiddenLeafAccess.getHiddenLeafsAfter(node);
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData = this.newFormattingData(_hiddenLeafsAfter, init);
      _xifexpression = _newFormattingData;
    }
    return _xifexpression;
  }
  
  public Function1<? super FormattableDocument,? extends Iterable<FormattingData>> prepend(final INode node, final Procedure1<? super FormattingDataInit> init) {
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _xifexpression = null;
    boolean _notEquals = (!Objects.equal(node, null));
    if (_notEquals) {
      HiddenLeafs _hiddenLeafsBefore = this._hiddenLeafAccess.getHiddenLeafsBefore(node);
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData = this.newFormattingData(_hiddenLeafsBefore, init);
      _xifexpression = _newFormattingData;
    }
    return _xifexpression;
  }
  
  public Function1<? super FormattableDocument,? extends Iterable<FormattingData>> surround(final INode node, final Procedure1<? super FormattingDataInit> init) {
    final Function1<FormattableDocument,ArrayList<FormattingData>> _function = new Function1<FormattableDocument,ArrayList<FormattingData>>() {
      public ArrayList<FormattingData> apply(final FormattableDocument doc) {
        ArrayList<FormattingData> _xblockexpression = null;
        {
          final ArrayList<FormattingData> result = CollectionLiterals.<FormattingData>newArrayList();
          boolean _notEquals = (!Objects.equal(node, null));
          if (_notEquals) {
            Iterable<FormattingData> _elvis = null;
            HiddenLeafs _hiddenLeafsBefore = FormattingDataFactory.this._hiddenLeafAccess.getHiddenLeafsBefore(node);
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData = FormattingDataFactory.this.newFormattingData(_hiddenLeafsBefore, init);
            Iterable<FormattingData> _apply = null;
            if (_newFormattingData!=null) {
              _apply=_newFormattingData.apply(doc);
            }
            if (_apply != null) {
              _elvis = _apply;
            } else {
              List<FormattingData> _emptyList = CollectionLiterals.<FormattingData>emptyList();
              _elvis = ObjectExtensions.<Iterable<FormattingData>>operator_elvis(_apply, _emptyList);
            }
            Iterables.<FormattingData>addAll(result, _elvis);
            Iterable<FormattingData> _elvis_1 = null;
            HiddenLeafs _hiddenLeafsAfter = FormattingDataFactory.this._hiddenLeafAccess.getHiddenLeafsAfter(node);
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData_1 = FormattingDataFactory.this.newFormattingData(_hiddenLeafsAfter, init);
            Iterable<FormattingData> _apply_1 = null;
            if (_newFormattingData_1!=null) {
              _apply_1=_newFormattingData_1.apply(doc);
            }
            if (_apply_1 != null) {
              _elvis_1 = _apply_1;
            } else {
              List<FormattingData> _emptyList_1 = CollectionLiterals.<FormattingData>emptyList();
              _elvis_1 = ObjectExtensions.<Iterable<FormattingData>>operator_elvis(_apply_1, _emptyList_1);
            }
            Iterables.<FormattingData>addAll(result, _elvis_1);
          }
          _xblockexpression = (result);
        }
        return _xblockexpression;
      }
    };
    return _function;
  }
  
  public Function1<? super FormattableDocument,? extends Iterable<FormattingData>> surround(final INode node, final Procedure1<? super FormattingDataInit> before, final Procedure1<? super FormattingDataInit> after) {
    final Function1<FormattableDocument,ArrayList<FormattingData>> _function = new Function1<FormattableDocument,ArrayList<FormattingData>>() {
      public ArrayList<FormattingData> apply(final FormattableDocument doc) {
        ArrayList<FormattingData> _xblockexpression = null;
        {
          final ArrayList<FormattingData> result = CollectionLiterals.<FormattingData>newArrayList();
          boolean _notEquals = (!Objects.equal(node, null));
          if (_notEquals) {
            Iterable<FormattingData> _elvis = null;
            HiddenLeafs _hiddenLeafsBefore = FormattingDataFactory.this._hiddenLeafAccess.getHiddenLeafsBefore(node);
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData = FormattingDataFactory.this.newFormattingData(_hiddenLeafsBefore, before);
            Iterable<FormattingData> _apply = null;
            if (_newFormattingData!=null) {
              _apply=_newFormattingData.apply(doc);
            }
            if (_apply != null) {
              _elvis = _apply;
            } else {
              List<FormattingData> _emptyList = CollectionLiterals.<FormattingData>emptyList();
              _elvis = ObjectExtensions.<Iterable<FormattingData>>operator_elvis(_apply, _emptyList);
            }
            Iterables.<FormattingData>addAll(result, _elvis);
            Iterable<FormattingData> _elvis_1 = null;
            HiddenLeafs _hiddenLeafsAfter = FormattingDataFactory.this._hiddenLeafAccess.getHiddenLeafsAfter(node);
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _newFormattingData_1 = FormattingDataFactory.this.newFormattingData(_hiddenLeafsAfter, after);
            Iterable<FormattingData> _apply_1 = null;
            if (_newFormattingData_1!=null) {
              _apply_1=_newFormattingData_1.apply(doc);
            }
            if (_apply_1 != null) {
              _elvis_1 = _apply_1;
            } else {
              List<FormattingData> _emptyList_1 = CollectionLiterals.<FormattingData>emptyList();
              _elvis_1 = ObjectExtensions.<Iterable<FormattingData>>operator_elvis(_apply_1, _emptyList_1);
            }
            Iterables.<FormattingData>addAll(result, _elvis_1);
          }
          _xblockexpression = (result);
        }
        return _xblockexpression;
      }
    };
    return _function;
  }
  
  protected Function1<? super FormattableDocument,? extends Iterable<FormattingData>> newFormattingData(final HiddenLeafs leafs, final PreferenceKey key, final FormattingDataInit it) {
    if (key instanceof BlankLineKey) {
      return _newFormattingData(leafs, (BlankLineKey)key, it);
    } else if (key instanceof NewLineKey) {
      return _newFormattingData(leafs, (NewLineKey)key, it);
    } else if (key instanceof NewLineOrPreserveKey) {
      return _newFormattingData(leafs, (NewLineOrPreserveKey)key, it);
    } else if (key instanceof WhitespaceKey) {
      return _newFormattingData(leafs, (WhitespaceKey)key, it);
    } else if (key == null) {
      return _newFormattingData(leafs, (Void)null, it);
    } else if (key != null) {
      return _newFormattingData(leafs, key, it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(leafs, key, it).toString());
    }
  }
}
