package io.j99.idea.vue.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

/**
 * Created by android on 1/14/16.
 */
public interface VueTokenTypes {
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = new VueElementType("BAD_CHARACTER");
    IElementType TAG_TEMPLATE = new VueElementType("TAG_TEMPLATE");
    IElementType TAG_SCRIPT = new VueElementType("TAG_SCRIPT");
    IElementType TAG_STYLE = new VueElementType("TAG_STYLE");
    IElementType TAG_TEMPLATE_BODY = new VueElementType("TAG_TEMPLATE_BODY");
    IElementType TAG_SCRIPT_BODY = new VueElementType("TAG_SCRIPT_BODY");
    IElementType TAG_STYLE_BODY = new VueElementType("TAG_STYLE_BODY");
    IElementType ATTR = new VueElementType("ATTR");
}
