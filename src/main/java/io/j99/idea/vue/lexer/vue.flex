package io.j99.idea.vue.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.j99.idea.vue.VueTypes;
import com.intellij.psi.TokenType;

%%

%class VueLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    return;
%eof}


CRLF= \n|\r|\r\n
WHITE_SPACE=[\ \t\f]
TAG_START="<template * >"

%state WAITING_VALUE

%%