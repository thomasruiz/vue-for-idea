package io.j99.idea.vue.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import io.j99.idea.vue.VueTokenizer;
import io.j99.idea.vue.lexer.VueLexer2;
import io.j99.idea.vue.lexer.VueTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by android on 1/14/16.
 */
public class VueSyntaxHighligher extends SyntaxHighlighterBase {
    private static final TextAttributes error = new TextAttributes(new Color(0, 0, 0), null, new Color(255, 0, 0), EffectType.WAVE_UNDERSCORE, Font.PLAIN);
    private static final TextAttributes keyword = new TextAttributes(new Color(0, 0, 139), null, null, null, Font.BOLD);
    static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
            "vue.error", error);
    static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
            "vue.keyword", keyword);
    private static final Map<IElementType, TextAttributesKey> TOKENS_TO_STYLES;
    static {
        TOKENS_TO_STYLES = new HashMap<>();
        TOKENS_TO_STYLES.put(VueTokenTypes.WHITE_SPACE, HighlighterColors.TEXT);
        TOKENS_TO_STYLES.put(VueTokenTypes.BAD_CHARACTER, BAD_CHARACTER);
        TOKENS_TO_STYLES.put(VueTokenTypes.TAG, KEYWORD);

    }
    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new VueLexer2(new VueTokenizer());
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(TOKENS_TO_STYLES.get(tokenType));
    }
}
