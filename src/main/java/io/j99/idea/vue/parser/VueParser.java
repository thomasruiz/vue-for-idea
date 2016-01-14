package io.j99.idea.vue.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import static io.j99.idea.vue.lexer.VueTokenTypes.*;
/**
 * Created by android on 1/14/16.
 */
public class VueParser implements PsiParser {
    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        final PsiBuilder.Marker rootMarker = builder.mark();

        while (!builder.eof()) {
            parse(builder);
        }
        rootMarker.done(root);

        return builder.getTreeBuilt();
    }

    private void parse(PsiBuilder builder) {
        IElementType first = builder.getTokenType();
        if (first == TAG) {
            parseToken(builder, first);
        } else {
            builder.advanceLexer();
        }
    }
    private void parseToken(PsiBuilder builder, IElementType type) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();
        marker.done(type);
    }
}
